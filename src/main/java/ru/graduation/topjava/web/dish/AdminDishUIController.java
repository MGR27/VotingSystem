package ru.graduation.topjava.web.dish;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.graduation.topjava.model.Dish;

import java.util.List;

@RestController
@RequestMapping(value = "admin/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishUIController extends AbstractDishController {

    @PostMapping(value = "/{restId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(Dish dish, @PathVariable int restId) {
        if (dish.isNew()) {
            super.create(dish, restId);
        } else {
            super.update(dish, restId);
        }
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping(value = "/{id}")
    public Dish get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(value = "/lastMenu/{restId}")
    public List<Dish> getTodayMenu(@PathVariable int restId) {
        return super.getTodayMenu(restId);
    }
}

