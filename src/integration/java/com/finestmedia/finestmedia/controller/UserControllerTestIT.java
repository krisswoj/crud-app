package com.finestmedia.finestmedia.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finestmedia.finestmedia.AbstractIntegrationTest;
import com.finestmedia.finestmedia.TestUserRepository;
import com.finestmedia.finestmedia.domain.model.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static com.finestmedia.finestmedia.controller.UserControllerTestIT.Fixtures.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Tag("integration")
@AutoConfigureMockMvc
class UserControllerTestIT extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TestUserRepository testUserRepository;

    @BeforeEach
    void setUp() {
        testUserRepository.deleteAll();
        testUserRepository.saveAll(createDummyUserEntities());
    }

    @Test
    void shouldReturnAllUserEntities() throws Exception {
        MvcResult result = mockMvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andReturn();

        List<UserEntity> userEntities = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(3, userEntities.size());
        assertTrue(userEntities.stream().anyMatch(userEntity -> "name-1".equals(userEntity.getName())));
        assertTrue(userEntities.stream().anyMatch(userEntity -> "name-2".equals(userEntity.getName())));
        assertTrue(userEntities.stream().anyMatch(userEntity -> "name-3".equals(userEntity.getName())));
    }

    @Test
    void shouldAddNewUserEntity() throws Exception {
        MvcResult result = mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON).content(DUMMY_USER_CREATION_DATA))
                .andExpect(status().isCreated())
                .andReturn();

        UserEntity userEntity = mapper.readValue(result.getResponse().getContentAsString(), UserEntity.class);

        assertEquals("dummy-name", userEntity.getName());
        assertEquals("dummy-surname", userEntity.getSurname());
        assertEquals("dummy-email", userEntity.getEmail());
        assertEquals("dummy-avatar-url", userEntity.getAvatarUrl());
        assertNotNull(userEntity.getCreatedAt());
        assertNotNull(userEntity.getModifiedAt());
    }

    @Test
    void shouldCreateNewUserEntityForEachRequest() throws Exception {
        assertEquals(3, testUserRepository.count());

        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON).content(DUMMY_USER_CREATION_DATA))
                .andExpect(status().isCreated());
        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON).content(DUMMY_USER_CREATION_DATA))
                .andExpect(status().isCreated());
        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON).content(DUMMY_USER_CREATION_DATA))
                .andExpect(status().isCreated());

        assertEquals(6, testUserRepository.count());
    }

    @Test
    void shouldReturnHttp400StatusCodeForWrongFormatOfUserCreationData() throws Exception {
        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON).content(WRONG_FORMAT_OF_USER_CREATION_DATA))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdateUsersBeIdempotent() throws Exception {
        UserEntity userEntityWithName1 = testUserRepository.findFirstByName("name-1").get();

        String dummyUserDtoWithInsertedId = DUMMY_USER_DTO_DATA.replace("id-to-replace", userEntityWithName1.getId().toString());

        assertEquals(3, testUserRepository.count());

        mockMvc.perform(put("/update")
                        .contentType(MediaType.APPLICATION_JSON).content(dummyUserDtoWithInsertedId))
                .andExpect(status().isOk());

        mockMvc.perform(put("/update")
                        .contentType(MediaType.APPLICATION_JSON).content(dummyUserDtoWithInsertedId))
                .andExpect(status().isOk());

        mockMvc.perform(put("/update")
                        .contentType(MediaType.APPLICATION_JSON).content(dummyUserDtoWithInsertedId))
                .andExpect(status().isOk());

        assertEquals(3, testUserRepository.count());
    }

    @Test
    void shouldUpdateExistsUserEntity() throws Exception {
        UserEntity userEntityWithName1 = testUserRepository.findFirstByName("name-1").get();

        String dummyUserDtoWithInsertedId = DUMMY_USER_DTO_DATA.replace("id-to-replace", userEntityWithName1.getId().toString());

        MvcResult result = mockMvc.perform(put("/update")
                        .contentType(MediaType.APPLICATION_JSON).content(dummyUserDtoWithInsertedId))
                .andExpect(status().isOk())
                .andReturn();

        UserEntity userEntity = mapper.readValue(result.getResponse().getContentAsString(), UserEntity.class);

        assertEquals("dummy-name", userEntity.getName());
        assertEquals("surname-1", userEntity.getSurname());
        assertEquals("dummy-email", userEntity.getEmail());
        assertEquals("none-1", userEntity.getAvatarUrl());
        assertNotEquals(userEntity.getCreatedAt(), userEntity.getModifiedAt());
    }

    @Test
    void shouldRemoveUserEntityForGivenId() throws Exception {
        UserEntity userEntityWithName1 = testUserRepository.findFirstByName("name-1").get();

        mockMvc.perform(delete("/delete/{id}", userEntityWithName1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertEquals(2, testUserRepository.count());
        assertTrue(testUserRepository.findFirstByName("name-1").isEmpty());
    }


    static class Fixtures {
        static final String WRONG_FORMAT_OF_USER_CREATION_DATA = "wrong-format-of-user-creation-data";

        static List<UserEntity> createDummyUserEntities() {
            List<UserEntity> userEntities = new ArrayList<>();

            for (int i = 1; i <= 3; i++) {
                var userEntity = UserEntity.builder()
                        .name("name-" + i)
                        .surname("surname-" + i)
                        .email("email-" + i)
                        .avatarUrl("none-" + i)
                        .build();

                userEntities.add(userEntity);
            }

            return userEntities;
        }

        static String DUMMY_USER_CREATION_DATA = "{\n" +
                "   \"name\":\"dummy-name\",\n" +
                "   \"surname\":\"dummy-surname\",\n" +
                "   \"email\":\"dummy-email\",\n" +
                "   \"avatarUrl\":\"dummy-avatar-url\"\n" +
                "}";

        static String DUMMY_USER_DTO_DATA = "{\n" +
                "   \"id\":\"id-to-replace\",\n" +
                "   \"name\":\"dummy-name\",\n" +
                "   \"email\":\"dummy-email\"\n" +
                "}";
    }
}
