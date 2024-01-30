package br.com.salomaotech.team;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.salomaotech.team.dto.TeamFormDTO;
import br.com.salomaotech.team.dto.TeamGridDTO;


@RestController
@RequestMapping("/api")
public class TeamController {

    @Autowired
    private TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/teams/{id}")
    public TeamGridDTO find(@PathVariable("id") UUID id) {
        return service.findById(id);
    }

    @PostMapping("/teams")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid TeamFormDTO form) {
        service.save(form);
    }
    
    @PutMapping("/teams/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid TeamFormDTO form) {
        service.update(id, form);
    }

    @DeleteMapping("/teams/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        service.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/teams")
    public List<TeamGridDTO> findAll() {
        return service.findAll();
    }
}
