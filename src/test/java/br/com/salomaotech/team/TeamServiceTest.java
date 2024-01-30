package br.com.salomaotech.team;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.salomaotech.team.dto.TeamGridDTO;
import br.com.salomaotech.team.environment.TeamEnvironment;
import br.com.salomaotech.util.exception.NotFoundException;

class TeamServiceTest {

    @Mock
    private TeamRepository repository;

    @InjectMocks
    private TeamService service;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void should_find_by_id() {
        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(TeamEnvironment.correct()));
        service.findById(UUID.fromString("95ad2346-c50c-4e07-864d-727e03b4b419"));
        verify(repository).findById(any(UUID.class));
    }

    @Test
    void should_find_by_id_returns_not_found_exception() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());
            service.findById(UUID.fromString("95ad2346-c50c-4e07-864d-727e03b4b419"));
            verify(repository).findById(any(UUID.class));
        });

        assertTrue(exception.getMessage().contains("not.found"));
    }

    @Test
    void should_find_all_teams() {
        when(repository.findAll()).thenReturn(List.of(TeamEnvironment.correct()));
        List<TeamGridDTO> gridDTOS = service.findAll();
        assertTrue(CollectionUtils.isNotEmpty(gridDTOS));
        verify(repository).findAll();
    }

    @Test
    void should_save_team() {
        when(repository.save(any(Team.class)))
                .thenReturn(TeamEnvironment.correct());
        service.save(TeamEnvironment.correctForm());
        verify(repository).save(any(Team.class));
    }

    @Test
    void should_update_team() {
        when(repository.findById(any(UUID.class)))
                .thenReturn(Optional.of(TeamEnvironment.correct()));
        service.update(UUID.randomUUID(), TeamEnvironment.correctForm());
        verify(repository).save(any(Team.class));
    }

    @Test
    void should_throw_exception_when_cannot_find_team_to_update() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());
            service.update(UUID.randomUUID(), TeamEnvironment.correctForm());
            verify(repository).save(any(Team.class));
        });
        assertTrue(exception.getMessage().contains("not.found"));
    }

    @Test
    void should_delete_team() {
        when(repository.findById(any(UUID.class)))
                .thenReturn(Optional.of(TeamEnvironment.correct()));
        service.delete(UUID.randomUUID());
        verify(repository).delete(any(Team.class));
    }

    @Test
    void should_throw_exception_when_cannot_find_team_to_delete() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());
            service.delete(UUID.randomUUID());
            verify(repository).delete(any(Team.class));
        });
        assertTrue(exception.getMessage().contains("not.found"));
    }

   
}