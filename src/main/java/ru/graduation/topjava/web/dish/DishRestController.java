package ru.graduation.topjava.web.dish;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.graduation.topjava.model.Dish;

import java.util.List;

@RestController
@RequestMapping(value = "rest/profile/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController extends AbstractDishController {

    @Override
    @GetMapping
    public List<Dish> getAll() {
        return super.getAll();
    }

    @GetMapping("/menu/emptyList")
    public List<Dish> getDefaultMenu() {
        return super.getEmptyList();
    }

    @Override
    @GetMapping("/menu/{id}")
    public List<Dish> getTodayMenu(@PathVariable int id) {
        return super.getTodayMenu(id);
    }
}
