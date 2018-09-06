package digdevice.nikhil.com.retrofittestproject.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient
{
    private static final String BaseUrl="https://api.themoviedb.org/3/";
    private static Retrofit retrofit=null;

    public static   Retrofit getClient()
    {
         if(retrofit==null)
         {
              retrofit=new Retrofit.Builder()
                      .baseUrl(BaseUrl)
                      .addConverterFactory(GsonConverterFactory.create())
                      .build();
         }
         return retrofit;
    }
}
