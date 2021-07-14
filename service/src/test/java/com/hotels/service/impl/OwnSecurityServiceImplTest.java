package com.hotels.service.impl;

import com.hotels.ModelUtils;
import com.hotels.constant.ErrorMessage;
import com.hotels.dto.EmailDto;
import com.hotels.dto.OwnSignInDto;
import com.hotels.dto.OwnSignUpDto;
import com.hotels.dto.SuccessSignInDto;
import com.hotels.dto.SuccessSignUpDto;
import com.hotels.entity.OwnSecurity;
import com.hotels.entity.User;
import com.hotels.enums.UserStatus;
import com.hotels.exceptions.EmailNotVerified;
import com.hotels.exceptions.UserAlreadyRegisteredException;
import com.hotels.exceptions.UserDeactivatedException;
import com.hotels.exceptions.WrongEmailException;
import com.hotels.exceptions.WrongPasswordException;
import com.hotels.jwt.JwtTool;
import com.hotels.repo.OwnSecurityRepo;
import com.hotels.service.EmailService;
import com.hotels.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnSecurityServiceImplTest {
    @Mock
    private OwnSecurityRepo ownSecurityRepo;
    @Mock
    private UserService userService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtTool jwtTool;
    @Mock
    private EmailService emailService;

    @InjectMocks
    private OwnSecurityServiceImpl ownSecurityService;

    @BeforeEach
    public void setup() {
        ownSecurityService = new OwnSecurityServiceImpl(ownSecurityRepo, userService, passwordEncoder,
                                                        jwtTool, 100, emailService);
    }

    @Test
    void signUp() {
        OwnSignUpDto ownSignUpDto = ModelUtils.getOwnSignUpDto();
        SuccessSignUpDto successSignUpDto = ModelUtils.getSuccessSignUpDto();
        User user = ModelUtils.getUser();
        EmailDto emailDto = EmailDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .userName(user.getFirstName() + " " + user.getLastName())
                .token(user.getVerifyEmail().getToken())
                .build();
        when(userService.save(any(User.class))).thenReturn(user);
        doNothing().when(emailService).sendVerificationEmail(emailDto);
        assertEquals(ownSecurityService.signUp(ownSignUpDto), successSignUpDto);
        verify(passwordEncoder).encode(ownSignUpDto.getPassword());
        verify(jwtTool, times(2)).generateTokenKey();
    }

    @Test
    void signUpUserAlreadyRegisteredException() {
        OwnSignUpDto ownSignUpDto = ModelUtils.getOwnSignUpDto();
        when(userService.save(any(User.class)))
                .thenThrow(new DataIntegrityViolationException(ErrorMessage.USER_ALREADY_REGISTERED_WITH_THIS_EMAIL));
        assertThrows(UserAlreadyRegisteredException.class, () -> ownSecurityService.signUp(ownSignUpDto));
        verify(passwordEncoder).encode(ownSignUpDto.getPassword());
        verify(jwtTool, times(2)).generateTokenKey();
    }

    @Test
    void signIn() {
        OwnSignInDto ownSignInDto = new OwnSignInDto("test@gmail.com", "password");
        User user = ModelUtils.getUser();
        OwnSecurity ownSecurity = new OwnSecurity(1L, "password", user);
        user.setOwnSecurity(ownSecurity);
        user.setVerifyEmail(null);
        SuccessSignInDto successSignUpDto = ModelUtils.getSuccessSignInDto();
        when(userService.findByEmail(ownSignInDto.getEmail())).thenReturn(user);
        when(passwordEncoder.matches(ownSignInDto.getPassword(), user.getOwnSecurity().getPassword())).thenReturn(true);
        when(jwtTool.createAccessToken(user.getEmail(), user.getRole())).thenReturn("accessToken");
        when(jwtTool.createRefreshToken(user)).thenReturn("refreshToken");
        assertEquals(ownSecurityService.signIn(ownSignInDto), successSignUpDto);
    }

    @Test
    void signInWrongEmailException() {
        OwnSignInDto ownSignInDto = new OwnSignInDto("test@gmail.com", "password");
        when(userService.findByEmail(ownSignInDto.getEmail())).thenReturn(null);
        assertThrows(WrongEmailException.class, () -> ownSecurityService.signIn(ownSignInDto));
    }

    @Test
    void signInWrongPasswordException() {
        OwnSignInDto ownSignInDto = new OwnSignInDto("test@gmail.com", "password");
        User user = ModelUtils.getUser();
        when(userService.findByEmail(ownSignInDto.getEmail())).thenReturn(user);
        assertThrows(WrongPasswordException.class, () -> ownSecurityService.signIn(ownSignInDto));
    }

    @Test
    void signInEmailNotVerified() {
        OwnSignInDto ownSignInDto = new OwnSignInDto("test@gmail.com", "password");
        User user = ModelUtils.getUser();
        OwnSecurity ownSecurity = new OwnSecurity(1L, "password", user);
        user.setOwnSecurity(ownSecurity);
        when(userService.findByEmail(ownSignInDto.getEmail())).thenReturn(user);
        when(passwordEncoder.matches(ownSignInDto.getPassword(), user.getOwnSecurity().getPassword())).thenReturn(true);
        assertThrows(EmailNotVerified.class, () -> ownSecurityService.signIn(ownSignInDto));
    }

    @Test
    void signInUserDeactivatedException() {
        OwnSignInDto ownSignInDto = new OwnSignInDto("test@gmail.com", "password");
        User user = ModelUtils.getUser();
        OwnSecurity ownSecurity = new OwnSecurity(1L, "password", user);
        user.setOwnSecurity(ownSecurity);
        user.setVerifyEmail(null);
        user.setUserStatus(UserStatus.DEACTIVATED);
        when(userService.findByEmail(ownSignInDto.getEmail())).thenReturn(user);
        when(passwordEncoder.matches(ownSignInDto.getPassword(), user.getOwnSecurity().getPassword())).thenReturn(true);
        assertThrows(UserDeactivatedException.class, () -> ownSecurityService.signIn(ownSignInDto));
    }

}
