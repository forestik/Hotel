package com.hotels.service.impl;

import com.hotels.constant.ErrorMessage;
import com.hotels.dto.UserDto;
import com.hotels.entity.User;
import com.hotels.enums.UserStatus;
import com.hotels.exceptions.WrongIdException;
import com.hotels.repo.UserRepo;
import com.hotels.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * The class provides implementation of the {@code UserService}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDto findById(Long id) {
        User user = getUser(id);
        return modelMapper.map(user, UserDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User findByEmail(String email) {
        Optional<User> optionalUser = userRepo.findByEmail(email);
        return optionalUser.isEmpty() ? null : optionalUser.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateUserRefreshToken(String refreshTokenKey, Long id) {
        return userRepo.updateUserRefreshToken(refreshTokenKey, id);
    }

    @Override
    public User update(UserDto userDto) {
        User user = getUser(userDto.getId());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return userRepo.save(user);
    }

    @Override
    public List<UserDto> findAll() {
        return modelMapper.map(userRepo.findAll(),
            new TypeToken<List<UserDto>>() {
            }.getType());
    }

    @Override
    public void deactivate(Long id) {
        User user = getUser(id);
        user.setUserStatus(UserStatus.DEACTIVATED);
        userRepo.save(user);
    }

    private User getUser(Long id) {
        return userRepo.findById(id)
            .orElseThrow(() -> new WrongIdException(ErrorMessage.USER_NOT_FOUND_BY_ID + id));
    }

}
