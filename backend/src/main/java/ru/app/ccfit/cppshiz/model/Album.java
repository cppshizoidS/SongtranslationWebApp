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
@Table(name = "albums")
public class Album {
    @Id
    @Getter 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "album_name")    
    @NotBlank
    @Getter @Setter
    private String albumName;



    public Album(String name) {
        this.albumName = name;
    }
}
