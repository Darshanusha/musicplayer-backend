package com.music.musicplayer.musicplayer.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResponseClass {
    LocalDateTime queryTime;
    String message;

    @Override
    public String toString() {
        return "ResponseClass{" +
                "queryTime=" + queryTime +
                ", message='" + message + '\'' +
                '}';
    }
}
