package br.com.salomaotech.team.converter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.salomaotech.team.Team;
import br.com.salomaotech.team.dto.TeamFormDTO;
import br.com.salomaotech.team.dto.TeamGridDTO;

public class TeamConverter {

    private TeamConverter() {
    }

    public static Team toEntity(TeamFormDTO dto, UUID id) {
        return Team.builder()
                .withId(id)
                .withName(dto.getName())
                .build();
    }

    public static TeamGridDTO toGridDTO(Team team) {
        return TeamGridDTO.builder()
                .withId(team.getId())
                .withName(team.getName())
                .build();
    }

    public static List<TeamGridDTO> toGridsDTO(List<Team> teams) {
        return teams.stream().map(TeamConverter::toGridDTO).collect(Collectors.toList());
    }

}
