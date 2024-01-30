package br.com.salomaotech.role.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.com.salomaotech.role.Role;
import br.com.salomaotech.role.dto.RoleFormDTO;
import br.com.salomaotech.role.dto.RoleGridDTO;
import br.com.salomaotech.role.environment.RoleEnvironment;

class RoleConverterTest {

    @Test
    void should_convert_form_dto_to_entity() {
        RoleFormDTO form = RoleEnvironment.correctForm();
        UUID id = UUID.randomUUID();
        Role role = RoleConverter.toEntity(form, id);
        assertEquals(role.getName(), form.getName());
    }

    @Test
    void should_convert_entity_to_grid_dto() {
        Role role = RoleEnvironment.correct();
        RoleGridDTO gridDTO = RoleConverter.toGridDTO(role);
        assertEquals(gridDTO.getId(), role.getId());
        assertEquals(gridDTO.getName(), role.getName());
    }

    @Test
    void should_convert_entities_to_grids_dto() {
        Role role = RoleEnvironment.correct();
        List<RoleGridDTO> gridsDTO = RoleConverter.toGridsDTO(List.of(role));
        assertEquals(1, gridsDTO.size());
    }
}