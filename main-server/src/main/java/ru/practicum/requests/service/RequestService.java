package ru.practicum.requests.service;


import ru.practicum.requests.dto.RequestDto;
import ru.practicum.requests.dto.RequestStatusConfirm;
import ru.practicum.requests.dto.RequestStatusParticipation;

import java.util.List;

public interface RequestService {

    List<RequestDto> getParticipationRequest(Long userId, Long eventId);

    RequestStatusConfirm updateEventRequestStatus(Long userId, Long eventId, RequestStatusParticipation dtoRequest);

    List<RequestDto> getRequestByUserId(Long userId);

    RequestDto createRequest(Long userId, Long eventId);

    RequestDto updateStatusParticipationRequest(Long userId, Long requestId);
}
