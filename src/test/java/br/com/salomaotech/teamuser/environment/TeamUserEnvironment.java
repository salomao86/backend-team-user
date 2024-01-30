package br.com.salomaotech.teamuser.environment;

import java.util.UUID;

import br.com.salomaotech.role.environment.RoleEnvironment;
import br.com.salomaotech.team.environment.TeamEnvironment;
import br.com.salomaotech.teamuser.TeamUser;
import br.com.salomaotech.teamuser.TeamUserId;
import br.com.salomaotech.teamuser.dto.TeamUserFormDTO;
import br.com.salomaotech.teamuser.dto.TeamUserGridDTO;
import br.com.salomaotech.user.environment.UserEnvironment;

public class TeamUserEnvironment {

    public static TeamUserFormDTO correctForm() {
        return TeamUserFormDTO.builder()
                .withIdRole(UUID.randomUUID())
                .withIdTeam(UUID.randomUUID())
                .withIdUser(UUID.randomUUID())
                .build();
    }

    public static TeamUserFormDTO incorrectForm() {
        return TeamUserFormDTO.builder().build();
    }

    private static TeamUserId correctId() {
        return TeamUserId.builder()
                .withTeam(TeamEnvironment.correct())
                .withUser(UserEnvironment.correct())
                .build();
    }

    public static TeamUser correct() {
        return TeamUser.builder()
                .withId(correctId())
                .withRole(RoleEnvironment.correct())
                .build();
    }

    public static TeamUserGridDTO correctGridDTO() {
        return TeamUserGridDTO.builder()
                .withTeam(TeamEnvironment.correctGridDTO())
                .withUser(UserEnvironment.correctGridDTO())
                .withRole(RoleEnvironment.correctGridDTO())
                .build();
    }

}
