package com.eden.gallery.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller for testing.
 */
@RestController
@RequestMapping("test")
@Log4j2
public class TestController {

    /**
     * Test method.
     */
    @GetMapping
    public void test() {

        log.trace("A TRACE message");
        log.debug("A DEBUG message");
        log.info("An INFO message");
        log.warn("A WARN message");
        log.error("An ERROR message");
    }
}
