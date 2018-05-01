package com.rossevineartha.pointofsale_project2.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rossevineartha.pointofsale_project2.Entity.User;
import com.rossevineartha.pointofsale_project2.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private ArrayList<User> userArrayList;
    private UserDataListener userDataListener;

    public void setUserDataListener(UserDataListener userDataListener) {
        this.userDataListener = userDataListener;
    }

    public ArrayList<User> getUserArrayList() {
        if (userArrayList == null) {
            userArrayList = new ArrayList<>();
        }

        return userArrayList;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {

        this.getUserArrayList().clear();
        this.getUserArrayList().addAll(userArrayList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final User user = getUserArrayList().get(position);

        holder.txtid.setText(user.getIdUser());
        holder.txtNama.setText(user.getNamaUser());
        holder.txtUsername.setText(user.getUsername());
        holder.txtAlamat.setText(user.getAlamatUser());
        holder.txtNoTlp.setText(user.getNoTelpUser());
        holder.txtAdmin.setText(String.valueOf(user.getAdmin()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDataListener.onUserDataClicked(user);

            }
        });
    }

    @Override
    public int getItemCount() {
        return getUserArrayList().size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtID_user_rowUser)
        TextView txtid;
        @BindView(R.id.txtNama_user_rowUser)
        TextView txtNama;
        @BindView(R.id.txtUsername_user_rowUser)
        TextView txtUsername;
        @BindView(R.id.txtAlamat_user_rowUser)
        TextView txtAlamat;
        @BindView(R.id.txtNoTlp_user_rowUser)
        TextView txtNoTlp;
        @BindView(R.id.txtAdmin_user_rowUser)
        TextView txtAdmin;
        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


    }

    public interface UserDataListener {
        void onUserDataClicked(User user);

    }
}
