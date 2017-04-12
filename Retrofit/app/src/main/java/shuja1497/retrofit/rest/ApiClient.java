package shuja1497.retrofit.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shuja1497 on 12/4/17.
 */
public class ApiClient {

    //public static final String BASE_URL = "http://api.themoviedb.org/3/movie/550?api_key=aba105d02dc5d3c6b3489a67668dc002";
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
