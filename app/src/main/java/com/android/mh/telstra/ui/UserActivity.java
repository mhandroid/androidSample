package com.android.mh.telstra.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.mh.telstra.R;
import com.android.mh.telstra.model.AboutCanada;
import com.android.mh.telstra.viewmodel.UserViewModel;

/**
 * Main activity to show list of results
 */
public class UserActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = UserActivity.class.getSimpleName();
    private UserViewModel mUserViewModel;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView errorTextView;
    private RecyclerView recyclerView;
    private UserListAdapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();

        adapter = new UserListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mSwipeRefreshLayout.setOnRefreshListener(UserActivity.this);
        mSwipeRefreshLayout.setRefreshing(true);
        refreshContent();
        toolbar.setTitle(R.string.title);
        errorTextView.setText(getString(R.string.loading_txt));

    }

    /**
     * Initialize the ui views
     */
    private void initView() {
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        errorTextView = findViewById(R.id.textError);

        recyclerView = findViewById(R.id.recyclerview);
    }

    /**
     * to refresh the list
     */
    private void refreshContent() {
        mUserViewModel.getAboutCanada().observe(this, new Observer<AboutCanada>() {
            @Override
            public void onChanged(AboutCanada aboutCanada) {
                if (aboutCanada != null) {
                    //update the list
                    Log.d(TAG, aboutCanada.getTitle());
                    adapter.setWords(aboutCanada.getRows());
                    toolbar.setTitle(aboutCanada.getTitle());
                    mSwipeRefreshLayout.setRefreshing(false);
                    //hide the error rtext view
                    errorTextView.setVisibility(View.GONE);
                } else {
                    //If data is not available show the error text view and hide recycler view
                    errorTextView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);

                }
            }
        });

    }

    @Override
    public void onRefresh() {
        refreshContent();
    }
}
