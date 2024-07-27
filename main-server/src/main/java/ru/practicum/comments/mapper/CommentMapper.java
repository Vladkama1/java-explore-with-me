package ru.practicum.comments.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.comments.dto.CommentOutDTO;
import ru.practicum.comments.dto.CommentShortDTO;
import ru.practicum.comments.model.Comment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "event.id", target = "event")
    @Mapping(source = "author.id", target = "author")
    CommentOutDTO toOutDTO(Comment comment);

    List<CommentShortDTO> toShortDTOList(List<Comment> comments);
}