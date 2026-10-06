package com.example.capstone_3.DtoOut;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ZoomMeetingDtoOut {

    private Integer sessionId;
    private Long meetingId;
    private String title;
    private LocalDateTime scheduledAt;
    private Integer durationMinutes;
    private String timezone;
    private String meetingLink;
}