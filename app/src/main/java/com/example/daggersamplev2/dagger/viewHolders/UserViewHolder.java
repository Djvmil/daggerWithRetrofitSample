package com.example.daggersamplev2.dagger.viewHolders;
/**
 * Created by Djvmil_ on 13/11/2019.
 */
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.daggersamplev2.R;


public class UserViewHolder extends RecyclerView.ViewHolder {

    private TextView car_category, rate_per_min, rate_per_km, base_fare;

    public UserViewHolder(View itemView) {
        super(itemView);
        car_category = itemView.findViewById(R.id.car_category);
        rate_per_min = itemView.findViewById(R.id.rate_per_min);
        rate_per_km = itemView.findViewById(R.id.rate_per_km);
        base_fare = itemView.findViewById(R.id.base_fare);
    }

    public TextView getCar_category() {
        return car_category;
    }

    public TextView getRate_per_min() {
        return rate_per_min;
    }

    public TextView getRate_per_km() {
        return rate_per_km;
    }

    public TextView getBase_fare() {
        return base_fare;
    }
}
