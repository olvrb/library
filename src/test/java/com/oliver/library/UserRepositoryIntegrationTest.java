package com.oliver.library;

import com.oliver.library.Application.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryIntegrationTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenLoad_thenUserRepositoryShouldBeInitialized() {
        assertThat(this.userRepository).isNotNull();
    }
}
