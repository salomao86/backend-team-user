package br.com.salomaotech.teamuser.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.com.salomaotech.teamuser.TeamUser;
import br.com.salomaotech.teamuser.TeamUserId;
import br.com.salomaotech.teamuser.dto.TeamUserFormDTO;
import br.com.salomaotech.teamuser.dto.TeamUserGridDTO;
import br.com.salomaotech.teamuser.environment.TeamUserEnvironment;

class TeamUserConverterTest {

    @Test
    void should_convert_form_dto_to_entity() {
        TeamUserFormDTO form = TeamUserEnvironment.correctForm();
        TeamUser teamUser = TeamUserConverter.toEntity(form);
        assertEquals(teamUser.getId().getTeam().getId(), form.getIdTeam());
        assertEquals(teamUser.getId().getUser().getId(), form.getIdUser());
        assertEquals(teamUser.getRole().getId(), form.getIdRole());
    }

    @Test
    void should_convert_entity_to_grid_dto() {
        TeamUser teamUser = TeamUserEnvironment.correct();
        TeamUserGridDTO gridDTO = TeamUserConverter.toGridDTO(teamUser);
        assertEquals(gridDTO.getTeam().getId(), teamUser.getId().getTeam().getId());
        assertEquals(gridDTO.getTeam().getName(), teamUser.getId().getTeam().getName());
        assertEquals(gridDTO.getUser().getId(), teamUser.getId().getUser().getId());
        assertEquals(gridDTO.getUser().getName(), teamUser.getId().getUser().getName());
        assertEquals(gridDTO.getRole().getId(), teamUser.getRole().getId());
        assertEquals(gridDTO.getRole().getName(), teamUser.getRole().getName());
    }

    @Test
    void should_convert_to_entity_id() {
        TeamUser teamUser = TeamUserEnvironment.correct();
        UUID idTeam = teamUser.getId().getTeam().getId();
        UUID idUser = teamUser.getId().getUser().getId();
        TeamUserId teamUserId = TeamUserConverter.toEntityId(idTeam, idUser);
        assertEquals(teamUserId.getTeam().getId(), idTeam);
        assertEquals(teamUserId.getUser().getId(), idUser);
    }

    @Test
    void should_convert_entities_to_grids_dto() {
        TeamUser teamUser = TeamUserEnvironment.correct();
        List<TeamUserGridDTO> gridsDTO = TeamUserConverter.toGridsDTO(List.of(teamUser));
        assertEquals(1, gridsDTO.size());
    }
}