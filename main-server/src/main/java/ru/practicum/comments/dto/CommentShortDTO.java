package ru.practicum.comments.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import ru.practicum.users.dto.UserShortDto;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentShortDTO {
    private Long id;
    private String text;
    private UserShortDto author;
    private LocalDateTime created;
    private LocalDateTime updated;

    @JsonCreator
    public CommentShortDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("text") String text,
            @JsonProperty("author") UserShortDto author,
            @JsonProperty("created") LocalDateTime created,
            @JsonProperty("updated") LocalDateTime updated) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.created = created;
        this.updated = updated;
    }
}
