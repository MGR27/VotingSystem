package ru.graduation.topjava.repository.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.topjava.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.date=:date")
    Optional<Vote> getByUserIdAndDate(@Param("userId") int userId, @Param("date") LocalDate date);

    List<Vote> getAllByDate(LocalDate date);
}
