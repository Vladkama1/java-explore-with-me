package ru.practicum.events.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.categories.Category;
import ru.practicum.events.dto.AdditionalInformation;
import ru.practicum.events.dto.EventFullDto;
import ru.practicum.events.dto.EventShortDto;
import ru.practicum.events.dto.NewEventDto;
import ru.practicum.events.model.Event;
import ru.practicum.events.model.Location;
import ru.practicum.users.model.User;

import static ru.practicum.categories.CategoryMapper.toCategoryDto;
import static ru.practicum.users.mapper.UserMapper.toUserShortDto;


@UtilityClass
public class EventMapper {

    public static EventShortDto mapToEventShortDto(Event event) {
        EventShortDto shortDto = new EventShortDto();
        shortDto.setAnnotation(event.getAnnotation());
        shortDto.setCategory(toCategoryDto(event.getCategory()));
        shortDto.setConfirmedRequests(event.getConfirmedRequests());
        shortDto.setEventDate(event.getEventDate());
        shortDto.setId(event.getId());
        shortDto.setInitiator(toUserShortDto(event.getInitiator()));
        shortDto.setPaid(event.getPaid());
        shortDto.setTitle(event.getTitle());
        return shortDto;
    }

    public static Event mapToNewEvent(NewEventDto eventDto, User user, Category category) {
        Event event = new Event();
        event.setAnnotation(eventDto.getAnnotation());
        event.setCategory(category);
        event.setDescription(eventDto.getDescription());
        event.setEventDate(eventDto.getEventDate());
        event.setLat(eventDto.getLocation().getLat());
        event.setLon(eventDto.getLocation().getLon());
        event.setPaid(eventDto.getPaid());
        event.setParticipantLimit(eventDto.getParticipantLimit());
        event.setRequestModeration(eventDto.getRequestModeration());
        event.setTitle(eventDto.getTitle());
        event.setInitiator(user);
        return event;
    }

    public static EventFullDto mapToEventFullDto(Event event) {
        EventFullDto eventFullDto = new EventFullDto();
        eventFullDto.setAnnotation(event.getAnnotation());
        eventFullDto.setConfirmedRequests(event.getConfirmedRequests());
        eventFullDto.setEventDate(event.getEventDate());
        eventFullDto.setId(event.getId());
        Location location = new Location(event.getLat(), event.getLon());
        eventFullDto.setLocation(location);
        eventFullDto.setPaid(event.getPaid());
        eventFullDto.setParticipantLimit(event.getParticipantLimit());
        eventFullDto.setTitle(event.getTitle());
        eventFullDto.setState(event.getState());
        AdditionalInformation eventInformation = new AdditionalInformation();
        eventInformation.setDescription(event.getDescription());
        eventInformation.setCategory(toCategoryDto(event.getCategory()));
        eventInformation.setCreatedOn(event.getCreatedOn());
        eventInformation.setInitiator(toUserShortDto(event.getInitiator()));
        eventInformation.setRequestModeration(event.getRequestModeration());
        if (event.getPublishedOn() != null) {
            eventInformation.setPublishedOn(event.getPublishedOn());
        }
        eventFullDto.setEventInformation(eventInformation);
        return eventFullDto;
    }
}
