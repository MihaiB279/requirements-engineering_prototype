package org.example.model.internship;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Filter {
    private Map<String, String> criteria;

    public List<Internship> applyFilters(List<Internship> internships) {
        // Implementation for applying filters
        return null; // Placeholder
    }
}