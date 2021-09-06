package ru.graduation.topjava.repository.dish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.topjava.model.Dish;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:rest_id AND d.date=:date")
    List<Dish> getRestaurantMenu(@Param("rest_id") int rest_id, @Param("date") LocalDate date);

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);
}
