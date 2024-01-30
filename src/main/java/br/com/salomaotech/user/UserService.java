package br.com.salomaotech.user;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.salomaotech.user.converter.UserConverter;
import br.com.salomaotech.user.dto.UserFormDTO;
import br.com.salomaotech.user.dto.UserGridDTO;
import br.com.salomaotech.util.exception.NotFoundException;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    private User findOne(UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("user.not.found"));
    }

    public UserGridDTO findById(UUID id) {
        User user = findOne(id);
        LOG.info("Found user with id: {}", id);
        return UserConverter.toGridDTO(user);
    }

    public List<UserGridDTO> findAll() {
        LOG.info("Finding all Users");
        return UserConverter.toGridsDTO(repository.findAll());
    }

    public void save(UserFormDTO form) {
        LOG.info("Creating a new User.");
        User saved = UserConverter.toEntity(form, null);
        repository.save(saved);
        LOG.info("Created User with id: {}", saved.getId());
    }

    public void update(UUID id, UserFormDTO form) {
        LOG.info("Updating an User database");
        User value = findOne(id);
        User toMerge = UserConverter.toEntity(form, id);
        value.merge(toMerge);
        repository.save(value);
        LOG.info("User with id: {} updated", id);
    }

    public void delete(UUID id) {
        LOG.info("Deleting with id: {}", id);
        User value = findOne(id);
        repository.delete(value);
        LOG.info("User with id: {} deleted", id);
    }
}