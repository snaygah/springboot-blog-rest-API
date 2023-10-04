package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        description = "CommentDto Model Information"
)
public class CommentDto {
    private long id;

    @Schema(
            description = "Blog Comment Name"
    )
    //name should not be null or empty
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    @Schema(
            description = "Blog Comment Email"
    )
    //Email should not be null or empty
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    @Schema(
            description = "Blog Comment Body"
    )
    //Body should not be null or empty
    //Comment body must be minimum 10 characters
    @NotEmpty(message = "Body should not be null or empty")
    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String body;
}
