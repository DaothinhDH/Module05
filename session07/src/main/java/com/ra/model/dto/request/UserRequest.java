package com.ra.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {
    @NotEmpty(message = "Vui long dien day du thong tin ")
    private String username;
    private String fullName;
    @Size(min = 3,message = "Nhap cho dung vao")
    private String password;
}
