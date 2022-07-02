package ru.app.ccfit.cppshiz.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.app.ccfit.cppshiz.model.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    Language findByLanguageName(String languageName);       
}
