package com.github.pksokolowski.hello;

import org.springframework.stereotype.Component;

@Component
public class SimpleGreeter implements Greeter {
    @Override
    public String greet() {
       return "Hello world";
    }
}
