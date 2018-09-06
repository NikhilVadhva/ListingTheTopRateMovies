package digdevice.nikhil.com.retrofittestproject.rest;

import digdevice.nikhil.com.retrofittestproject.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface
{
    @GET("movie/top_rated")
    Call<MovieResponse>getTopRatedMovie(@Query("api_key")String apiKey);

    @GET("movie/{id}")
    Call<MovieResponse>getMoveiDetails(@Path("id")int id,@Query("api_key") String api_key);
}


