package br.com.salomaotech.user.environment;

import java.util.UUID;

import br.com.salomaotech.user.User;
import br.com.salomaotech.user.dto.UserFormDTO;
import br.com.salomaotech.user.dto.UserGridDTO;

public class UserEnvironment {

    public static UserFormDTO correctForm() {
        return UserFormDTO.builder()
                .withName("UserName")
                .build();
    }

    public static UserFormDTO incorrectForm() {
        return UserFormDTO.builder().build();
    }

    public static User correct() {
        return User.builder()
                .withName("UserName")
                .withId(UUID.fromString("23a4b2d7-55ad-46f2-80c3-8e1e44bffc7f"))
                .build();
    }

    public static UserGridDTO correctGridDTO() {
        return UserGridDTO.builder()
                .withName("UserName")
                .withId(UUID.fromString("95ad2346-c50c-4e07-864d-727e03b4b419"))
                .build();
    }

}
