package ru.practicum.comments.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.comments.dao.CommentRepository;
import ru.practicum.comments.dto.CommentDTO;
import ru.practicum.comments.dto.CommentOutDTO;
import ru.practicum.comments.mapper.CommentMapper;
import ru.practicum.comments.model.Comment;
import ru.practicum.events.dao.EventRepository;
import ru.practicum.events.model.Event;
import ru.practicum.exception.NotFoundException;
import ru.practicum.users.dao.UserRepository;
import ru.practicum.users.model.User;

import java.time.LocalDateTime;


@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public CommentOutDTO saveComment(CommentDTO commentDTO, Long userId, Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Событие " + eventId + " не найдено"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь " + userId + " не найден"));
        Comment comment = new Comment();
        comment.setAuthor(user);
        comment.setText(commentDTO.getText());
        comment.setCreated(LocalDateTime.now());
        comment.setEvent(event);
        return commentMapper.toOutDTO(commentRepository.save(comment));
    }

    @Override
    public CommentOutDTO updateComment(CommentDTO commentDTO, Long userId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий " + commentId + " не найден"));
        if (!comment.getAuthor().getId().equals(userId)) {
            throw new NotFoundException("Комментарий " + commentId + " не найден");
        }
        comment.setText(commentDTO.getText());
        comment.setUpdated(LocalDateTime.now());
        return commentMapper.toOutDTO(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Long userId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий " + commentId + " не найден"));
        if (!comment.getAuthor().getId().equals(userId)) {
            throw new NotFoundException("Комментарий " + commentId + " не найден");
        }
        commentRepository.deleteById(commentId);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
