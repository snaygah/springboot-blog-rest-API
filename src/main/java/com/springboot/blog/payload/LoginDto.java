package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "LoginDto Model Information"
)
public class LoginDto {

    @Schema(
            description = "Blog Username or Email"
    )
    private String usernameOrEmail;

    @Schema(
            description = "Blog Password"
    )
    private String password;
}
