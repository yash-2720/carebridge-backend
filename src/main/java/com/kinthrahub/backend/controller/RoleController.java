package com.kinthrahub.backend.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kinthrahub.backend.dto.response.RoleResponseDTO;
import com.kinthrahub.backend.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getActiveRoles")
    public List<RoleResponseDTO> getActiveRoles() {
        return roleService.getActiveRoles();
    }
}