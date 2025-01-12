package org.example.repository;

import org.example.model.chat.Chat;
import org.example.model.user.Employee;
import org.example.model.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    Optional<Chat> findByStudentAndEmployee(Student student, Employee employee);
}
