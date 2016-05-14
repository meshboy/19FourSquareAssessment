package fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.root.assessment.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import adapter.ContestantAdapter;
import model.Contestant;
import utility.JsonParser;
import utility.OnFragmentNameSelectionChangeListener;

/**
 * Created by mesh on 5/14/16.
 */
public class ListContestant  extends ListFragment{

    ContestantAdapter contestantAdapter;

    List<Contestant> contestantList;

    View view;

    public ListContestant(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_list, container, false);

        requestData();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void requestData(){

        String jsonString = readFile();

        contestantList = JsonParser.parseContestant(jsonString);

        contestantAdapter = new ContestantAdapter(getActivity(), android.R.layout.simple_list_item_1, contestantList);

        setListAdapter(contestantAdapter);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        OnFragmentNameSelectionChangeListener listener = (OnFragmentNameSelectionChangeListener) getActivity();

        listener.OnSelectionChanged(position);
    }

    public  String readFile (){

        StringBuilder builder = new StringBuilder();


        int rawId = R.raw.assessment;

        try {

            String currentLine;

            InputStream inputStream = getResources().openRawResource(rawId);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while ((currentLine = bufferedReader.readLine()) != null) {

                builder.append(currentLine).append("\n");

            }
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}
