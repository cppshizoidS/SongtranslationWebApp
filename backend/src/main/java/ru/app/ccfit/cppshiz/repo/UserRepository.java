package ru.app.ccfit.cppshiz.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.app.ccfit.cppshiz.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
    Optional<User> findByLogin(String login);
	Boolean existsByName(String userName);
	Boolean existsByLogin(String login);
	Boolean existsByEmail(String email);
}
