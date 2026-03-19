package com.innostax.transaction.service;

import com.innostax.transaction.entity.Expense;
import com.innostax.transaction.entity.User;
import com.innostax.transaction.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public  class DataInitService{

    private final UserRepository userRepository;

    DataInitService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public void insertBulkData(int numberOfUsers, int expensesPerUser){
        List<User> users = new ArrayList<>();

        for (int i = 1; i <= numberOfUsers; i++) {
            User user = new User();
            user.setName("User_" + i);

            List<Expense> expenses = new ArrayList<>();
            for (int j = 1; j <= expensesPerUser; j++) {
                Expense expense = new Expense();
                expense.setAmount(50.0 + Math.random()* 950.0); // random amount
                expense.setDescription("Expense_" + j + "_for_User_" + i);
                expense.setUser(user);
                expenses.add(expense);
            }

            user.setExpenses(expenses);
            users.add(user);
        }
        userRepository.saveAll(users);
    }

}