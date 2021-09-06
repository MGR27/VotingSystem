package ru.graduation.topjava.repository.restaurant;

import org.springframework.stereotype.Repository;
import ru.graduation.topjava.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    // null if not found, when updated
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    List<Restaurant> getAll();

    public void enable(int id, boolean enabled);
}
