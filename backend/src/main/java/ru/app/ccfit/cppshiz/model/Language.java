package ru.app.ccfit.cppshiz.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@NoArgsConstructor
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Column(name = "name")
    @NotBlank
    @Getter @Setter
    private String languageName;

    public Language(String name) {
        this.languageName = name;
    }

}