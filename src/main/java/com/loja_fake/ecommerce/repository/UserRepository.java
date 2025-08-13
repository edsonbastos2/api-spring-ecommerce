package com.loja_fake.ecommerce.repository;

import com.loja_fake.ecommerce.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UsersEntity, UUID> {
}
