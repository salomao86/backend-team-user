package br.com.salomaotech.team.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.com.salomaotech.team.Team;
import br.com.salomaotech.team.dto.TeamFormDTO;
import br.com.salomaotech.team.dto.TeamGridDTO;
import br.com.salomaotech.team.environment.TeamEnvironment;

class TeamConverterTest {

    @Test
    void should_convert_form_dto_to_entity() {
        TeamFormDTO form = TeamEnvironment.correctForm();
        UUID id = UUID.randomUUID();
        Team team = TeamConverter.toEntity(form, id);
        assertEquals(team.getName(), form.getName());
    }

    @Test
    void should_convert_entity_to_grid_dto() {
        Team team = TeamEnvironment.correct();
        TeamGridDTO gridDTO = TeamConverter.toGridDTO(team);
        assertEquals(gridDTO.getId(), team.getId());
        assertEquals(gridDTO.getName(), team.getName());
    }

    @Test
    void should_convert_entities_to_grids_dto() {
        Team team = TeamEnvironment.correct();
        List<TeamGridDTO> gridsDTO = TeamConverter.toGridsDTO(List.of(team));
        assertEquals(1, gridsDTO.size());
    }
}