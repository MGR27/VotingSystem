package ru.graduation.topjava.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"},
        name = "votes_unique_user_date_idx")})
public class Vote extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @Column(name = "date", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate date;

    public Vote() {
    }

    public Vote(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", user=" + user.getId() +
                ", restaurant=" + restaurant.getId() +
                ", date=" + date +
                '}';
    }
}
