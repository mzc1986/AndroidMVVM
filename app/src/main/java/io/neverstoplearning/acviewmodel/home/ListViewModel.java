package io.neverstoplearning.acviewmodel.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import io.neverstoplearning.acviewmodel.model.Repo;
import io.neverstoplearning.acviewmodel.networking.RepoApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewModel extends ViewModel {

    MutableLiveData<List<Repo>> repos = new MutableLiveData<>();
    MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private Call<List<Repo>> repoCall;


    LiveData<List<Repo>> getRepos(){
                return repos;
    }

    LiveData<Boolean> getError(){
        return repoLoadError;
    }

    LiveData<Boolean> getLoadin(){
        return loading;
    }

    public ListViewModel() {
        fetchRepos();
    }

    private void fetchRepos() {
        loading.setValue(true);
        repoCall = RepoApi.getInstance().getRepositories();

        repoCall.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                loading.setValue(false);
                repoLoadError.setValue(false);
                repos.setValue(response.body());
                repoCall = null;
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                loading.setValue(false);
                repoLoadError.setValue(false);
                repoCall=null;
            }
        });
    }

    @Override
    protected void onCleared() {
        if(repoCall != null){
            repoCall.cancel();
            repoCall = null;
        }
    }
}
