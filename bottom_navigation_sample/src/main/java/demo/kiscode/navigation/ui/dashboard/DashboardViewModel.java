package demo.kiscode.navigation.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Calendar;

import demo.kiscode.navigation.R;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<Integer> mBgColorLiveData;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mBgColorLiveData = new MutableLiveData<>();
        int second = Calendar.getInstance().get(Calendar.SECOND);
        mText.setValue("This is dashboard fragment " + second);
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