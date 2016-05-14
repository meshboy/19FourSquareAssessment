package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.assessment.R;

import java.util.List;

import model.Contestant;
import utility.StringTrim;

/**
 * Created by mesh on 5/14/16.
 */
public class ContestantAdapter extends ArrayAdapter<Contestant> {

    private Context context;
    private List<Contestant> contestantList;

    public ContestantAdapter(Context context, int resource, List<Contestant> contestantList) {
        super(context, resource, contestantList);

        this.context = context;
        this.contestantList = contestantList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View view;

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        view = layoutInflater.inflate(R.layout.list_row, parent, false);

        TextView nameView = (TextView) view.findViewById(R.id.name);
        TextView voteCountView = (TextView) view.findViewById(R.id.voteCount);
        TextView descriptionView = (TextView) view.findViewById(R.id.description);

        ImageView imageView = (ImageView) view.findViewById(R.id.image);

        Contestant contestant = contestantList.get(position);

//        bind values to view
        nameView.setText(contestant.getContestantName());
        voteCountView.setText(contestant.getVoteCount()+ " VOTES");
        descriptionView.setText(StringTrim.ellipsize(contestant.getDescription(), 80));

//        get image from resoure from image name
        Resources resources = context.getResources();

        int resourceId = resources.getIdentifier(contestant.getImageName(), "drawable", context.getPackageName());

        imageView.setImageResource(resourceId);


        return view;
    }
}
