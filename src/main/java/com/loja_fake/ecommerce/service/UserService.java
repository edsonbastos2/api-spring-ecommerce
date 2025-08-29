package com.loja_fake.ecommerce.service;

import com.loja_fake.ecommerce.controller.dto.CreateUserDto;
import com.loja_fake.ecommerce.entities.BillingAddressEntity;
import com.loja_fake.ecommerce.entities.UsersEntity;
import com.loja_fake.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsersEntity createUser(CreateUserDto dto) {

        var billingAddress = new BillingAddressEntity();
        billingAddress.setAddress(dto.address());
        billingAddress.setNumber(dto.number());
        billingAddress.setComplement(dto.complement());

        var user = new UsersEntity();
        user.setFullName(dto.fullName());
        user.setBillingAddress(billingAddress);

        return userRepository.save(user);
    }

    public Optional<UsersEntity> findbyId(UUID userId) {
        return userRepository.findById(userId);
    }

    public boolean deleteById(UUID userId) {
        var user = findbyId(userId);

        if(user.isPresent()) {
            userRepository.deleteById(userId);
        }

        return user.isPresent();
    }
}
