package br.com.salomaotech.team.environment;

import java.util.UUID;

import br.com.salomaotech.team.Team;
import br.com.salomaotech.team.dto.TeamFormDTO;
import br.com.salomaotech.team.dto.TeamGridDTO;

public class TeamEnvironment {

    public static TeamFormDTO correctForm() {
        return TeamFormDTO.builder()
                .withName("TeamName")
                .build();
    }

    public static TeamFormDTO incorrectForm() {
        return TeamFormDTO.builder().build();
    }

    public static Team correct() {
        return Team.builder()
                .withName("TeamName")
                .withId(UUID.fromString("95ad2346-c50c-4e07-864d-727e03b4b419"))
                .build();
    }

    public static TeamGridDTO correctGridDTO() {
        return TeamGridDTO.builder()
                .withName("TeamName")
                .withId(UUID.fromString("95ad2346-c50c-4e07-864d-727e03b4b419"))
                .build();
    }

}
