package com.akash.blogApplication.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 4,message = "Username must be of minimum four characters")
    private String name;
    @Email(message = "Given email address is not valid")
    private String email;
    @NotEmpty
    @Size(min = 7,message = "Password must of minimum 7 characters")
    private String password;
    @NotEmpty
    private String about;
}
