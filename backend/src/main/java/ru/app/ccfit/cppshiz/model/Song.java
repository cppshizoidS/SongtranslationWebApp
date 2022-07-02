package ru.app.ccfit.cppshiz.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@NoArgsConstructor
@Table(name = "songs")
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@NotNull
	@JoinColumn(name = "album_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	
	@Getter @Setter
	private Album album;

	@Column(name = "name")
	@NotBlank
	@Getter @Setter
	private String songName; 

	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "text")
	@NotBlank
	@Getter @Setter
	private String text;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "language_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Getter @Setter
	private Language language;

	@ManyToMany(fetch = FetchType.LAZY,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        },
        mappedBy = "songs")
	@Getter @Setter
	private Set<Performer> performers = new HashSet<>();


	// @OneToMany(mappedBy = "song")
    // @Getter
    // private Set<Translation> translation;

	public Song(Album album, String name, String text, Language language) {
		this.album = album;
		this.songName = name;
		this.text = text;
		this.language = language;
	}

	// @Override
	// public String toString() {
	// 	return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
	// }



}


