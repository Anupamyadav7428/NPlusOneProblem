package com.innostax.transaction.service;

import com.innostax.transaction.entity.User;
import com.innostax.transaction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NPlusOneProblem {

    @Autowired
    private UserRepository userRepository;

    public List<User> run() {
        List<User> users = userRepository.findAll();
//        users.forEach(System.out::println);
        for (User user : users) {
            System.out.println(user.getName() + " -> " + user.getExpenses().size());
        }
        return users;
    }
}
