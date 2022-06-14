package com.finestmedia.finestmedia;

import com.finestmedia.finestmedia.domain.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestUserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findFirstByName(String name);
}
