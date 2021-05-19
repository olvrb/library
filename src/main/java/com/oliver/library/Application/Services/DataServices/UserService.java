package com.oliver.library.Application.Services.DataServices;

import com.oliver.library.Application.Entities.Abstract.Rental;
import com.oliver.library.Application.Entities.User.User;
import com.oliver.library.Application.Repositories.RentalRepository;
import com.oliver.library.Application.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalRepository rentalRepository;


    public User getAuthenticatedUser(String ssn, String pw) throws AuthenticationException {
        Optional<User> user = this.userRepository.findBySsn(ssn);
        String error = "Invalid username or password.";

        // If user is not found, return null.
        // If user is found and password matches, return user.
        // Else return null.
        if (user.isEmpty()) {
            throw new AuthenticationException(error);

            //noinspection ConstantConditions
        } else if (user.isPresent() && new BCryptPasswordEncoder().matches(pw,
                                                                           user.get()
                                                                               .getPassword())) {
            return user.get();
        } else throw new AuthenticationException(error);
    }

    public User getUser(String id) {
        return this.userRepository.findById(id)
                                  .orElse(null);
    }

    public void refreshUserRentals(User u) {
        u.setRentals(this.rentalRepository.findByIdUserId(u.getId()));
    }


    public User createUser(User u) {
        this.userRepository.save(u);
        return u;
    }
}
