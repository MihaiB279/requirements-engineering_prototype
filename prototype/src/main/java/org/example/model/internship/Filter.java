package org.example.model.internship;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class Filter {
    private Map<String, String> criteria;

    public List<Internship> applyFilters(List<Internship> internships) {
        if (criteria == null || criteria.isEmpty()) {
            return internships;
        }

        return internships.stream()
                .filter(this::matchesCriteria)
                .collect(Collectors.toList());
    }

    private boolean matchesCriteria(Internship internship) {
        for (Map.Entry<String, String> entry : criteria.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();

            // Match the field against the corresponding value in the internship
            switch (field.toLowerCase()) {
                case "company":
                    if (!internship.getCompany().equalsIgnoreCase(value)) return false;
                    break;
                case "title":
                    if (!internship.getTitle().equalsIgnoreCase(value)) return false;
                    break;
                case "period":
                    if (!internship.getPeriod().equalsIgnoreCase(value)) return false;
                    break;
                case "skillsrequired":
                    if (internship.getSkillsRequired() == null ||
                            !internship.getSkillsRequired().contains(value)) return false;
                    break;
                case "description":
                    if (internship.getDescription() == null ||
                            !internship.getDescription().contains(value)) return false;
                    break;
                case "benefits":
                    if (internship.getBenefits() == null ||
                            !internship.getBenefits().contains(value)) return false;
                    break;
                case "expectations":
                    if (internship.getExpectations() == null ||
                            !internship.getExpectations().contains(value)) return false;
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported filter field: " + field);
            }
        }

        return true; // All criteria match
    }
}
