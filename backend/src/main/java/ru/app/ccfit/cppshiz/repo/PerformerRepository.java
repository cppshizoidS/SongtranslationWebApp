package ru.app.ccfit.cppshiz.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.app.ccfit.cppshiz.model.Performer;

@Repository
public interface PerformerRepository extends JpaRepository<Performer, Long> {
    Performer findByPerformerName(String performerName);
}
