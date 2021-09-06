package ru.graduation.topjava.repository.vote;

import ru.graduation.topjava.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoteRepository {
    // null if not found, when updated
    Vote save(Vote vote, int userId, int restId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Vote get(int id);

    // null if not found
    Vote getByIdAndUserId(int id, int userId);

    // null if not found
    Vote getByUserIdAndDate(int userId, LocalDate date);

    // null if not found
    List<Vote> getAllByDate(LocalDate date);

    List<Vote> getAll();
}
