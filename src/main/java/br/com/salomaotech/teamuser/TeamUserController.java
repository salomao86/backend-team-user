package br.com.salomaotech.teamuser;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.salomaotech.teamuser.dto.TeamUserFormDTO;
import br.com.salomaotech.teamuser.dto.TeamUserGridDTO;


@RestController
@RequestMapping("/api")
public class TeamUserController {

    @Autowired
    private TeamUserService service;

    public TeamUserController(TeamUserService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/team-users/team/{idTeam}/user/{idUser}")
    public TeamUserGridDTO find(@PathVariable("idTeam") UUID idTeam,
            @PathVariable("idUser") UUID idUser) {
        return service.findById(idTeam, idUser);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/team-users/role/{idRole}")
    public List<TeamUserGridDTO> findByIdRole(@PathVariable("idRole") UUID idRole) {
        return service.findByIdRole(idRole);
    }

    @PostMapping("/team-users")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid TeamUserFormDTO form) {
        service.save(form);
    }

    @DeleteMapping("/team-users/team/{idTeam}/user/{idUser}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("idTeam") UUID idTeam,
            @PathVariable("idUser") UUID idUser) {
        service.delete(idUser, idTeam);
    }

}
