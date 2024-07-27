package ru.practicum.comments.service;


import ru.practicum.comments.dto.CommentDTO;
import ru.practicum.comments.dto.CommentOutDTO;

public interface CommentService {
    CommentOutDTO saveComment(CommentDTO commentDTO, Long userId, Long eventId);

    CommentOutDTO updateComment(CommentDTO commentDTO, Long userId, Long commentId);

    void deleteComment(Long userId, Long commentId);

    void deleteComment(Long commentId);
}
