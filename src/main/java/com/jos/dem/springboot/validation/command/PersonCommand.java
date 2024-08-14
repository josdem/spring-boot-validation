package com.jos.dem.springboot.validation.command;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PersonCommand implements Command {

    @NotNull
    @Size(min = 3, max = 50)
    private String nickname;

    @Email
    @NotNull
    @Size(min = 1, max = 250)
    private String email;

    @NotNull
    @Size(min = 9, max = 9)
    private String ein;

}
