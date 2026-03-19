package com.innostax.transaction.service;

import com.innostax.transaction.dto.userExpanseDto.UserExpanseDTO;
import com.innostax.transaction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DTOService {
    @Autowired
    private UserRepository userRepository;
    public List<UserExpanseDTO> run(){
        List<UserExpanseDTO> users = userRepository.fetchUserExpanseDTO();

// Using a for-each loop
        for (UserExpanseDTO user : users) {
            System.out.println("Name: " + user.getName()
                    + ", Description: " + user.getDescription()
                    + ", Amount: " + user.getAmount());
        }


        return userRepository.fetchUserExpanseDTO();
    }
}
