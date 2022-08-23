package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/port-info")
public class InfoController {
    @Value("${server.port.info}")
    private Integer port;

    @GetMapping
    public Integer getPort() {
        return port;
    }

    @GetMapping("/getIntSum")
    public int getIntSum() {
        int sum = Stream.iterate(1, a -> a + 1).limit(1_000_000).parallel().reduce(0, (a, b) -> a + b);
        return sum;
    }
}
