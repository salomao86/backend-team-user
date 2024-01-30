package br.com.salomaotech.team.dto;

import java.util.UUID;

public class TeamGridDTO {

    private UUID id;
    private String name;

    private TeamGridDTO(Builder builder) {
        id = builder.id;
        name = builder.name;
    }

    public TeamGridDTO() {
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

    public static final class Builder {
        private UUID id;
        private String name;

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

        public TeamGridDTO build() {
            return new TeamGridDTO(this);
        }
    }
}
