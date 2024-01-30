package br.com.salomaotech.user;


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

import br.com.salomaotech.user.dto.UserGridDTO;
import br.com.salomaotech.user.environment.UserEnvironment;
import br.com.salomaotech.util.exception.NotFoundException;

class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void should_find_by_id() {
        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(UserEnvironment.correct()));
        service.findById(UUID.fromString("23a4b2d7-55ad-46f2-80c3-8e1e44bffc7f"));
        verify(repository).findById(any(UUID.class));
    }

    @Test
    void should_find_by_id_returns_not_found_exception() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());
            service.findById(UUID.fromString("23a4b2d7-55ad-46f2-80c3-8e1e44bffc7f"));
            verify(repository).findById(any(UUID.class));
        });

        assertTrue(exception.getMessage().contains("not.found"));
    }

    @Test
    void should_find_all_users() {
        when(repository.findAll()).thenReturn(List.of(UserEnvironment.correct()));
        List<UserGridDTO> gridDTOS = service.findAll();
        assertTrue(CollectionUtils.isNotEmpty(gridDTOS));
        verify(repository).findAll();
    }

    @Test
    void should_save_user() {
        when(repository.save(any(User.class)))
                .thenReturn(UserEnvironment.correct());
        service.save(UserEnvironment.correctForm());
        verify(repository).save(any(User.class));
    }

    @Test
    void should_update_user() {
        when(repository.findById(any(UUID.class)))
                .thenReturn(Optional.of(UserEnvironment.correct()));
        service.update(UUID.randomUUID(), UserEnvironment.correctForm());
        verify(repository).save(any(User.class));
    }

    @Test
    void should_throw_exception_when_cannot_find_user_to_update() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());
            service.update(UUID.randomUUID(), UserEnvironment.correctForm());
            verify(repository).save(any(User.class));
        });
        assertTrue(exception.getMessage().contains("not.found"));
    }

    @Test
    void should_delete_user() {
        when(repository.findById(any(UUID.class)))
                .thenReturn(Optional.of(UserEnvironment.correct()));
        service.delete(UUID.randomUUID());
        verify(repository).delete(any(User.class));
    }

    @Test
    void should_throw_exception_when_cannot_find_user_to_delete() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());
            service.delete(UUID.randomUUID());
            verify(repository).delete(any(User.class));
        });
        assertTrue(exception.getMessage().contains("not.found"));
    }

   
}