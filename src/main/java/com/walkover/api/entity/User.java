package com.walkover.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_table")
public class User {

    private String userId ;

    @Id
    @Email(message = "{email}")
    @NotBlank(message = "{blank}")
    private String emailId ;

    @NotNull(message = "{passwordBlank}")
    @Pattern(regexp = "[a-zA-Z0-9@#$%^&].{7,}" , message = "{password}")
    private String password ;

}