package ca.bcit.coronafeed.ui.all_news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllNewsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AllNewsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is an all news fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}