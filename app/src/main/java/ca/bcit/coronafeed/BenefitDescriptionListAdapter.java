package ca.bcit.coronafeed;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class BenefitDescriptionListAdapter extends ArrayAdapter<BenefitDescription> {
    private Activity context;
    private List<BenefitDescription> BDList;

    public BenefitDescriptionListAdapter(Activity context, List<BenefitDescription> BDList) {
        super(context, R.layout.benefit_description_list_layout, BDList);
        this.context = context;
        this.BDList = BDList;
    }

    public BenefitDescriptionListAdapter(Context context, int resource, List<BenefitDescription> objects, Activity context1, List<BenefitDescription> BDList) {
        super(context, resource, objects);
        this.context = context1;
        this.BDList = BDList;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.benefit_description_list_layout, null, true);

        TextView title = listViewItem.findViewById(R.id.title);
        TextView link = listViewItem.findViewById(R.id.link);


        BenefitDescription benefitDescription = BDList.get(position);
        title.setText(benefitDescription.getTitle());
        link.setText(benefitDescription.getLink());



        return listViewItem;
    }

}

