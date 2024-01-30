package br.com.salomaotech.role;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.salomaotech.role.converter.RoleConverter;
import br.com.salomaotech.role.dto.RoleFormDTO;
import br.com.salomaotech.role.dto.RoleGridDTO;
import br.com.salomaotech.util.exception.NotFoundException;

@Service
public class RoleService {

    private static final Logger LOG = LoggerFactory.getLogger(RoleService.class);
    
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    private Role findOne(UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("role.not.found"));
    }

    public RoleGridDTO findById(UUID id) {
        Role role = findOne(id);
        LOG.info("Found role with id: {}", id);
        return RoleConverter.toGridDTO(role);
    }

    public List<RoleGridDTO> findAll() {
        LOG.info("Finding all Roles");
        return RoleConverter.toGridsDTO(repository.findAll());
    }

    public void save(RoleFormDTO form) {
        LOG.info("Creating a new Role.");
        Role saved = RoleConverter.toEntity(form, null);
        repository.save(saved);
        LOG.info("Created Role with id: {}", saved.getId());
    }

    public void update(UUID id, RoleFormDTO form) {
        LOG.info("Updating a Role database");
        Role value = findOne(id);
        Role toMerge = RoleConverter.toEntity(form, id);
        value.merge(toMerge);
        repository.save(value);
        LOG.info("Role with id: {} updated", id);
    }

    public void delete(UUID id) {
        LOG.info("Deleting with id: {}", id);
        Role value = findOne(id);
        repository.delete(value);
        LOG.info("Role with id: {} deleted", id);
    }
}