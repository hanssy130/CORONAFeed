package ca.bcit.coronafeed.ui.all_news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import ca.bcit.coronafeed.R;

public class AllNewsFragment extends Fragment {

    private AllNewsViewModel allNewsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        allNewsViewModel =
                ViewModelProviders.of(this).get(AllNewsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_all_news, container, false);
        final TextView textView = root.findViewById(R.id.text_all_news);
        allNewsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}