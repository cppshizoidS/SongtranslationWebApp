package ru.app.ccfit.cppshiz.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@NoArgsConstructor
@Table(name = "performers")
public class Performer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Column(name = "performer_name")
    @NotBlank
    @Getter @Setter
    private String performerName;

 	@ManyToMany(
         fetch = FetchType.LAZY,
         cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
	@JoinTable(
  		name = "songs_info", 
  		joinColumns = @JoinColumn(name = "performer_id"), 
        inverseJoinColumns = @JoinColumn(name = "song_id"))
    @Getter @Setter
    @JsonIgnore
    private Set<Song> songs = new HashSet<>();  

    public Performer(String name) {
        System.out.println(name);
        this.performerName = name;
    }

    public void addSong(Song song) {
       songs.add(song);
       song.getPerformers().add(this);
    }
}
