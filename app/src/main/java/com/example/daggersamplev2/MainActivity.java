package com.example.daggersamplev2;
/**
 * Created by Djvmil_ on 13/11/2019.
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.daggersamplev2.dagger.adapter.UserAdapter;
import com.example.daggersamplev2.dagger.backend.ServiceUtil;
import com.example.daggersamplev2.dagger.components.DaggerServiceApplicationComponent;
import com.example.daggersamplev2.dagger.components.ServiceApplicationComponent;
import com.example.daggersamplev2.dagger.modules.ApplicationContextModule;
import com.example.daggersamplev2.dagger.response.UserResponseResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<List<UserResponseResponse>> {
    private static final String TAG = "MainActivity";

    @Inject
    Picasso picasso;
    @Inject
    ServiceUtil serviceUtil;

    private RecyclerView mainRecyclerView;
    private UserAdapter userAdapter;
    private List<UserResponseResponse> carCategories = new ArrayList<>();
    private ProgressBar mainProgressBar;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ServiceApplicationComponent component = DaggerServiceApplicationComponent.builder()
                .applicationContextModule(new ApplicationContextModule(this))
                .build();

        component.inject(this);
        initViews();
        setRecyclerViewProperties();
        serviceUtil.getUsers().enqueue(this);

    }

    private void setRecyclerViewProperties() {
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.setHasFixedSize(true);
        userAdapter = new UserAdapter(carCategories, this);
        mainRecyclerView.setAdapter(userAdapter);
    }

    private void initViews() {
        mainRecyclerView = findViewById(R.id.mainRecyclerView);
        mainProgressBar = findViewById(R.id.mainProgressBar);
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
                if (mainProgressBar != null && mainProgressBar.getVisibility() == View.VISIBLE)
                    mainProgressBar.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        userAdapter = null;
        carCategories.clear();
        carCategories = null;
        serviceUtil = null;
        picasso = null;
        disposable = null;
    }


    @Override
    public void onResponse(Call<List<UserResponseResponse>> call, Response<List<UserResponseResponse>> response) {
        if (mainProgressBar.getVisibility() == View.VISIBLE)
            mainProgressBar.setVisibility(View.GONE);
        Log.e(TAG, "onResponse: "+response.toString() );
        if (response.isSuccessful()) {
            if (response.body() != null && response.body().size() > 0) {
                this.carCategories.addAll(response.body());
                userAdapter.notifyDataSetChanged();
            } else
                Toast.makeText(this, "No data found!", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Communication error internet not connect!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Call<List<UserResponseResponse>> call, Throwable t) {
        Log.e(TAG, "onResponse: "+t.getMessage() );
        if (mainProgressBar.getVisibility() == View.VISIBLE)
            mainProgressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Communication error internet not connect!", Toast.LENGTH_SHORT).show();
    }


}
