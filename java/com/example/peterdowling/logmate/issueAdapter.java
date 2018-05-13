package com.example.peterdowling.logmate;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by peterdowling on 21/04/2018.
 */
public class issueAdapter extends ArrayAdapter<Issue> {
    /**
     * Instantiates a new Issue adapter.
     * This takes the items in the ArrayList<Issue> and creates the custom adapter for the listView
     * https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
     * @param context the context
     * @param users   the users
     */
    public issueAdapter(Context context, ArrayList<Issue> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.da_item, parent, false);

        }
        // Get the data item for this position
        Issue issue = getItem(position);
        // Lookup view for data population
        TextView title = convertView.findViewById(R.id.title);
        TextView des = convertView.findViewById(R.id.des);
        TextView assignee = convertView.findViewById(R.id.assignee);
        TextView createdby = convertView.findViewById(R.id.createdby);
        //TextView date = convertView.findViewById(R.id.date);
        TextView coordinates = convertView.findViewById(R.id.coordinates);

        // Populate the data into the template view using the data object
        title.setText(issue.getTitle());
        des.setText(issue.getDes());
        assignee.setText("Assigned to: "+ issue.getAssignee());
        createdby.setText("Creator: " + issue.getcreatedBy()+ " at " + issue.getTimeDate());
        coordinates.setText("lat:" + issue.getLatitude() + " long: " + issue.getLongitude());
      //  ListView theList = convertView.findViewById(listView);

        if (issue.getPriority().equals( "Low")){
            Log.d("This issue is low", issue.getTitle());
            title.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green));
        }
        if (issue.getPriority().equals( "Medium")){
            Log.d("This issue is low", issue.getTitle());
            title.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.yellow));
        }
        if (issue.getPriority().equals( "High")){
            Log.d("This issue is low", issue.getTitle());
            title.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.red));
        }
        // Return the completed view to render on screen
        return convertView;

    }
}