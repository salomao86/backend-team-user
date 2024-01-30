package br.com.salomaotech.user.dto;

import javax.validation.constraints.NotEmpty;

public class UserFormDTO {

    @NotEmpty(message = "role.name.required")
    private String name;

    private UserFormDTO(Builder builder) {
        name = builder.name;
    }

    public UserFormDTO() {
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

        public UserFormDTO build() {
            return new UserFormDTO(this);
        }
    }
}
