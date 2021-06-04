package com.oop.backend.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ShortChatData {
    @NonNull
    private Long id;


    @NonNull
    private UserPublicData first;
    @NonNull
    private UserPublicData second;
}
