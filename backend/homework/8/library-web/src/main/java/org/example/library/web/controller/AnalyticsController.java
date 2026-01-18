package org.example.library.web.controller;

import org.example.library.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/audit")
    public ResponseEntity<Map<String, Long>> audit() {
        return ResponseEntity.ok(
                analyticsService.countBooksByStatus()
        );
    }
}
