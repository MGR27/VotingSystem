package ru.graduation.topjava.web.vote;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.topjava.model.Vote;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.graduation.topjava.util.DateTimeUtil.canChangeVote;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController extends AbstractVoteController {
    public static final String REST_URL = "/rest/profile/votes";

    @PostMapping("/{restId}")
    public ResponseEntity<Vote> createWithLocation(@PathVariable int restId) {
        Vote created = super.create(restId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping("/{id}/{restId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @PathVariable int restId) {
        Vote created = super.get(id);
        if (super.get(id) != null && canChangeVote(LocalTime.now())) {
            super.update(created, restId);
        }
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping
    public List<Vote> getAll() {
        return super.getAll();
    }

    @GetMapping("/today")
    public List<Vote> getToday() {
        return super.getAllByDate(LocalDate.now());
    }
}
