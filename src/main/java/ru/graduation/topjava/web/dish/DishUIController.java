package ru.graduation.topjava.web.dish;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.graduation.topjava.model.Dish;

import java.util.List;

@RestController
@RequestMapping(value = "profile/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
public class DishUIController extends AbstractDishController {

    @GetMapping
    public List<Dish> getAll() {
        return super.getAll();
    }

    @GetMapping("/menu/{id}")
    public List<Dish> getTodayMenu(@PathVariable int id) {
        return super.getTodayMenu(id);
    }
}
