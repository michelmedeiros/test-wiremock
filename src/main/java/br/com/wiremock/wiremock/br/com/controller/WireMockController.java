package br.com.wiremock.wiremock.br.com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class WireMockController {


    @GetMapping("/some/thing")
    public String isNumberPrime() {
        return "Hello Ana - Controller!";
    }

    @GetMapping("/validate/prime-number")
    public String isNumberPrime(@RequestParam("number") String value) {
        return Integer.parseInt(value) % 2 == 0 ? "Even" : "Odd";
    }
}
