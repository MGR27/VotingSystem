package ru.graduation.topjava.repository.user;

import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.graduation.topjava.AuthorizedUser;
import ru.graduation.topjava.model.User;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    private final CrudUserRepository userRepository;

    public UserRepositoryImpl(CrudUserRepository crudRepository) {
        this.userRepository = crudRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll(SORT_NAME_EMAIL);
    }
}
