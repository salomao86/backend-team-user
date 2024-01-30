package br.com.salomaotech.user.dto;

import java.util.UUID;

public class UserGridDTO {

    private UUID id;
    private String name;

    private UserGridDTO(Builder builder) {
        id = builder.id;
        name = builder.name;
    }

    public UserGridDTO() {
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

        public UserGridDTO build() {
            return new UserGridDTO(this);
        }
    }
}
