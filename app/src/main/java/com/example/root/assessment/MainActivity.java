package com.example.root.assessment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fragment.Description;
import fragment.ListContestant;
import utility.OnFragmentNameSelectionChangeListener;


public class MainActivity extends AppCompatActivity implements OnFragmentNameSelectionChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        add first fragment if the container is right
        if (findViewById(R.id.fragment_container) != null) {

//            if restored from previous state, return to avoid overlapping fragment
            if (savedInstanceState != null) {
                return;
            }

            ListContestant listContestant = new ListContestant();
            listContestant.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction().add(R.id.fragment_container, listContestant).commit();
        }
    }

    @Override
    public void OnSelectionChanged(int index) {

        Description description = (Description) getFragmentManager().findFragmentById(R.id.description);

        if (description != null) {

//            if description fragment is available, update the content of the description fragment
            description.setDescription(index);
        } else {

            Description newDescription = new Description();

            Bundle bundle = new Bundle();

            bundle.putInt(Description.KEY_POSITION, index);

            newDescription.setArguments(bundle);

//            replace whatever that is there with this fragment
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, newDescription);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() != 0) {

            getFragmentManager().popBackStack();

        } else {

            super.onBackPressed();

        }

    }
}
