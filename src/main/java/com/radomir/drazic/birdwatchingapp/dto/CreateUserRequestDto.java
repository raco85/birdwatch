package com.radomir.drazic.birdwatchingapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserRequestDto(
    @NotNull
    String username,
    @NotNull
    @Size(min=4)
    String password
) {}