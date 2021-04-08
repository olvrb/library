package com.oliver.library;

import static org.assertj.core.api.Assertions.assertThat;

import com.oliver.library.Application.Entities.User.Student;
import com.oliver.library.Application.Entities.User.User;
import com.oliver.library.Application.Repositories.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Set;

@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeTestMethod
    public void setUp() {
        User alex = new Student("oliver", "0204150072", "pw");
        Mockito.when(userRepository.findFirstByName(alex.getName()))
               .thenReturn(alex);
    }

    @Test
    public void whenValidName_thenUserShouldBeFound() {
        String name = "oliver";
        User found = userRepository.findFirstByName(name);

        assertThat(found.getName()).isEqualTo(name);
    }
}
