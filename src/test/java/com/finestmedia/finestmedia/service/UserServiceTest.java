package com.finestmedia.finestmedia.service;

import com.finestmedia.finestmedia.domain.model.dto.UserCreationDto;
import com.finestmedia.finestmedia.domain.model.entity.UserEntity;
import com.finestmedia.finestmedia.mapper.UserMapper;
import com.finestmedia.finestmedia.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

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

}
