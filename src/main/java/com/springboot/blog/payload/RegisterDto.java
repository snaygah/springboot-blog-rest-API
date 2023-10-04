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
        description = "RegisterDto Model Information"
)
public class RegisterDto {

    @Schema(
            description = "Blog Register Name"
    )
    private String name;

    @Schema(
            description = "Blog Register Username"
    )
    private String username;

    @Schema(
            description = "Blog Register Email"
    )
    private String email;

    @Schema(
            description = "Blog Register Password"
    )
    private String password;
}
