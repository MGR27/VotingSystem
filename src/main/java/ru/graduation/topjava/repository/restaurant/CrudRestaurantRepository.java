package ru.graduation.topjava.repository.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.topjava.model.Restaurant;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
