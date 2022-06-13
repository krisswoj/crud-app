package com.finestmedia.finestmedia.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserCreationDto {
    private String name;
    private String surname;
    private String email;
    private String avatarUrl;
}
