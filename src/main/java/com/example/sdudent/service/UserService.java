package com.example.sdudent.service;

import com.example.sdudent.entity.Events;
import com.example.sdudent.entity.User;
import com.example.sdudent.entity.UserDocumentFile;
import com.example.sdudent.repository.EventsRepository;
import com.example.sdudent.repository.UserDocumentFileRepository;
import com.example.sdudent.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.events.Event;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EventsRepository eventsRepository;
    private final UserDocumentFileRepository userDocumentFileRepository;


    public UserService(UserRepository userRepository, EventsRepository eventsRepository, UserDocumentFileRepository userDocumentFileRepository) {
        this.userRepository = userRepository;
        this.eventsRepository = eventsRepository;
        this.userDocumentFileRepository = userDocumentFileRepository;
    }

    public User createUser(User user) {
        // Method to create a new user and save it to the database
        return userRepository.save(user);
    }
    public String deleteEvent(Long id){
       eventsRepository.deleteById(id);
       return "true";
    }

    public List<User> getAllUsers() {
        // Method to retrieve all users from the database
        return userRepository.findAll();
    }

    public List<User> getAllCandidates() {
        // Method to retrieve all candidates from the database
        return userRepository.findAllCandidate();
    }

    public User getUserById(Long id) {
        // Method to retrieve a user by their ID from the database
        return userRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new NoSuchElementException("User with id " + id + " not found"));
    }

    public User getCandidateById(Long id){
        // Method to retrieve a candidate by their ID from the database
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User user) {
        // Method to update a user's information in the database
        User existingUser = getUserById(id);
        existingUser.setName(user.getName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        // Method to delete a user by their ID from the database
        userRepository.deleteById(Math.toIntExact(id));
    }

    public UserDocumentFile saveDocumentFile(Long userId,Long eventsId, MultipartFile photoFile) throws IOException {
        // Получаем объект пользователя по его идентификатору
        User optionalUser = userRepository.findById(userId);
        if (optionalUser != null ) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
        Optional<Events> optionalEvents = eventsRepository.findById(eventsId);
        if (!optionalEvents.isPresent()){
            throw new IllegalArgumentException("Events not found with id: " + eventsId);
        }
        Events events = optionalEvents.get();
        optionalUser.setEvents(events);

        // Создаем объект UserDocumentFile и устанавливаем пользователя
        UserDocumentFile userDocumentFile = new UserDocumentFile();
        userDocumentFile.setUser(optionalUser);

        // Устанавливаем содержимое файла в виде массива байтов


        // Сохраняем объект UserDocumentFile в базе данных
        userDocumentFileRepository.save(userDocumentFile);
        return userDocumentFile;
    }
}
