package demo.kiscode.navigation.ui.notifications;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<List<String>> mutableLiveData;

    public NotificationsViewModel() {
        mutableLiveData = new MutableLiveData<>();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                double maxLen = Math.random() * 1000 + 10;
                List<String> dataList = new ArrayList<>();
                for (int i = 1; i < maxLen; i++) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dataList.add(String.valueOf(i));
                }
                mutableLiveData.postValue(dataList);
            }
        });
    }

    public MutableLiveData<List<String>> getMutableLiveData() {
        return mutableLiveData;
    }
}