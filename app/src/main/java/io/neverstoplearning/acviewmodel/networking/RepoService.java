package io.neverstoplearning.acviewmodel.networking;

import java.util.List;

import io.neverstoplearning.acviewmodel.model.Repo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RepoService {

    @GET("/orgs/Google/repos")
    Call<List<Repo>> getRepositories();
}
