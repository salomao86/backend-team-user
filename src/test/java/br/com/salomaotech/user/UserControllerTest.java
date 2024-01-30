package br.com.salomaotech.user;

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

import br.com.salomaotech.team.dto.TeamFormDTO;
import br.com.salomaotech.team.environment.TeamEnvironment;
import br.com.salomaotech.user.dto.UserFormDTO;
import br.com.salomaotech.user.environment.UserEnvironment;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService service;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_find_by_id() throws Exception {
        UUID id = UUID.fromString("95ad2346-c50c-4e07-864d-727e03b4b419");
        when(service.findById(id)).thenReturn(UserEnvironment.correctGridDTO());
        this.mockMvc.perform(get("/api/users/".concat(id.toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.name", is("UserName")))
                .andExpect(status().isOk());
    }

    @Test
    void should_save_a_user() throws Exception {
        UserFormDTO formData = UserEnvironment.correctForm();

        this.mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(formData)))
                .andExpect(status().isCreated());
    }

    @Test
    void should_validate_user_form_with_errors() throws Exception {
        TeamFormDTO incorrectForm = TeamEnvironment.incorrectForm();
        this.mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(incorrectForm)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void show_update_a_user_and_return_no_content() throws Exception {
        UserFormDTO formData = UserEnvironment.correctForm();

        this.mockMvc.perform(put("/api/users/".concat(UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(formData)))
                .andExpect(status().isNoContent());
    }

    @Test
    void should_delete_a_user_and_return_no_content() throws Exception {
        this.mockMvc.perform(delete("/api/users/".concat(UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void should_find_all_users() throws Exception {
        when(service.findAll()).thenReturn(List.of(UserEnvironment.correctGridDTO()));
        this.mockMvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[0].name", is("UserName")))
                .andExpect(status().isOk());
    }

}