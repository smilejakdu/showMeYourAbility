package com.example.showmeyourability.users.domain;

import io.swagger.v3.oas.annotations.media.Schema;

public enum GenderType {
    @Schema(description = "Female Gender")
    FEMALE,

    @Schema(description = "Male Gender")
    MALE,
}
