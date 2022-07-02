package ru.app.ccfit.cppshiz.controller;


import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.app.ccfit.cppshiz.model.*;
import ru.app.ccfit.cppshiz.repo.*;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/api")
public class SongController {

	@Autowired
	SongRepository songRepository;
	@Autowired
	LanguageRepository languageRepository;
	@Autowired
	AlbumRepository albumRepository;
	@Autowired
	PerformerRepository performerRepository;

	@GetMapping("/search")
	public ResponseEntity<Set<Song>> getSongByName(@RequestParam(name = "song_name") String songName) {
		Set<Song> songs = new HashSet<>();
		System.out.println("hi");
		songRepository.findBySongName(songName).forEach(songs::add);
        if (songs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songs, HttpStatus.OK);
	}


	// @GetMapping("/song")
	// public ResponseEntity<Song> getOneSong(@RequestParam(name = "song_name") String songName, 
	// 	@RequestParam(name = "album_name") String albumName, 
	// 	@RequestParam(name = "performer_name") Set<String> performerName ) {
	// 	Set<Song> songs = songRepository.findBySongName(songName);
	// 	for (var song : songs) {
	// 		if (song.getAlbum().getAlbumName() == albumName) {
	// 			var performers = song.getPerformers();
	// 			for (var performer : performers) {
	// 				if ()
	// 			}
	// 		}
	// 	}
	// }

	@PostMapping("/add_songs")
	public ResponseEntity<Song> addSong(@RequestParam(name = "album_id") long albumId, 
		@RequestParam(name = "language_id") long languageId,
	 	@RequestParam(name = "performer_id") long performerId,
		@RequestBody Song songRequest) {
			
	
			Song song = performerRepository.findById(performerId).map(performer -> {
				long songId = songRequest.getId();
				if (songId != 0L) {
					Song _song = songRepository.findById(songId)
						.orElseThrow(() -> new IllegalAccessError("Not found Tag with id = " + songId));
					performer.addSong(_song);
					performerRepository.save(performer);
					return _song;
				  }
				Album album = albumRepository.findById(albumId).orElseThrow(() -> new IllegalAccessError("There is no album with id " + albumId));
				songRequest.setAlbum(album);
				Language language = languageRepository.findById(languageId).orElseThrow(() -> new IllegalAccessError("There is no language with id " + languageId));
				songRequest.setLanguage(language);
			 	performer.addSong(songRequest);
				return songRepository.save(songRequest);
		  	}).orElseThrow(() -> new IllegalAccessError("There is no performer with id " + performerId));
		return new ResponseEntity<>(song, HttpStatus.CREATED);
	}

// 	@PutMapping("/comments/{id}")
// 	public ResponseEntity<Comment> updateComment(@PathVariable("id") long id, @RequestBody Comment commentRequest) {
// 	  Comment comment = commentRepository.findById(id)
// 		  .orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + "not found"));
// 	  comment.setContent(commentRequest.getContent());
// 	  return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.OK);
// 	}
// 	@DeleteMapping("/comments/{id}")
// 	public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
// 	  commentRepository.deleteById(id);
// 	  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
// 	}
	
// 	@DeleteMapping("/tutorials/{tutorialId}/comments")
// 	public ResponseEntity<List<Comment>> deleteAllCommentsOfTutorial(@PathVariable(value = "tutorialId") Long tutorialId) {
// 	  if (!tutorialRepository.existsById(tutorialId)) {
// 		throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
// 	  }
// 	  commentRepository.deleteByTutorialId(tutorialId);
// 	  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
// 	}
//   }
}
