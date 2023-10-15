package com.example.sell_book.converter;


import com.example.sell_book.entity.Role;
import com.example.sell_book.models.RoleDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {
    private ModelMapper modelMapper;

    public RoleConverter() {
        modelMapper = new ModelMapper();
    }

    public RoleDTO toDTO(Role role) {
        RoleDTO roleDTO = modelMapper.map(role, RoleDTO.class);

        return roleDTO;
    }

    public Role toEntity(RoleDTO roleDTO) {
        Role role = modelMapper.map(roleDTO, Role.class);

        return role;
    }
}
