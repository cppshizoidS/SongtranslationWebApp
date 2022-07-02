package ru.app.ccfit.cppshiz.auth_security.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

public class LoginRequest {
	@NotBlank
    @Getter @Setter
    private String login;

	@NotBlank
    @Getter @Setter
	private String password;


}