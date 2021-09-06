package ru.graduation.topjava.web.vote;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.graduation.topjava.model.Vote;
import ru.graduation.topjava.util.exception.BadChangeVoteTimeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.graduation.topjava.util.DateTimeUtil.canChangeVote;

@RestController
@RequestMapping(value = "profile/votes", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteUIController extends AbstractVoteController {

    @PostMapping(value = "/{restId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createOrUpdate(@PathVariable int restId) {
        Vote created = super.getOneByDate(LocalDate.now());

        if (created == null) {
            super.create(restId);
        } else {
            if (!canChangeVote(LocalTime.now())) {
                throw new BadChangeVoteTimeException("After 11:00 a.m. voting change is impossible.");
            }
            super.update(created, restId);
        }
    }

    @GetMapping
    public List<Vote> getAll() {
        return super.getAll();
    }

    @GetMapping(value = "/today")
    public List<Vote> getToday() {
        return super.getAllByDate(LocalDate.now());
    }
}
