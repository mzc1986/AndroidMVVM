package io.neverstoplearning.acviewmodel.networking;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RepoApi {

    private static  final String BASE_URL = "https://api.github.com/";

    public static Retrofit retrofit;
    public static RepoService repoService;

    public static RepoService getInstance(){
        if(repoService != null ){
            return repoService;
        }
        if(repoService == null){
            initilizeRetrofit();
        }
        repoService = retrofit.create(RepoService.class);
        return  repoService;
    }

    public static void initilizeRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }
    public RepoApi() {

    }
}
