package ru.graduation.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.graduation.topjava.repository.restaurant.RestaurantRepository;

@Controller
public class RootController {

    @Autowired
    private RestaurantRepository repository;

    @GetMapping("/")
    public String root() {
        return "redirect:/user";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String getMenu(Model model) {
        model.addAttribute("restaurants", repository.getAll());
        return "votingForm";
    }

    @GetMapping("/admin")
    public String adminForm() {
        return "restaurantForm";
    }
}
