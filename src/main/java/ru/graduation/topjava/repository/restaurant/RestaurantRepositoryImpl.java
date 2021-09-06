package ru.graduation.topjava.repository.restaurant;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.topjava.model.Restaurant;

import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {
    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    private final CrudRestaurantRepository restaurantRepository;

    public RestaurantRepositoryImpl(CrudRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Restaurant get(int id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll(SORT_NAME);
    }

    @Transactional
    public void enable(int id, boolean enabled) {
        Restaurant restaurant = get(id);
        restaurant.setEnabled(enabled);
       save(restaurant);  // !! need only for JDBC implementation
    }

}
