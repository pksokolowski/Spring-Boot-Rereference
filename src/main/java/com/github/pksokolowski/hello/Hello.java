package com.github.pksokolowski.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class Hello {

    private final UserRepository userRepository;

    @Autowired
    public Hello(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    public String greet() {
        var users = userRepository.findAll();

        StringBuilder sb = new StringBuilder();
        sb.append("hello: <br>");
        users.forEach(user -> {
            sb.append(user.getName());
            sb.append("<br>");
        });

        return sb.toString();
    }

    @RequestMapping("/addUser/{name}")
    public String addUser(@PathVariable("name") String name) {
        var user = new User(name);
        try {
            userRepository.save(user);
        }catch (Exception e){
            return "an error occurred while saving the name.";
        }
        return "the name has been successfully saved";
    }


    @RequestMapping(value = "/postUser", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:4200")
    public void postUser(@RequestBody User user) {
        try {
            userRepository.save(user);
        } catch (Exception ignored){}
    }

    /**
     * Gets users with a given initial, in form of a JSON array.
     *
     * @param initial a String containing the initial, in fact more than one initial letter can be used.
     * @return a JSON array of users whose names match the initial string provided.
     */
    @RequestMapping("/getUsers/{initial}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<User> getUsersWithInitial(@PathVariable("initial") String initial) {
        Function<User, Boolean> matchesInitial = u -> {
            final var name = u.getName();
            final var initialLen = initial.length();
            if (initialLen > name.length()) return false;
            return name.substring(0, initialLen).equals(initial);
        };
        return userRepository.findAll().stream()
                .filter(matchesInitial::apply)
                .collect(Collectors.toList());
    }

    /**
     * Fetches all users.
     *
     * @return a JSON list of all users.
     */
    @RequestMapping("/getAllUsers")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<User> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

}
