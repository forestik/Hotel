package com.hotels.service.impl;

import com.hotels.constant.ErrorMessage;
import com.hotels.dto.AccessRefreshTokensDto;
import com.hotels.dto.OwnSignInDto;
import com.hotels.dto.OwnSignUpDto;
import com.hotels.dto.SuccessSignInDto;
import com.hotels.dto.SuccessSignUpDto;
import com.hotels.dto.UpdatePasswordDto;
import com.hotels.entity.OwnSecurity;
import com.hotels.entity.User;
import com.hotels.entity.VerifyEmail;
import com.hotels.enums.UserStatus;
import com.hotels.exceptions.BadRefreshTokenException;
import com.hotels.exceptions.EmailNotVerified;
import com.hotels.exceptions.PasswordsDoNotMatchesException;
import com.hotels.exceptions.UserAlreadyRegisteredException;
import com.hotels.exceptions.UserBlockedException;
import com.hotels.exceptions.UserDeactivatedException;
import com.hotels.exceptions.WrongEmailException;
import com.hotels.exceptions.WrongPasswordException;
import com.hotels.jwt.JwtTool;
import com.hotels.repo.OwnSecurityRepo;
import com.hotels.service.EmailService;
import com.hotels.service.OwnSecurityService;
import com.hotels.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.hotels.constant.ErrorMessage.USER_DEACTIVATED;
import static com.hotels.constant.ErrorMessage.USER_NOT_FOUND_BY_EMAIL;

/**
 * {@inheritDoc}
 */
@Service
@Slf4j
public class OwnSecurityServiceImpl extends AuthService implements OwnSecurityService {
    private final OwnSecurityRepo ownSecurityRepo;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTool jwtTool;
    private final Integer expirationTime;
    private final EmailService emailService;

    /**
     * Constructor.
     */
    @Autowired
    public OwnSecurityServiceImpl(OwnSecurityRepo ownSecurityRepo,
        UserService userService,
        PasswordEncoder passwordEncoder,
        JwtTool jwtTool,
        @Value("${verifyEmailTimeHour}") Integer expirationTime,
        EmailService emailService) {
        super(jwtTool);
        this.ownSecurityRepo = ownSecurityRepo;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTool = jwtTool;
        this.expirationTime = expirationTime;
        this.emailService = emailService;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Transactional
    @Override
    public SuccessSignUpDto signUp(OwnSignUpDto dto) {
        User user = createNewRegisteredUser(dto, null);
        OwnSecurity ownSecurity = createOwnSecurity(dto, user);
        VerifyEmail verifyEmail = createVerifyEmail(user, jwtTool.generateTokenKey());
        user.setOwnSecurity(ownSecurity);
        user.setVerifyEmail(verifyEmail);
        try {
            User savedUser = userService.save(user);
            user.setId(savedUser.getId());
            emailService.sendVerificationEmail(savedUser.getId(), savedUser.getFirstName(), savedUser.getEmail(),
                savedUser.getVerifyEmail().getToken());
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyRegisteredException(ErrorMessage.USER_ALREADY_REGISTERED_WITH_THIS_EMAIL);
        }
        return new SuccessSignUpDto(user.getId(), user.getFirstName(), user.getEmail(), true);
    }

    private OwnSecurity createOwnSecurity(OwnSignUpDto dto, User user) {
        return OwnSecurity.builder()
            .password(passwordEncoder.encode(dto.getPassword()))
            .user(user)
            .build();
    }

    private VerifyEmail createVerifyEmail(User user, String emailVerificationToken) {
        return VerifyEmail.builder()
            .user(user)
            .token(emailVerificationToken)
            .expiryDate(calculateExpirationDateTime())
            .build();
    }

    private LocalDateTime calculateExpirationDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.plusHours(this.expirationTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SuccessSignInDto signIn(final OwnSignInDto dto) {
        User user = userService.findByEmail(dto.getEmail());
        if (user == null) {
            throw new WrongEmailException(USER_NOT_FOUND_BY_EMAIL + dto.getEmail());
        }
        if (!isPasswordCorrect(dto, user)) {
            throw new WrongPasswordException(ErrorMessage.BAD_PASSWORD);
        }
        if (user.getVerifyEmail() != null) {
            throw new EmailNotVerified("You should verify the email first, check your email box!");
        }
        if (user.getUserStatus() == UserStatus.DEACTIVATED) {
            throw new UserDeactivatedException(USER_DEACTIVATED);
        }
        return getSuccessSignInDto(user);
    }

    private boolean isPasswordCorrect(OwnSignInDto signInDto, User user) {
        if (user.getOwnSecurity() == null) {
            return false;
        }
        return passwordEncoder.matches(signInDto.getPassword(), user.getOwnSecurity().getPassword());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public AccessRefreshTokensDto updateAccessTokens(String refreshToken) {
        String email;
        try {
            email = jwtTool.getEmailOutOfAccessToken(refreshToken);
        } catch (ExpiredJwtException e) {
            throw new BadRefreshTokenException(ErrorMessage.REFRESH_TOKEN_NOT_VALID);
        }
        User user = userService.findByEmail(email);
        checkUserStatus(user);
        String newRefreshTokenKey = jwtTool.generateTokenKey();
        userService.updateUserRefreshToken(newRefreshTokenKey, user.getId());
        if (jwtTool.isTokenValid(refreshToken, user.getRefreshTokenKey())) {
            user.setRefreshTokenKey(newRefreshTokenKey);
            return new AccessRefreshTokensDto(
                jwtTool.createAccessToken(user.getEmail(), user.getRole()),
                jwtTool.createRefreshToken(user));
        }
        throw new BadRefreshTokenException(ErrorMessage.REFRESH_TOKEN_NOT_VALID);
    }

    private void checkUserStatus(User user) {
        UserStatus status = user.getUserStatus();
        if (status == UserStatus.BLOCKED) {
            throw new UserBlockedException(USER_DEACTIVATED);
        } else if (status == UserStatus.DEACTIVATED) {
            throw new UserDeactivatedException(USER_DEACTIVATED);
        }
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void updatePassword(String pass, Long id) {
        String password = passwordEncoder.encode(pass);
        ownSecurityRepo.updatePassword(password, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateCurrentPassword(UpdatePasswordDto updatePasswordDto, String email) {
        User user = userService.findByEmail(email);
        if (!updatePasswordDto.getPassword().equals(updatePasswordDto.getConfirmPassword())) {
            throw new PasswordsDoNotMatchesException(ErrorMessage.PASSWORDS_DO_NOT_MATCHES);
        }
        if (!passwordEncoder.matches(updatePasswordDto.getCurrentPassword(), user.getOwnSecurity().getPassword())) {
            throw new PasswordsDoNotMatchesException(ErrorMessage.PASSWORD_DOES_NOT_MATCH);
        }
        updatePassword(updatePasswordDto.getPassword(), user.getId());
    }
}
