package ru.app.ccfit.cppshiz.repo;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.app.ccfit.cppshiz.model.*;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
  Set<Song> findBySongName(String songName);
  Song findByAlbumId(long albumId);
}