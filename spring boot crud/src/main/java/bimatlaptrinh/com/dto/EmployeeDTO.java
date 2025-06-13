package bimatlaptrinh.com.dto;

import bimatlaptrinh.com.validation.NotAdminName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record EmployeeDTO(
        @NotBlank(message = "Name is required")
        @NotAdminName
        String name,

        @Email
        String email,

        @NotBlank
        String position,

        @Min(0)
        double salary
) {}