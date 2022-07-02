import React, { useState} from "react";
import { Link } from "react-router-dom";
import SongService from "../services/song.service";

const SongsList = () => {
  const[currentIndex, setCurrentIndex] = useState(-1);
  const [songs, setSongs] = useState([]);
  const [performers, setPerformes] = useState([]);
  const [album, setAlbum] = useState(null);
  const [currentSong, setCurrentSong] = useState(null);
  const [searchName, setSearchName] = useState("");

 

  const onChangeSearchName = (e) => {
    const searchName = e.target.value;
    setSearchName(searchName);
  }

  const setChosenSong = (index, song, album, performers) => {
    setCurrentIndex(index);
    setCurrentSong(song);
    setPerformes(performers);
    setAlbum(album);
  }

  const findSong = () => {
    SongService.findSongByName(searchName)
      .then(response => {
        setSongs(response.data);
        setCurrentIndex(-1);
        setCurrentSong("");
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }
  
  return (
        <div className="list row">
          <div className="col-md-8">
            <div className="input-group mb-3">
              <input
                type="text"
                className="form-control"
                placeholder="Enter song name"
                value={searchName}
                onChange={onChangeSearchName} />
                <div className="input-group-append">
                  <button 
                    className="btn btn-outline-secondary"
                    type="button"
                    onClick={findSong}
                  >
                  Search
                  </button>
                </div>
            </div>
          </div>
          <div className="col-md-6">
            <h4>Result of searching</h4>
            <ul className="list-group">
              { songs ? ( 
                songs.map((song, index) => (
                  <li
                    className={
                      "list-group-item" + (index === currentIndex ? "active" : "")
                    }
                    onClick = {() => setChosenSong(index, song)}
                    key={index}
                    >
                      <p>{song.songName}</p>
                      <p>{
                        song.performers.map((performer) => (
                          <small>{performer.performerName} </small>
                        ))
                      }</p>
                      <small>{song.album.albumName}</small>
                  </li>
                ))
              ) : (
                <div>
                  <li>
                    No result!
                  </li>
                </div>
              )}
            </ul>
          </div>
          <div className="col-md-6">
            {currentSong && (
              <div>
                <h4>Song</h4>
                <div>
                  <label>
                    <strong>Song name:</strong>
                  </label>{" "}
                  {currentSong.songName}
                </div>
                <div>
                  <label>
                      <strong>Album:</strong>
                  </label>{" "}
                  {currentSong.album.albumName}
               </div>
                <div>
                  <label>
                    <strong>Perfomers:</strong>                    
                  </label>{" "}
                  { currentSong.performers.map((performer) => (
                      <small>{performer.performerName} </small>
                    ))
                  }
                </div>
                <div>
                  <label>
                      <strong>Text:</strong>
                  </label>{" "}
                  {currentSong.text}
               </div>
              </div>
            )
          }
          </div>
        </div>
  );

};
export default SongsList;