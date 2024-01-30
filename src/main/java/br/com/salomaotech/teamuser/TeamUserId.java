package br.com.salomaotech.teamuser;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.salomaotech.team.Team;
import br.com.salomaotech.user.User;

@Embeddable
public class TeamUserId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    protected TeamUserId() {
    }

    private TeamUserId(Builder builder) {
        user = builder.user;
        team = builder.team;        
    }

    public static Builder builder() {
        return new Builder();
    }

    public User getUser() {
        return user;
    }

    public Team getTeam() {
        return team;
    }

    public static final class Builder {
        private User user;
        private Team team;

        private Builder() {
        }

        public Builder withUser(User val) {
            user = val;
            return this;
        }

        public Builder withTeam(Team val) {
            team = val;
            return this;
        }
        public TeamUserId build() {
            return new TeamUserId(this);
        }
    }
}