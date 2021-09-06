package ru.graduation.topjava.repository.vote;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.topjava.model.Vote;
import ru.graduation.topjava.repository.restaurant.CrudRestaurantRepository;
import ru.graduation.topjava.repository.user.CrudUserRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VoteRepositoryImpl implements VoteRepository {
    private final CrudVoteRepository voteRepository;
    private final CrudUserRepository userRepository;
    private final CrudRestaurantRepository restRepository;

    public VoteRepositoryImpl(CrudVoteRepository crudVoteRepository,
                              CrudUserRepository userRepository,
                              CrudRestaurantRepository restRepository) {
        this.voteRepository = crudVoteRepository;
        this.userRepository = userRepository;
        this.restRepository = restRepository;
    }

    @Override
    @Transactional
    public Vote save(Vote vote, int userId, int restId) {
        if (!vote.isNew() && getByIdAndUserId(vote.getId(), userId) == null) {
            return null;
        }
        vote.setUser(userRepository.getOne(userId));
        vote.setRestaurant(restRepository.getOne(restId));
        return voteRepository.save(vote);
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Vote get(int id) {
        return voteRepository.getOne(id);
    }

    @Override
    public Vote getByIdAndUserId(int id, int userId) {
        return voteRepository.findById(id)
                .filter(vote -> vote.getUser().getId() == userId)
                .orElse(null);
    }

    @Override
    public Vote getByUserIdAndDate(int userId, LocalDate date) {
        return voteRepository.getByUserIdAndDate(userId, date).orElse(null);
    }

    @Override
    public List<Vote> getAllByDate(LocalDate date) {
        return voteRepository.getAllByDate(date);
    }

    @Override
    public List<Vote> getAll() {
        return voteRepository.findAll();
    }
}
