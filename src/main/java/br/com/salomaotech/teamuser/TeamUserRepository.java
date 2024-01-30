package br.com.salomaotech.teamuser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeamUserRepository extends JpaRepository<TeamUser, TeamUserId> {

    List<TeamUser> findByRoleId(UUID idRole);

    @Query("SELECT td FROM TeamUser td " +
            "JOIN td.id.team t " +
            "JOIN td.role r " +
            "WHERE t.id = :idTeam " +
            " AND upper(r.name) like upper('%' || :roleName || '%') ")
    Optional<TeamUser> findByTeamIdAndRoleName(@Param("idTeam") UUID idTeam,
         @Param("roleName") String roleName);
}
