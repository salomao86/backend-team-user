package br.com.salomaotech.teamuser.dto;

import br.com.salomaotech.role.dto.RoleGridDTO;
import br.com.salomaotech.team.dto.TeamGridDTO;
import br.com.salomaotech.user.dto.UserGridDTO;

public class TeamUserGridDTO {

    private UserGridDTO user;
    private TeamGridDTO team;
    private RoleGridDTO role;

    private TeamUserGridDTO(Builder builder) {
        user = builder.user;
        team = builder.team;
        role = builder.role;
    }

    public TeamUserGridDTO() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public UserGridDTO getUser() {
        return user;
    }

    public TeamGridDTO getTeam() {
        return team;
    }

    public RoleGridDTO getRole() {
        return role;
    }

    public static final class Builder {
        private UserGridDTO user;
        private TeamGridDTO team;
        private RoleGridDTO role;

        private Builder() {
        }

        public Builder withUser(UserGridDTO val) {
            user = val;
            return this;
        }

        public Builder withTeam(TeamGridDTO val) {
            team = val;
            return this;
        }

        public Builder withRole(RoleGridDTO val) {
            role = val;
            return this;
        }

        public TeamUserGridDTO build() {
            return new TeamUserGridDTO(this);
        }
    }
}
