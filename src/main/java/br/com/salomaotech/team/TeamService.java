package br.com.salomaotech.team;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.salomaotech.team.converter.TeamConverter;
import br.com.salomaotech.team.dto.TeamFormDTO;
import br.com.salomaotech.team.dto.TeamGridDTO;
import br.com.salomaotech.util.exception.NotFoundException;

@Service
public class TeamService {

    private static final Logger LOG = LoggerFactory.getLogger(TeamService.class);
        
    private final TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    private Team findOne(UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("team.not.found"));
    
    }

    public TeamGridDTO findById(UUID id) {
        Team user = findOne(id);
        LOG.info("Found team with id: {}", id);
        return TeamConverter.toGridDTO(user);
    }

    public List<TeamGridDTO> findAll() {
        LOG.info("Finding all Teams");
        return TeamConverter.toGridsDTO(repository.findAll());
    }

    public void save(TeamFormDTO form) {
        LOG.info("Creating a new Team.");
        Team saved = TeamConverter.toEntity(form, null);
        repository.save(saved);
        LOG.info("Created Team with id: {}", saved.getId());
    }

    public void update(UUID id, TeamFormDTO form) {
        LOG.info("Updating a Team database");
        Team value = findOne(id);
        Team toMerge = TeamConverter.toEntity(form, id);
        value.merge(toMerge);
        repository.save(value);
        LOG.info("Team with id: {} updated", id);
    }

    public void delete(UUID id) {
        LOG.info("Deleting with id: {}", id);
        Team value = findOne(id);
        repository.delete(value);
        LOG.info("Team with id: {} deleted", id);
    }
}