package br.com.salomaotech.teamuser.dto;

import java.util.UUID;

import javax.validation.constraints.NotNull;

public class TeamUserFormDTO {

    @NotNull(message = "user.id.required")
    private UUID idUser;

    @NotNull(message = "team.id.required")
    private UUID idTeam;

    @NotNull(message = "role.id.required")
    private UUID idRole;

    private TeamUserFormDTO(Builder builder) {
        idUser = builder.idUser;
        idTeam = builder.idTeam;
        idRole = builder.idRole;
    }

    public TeamUserFormDTO() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getIdUser() {
        return idUser;
    }

    public UUID getIdTeam() {
        return idTeam;
    }

    public UUID getIdRole() {
        return idRole;
    }

    public static final class Builder {
        private UUID idUser;
        private UUID idTeam;
        private UUID idRole;

        private Builder() {
        }

        public Builder withIdUser(UUID val) {
            idUser = val;
            return this;
        }

        public Builder withIdTeam(UUID val) {
            idTeam = val;
            return this;
        }

        public Builder withIdRole(UUID val) {
            idRole = val;
            return this;
        }

        public TeamUserFormDTO build() {
            return new TeamUserFormDTO(this);
        }
    }
}
