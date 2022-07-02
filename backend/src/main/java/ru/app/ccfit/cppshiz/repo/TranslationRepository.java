package ru.app.ccfit.cppshiz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.app.ccfit.cppshiz.Translation;


@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long> {
    
}
