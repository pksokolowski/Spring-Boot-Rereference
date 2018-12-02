package com.github.pksokolowski.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
