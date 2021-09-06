package ru.graduation.topjava.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.topjava.model.User;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {

    User getByEmail(String email);
}
