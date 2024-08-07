package ru.practicum.events.dao;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.events.enums.EventState;
import ru.practicum.events.model.Event;
import ru.practicum.util.PaginationSetup;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllWithInitiatorByInitiator_Id(Long userId, PaginationSetup paginationSetup);

    @Query("select e " +
            "from Event AS e " +
            "JOIN FETCH e.initiator " +
            "JOIN FETCH e.category " +
            "where e.initiator.id = :userId")
    List<Event> findAllWithInitiatorByInitiatorId(Long userId, PaginationSetup paginationSetup);

    Optional<Event> findByIdAndInitiatorId(Long eventId, Long initiatorId);

    @Query("select e " +
            "from Event AS e " +
            "JOIN FETCH e.initiator " +
            "JOIN FETCH e.category " +
            "where e.state = :state " +
            "and e.eventDate > :rangeStart " +
            "and (:categories is null or e.category.id in :categories) " +
            "and (e.participantLimit = 0 or e.participantLimit > e.confirmedRequests) " +
            "and (:paid is null or e.paid = :paid) " +
            "and (:text is null or (upper(e.annotation) like upper(concat('%', :text, '%'))) " +
            "or (upper(e.description) like upper(concat('%', :text, '%')))" +
            "or (upper(e.title) like upper(concat('%', :text, '%'))))")
    List<Event> findAllPublishStateOnlyAvailable(EventState state, LocalDateTime rangeStart, List<Long> categories,
                                                 Boolean paid, String text, PaginationSetup pageable);

    @Query("select e " +
            "from Event AS e " +
            "JOIN FETCH e.initiator " +
            "JOIN FETCH e.category " +
            "where e.state = :state " +
            "and e.eventDate > :rangeStart " +
            "and (:categories is null or e.category.id in :categories) " +
            "and e.participantLimit != 0 " +
            "and e.participantLimit = e.confirmedRequests " +
            "and (:paid is null or e.paid = :paid) " +
            "and (:text is null or (upper(e.annotation) like upper(concat('%', :text, '%'))) " +
            "or (upper(e.description) like upper(concat('%', :text, '%'))))")
    List<Event> findAllPublishStateOnlyNotAvailable(EventState state, LocalDateTime rangeStart, List<Long> categories,
                                                    Boolean paid, String text, PaginationSetup pageable);

    @Query("select e " +
            "from Event AS e " +
            "JOIN FETCH e.initiator " +
            "JOIN FETCH e.category " +
            "where e.eventDate > :rangeStart " +
            "and (:users is null or e.initiator.id in :users) " +
            "and (:categories is null or e.category.id in :categories) " +
            "and (:states is null or e.state in :states)")
    List<Event> findAllForAdmin(List<Long> users, List<EventState> states, List<Long> categories,
                                LocalDateTime rangeStart, PageRequest pageable);

    List<Event> findAllByIdIn(Set<Long> events);
}
