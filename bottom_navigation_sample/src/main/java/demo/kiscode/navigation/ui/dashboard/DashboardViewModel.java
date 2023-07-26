package demo.kiscode.navigation.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Calendar;

import demo.kiscode.navigation.R;

public class DashboardViewModel extends ViewModel {
    private final MutableLiveData<String> mText;
    private final MutableLiveData<Integer> mBgColorLiveData;
    private boolean hasInitLoad;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mBgColorLiveData = new MutableLiveData<>();
    }

    public void load() {
        if (!hasInitLoad) {
            int second = Calendar.getInstance().get(Calendar.SECOND);
            mText.setValue("This is dashboard at " + second);
            hasInitLoad = true;
        }
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<Integer> getBgColorLiveData() {
        return mBgColorLiveData;
    }

    public void changeBgColor() {
        mBgColorLiveData.postValue(R.color.purple_200);
    }
}