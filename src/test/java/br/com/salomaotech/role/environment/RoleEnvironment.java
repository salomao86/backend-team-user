package br.com.salomaotech.role.environment;

import java.util.UUID;

import br.com.salomaotech.role.Role;
import br.com.salomaotech.role.dto.RoleFormDTO;
import br.com.salomaotech.role.dto.RoleGridDTO;

public class RoleEnvironment {

    public static RoleFormDTO correctForm() {
        return RoleFormDTO.builder()
                .withName("RoleName")
                .build();
    }

    public static RoleFormDTO incorrectForm() {
        return RoleFormDTO.builder().build();
    }

    public static Role correct() {
        return Role.builder()
                .withName("RoleName")
                .withId(UUID.fromString("0ebf68d7-18ee-449b-bb4f-358b3d65532a"))
                .build();
    }

    public static RoleGridDTO correctGridDTO() {
        return RoleGridDTO.builder()
                .withName("RoleName")
                .withId(UUID.fromString("0ebf68d7-18ee-449b-bb4f-358b3d65532a"))
                .build();
    }

    public static Role correctTeamLeader() {
        return Role.builder()
                .withName("TEAM LEADER")
                .withId(UUID.fromString("0ebf68d7-18ee-449b-bb4f-358b3d65532a"))
                .build();
    }

}
