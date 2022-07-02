package ru.nsu.ccfit.trubitsyna.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.app.ccfit.cppshiz.model.Language;
import ru.app.ccfit.cppshiz.repo.LanguageRepository;

@RestController
@RequestMapping("/api")
public class LanguageController {
    @Autowired
    LanguageRepository languageRepository;

    @GetMapping("/get_all_languages")
    public ResponseEntity<Set<Language>> getAllLanguages() {
        Set<Language> languages = new HashSet<Language>();
        languageRepository.findAll().forEach(languages::add);
    
        if (languages.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(languages, HttpStatus.OK);
    }


    @GetMapping("/get_language_by_name")
    public ResponseEntity<Language> getLanguageByName(@RequestParam(name = "language_name") String name) {
        Language language = languageRepository.findByLanguageName(name);
        if (language == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(language, HttpStatus.OK);
    }

    @GetMapping("/get_language_by_id")
    public ResponseEntity<Language> getLanguageById(@RequestParam(name = "language_id") long id) {
        Language language = languageRepository.findById(id)
        .orElseThrow(IllegalAccessError::new);
        return new ResponseEntity<>(language, HttpStatus.OK);
    }

    @PostMapping("/add_languages")
    public ResponseEntity<Language> createLanguage(@RequestBody Language language) {
        Language _language = languageRepository.save(new Language(language.getLanguageName()));
        return new ResponseEntity<>(_language, HttpStatus.CREATED);
    
    }

    @DeleteMapping("/delete_language_by_id")
    public ResponseEntity<HttpStatus> deleteAlbum(@RequestParam(name = "id") long id) {
        languageRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete_all_languages")
    public ResponseEntity<HttpStatus> deleteAllAlbums() {
        languageRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
