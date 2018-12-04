package com.github.pksokolowski.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * Gets users with a given initial, in form of a JSON array.
     * @param initial a String containing the initial, in fact more than one initial letter can be used.
     * @return a JSON array of users whose names match the initial string provided.
     */
    @RequestMapping("/getUsers/{initial}")
    public Collection<User> getJsonList(@PathVariable("initial") String initial) {
        Function<User, Boolean> matchesInitial = u -> u.getName().substring(0, initial.length()).equals(initial);
        return userRepository.findAll().stream()
                .filter(matchesInitial::apply)
                .collect(Collectors.toList());
    }


}
