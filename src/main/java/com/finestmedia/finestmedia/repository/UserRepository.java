package com.finestmedia.finestmedia.repository;

import com.finestmedia.finestmedia.domain.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
