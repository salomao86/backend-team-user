package br.com.salomaotech.role;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.salomaotech.role.dto.RoleFormDTO;
import br.com.salomaotech.role.environment.RoleEnvironment;

@WebMvcTest(controllers = RoleController.class)
class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RoleService service;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_find_by_id() throws Exception {
        UUID id = UUID.fromString("0ebf68d7-18ee-449b-bb4f-358b3d65532a");
        when(service.findById(id)).thenReturn(RoleEnvironment.correctGridDTO());
        this.mockMvc.perform(get("/api/roles/".concat(id.toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.name", is("RoleName")))
                .andExpect(status().isOk());
    }

    @Test
    void should_save_a_role() throws Exception {
        RoleFormDTO formData = RoleEnvironment.correctForm();

        this.mockMvc.perform(post("/api/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(formData)))
                .andExpect(status().isCreated());
    }

    @Test
    void should_validate_role_form_with_errors() throws Exception {
        RoleFormDTO incorrectForm = RoleEnvironment.incorrectForm();
        this.mockMvc.perform(post("/api/roles")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(incorrectForm)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void show_update_a_role_and_return_no_content() throws Exception {
        RoleFormDTO formData = RoleEnvironment.correctForm();

        this.mockMvc.perform(put("/api/roles/".concat(UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(formData)))
                .andExpect(status().isNoContent());
    }

    @Test
    void should_delete_a_role_and_return_no_content() throws Exception {
        this.mockMvc.perform(delete("/api/roles/".concat(UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void should_find_all_roles() throws Exception {
        when(service.findAll()).thenReturn(List.of(RoleEnvironment.correctGridDTO()));
        this.mockMvc.perform(get("/api/roles")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[0].name", is("RoleName")))
                .andExpect(status().isOk());
    }

}