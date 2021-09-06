package ru.graduation.topjava.repository.dish;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.topjava.model.Dish;
import ru.graduation.topjava.repository.restaurant.CrudRestaurantRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public class DishRepositoryImpl implements DishRepository {
    private static final Sort SORT_DATE = Sort.by(Sort.Direction.DESC, "date");

    private final CrudDishRepository dishRepository;
    private final CrudRestaurantRepository restRepository;

    public DishRepositoryImpl(CrudDishRepository crudDishRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.dishRepository = crudDishRepository;
        this.restRepository = crudRestaurantRepository;
    }

    @Override
    @Transactional
    public Dish save(Dish dish, int restId) {
        if (!dish.isNew() && getByIdAndRestId(dish.getId(), restId) == null) {
            return null;
        }
        dish.setRestaurant(restRepository.getOne(restId));
        return dishRepository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return dishRepository.delete(id) != 0;
    }

    @Override
    public Dish get(int id) {
        return dishRepository.findById(id).orElse(null);
    }

    @Override
    public List<Dish> getAll() {
        return dishRepository.findAll(SORT_DATE);
    }

    @Override
    public Dish getByIdAndRestId(int id, int restId) {
        return dishRepository.findById(id)
                .filter(dish -> dish.getRestaurant().getId() == restId)
                .orElse(null);
    }

    @Override
    public List<Dish> getRestaurantMenu(int rest_id, LocalDate date) {
        return dishRepository.getRestaurantMenu(rest_id, date);
    }
}
