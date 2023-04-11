package com.example.MiniProject.Service;

import com.example.MiniProject.Model.User;
import com.example.MiniProject.Model.WishLists;
import com.example.MiniProject.Repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceWish {

    private WishRepository wishRepository;

    public ServiceWish(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public void createUser(User user) throws InvalidInputException {
        if (user.getFirstName() == null || user.getFirstName().isEmpty() ||
                user.getLastName() == null || user.getLastName().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new InvalidInputException("All fields must be filled");
        }

        wishRepository.createUser(user);
    }

    public boolean verifyAccount(String email, String password) {
        User user = wishRepository.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public class InvalidInputException extends Exception {
        public InvalidInputException(String errorMessage) {
            super(errorMessage);
        }
    }

    public void createWishList(String name) {
        // Opret et ny ønskeliste baseret på navn ved hjælp af WishRepository
        WishLists wishLists = new WishLists(name);
        wishLists.setName(name);
        wishRepository.createWishList(name);
    }



}






