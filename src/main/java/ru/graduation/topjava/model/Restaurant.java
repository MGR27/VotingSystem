package ru.graduation.topjava.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurants_unique_name_idx")})
public class Restaurant extends AbstractEnabledEntity {

    public Restaurant() {
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Vote> votes;

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
