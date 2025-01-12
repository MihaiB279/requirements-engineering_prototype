package org.example.model.repository;

import org.example.model.chat.Message;
import org.example.model.user.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class MessageRepository {

    private final String url;
    private final String username;
    private final String password;


    public MessageRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Set<Message> findAll() {
        Set<Message> messages = new HashSet<>();
        String sql = "SELECT * FROM messages";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long senderId = resultSet.getLong("sender_id");
                Long receiverId = resultSet.getLong("reciver_id");
                String content = resultSet.getString("content");
                LocalDateTime timestamp = resultSet.getTimestamp("timestamp").toLocalDateTime();

                Message message = new Message();
                message.setId(id);
                // Populează sender și receiver prin ID-uri (poate fi îmbunătățit pentru relații)
                message.setContent(content);
                message.setTimestamp(timestamp);

                messages.add(message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public Optional<Message> findById(Long id) {
        String sql = "SELECT * FROM messages WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long senderId = resultSet.getLong("sender_id");
                Long receiverId = resultSet.getLong("reciver_id");
                String content = resultSet.getString("content");
                LocalDateTime timestamp = resultSet.getTimestamp("timestamp").toLocalDateTime();

                Message message = new Message();
                message.setId(id);
                message.setContent(content);
                message.setTimestamp(timestamp);
                return Optional.of(message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Message save(Message message) {
        String sql = "INSERT INTO messages (sender_id, reciver_id, content, timestamp) VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, message.getSender().getId());
            statement.setLong(2, message.getReceiver().getId());
            statement.setString(3, message.getContent());
            statement.setTimestamp(4, Timestamp.valueOf(message.getTimestamp()));

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                message.setId(resultSet.getLong("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }




}
