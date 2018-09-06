package digdevice.nikhil.com.retrofittestproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import digdevice.nikhil.com.retrofittestproject.R;
import digdevice.nikhil.com.retrofittestproject.adapter.MoviesAdapter;
import digdevice.nikhil.com.retrofittestproject.model.Movie;
import digdevice.nikhil.com.retrofittestproject.model.MovieResponse;
import digdevice.nikhil.com.retrofittestproject.rest.ApiClient;
import digdevice.nikhil.com.retrofittestproject.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG =MainActivity.class.getSimpleName();
    private List<Movie> movies;
    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "63166f09b6373948740c7fcc89b30ec0";
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         MoviesAdapter moviesAdapter;
        
         if(API_KEY.isEmpty())
         {
             Toast.makeText(this, "Please Obtain your ApiKey First,and then Test", Toast.LENGTH_SHORT).show();
             return;
         }
         
         // Instantiate of RecyclerView
         recyclerView =(RecyclerView)findViewById(R.id.my_recycler_view);
         recyclerView.setHasFixedSize(true);
         layoutManager=new LinearLayoutManager(this);
         recyclerView.setLayoutManager(layoutManager);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call =apiInterface.getTopRatedMovie(API_KEY);
        call.enqueue(new Callback<MovieResponse>()
        {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response)
            {
                 movies=response.body().getResults();
                 MoviesAdapter mAdapter =new MoviesAdapter(movies,R.layout.list_item_movie,getApplicationContext());
                 recyclerView.setAdapter(mAdapter);
                 Log.d(TAG,"Response Successfully");
                 Log.i(TAG,"Response By Retrofit :"+response.toString());
                 Log.d(TAG,"NO of Movies Received :  "+movies.size());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t)
            {
                Log.e(TAG,t.toString());
            }
        });

    }
}
