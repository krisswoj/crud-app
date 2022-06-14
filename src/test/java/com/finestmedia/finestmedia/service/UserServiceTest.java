package com.finestmedia.finestmedia.service;

import com.finestmedia.finestmedia.domain.model.dto.UserCreationDto;
import com.finestmedia.finestmedia.domain.model.dto.UserDto;
import com.finestmedia.finestmedia.domain.model.entity.UserEntity;
import com.finestmedia.finestmedia.mapper.UserMapper;
import com.finestmedia.finestmedia.repository.UserRepository;
import com.finestmedia.finestmedia.service.exception.NoExistsUserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserCreationDto userCreationDto;

    @Mock
    private UserDto userDto;

    @Mock
    private UserEntity userEntityOne, userEntityTwo, userEntityThree;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnAllUserEntities() {
        List<UserEntity> dummyUserEntities = Arrays.asList(userEntityOne, userEntityTwo, userEntityThree);

        when(userRepository.findAll()).thenReturn(dummyUserEntities);

        List<UserEntity> result = userService.getAll();

        assertEquals(3, result.size());
        assertTrue(result.containsAll(dummyUserEntities));
    }

    @Test
    void shouldSaveUserEntity() {
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntityOne);

        UserEntity result = userService.save(userCreationDto);

        assertEquals(userEntityOne, result);
        verify(userMapper).updateUserEntityFromUserCreationDto(eq(userCreationDto), any(UserEntity.class));
    }

    @Test
    void shouldDeleteUserForGivenId(){
        when(userRepository.existsById(1L)).thenReturn(true);

        userService.delete(1L);

        verify(userRepository).deleteById(1L);
    }

    @Test
    void shouldThrowNoExistsUserExceptionForTryingToDeleteNonExistentUser(){
        when(userRepository.existsById(1L)).thenReturn(false);

        assertThrows(NoExistsUserException.class, () -> userService.delete(1L));
    }

    @Test
    void shouldReturnUpdatedUserEntity(){
        when(userDto.getId()).thenReturn(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntityOne));
        when(userRepository.save(userEntityOne)).thenReturn(userEntityTwo);

        UserEntity result = userService.update(userDto);

        assertEquals(userEntityTwo, result);
        verify(userMapper).updateUserEntityFromUserDto(userDto, userEntityOne);
    }

    @Test
    void shouldThrowNoExistsUserExceptionForTryingToUpdateNonExistentUser(){
        when(userDto.getId()).thenReturn(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoExistsUserException.class, () -> userService.update(userDto));
    }
}
