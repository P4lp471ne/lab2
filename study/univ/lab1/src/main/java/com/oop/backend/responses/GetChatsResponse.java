package com.oop.backend.responses;

import com.oop.backend.models.chat.PersonalChat;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class GetChatsResponse {
    private List<PersonalChatData> data;

    public GetChatsResponse(List<PersonalChat> chats){
        data = chats.stream().map(PersonalChatData::new).collect(Collectors.toList());
    }

    @Data
    private static class PersonalChatData{
        private Long id;
        private UserPublicData first;
        private UserPublicData second;

        PersonalChatData(PersonalChat chat){
            id = chat.getId();
            first = new UserPublicData(chat.getFirst());
            second = new UserPublicData(chat.getSecond());
        }
    }
}
