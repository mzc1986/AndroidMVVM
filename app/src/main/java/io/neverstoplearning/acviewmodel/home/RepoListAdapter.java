package io.neverstoplearning.acviewmodel.home;

import android.arch.lifecycle.LifecycleOwner;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.neverstoplearning.acviewmodel.R;
import io.neverstoplearning.acviewmodel.model.Repo;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>{

    private final List<Repo> data = new ArrayList<>();

    public RepoListAdapter(ListViewModel listViewModel, LifecycleOwner lifecycleOwner) {

        listViewModel.getRepos().observe(lifecycleOwner, repos -> {
            data.clear();
            if(repos != null) {
                data.addAll(repos);
            }
            notifyDataSetChanged();
        });

        setHasStableIds(true);
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_repo_list_item, parent, false);
        return new RepoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).id;
    }

    static final class RepoViewHolder extends RecyclerView.ViewHolder{

        private final Unbinder unbinder;

        @BindView(R.id.tv_repo_name)
        TextView repoNameTextView;
        @BindView(R.id.tv_repo_description)
        TextView repoDescriptionTextView;
        @BindView(R.id.tv_forks)
        TextView forksTextView;
        @BindView(R.id.tv_stars)
        TextView startsTextview;

        public RepoViewHolder(View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }

        void bind(Repo repo){
            repoNameTextView.setText(repo.name);
            repoDescriptionTextView.setText(repo.description);
            forksTextView.setText(String.valueOf(repo.forks));
            startsTextview.setText(String.valueOf(repo.stars));
        }
    }
}
