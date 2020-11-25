package ca.bcit.coronafeed.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;

import java.util.ArrayList;

import ca.bcit.coronafeed.BaseResults;
import ca.bcit.coronafeed.HttpHandler;
import ca.bcit.coronafeed.R;
import ca.bcit.coronafeed.Results;
import ca.bcit.coronafeed.ResultsAdapter;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private String TAG = HomeFragment.class.getSimpleName();
    private ListView lv;
    // URL to get contacts JSON
    private static String SERVICE_URL = "https://gnews.io/api/v4/search?q=Corona%20english&token=8b19c0fa371cffba0016e92f4371ca06";
    private ArrayList<Results> resultsList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        System.out.println(SERVICE_URL);
        resultsList = new ArrayList<Results>();
        lv = root.findViewById(R.id.resultList);
        new GetResults().execute();

        return root;
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetResults extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)  {
            HttpHandler sh = new HttpHandler();
            String jsonStr = null;

            // Making a request to URL and getting response
            jsonStr = sh.makeServiceCall(SERVICE_URL);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                Log.d(TAG, "Json: " + jsonStr);
                Gson gson = new Gson();
                BaseResults baseResults = gson.fromJson(jsonStr, BaseResults.class);
                resultsList = baseResults.getResults();
            } else {
                Log.e(TAG, "Couldn't get json from server.");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);

            ResultsAdapter adapter = new ResultsAdapter(getActivity(), resultsList);

            // Attach the adapter to a ListView
            lv.setAdapter(adapter);
        }
    }
}