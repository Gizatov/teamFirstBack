package com.example.sdudent.service;

import com.example.sdudent.dto.EventsDto;
import com.example.sdudent.dto.EventsUpdateDto;
import com.example.sdudent.entity.Events;
import com.example.sdudent.repository.EventsRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventsService {
    private final EventsRepository eventRepository;

    public EventsService(EventsRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // Метод для создания нового события
    public Events createEvent(EventsDto eventsDto) {
        Events events = new Events();
        events.setRegistrationStart(eventsDto.getDateRegStart());
        events.setRegistrationEnd(eventsDto.getDateRegEnd());
        events.setPersons(eventsDto.getCountCandidate());
        events.setChoiceStart(eventsDto.getDateEventStart());
        events.setChoiceEnd(eventsDto.getDateEventEnd());
        return eventRepository.save(events);
    }

    public Events updateEvent(EventsUpdateDto eventsUpdateDto) {
        Events events = eventRepository.findAllById(eventsUpdateDto.getEventId());

        if (eventsUpdateDto.getRegStartUpdate() != null) {
            events.setRegistrationStart(eventsUpdateDto.getRegStartUpdate());
        }

        if (eventsUpdateDto.getRegistrationEndUpdate() != null) {
            events.setRegistrationEnd(eventsUpdateDto.getRegistrationEndUpdate());
        }

        if (eventsUpdateDto.getPersonsUpdate() != 0) {
            events.setPersons(eventsUpdateDto.getPersonsUpdate());
        }

        if (eventsUpdateDto.getChoiceStartUpdate() != null) {
            events.setChoiceStart(eventsUpdateDto.getChoiceStartUpdate());
        }

        if (eventsUpdateDto.getChoiceEndUpdate() != null) {
            events.setChoiceEnd(eventsUpdateDto.getChoiceEndUpdate());
        }

        return eventRepository.save(events);
    }


    public List<Events> getEvent(){
        return eventRepository.findAll();
    }

    // Другие методы сервиса, например, для получения события по ID, обновления информации о событии и т.д.
}
