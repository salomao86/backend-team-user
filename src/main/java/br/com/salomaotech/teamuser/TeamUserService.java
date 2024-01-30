package br.com.salomaotech.teamuser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.salomaotech.role.Role;
import br.com.salomaotech.role.RoleRepository;
import br.com.salomaotech.teamuser.converter.TeamUserConverter;
import br.com.salomaotech.teamuser.dto.TeamUserFormDTO;
import br.com.salomaotech.teamuser.dto.TeamUserGridDTO;
import br.com.salomaotech.util.exception.ForbiddenException;
import br.com.salomaotech.util.exception.NotFoundException;

@Service
public class TeamUserService {

    private static final Logger LOG = LoggerFactory.getLogger(TeamUserService.class);
    
    private final TeamUserRepository repository;
    private final RoleRepository roleRepository;

    public TeamUserService(TeamUserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    private TeamUser findOne(TeamUserId id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("teamuser.not.found"));
    }

    public TeamUserGridDTO findById(UUID idTeam, UUID idUser) {
        TeamUser teamUser = findOne(TeamUserConverter.toEntityId(idTeam, idUser));
        LOG.info("Found team user with teamId: {} and userId: {}", idTeam, idUser);
        return TeamUserConverter.toGridDTO(teamUser);
    }

    public List<TeamUserGridDTO> findByIdRole(UUID idRole) {
        LOG.info("Finding all Team Users by id role");
        return TeamUserConverter.toGridsDTO(repository.findByRoleId(idRole));
    }

    public void save(TeamUserFormDTO form) {
        LOG.info("Creating a new Team User.");

        Optional<Role> roleTechLeader = roleRepository.findById(form.getIdRole());
        if (roleTechLeader.isPresent()) {
            if (roleTechLeader.get().getName().equalsIgnoreCase("TEAM LEADER")) {
                Optional<TeamUser> optional = repository.findByTeamIdAndRoleName(
                    form.getIdTeam(), "TEAM LEADER");
                
                if (optional.isPresent()) {
                    throw new ForbiddenException(
                        "teamuser.there.is.already.teamleader.in.this.teamuser");
                }
            }
        }

        TeamUser saved = TeamUserConverter.toEntity(form);
        repository.save(saved);
        LOG.info("Created team user with teamId: {} and userId: {} and roleId: {}",
             form.getIdTeam(), form.getIdUser(), form.getIdRole());
    }

    public void delete(UUID idUser, UUID idTeam) {
        LOG.info("Deleting with teamId: {} and userId: {}", idTeam, idUser);
        TeamUser value = findOne(TeamUserConverter.toEntityId(idTeam, idUser));
        repository.delete(value);
        LOG.info("TeamUser with teamId: {} and userId: {} deleted", idTeam, idUser);
    }
}