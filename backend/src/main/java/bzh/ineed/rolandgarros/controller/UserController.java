package bzh.ineed.rolandgarros.controller;


import bzh.ineed.rolandgarros.model.User;
import bzh.ineed.rolandgarros.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> users() {
        return userRepository.findAll();
    }
}
