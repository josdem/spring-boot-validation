package com.josdem.springboot.validation.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PersonCommand implements Command {

    @Size(min = 6, max = 50)
    private String nickname;

    @Email
    @Size(min = 6, max = 250)
    private String email;

    @Size(min = 9, max = 9)
    private String ein;

}
