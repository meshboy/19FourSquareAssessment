package utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Contestant;

/**
 * Created by mesh on 5/14/16.
 */
public class JsonParser {

    /**
     *
     * @param jsonData
     * @return contestant list that holds object of contestant
     */

    public static List<Contestant> parseContestant (String jsonData){

        List<Contestant> contestantList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonData);

            JSONArray jsonArray = jsonObject.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject obj = jsonArray.getJSONObject(i);

                Contestant contestant = new Contestant();

                contestant.setContestantName(obj.getString("contestant"));
                contestant.setDescription(obj.getString("desc"));
                contestant.setVoteCount(obj.getString("votes"));
                contestant.setImageName(obj.getString("image"));

                contestantList.add(contestant);
            }

            return  contestantList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
