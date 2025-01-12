package org.example.model.internship;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;
    private String title;
    private String period;
    @ElementCollection
    @CollectionTable(name = "internship_skills", joinColumns = @JoinColumn(name = "internship_id"))
    @Column(name = "skill")
    private List<String> skillsRequired;
    private String description;
    private String benefits;
    private String expectations;

    @OneToMany(mappedBy = "internship", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications;
}
