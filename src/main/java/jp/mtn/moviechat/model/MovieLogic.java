package jp.mtn.moviechat.model;

import jp.mtn.moviechat.dao.MoviesDAO;

public class MovieLogic {
    public Movie findOrCreate(int movieId, String movieTitle) {
        MoviesDAO dao = new MoviesDAO();
        Movie movie = dao.findByMovieId(movieId);
        // 映画が存在しないならばcreate
        if (movie == null) {
            movie = dao.create(movieId, movieTitle);
        }
        return movie;
    }
}
