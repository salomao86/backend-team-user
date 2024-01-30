package br.com.salomaotech.teamuser.converter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.salomaotech.role.Role;
import br.com.salomaotech.role.converter.RoleConverter;
import br.com.salomaotech.team.Team;
import br.com.salomaotech.team.converter.TeamConverter;
import br.com.salomaotech.teamuser.TeamUser;
import br.com.salomaotech.teamuser.TeamUserId;
import br.com.salomaotech.teamuser.dto.TeamUserFormDTO;
import br.com.salomaotech.teamuser.dto.TeamUserGridDTO;
import br.com.salomaotech.user.User;
import br.com.salomaotech.user.converter.UserConverter;

public class TeamUserConverter {

    private TeamUserConverter() {
    }

    public static TeamUserId toEntityId(UUID idTeam, UUID idUser) {
        return TeamUserId.builder()
                .withUser(User.builder().withId(idUser).build())
                .withTeam(Team.builder().withId(idTeam).build())
                .build();
    }

    public static TeamUserGridDTO toGridDTO(TeamUser teamUser) {
        return TeamUserGridDTO.builder()
                .withUser(UserConverter.toGridDTO(teamUser.getId().getUser()))
                .withTeam(TeamConverter.toGridDTO(teamUser.getId().getTeam()))
                .withRole(RoleConverter.toGridDTO(teamUser.getRole()))
                .build();
    }

    public static TeamUser toEntity(TeamUserFormDTO dto) {
        return TeamUser.builder()
                .withId(toEntityId(dto.getIdTeam(), dto.getIdUser()))
                .withRole(Role.builder().withId(dto.getIdRole()).build())
                .build();
    }

    public static List<TeamUserGridDTO> toGridsDTO(List<TeamUser> teamUsers) {
        return teamUsers.stream().map(TeamUserConverter::toGridDTO).collect(Collectors.toList());
    }

}
