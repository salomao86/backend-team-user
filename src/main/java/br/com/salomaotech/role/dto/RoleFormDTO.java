package br.com.salomaotech.role.dto;

import javax.validation.constraints.NotNull;

public class RoleFormDTO {

    @NotNull(message = "role.name.required")
    private String name;

    private RoleFormDTO(Builder builder) {
        name = builder.name;        
    }

    public RoleFormDTO() {
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

        public RoleFormDTO build() {
            return new RoleFormDTO(this);
        }
    }
}
