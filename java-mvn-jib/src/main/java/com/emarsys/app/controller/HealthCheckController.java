package com.emarsys.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    private final Logger logger = LoggerFactory.getLogger(HealthCheckController.class);

    @GetMapping(path = "/healthcheck", produces = "application/json")
    public ResponseEntity<String> index() {
        logger.info("Healthcheck called");
        return ResponseEntity.ok()
                .body("{\"success\":true}");
    }
}
