package bzh.ineed.rolandgarros.controller;


import bzh.ineed.rolandgarros.api.request.UserRequest;
import bzh.ineed.rolandgarros.exception.NotFoundException;
import bzh.ineed.rolandgarros.model.Role;
import bzh.ineed.rolandgarros.model.User;
import bzh.ineed.rolandgarros.repository.PersonRepository;
import bzh.ineed.rolandgarros.repository.RoleRepository;
import bzh.ineed.rolandgarros.repository.UserRepository;
import bzh.ineed.rolandgarros.util.ERoleFormat;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@OpenAPIDefinition(
        tags = {
                @Tag(name = "Users", description = "User accounts")
        }
)
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/users")
    @Tag(name = "Users")
    public List<User> users() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    @Operation(tags={"Users"})
    User one(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find user " + id));
    }

    @PostMapping("/users")
    @Operation(tags={"Users"})
    User newPerson(@RequestBody UserRequest newUser) {
        User user = new User();

        user.setEmail(newUser.getEmail());
        user.setUsername(newUser.getUsername());

        // Encode password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));

        // Person
        if (newUser.getPersonId() != null && personRepository.existsById(newUser.getPersonId())) {
            user.setPerson(personRepository.findById(newUser.getPersonId()).get());
        }

        // Roles
        Set<Role> roles = new HashSet<>();
        for (String role : newUser.getRoles()) {
            if (roleRepository.findByName(ERoleFormat.format(role)) != null) {
                roles.add(roleRepository.findByName(ERoleFormat.format(role)));
            }
        }
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    @Operation(tags={"Users"})
    User editPerson(@RequestBody UserRequest newUser, @PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find user " + id));

        user.setUsername(newUser.getUsername());

        // If password is not empty, encode it
        if (newUser.getPassword() != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        }

        // Person
        if (newUser.getPersonId() != null && personRepository.existsById(newUser.getPersonId())) {
            user.setPerson(personRepository.findById(newUser.getPersonId()).get());
        }

        // Roles
        Set<Role> roles = new HashSet<>();
        for (String role : newUser.getRoles()) {
            if (roleRepository.findByName(ERoleFormat.format(role)) != null) {
                roles.add(roleRepository.findByName(ERoleFormat.format(role)));
            }
        }
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    @Operation(tags={"Users"})
    void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
