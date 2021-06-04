package com.oop.backend.responses;

import com.oop.backend.models.user.Contact;
import com.oop.backend.models.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
public class CurrentUserDataResponse extends UserPageDataResponse{
    private List<Long> ids;
    public CurrentUserDataResponse(User user) {
        super(user);
        ids = user.getContacts().stream().map(Contact::getContact).map(User::getId).collect(Collectors.toList());
    }
}
