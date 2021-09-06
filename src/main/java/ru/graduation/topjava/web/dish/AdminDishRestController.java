package ru.graduation.topjava.web.dish;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.topjava.model.Dish;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminDishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishRestController extends AbstractDishController{
    public static final String REST_URL = "rest/admin/dishes";

    @PostMapping(value = "/{restId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish, @PathVariable int restId) {
        Dish created = super.create(dish, restId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{restId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Dish dish, @PathVariable int restId) {
        super.update(dish, restId);
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
    @GetMapping("/emptyList")
    public List<Dish> getEmptyList() {
        return super.getEmptyList();
    }

    @Override
    @GetMapping(value = "/lastMenu/{restId}")
    public List<Dish> getTodayMenu(@PathVariable int restId) {
        return super.getTodayMenu(restId);
    }
}
