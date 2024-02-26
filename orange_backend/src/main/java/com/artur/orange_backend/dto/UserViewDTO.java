package com.artur.orange_backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserViewDTO {
    private Long id;
    private String email;
    private String name;
    private byte[] profileImg;
}
