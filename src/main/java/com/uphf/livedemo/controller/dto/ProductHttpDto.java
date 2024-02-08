package com.uphf.livedemo.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record ProductHttpDto(
        @NotNull(message = "Name cannot be null")
        String name,
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        double price
) {
}
