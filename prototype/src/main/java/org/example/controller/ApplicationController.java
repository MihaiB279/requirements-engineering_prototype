package org.example.controller;

import org.example.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/apply")
    public ResponseEntity<String> applyToInternship(@RequestParam Long studentId, @RequestParam Long internshipId) {
        try {
            applicationService.applyToInternship(studentId, internshipId);
            return ResponseEntity.ok("Application submitted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
