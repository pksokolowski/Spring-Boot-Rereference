package com.github.pksokolowski.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    private final Greeter greeter;

    @Autowired
    public Hello( Greeter greeter) {
        this.greeter = greeter;
    }

    @RequestMapping("/")
    public String greet() {
        return greeter.greet();
    }

    @RequestMapping("/hey")
    public String noFrillsGreeting() {
        return "Hey";
    }
}
