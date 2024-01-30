package br.com.salomaotech.user.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.com.salomaotech.user.User;
import br.com.salomaotech.user.dto.UserFormDTO;
import br.com.salomaotech.user.dto.UserGridDTO;
import br.com.salomaotech.user.environment.UserEnvironment;

class UserConverterTest {

    @Test
    void should_convert_form_dto_to_entity() {
        UserFormDTO form = UserEnvironment.correctForm();
        UUID id = UUID.randomUUID();
        User user = UserConverter.toEntity(form, id);
        assertEquals(user.getName(), form.getName());
    }

    @Test
    void should_convert_entity_to_grid_dto() {
        User user = UserEnvironment.correct();
        UserGridDTO gridDTO = UserConverter.toGridDTO(user);
        assertEquals(gridDTO.getId(), user.getId());
        assertEquals(gridDTO.getName(), user.getName());
    }

    @Test
    void should_convert_entities_to_grids_dto() {
        User user = UserEnvironment.correct();
        List<UserGridDTO> gridsDTO = UserConverter.toGridsDTO(List.of(user));
        assertEquals(1, gridsDTO.size());
    }
}