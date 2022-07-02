package ru.app.ccfit.cppshiz.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
	private Integer id;

    @Column(name = "role_type", length = 20)
	@Enumerated(EnumType.STRING)
    @Getter @Setter
	private ERole roleName;

    public Role(ERole role) {
        this.roleName = role;
    }
}
