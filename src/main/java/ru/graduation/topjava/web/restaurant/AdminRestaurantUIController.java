package ru.graduation.topjava.web.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.graduation.topjava.model.Restaurant;

import java.util.List;

@RestController
@RequestMapping(value = "admin/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantUIController extends AbstractRestaurantController {

    @Override
    @GetMapping(value = "/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(Restaurant restaurant) {
        if (restaurant.isNew()) {
            super.create(restaurant);
        } else {
            super.update(restaurant);
        }
    }

    @Override
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable int id, @RequestParam boolean enabled) {
        super.enable(id, enabled);
    }
}
