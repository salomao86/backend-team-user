package br.com.salomaotech.team.dto;

import javax.validation.constraints.NotEmpty;

public class TeamFormDTO {

    @NotEmpty(message = "team.name.required")
    private String name;

    private TeamFormDTO(Builder builder) {
        name = builder.name;
    }

    public TeamFormDTO() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public static final class Builder {
        private String name;

        private Builder() {
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public TeamFormDTO build() {
            return new TeamFormDTO(this);
        }
    }
}
