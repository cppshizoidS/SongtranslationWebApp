package ru.app.ccfit.cppshiz.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "translations")
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "song_id")
    @Getter
    private Song song;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @Getter
    private User user;

    @ManyToOne
    @JoinColumn(name = "language_id")
    @Getter
    private Language language;


    @Column(name = "text", columnDefinition = "text")
    @NotBlank
    @Getter @Setter
    private String text;

    @Column(name = "like_count")
    @NotNull
    @Getter @Setter
    private long likeCount;

    public Translation(Song song, User user, Language language, String text, long likeCount) {
        this.song = song;
        this.user = user; 
        this.language = language;
        this.text = text;
        this.likeCount = likeCount;
    }


}
