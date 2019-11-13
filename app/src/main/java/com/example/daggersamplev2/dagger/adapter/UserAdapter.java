package com.example.daggersamplev2.dagger.adapter;
/**
 * Created by Djvmil_ on 13/11/2019.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daggersamplev2.R;
import com.example.daggersamplev2.dagger.response.UserResponseResponse;
import com.example.daggersamplev2.dagger.viewHolders.UserViewHolder;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<UserResponseResponse> carCategories;
    private LayoutInflater layoutInflater;

    public UserAdapter(List<UserResponseResponse> carCategories, final Context context) {
        this.carCategories = carCategories;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(layoutInflater.inflate(R.layout.list_item_single_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserResponseResponse carCategory = carCategories.get(position);
        holder.getBase_fare().setText(String.valueOf("Rs ").concat(carCategory.getTitle()));
        holder.getCar_category().setText(carCategory.getUserId()+"");
        holder.getRate_per_km().setText(String.valueOf("Rs ").concat(carCategory.getBody()));
        holder.getRate_per_min().setText(String.valueOf("Rs ").concat(carCategory.getBody()));
    }

    @Override
    public int getItemCount() {
        return carCategories.size();
    }
}
