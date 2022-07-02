package ru.app.ccfit.cppshiz.repo;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.app.ccfit.cppshiz.model.ERole;
import ru.app.ccfit.cppshiz.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByRoleName(ERole name);
}