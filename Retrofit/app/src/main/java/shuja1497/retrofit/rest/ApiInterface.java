package shuja1497.retrofit.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import shuja1497.retrofit.Model.MovieResponse;

/**
 * Created by shuja1497 on 12/4/17.
 */
public interface ApiInterface {

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("aba105d02dc5d3c6b3489a67668dc002") String apiKey);
}

