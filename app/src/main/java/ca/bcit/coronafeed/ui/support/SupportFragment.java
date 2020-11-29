package ca.bcit.coronafeed.ui.support;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.bcit.coronafeed.BenefitDescription;
import ca.bcit.coronafeed.BenefitDescriptionListAdapter;
import ca.bcit.coronafeed.HomeActivity;
import ca.bcit.coronafeed.R;


public class SupportFragment extends Fragment {

    private SupportViewModel supportViewModel;

    DatabaseReference databaseBenefitDescription;

    private EditText link;
    private EditText title;
    private EditText province;
    private Button addBenefitDescription;
    private Button filterByProvince;


    private Spinner provinceSelector;

    ListView lvBDs;
    List<BenefitDescription> BDlist;
    List<BenefitDescription> filteredBDlist = new ArrayList<>();




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        supportViewModel =
                ViewModelProviders.of(this).get(SupportViewModel.class);
        View root = inflater.inflate(R.layout.fragment_support, container, false);

        databaseBenefitDescription = FirebaseDatabase.getInstance().getReference("benefitDescriptions");

        link = root.findViewById(R.id.link);
        title = root.findViewById(R.id.title);
        province = root.findViewById(R.id.province);
        addBenefitDescription = root.findViewById(R.id.addBenefitDescription);
        provinceSelector = root.findViewById(R.id.provinceSelector);
        filterByProvince = root.findViewById(R.id.filterByProvince);


        addBenefitDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBloodPressure();

            }
        });

        filterByProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateLV();
            }
        });

        lvBDs = root.findViewById(R.id.lvBDs);
        BDlist = new ArrayList<BenefitDescription>();





        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseBenefitDescription.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                BDlist.clear();
                for (DataSnapshot studentSnapshot : dataSnapshot.getChildren()) {
                    BenefitDescription benefitDescription = studentSnapshot.getValue(BenefitDescription.class);
                    BDlist.add(benefitDescription);
                }


                BenefitDescriptionListAdapter adapter = new BenefitDescriptionListAdapter(getActivity(), BDlist);
                lvBDs.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    private void updateLV() {
        filteredBDlist.clear();
        String selectedProvince = provinceSelector.getSelectedItem().toString();
        for (int i = 0; i < BDlist.size(); i++) {
            if (selectedProvince.equals(BDlist.get(i).getProvince())) {
                filteredBDlist.add(BDlist.get(i));
            }

            BenefitDescriptionListAdapter adapter = new BenefitDescriptionListAdapter(getActivity(), filteredBDlist);
            lvBDs.setAdapter(adapter);
        }
    }




    private void addBloodPressure() {

        String BDLink = link.getText().toString().trim();
        String BDProvince = province.getText().toString().trim();
        String BDTitle = title.getText().toString().trim();
        String id = databaseBenefitDescription.push().getKey();

        BenefitDescription benefitDescription = new BenefitDescription(id, BDLink, BDProvince, BDTitle);
        Task setValueTask = databaseBenefitDescription.child(id).setValue(benefitDescription);

        setValueTask.addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {

                link.setText("");
                province.setText("");
                title.setText("");
            }
        });

        setValueTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("some");
            }
        });
    }
}
