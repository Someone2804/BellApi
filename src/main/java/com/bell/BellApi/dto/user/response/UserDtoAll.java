package com.bell.BellApi.dto.user.response;

import com.bell.BellApi.model.Position;
import com.bell.BellApi.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDtoAll {
    private Long id;

    private String firstName;

    private String secondName;

    private String middleName;

    private List<String> position;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public List<String> getPosition() {
        return position;
    }

    public void setPosition(List<String> position) {
        this.position = position;
    }

    public static UserDtoAll mapToDto(User user){
        UserDtoAll result = new UserDtoAll();
        result.setId(user.getId());
        result.setFirstName(user.getFirstName());
        result.setSecondName(user.getSecondName());
        result.setMiddleName(user.getMiddleName());
        result.setPosition(user.getPosition().stream().map(Position::getPositionName).collect(Collectors.toList()));
        return result;
    }

    public static List<UserDtoAll> mapToDtoList(List<User> users){
        List<UserDtoAll> result = new ArrayList<>();
        for(User user : users){
            result.add(mapToDto(user));
        }
        return result;
    }
}
