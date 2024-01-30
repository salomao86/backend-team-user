package br.com.salomaotech.teamuser;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import br.com.salomaotech.teamuser.dto.TeamUserFormDTO;
import br.com.salomaotech.teamuser.environment.TeamUserEnvironment;

@WebMvcTest(controllers = TeamUserController.class)
class TeamUserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TeamUserService service;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_find_by_id() throws Exception {
        UUID idTeam = UUID.fromString("95ad2346-c50c-4e07-864d-727e03b4b419");
        UUID idUser = UUID.fromString("23a4b2d7-55ad-46f2-80c3-8e1e44bffc7f");
        when(service.findById(idTeam, idUser)).thenReturn(TeamUserEnvironment.correctGridDTO());
        this.mockMvc.perform(get("/api/team-users/team/".concat(idTeam.toString())
                            .concat("/user/").concat(idUser.toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.team.name", is("TeamName")))
                .andExpect(jsonPath("$.user.name", is("UserName")))
                .andExpect(jsonPath("$.role.name", is("RoleName")))
                .andExpect(status().isOk());
    }

    @Test
    void should_find_by_id_role() throws Exception {
        UUID id = UUID.fromString("0ebf68d7-18ee-449b-bb4f-358b3d65532a");
        when(service.findByIdRole(id)).thenReturn(List.of(TeamUserEnvironment.correctGridDTO()));
        this.mockMvc.perform(get("/api/team-users/role/".concat(id.toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[0].team.name", is("TeamName")))
                .andExpect(jsonPath("$[0].user.name", is("UserName")))
                .andExpect(jsonPath("$[0].role.name", is("RoleName")))
                .andExpect(status().isOk());
    }

    @Test
    void should_save_a_team_user() throws Exception {
        TeamUserFormDTO formData = TeamUserEnvironment.correctForm();

        this.mockMvc.perform(post("/api/team-users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(formData)))
                .andExpect(status().isCreated());
    }

    @Test
    void should_validate_team_user_form_with_errors() throws Exception {
        TeamUserFormDTO incorrectForm = TeamUserEnvironment.incorrectForm();
        this.mockMvc.perform(post("/api/team-users")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(incorrectForm)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_delete_a_team_user_and_return_no_content() throws Exception {
        UUID idTeam = UUID.fromString("95ad2346-c50c-4e07-864d-727e03b4b419");
        UUID idUser = UUID.fromString("23a4b2d7-55ad-46f2-80c3-8e1e44bffc7f");
        this.mockMvc.perform(delete("/api/team-users/team/".concat(idTeam.toString())
                                    .concat("/user/").concat(idUser.toString()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}