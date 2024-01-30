package br.com.salomaotech.user.converter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.salomaotech.user.User;
import br.com.salomaotech.user.dto.UserFormDTO;
import br.com.salomaotech.user.dto.UserGridDTO;

public class UserConverter {

    private UserConverter() {
    }

    public static User toEntity(UserFormDTO dto, UUID id) {
        return User.builder()
                .withId(id)
                .withName(dto.getName())
                .build();
    }

    public static UserGridDTO toGridDTO(User user) {
        return UserGridDTO.builder()
                .withId(user.getId())
                .withName(user.getName())
                .build();
    }

    public static List<UserGridDTO> toGridsDTO(List<User> users) {
        return users.stream().map(UserConverter::toGridDTO).collect(Collectors.toList());
    }

}
