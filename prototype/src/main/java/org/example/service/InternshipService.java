package org.example.service;

import org.example.model.internship.Internship;
import org.example.repository.InternshipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternshipService {

    private final InternshipRepository internshipRepository;

    public InternshipService(InternshipRepository internshipRepository) {
        this.internshipRepository = internshipRepository;
    }

    /**
     * Fetch all internships.
     *
     * @return List of internships
     */
    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }

    /**
     * Fetch details of a single internship by ID.
     *
     * @param internshipId the ID of the internship
     * @return the internship, if found
     * @throws IllegalArgumentException if the internship does not exist
     */
    public Internship getInternshipById(Long internshipId) {
        Optional<Internship> internship = internshipRepository.findById(internshipId);
        return internship.orElseThrow(() -> new IllegalArgumentException("Internship not found with ID: " + internshipId));
    }
}
