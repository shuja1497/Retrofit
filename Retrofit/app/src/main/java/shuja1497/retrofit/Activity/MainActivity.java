package shuja1497.retrofit.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shuja1497.retrofit.Model.Movie;
import shuja1497.retrofit.Model.MovieResponse;
import shuja1497.retrofit.R;
import shuja1497.retrofit.adapter.MoviesAdapter;
import shuja1497.retrofit.rest.ApiClient;
import shuja1497.retrofit.rest.ApiInterface;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static String API_KEY="aba105d02dc5d3c6b3489a67668dc002";

    private MoviesAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

       recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Movie> movie = new ArrayList<Movie>();
        adapter = new MoviesAdapter(movie,R.layout.list_item_movie,getApplicationContext());
        recyclerView.setAdapter(adapter);


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {

            @Override
            public void onResponse(Call<MovieResponse>call, Response<MovieResponse> response) {
                int statusCode = response.code();

                List<Movie> movies = new ArrayList<Movie>();
                if(response.isSuccessful() && response.body() != null)
                    movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                Log.d(TAG, "Number of movies received: " + movies.size());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

                // Log error here since request failed
                Log.e(TAG, t.toString());

            }
        });

    }


}
