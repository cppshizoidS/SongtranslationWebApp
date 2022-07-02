package ru.app.ccfit.cppshiz.auth_security.payload.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class JwtResponse {
    @Getter @Setter
    private String token;
    
    @Getter @Setter
    private String type = "Bearer";

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String login;

    @Getter @Setter
    private String email;
    
    @Getter @Setter
    private List<String> roles;
  
    public JwtResponse(String accessToken, Long id, String username, String login, String email, List<String> roles) {
      this.token = accessToken;
      this.id = id;
      this.name = username;
      this.login = login;
      this.email = email;
      this.roles = roles;
    }  
    
}
