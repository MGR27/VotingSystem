package ru.graduation.topjava.repository.dish;

import ru.graduation.topjava.model.Dish;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface DishRepository {
    // null if not found, when updated
    Dish save(Dish dish, int restId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Dish get(int id);

    List<Dish> getAll();

    Dish getByIdAndRestId(int id, int restId);

    List<Dish> getRestaurantMenu(int rest_id, LocalDate date);
}
