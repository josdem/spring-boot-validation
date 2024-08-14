package com.jos.dem.springboot.validation.command;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

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
