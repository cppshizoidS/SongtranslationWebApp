package ru.app.ccfit.cppshiz.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.app.ccfit.cppshiz.model.Album;
import ru.app.ccfit.cppshiz.repo.AlbumRepository;

@RestController
@RequestMapping("/api")
public class AlbumController {
    @Autowired
    AlbumRepository albumRepository;

    @GetMapping("/get_all_albums")
    public ResponseEntity<Set<Album>> getAllAlbums() {
        Set<Album> albums = new HashSet<Album>();
        albumRepository.findAll().forEach(albums::add);
        if (albums.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping("/get_album_by_name")
    public ResponseEntity<Set<Album>> getAlbumByName(@RequestParam(name = "album_name") String albumName) {
        Set<Album> albums = new HashSet<>();
        albumRepository.findByAlbumName(albumName).forEach(albums::add);

        if (albums.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(albums, HttpStatus.OK);

    }

    @GetMapping("/get_albums_by_id")
    public ResponseEntity<Album> getAlbumById(@RequestParam(name = "album_id") long id) {
        Album album = albumRepository.findById(id)
        .orElseThrow(IllegalAccessError::new);
        return new ResponseEntity<>(album, HttpStatus.OK);
    }

    @PostMapping("/add_albums")
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        Album _album = albumRepository.save(new Album(album.getAlbumName()));
        return new ResponseEntity<Album>(_album, HttpStatus.CREATED);
    
    }

    @DeleteMapping("/delete_album")
    public ResponseEntity<HttpStatus> deleteAlbum(@RequestParam(name = "album_id") long id) {
        albumRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete_all_albums")
    public ResponseEntity<HttpStatus> deleteAllAlbums() {
        albumRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
