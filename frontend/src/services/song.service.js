import http from "../http-common";

const findSongByName = name => {
  return http.get(`/search?song_name=${name}`);
}

const SongService = {
  findSongByName
};
export default SongService;
