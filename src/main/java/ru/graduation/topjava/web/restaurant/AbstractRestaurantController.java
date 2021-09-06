package ru.graduation.topjava.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.topjava.model.Restaurant;
import ru.graduation.topjava.repository.restaurant.RestaurantRepository;

import java.util.List;

import static ru.graduation.topjava.util.ValidationUtil.checkNew;

public class AbstractRestaurantController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantRepository repository;

    public Restaurant get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    public List<Restaurant> getAll() {
        log.info("getAll");
        return repository.getAll();
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create new restaurant");
        checkNew(restaurant);
        return repository.save(restaurant);
    }

    public void update(Restaurant restaurant) {
        log.info("update {}", restaurant);
        repository.save(restaurant);
    }

    public void enable(int id, boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        repository.enable(id, enabled);
    }
}
