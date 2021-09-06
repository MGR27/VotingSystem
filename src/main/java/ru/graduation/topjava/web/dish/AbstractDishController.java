package ru.graduation.topjava.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.topjava.model.Dish;
import ru.graduation.topjava.repository.dish.DishRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDishController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    DishRepository repository;

    public Dish create(Dish dish, int restId) {
        log.info("create new dish for restaurant {}", restId);
        return repository.save(dish, restId);
    }

    public void update(Dish dish, int restId) {
        log.info("update dish for restaurant {}", restId);
        repository.save(dish, restId);
    }

    public void delete(int id) {
        log.info("delete dish with id = {}", id);
        repository.delete(id);
    }

    public Dish get(int id) {
        log.info("get dish with id = {}", id);
        return repository.get(id);
    }

    public List<Dish> getEmptyList() {
        log.info("get empty list");
        return new ArrayList<>();
    }

    public List<Dish> getTodayMenu(int restId) {
        return repository.getRestaurantMenu(restId, LocalDate.now());
    }

    public List<Dish> getAll() {
        log.info("get All");
        return repository.getAll();
    }
}
