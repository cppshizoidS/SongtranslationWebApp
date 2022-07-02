package ru.app.ccfit.cppshiz.auth_security.userinfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import ru.app.ccfit.cppshiz.model.User;

public class UserInfo implements UserDetails{
    private static final long serialVersionUID = 1L;
    @Getter
	private Long id;
    @Getter
	private String name;
    private String login;
    @Getter
	private String email;
	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserInfo(Long id, String username, String login, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.name = username;
        this.login = login;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserInfo build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName().name()));
    
		return new UserInfo(
				user.getId(), 
				user.getName(), 
                user.getLogin(),
				user.getEmail(),
				user.getPassword(), 
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
    
    @Override
    public String getUsername() {
        return login;
    }

	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserInfo user = (UserInfo) o;
		return Objects.equals(id, user.id);
	}
    
}
