package com.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Show extends BaseModel{
    private Movie movie;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Screen screen;
    private List<Features> features;
}
