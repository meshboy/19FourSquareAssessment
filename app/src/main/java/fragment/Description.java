package fragment;


import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.assessment.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import model.Contestant;
import utility.JsonParser;

/**
 * A simple {@link Fragment} subclass.
 */
public class Description extends Fragment {

    View view;

    TextView votesCountView, nameView, descriptionView;

    ImageView imageView;

    Button voteButton;

    List<Contestant> contestantList;

    public static String KEY_POSITION = "position";

    int currentPosition = -1;


    public Description() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_description, container, false);

        if(savedInstanceState != null){

            currentPosition = savedInstanceState.getInt(KEY_POSITION);
        }


        votesCountView = (TextView) view.findViewById(R.id.votesCount);
        nameView = (TextView) view.findViewById(R.id.name);
        descriptionView = (TextView) view.findViewById(R.id.description);

        imageView = (ImageView) view.findViewById(R.id.image);

        voteButton = (Button) view.findViewById(R.id.vote);

        String jsonString = readFile();

        contestantList = JsonParser.parseContestant(jsonString);

        setDescription(0);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

//        during startup, check if arguments is passed to fragment

        Bundle args = getArguments();

        if(args != null){

            setDescription(args.getInt(KEY_POSITION));
        }
        else if (currentPosition != -1){

            setDescription(currentPosition);
        }
    }

    public void setDescription (int descriptionPosition){

        votesCountView.setText(contestantList.get(descriptionPosition).getVoteCount() + " VOTES");
        nameView.setText(contestantList.get(descriptionPosition).getContestantName());
        descriptionView.setText(contestantList.get(descriptionPosition).getDescription());

        voteButton.setText("VOTE FOR " + contestantList.get(descriptionPosition).getContestantName());

        Resources resources = getResources();

        int resourceId = resources.getIdentifier(contestantList.get(descriptionPosition).getImageName(), "drawable", getActivity().getPackageName());
//
        imageView.setImageResource(resourceId);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

//        save the current description selection incase fragment needs to be recreated
        outState.putInt(KEY_POSITION, currentPosition);
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
