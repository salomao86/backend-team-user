package br.com.salomaotech.team;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.salomaotech.teamuser.TeamUser;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "id.team", cascade = CascadeType.ALL)
    private List<TeamUser> teamUsers;

    protected Team() {
    }

    private Team(Builder builder) {
        id = builder.id;
        name = builder.name;
        teamUsers = builder.teamUsers;        
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<TeamUser> getTeamUsers() {
        return teamUsers;
    }

    public void merge(Team toMerge) {
        this.name = toMerge.name;
    }

    public static final class Builder {
        private UUID id;
        private String name;
        private List<TeamUser> teamUsers;

        private Builder() {
        }

        public Builder withId(UUID val) {
            id = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withTeamUsers(List<TeamUser> val) {
            teamUsers = val;
            return this;
        }

        public Team build() {
            return new Team(this);
        }
    }
}