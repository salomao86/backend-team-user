package br.com.salomaotech.role;


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

import br.com.salomaotech.role.dto.RoleGridDTO;
import br.com.salomaotech.role.environment.RoleEnvironment;
import br.com.salomaotech.util.exception.NotFoundException;

class RoleServiceTest {

    @Mock
    private RoleRepository repository;

    @InjectMocks
    private RoleService service;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void should_find_by_id() {
        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(RoleEnvironment.correct()));
        service.findById(UUID.fromString("0ebf68d7-18ee-449b-bb4f-358b3d65532a"));
        verify(repository).findById(any(UUID.class));
    }

    @Test
    void should_find_by_id_returns_not_found_exception() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());
            service.findById(UUID.fromString("0ebf68d7-18ee-449b-bb4f-358b3d65532a"));
            verify(repository).findById(any(UUID.class));
        });

        assertTrue(exception.getMessage().contains("not.found"));
    }

    @Test
    void should_find_all_roles() {
        when(repository.findAll()).thenReturn(List.of(RoleEnvironment.correct()));
        List<RoleGridDTO> gridDTOS = service.findAll();
        assertTrue(CollectionUtils.isNotEmpty(gridDTOS));
        verify(repository).findAll();
    }

    @Test
    void should_save_role() {
        when(repository.save(any(Role.class)))
                .thenReturn(RoleEnvironment.correct());
        service.save(RoleEnvironment.correctForm());
        verify(repository).save(any(Role.class));
    }

    @Test
    void should_update_role() {
        when(repository.findById(any(UUID.class)))
                .thenReturn(Optional.of(RoleEnvironment.correct()));
        service.update(UUID.randomUUID(), RoleEnvironment.correctForm());
        verify(repository).save(any(Role.class));
    }

    @Test
    void should_throw_exception_when_cannot_find_role_to_update() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());
            service.update(UUID.randomUUID(), RoleEnvironment.correctForm());
            verify(repository).save(any(Role.class));
        });
        assertTrue(exception.getMessage().contains("not.found"));
    }

    @Test
    void should_delete_role() {
        when(repository.findById(any(UUID.class)))
                .thenReturn(Optional.of(RoleEnvironment.correct()));
        service.delete(UUID.randomUUID());
        verify(repository).delete(any(Role.class));
    }

    @Test
    void should_throw_exception_when_cannot_find_role_to_delete() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());
            service.delete(UUID.randomUUID());
            verify(repository).delete(any(Role.class));
        });
        assertTrue(exception.getMessage().contains("not.found"));
    }

   
}