package br.com.salomaotech.teamuser;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.salomaotech.role.Role;

@Entity
@Table(name = "team_user")
public class TeamUser {

    @EmbeddedId
    private TeamUserId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    protected TeamUser() {
    }

    private TeamUser(Builder builder) {
        id = builder.id;
        role = builder.role;   
    }

    public static Builder builder() {
        return new Builder();
    }

    public TeamUserId getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public static final class Builder {
        private TeamUserId id;
        private Role role;

        private Builder() {
        }

        public Builder withId(TeamUserId val) {
            id = val;
            return this;
        }

        public Builder withRole(Role val) {
            role = val;
            return this;
        }

        public TeamUser build() {
            return new TeamUser(this);
        }
    }
}