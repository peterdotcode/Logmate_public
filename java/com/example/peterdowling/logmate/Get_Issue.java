package com.example.peterdowling.logmate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * The type Get issue.
 * Populateds the issue_list.xml with the items in the Issue array list.
 * Each item in the list uses the da_item.xml format as defined in the issue adapter class
 */

public class Get_Issue  extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.issue_list);
        populateUsersList();
    }

    private void populateUsersList() {
        // Construct the data source
        ArrayList<Issue> arrayOfUsers = ProfileActivity.getAllIssueList();
        // Create the adapter to convert the array to views
        issueAdapter adapter = new issueAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                Issue selectedItem = (Issue) parent.getItemAtPosition(position);
                // Log the selected item
                Log.d("clicked",selectedItem.getIssueID());
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
