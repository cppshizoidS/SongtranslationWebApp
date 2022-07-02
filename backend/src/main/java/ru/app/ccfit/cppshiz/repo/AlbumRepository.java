package ru.app.ccfit.cppshiz.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.app.ccfit.cppshiz.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    Set<Album> findByAlbumName(String albumName);
}