package br.com.salomaotech.role.converter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.salomaotech.role.Role;
import br.com.salomaotech.role.dto.RoleFormDTO;
import br.com.salomaotech.role.dto.RoleGridDTO;

public class RoleConverter {

    private RoleConverter() {
    }

    public static Role toEntity(RoleFormDTO dto, UUID id) {
        return Role.builder()
                .withId(id)
                .withName(dto.getName())
                .build();
    }

    public static RoleGridDTO toGridDTO(Role role) {
        return RoleGridDTO.builder()
                .withId(role.getId())
                .withName(role.getName())
                .build();
    }

    public static List<RoleGridDTO> toGridsDTO(List<Role> roles) {
        return roles.stream().map(RoleConverter::toGridDTO).collect(Collectors.toList());
    }

}
