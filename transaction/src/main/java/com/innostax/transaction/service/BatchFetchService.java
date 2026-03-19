package com.innostax.transaction.service;

import com.innostax.transaction.entity.User;
import com.innostax.transaction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class BatchFetchService {
    @Autowired
    private UserRepository userRepository;
    public List<User> run(){
        List<User> users = userRepository.findAll();

        for (User user : users) {
            System.out.println(user.getExpenses().size());
        }
        return users;
    }
}
