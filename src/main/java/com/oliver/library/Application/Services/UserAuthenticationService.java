package com.oliver.library.Application.Services;

import com.oliver.library.Application.Entities.User.User;
import com.oliver.library.Application.Repositories.UserRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
public class UserAuthenticationService {
    @Autowired
    private UserRepository userRepository;

    public User getAuthenticatedUser(String ssn, String pw) throws AuthenticationException {
        Optional<User> user = userRepository.findBySsn(ssn);
        String error = "Invalid username or password.";

        // If user is not found, return null.
        // If user is found and password matches, return user.
        // Else return null.
        if (user.isEmpty()) {
            throw new AuthenticationException(error);
        } else //noinspection ConstantConditions
            if (user.isPresent() && new BCryptPasswordEncoder().matches(pw,
                                                                        user.get()
                                                                            .getPassword())) {
                return user.get();
            } else throw new AuthenticationException(error);
    }
}
