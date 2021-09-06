package ru.graduation.topjava.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.graduation.topjava.AuthorizedUser;
import ru.graduation.topjava.model.Role;
import ru.graduation.topjava.model.User;
import ru.graduation.topjava.repository.user.UserRepository;

import java.util.List;

import static ru.graduation.topjava.util.UserUtil.prepareToSave;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User get(int id) {
        return userRepository.get(id);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
