package com.oliver.library;

import com.oliver.library.Application.Entities.User.Student;
import com.oliver.library.Application.Entities.User.User;
import com.oliver.library.Application.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeTestMethod
    public void setUp() {
        User alex = new Student("oliver", "0204150072", "pw");
        Mockito.when(this.userRepository.findFirstByName(alex.getName()))
               .thenReturn(alex);
    }

    @Test
    public void whenValidName_thenUserShouldBeFound() {
        String name = "oliver";
        User found = this.userRepository.findFirstByName(name);

        assertThat(found.getName()).isEqualTo(name);
    }
}
