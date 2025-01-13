package org.example.controller;

import org.example.model.internship.Filter;
import org.example.model.internship.Internship;
import org.example.service.InternshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/internships")
public class InternshipController {

    private final InternshipService internshipService;

    public InternshipController(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    /**
     * Get all internships.
     *
     * @return List of internships
     */
    @GetMapping
    public ResponseEntity<List<Internship>> getAllInternships() {
        List<Internship> internships = internshipService.getAllInternships();
        return ResponseEntity.ok(internships);
    }

    /**
     * Get details of a single internship.
     *
     * @param id the ID of the internship
     * @return Details of the internship
     */
    @GetMapping("/{id}")
    public ResponseEntity<Internship> getInternshipById(@PathVariable Long id) {
        try {
            Internship internship = internshipService.getInternshipById(id);
            return ResponseEntity.ok(internship);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Filter internships based on criteria.
     *
     * @param criteria Query parameters representing filter criteria
     * @return List of internships matching the criteria
     */
    @GetMapping("/filter")
    public ResponseEntity<List<Internship>> filterInternships(@RequestParam Map<String, String> criteria) {
        try {
            List<Internship> allInternships = internshipService.getAllInternships();
            Filter filter = new Filter();
            filter.setCriteria(criteria);

            List<Internship> filteredInternships = filter.applyFilters(allInternships);
            return ResponseEntity.ok(filteredInternships);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
