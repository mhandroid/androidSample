package com.android.mh.telstra;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.mh.telstra.api.ApiClient;
import com.android.mh.telstra.api.ApiInterface;
import com.android.mh.telstra.model.AboutCanada;
import com.android.mh.telstra.model.Row;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class to fetch data
 */
public class UserRepository {
    private static final String TAG = UserRepository.class.getSimpleName();
    private final MutableLiveData<AboutCanada> data;

    public UserRepository() {
        data = new MutableLiveData<>();
    }

    /**
     * Fetch list of data from network
     *
     * @return
     */
    public LiveData<AboutCanada> fetchFromWeb() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<AboutCanada> call = apiService.getListAboutCanada();

        call.enqueue(new Callback<AboutCanada>() {
            @Override
            public void onResponse(@NonNull Call<AboutCanada> call, @NonNull Response<AboutCanada> response) {
                if (response.body() != null) {
                    List<Row> row = response.body().getRows();
                    Log.d(TAG, "Number of rows received: " + row.size());
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AboutCanada> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                data.setValue(null);
            }


        });

        return data;
    }
}
