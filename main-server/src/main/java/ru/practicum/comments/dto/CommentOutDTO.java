package ru.practicum.comments.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentOutDTO {
    private Long id;
    private String text;
    private Long event;
    private Long author;
    private LocalDateTime created;
    private LocalDateTime updated;

    @JsonCreator
    public CommentOutDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("text") String text,
            @JsonProperty("event") Long event,
            @JsonProperty("author") Long author,
            @JsonProperty("created") LocalDateTime created,
            @JsonProperty("updated") LocalDateTime updated) {
        this.id = id;
        this.text = text;
        this.event = event;
        this.author = author;
        this.created = created;
        this.updated = updated;
    }
}
