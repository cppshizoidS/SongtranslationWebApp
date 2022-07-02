package ru.app.ccfit.cppshiz.model;



import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Column(name = "name")
    @NotBlank
    @Size(max = 20)         
    @Getter @Setter
  
    private String name;

    @Column(name = "login")
    @NotBlank
    @Size(max = 20)
    @Getter @Setter
    private String login;

    @Column(name = "password")
    @NotBlank
    @Getter @Setter
    private String password;

    @Column(name = "email")
    @NotBlank
    @Email
    @Getter @Setter
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "role_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
    @Getter @Setter
    private Role role;


    public User(String userName, String login, String password, String email) {
        this.name = userName;
        this.login = login;
        this.password = password;
        this.email = email;
    }
    
    // @Column(name = "like_count")
    // @Getter @Setter
    // @NotNull
    // private long likeCount; 
    // @OneToMany(mappedBy = "user")
    // @Getter
    // private Set<Translation> translations;

    // @ManyToMany(cascade = {
    //     CascadeType.PERSIST,
    //     CascadeType.MERGE
    // })
    // @JoinTable(
  	// 	name = "user_info", 
  	// 	joinColumns = @JoinColumn(name = "user_id"), 
  	// 	inverseJoinColumns = @JoinColumn(name = "language_id"))
    // @Getter
    // private Set<Language> languages;

    
}
