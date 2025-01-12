package org.example.model.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.example.model.user.Employee;
import org.example.model.user.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Student student;

    @ManyToOne
    @JsonIgnore
    private Employee employee;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    public Chat() {}

    public Chat(Student student, Employee employee) {
        this.student = student;
        this.employee = employee;
    }
}
