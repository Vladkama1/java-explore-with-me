package ru.practicum.requests.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestStatusConfirm {
    List<RequestDto> confirmedRequests;
    List<RequestDto> rejectedRequests;
}
