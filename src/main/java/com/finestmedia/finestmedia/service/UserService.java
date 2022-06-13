package com.finestmedia.finestmedia.service;

import com.finestmedia.finestmedia.domain.model.dto.UserCreationDto;
import com.finestmedia.finestmedia.domain.model.dto.UserDto;
import com.finestmedia.finestmedia.domain.model.entity.UserEntity;
import com.finestmedia.finestmedia.mapper.UserMapper;
import com.finestmedia.finestmedia.repository.UserRepository;
import com.finestmedia.finestmedia.service.exception.NoExistsUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserEntity> getAll() {
        return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public UserEntity save(UserCreationDto userCreationDto) {
        var userEntity = new UserEntity();
        userMapper.updateUserEntityFromUserCreationDto(userCreationDto, userEntity);

        return userRepository.save(userEntity);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NoExistsUserException(id);
        }

        userRepository.deleteById(id);
    }

    public UserEntity update(UserDto userDto) {
        var userEntity = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new NoExistsUserException(userDto.getId()));

        userMapper.updateUserEntityFromUserDto(userDto, userEntity);

        return userRepository.save(userEntity);
    }
}
