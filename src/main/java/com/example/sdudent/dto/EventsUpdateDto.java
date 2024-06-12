package com.example.sdudent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventsUpdateDto {
    private Long eventId;
    private Date regStartUpdate;
    private Date registrationEndUpdate;
    private int personsUpdate;
    private Date choiceStartUpdate;
    private Date choiceEndUpdate;
}
