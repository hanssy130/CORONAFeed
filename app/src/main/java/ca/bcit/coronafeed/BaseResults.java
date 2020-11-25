package ca.bcit.coronafeed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BaseResults {
    @SerializedName("articles")
    @Expose
    private ArrayList<Results> results = new ArrayList<>();
    public ArrayList<Results> getResults() {
        return results;
    }
    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }
}
