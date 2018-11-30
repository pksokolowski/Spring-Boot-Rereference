package com.github.pksokolowski.hello;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleGreeterTest {

    @Test
    public void returnsGreeting(){
        final var greeter = new SimpleGreeter();
        final var result = greeter.greet();
        assertEquals("Hello world", result);
    }

}
