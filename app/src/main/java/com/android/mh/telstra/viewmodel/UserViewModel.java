package com.android.mh.telstra.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.android.mh.telstra.UserRepository;
import com.android.mh.telstra.model.AboutCanada;

/**
 * View model class to communicate with UI and emits the data to The view
 */
public class UserViewModel extends AndroidViewModel {
    private UserRepository mRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepository = new UserRepository();
    }

    /**
     * provide observable to UI
     *
     * @return
     */
    public LiveData<AboutCanada> getAboutCanada() {
        return mRepository.fetchFromWeb();
    }
}
