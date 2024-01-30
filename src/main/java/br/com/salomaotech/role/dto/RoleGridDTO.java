package br.com.salomaotech.role.dto;

import java.util.UUID;

public class RoleGridDTO {

    private UUID id;
    private String name;

    private RoleGridDTO(Builder builder) {
        id = builder.id;
        name = builder.name;
    }

    public RoleGridDTO() {
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

        public RoleGridDTO build() {
            return new RoleGridDTO(this);
        }
    }
}
