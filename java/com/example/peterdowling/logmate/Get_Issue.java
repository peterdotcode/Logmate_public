package com.example.peterdowling.logmate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.peterdowling.logmate.R.layout.issue_list;

/**
 * The type Get issue.
 * Populateds the issue_list.xml with the items in the Issue array list.
 * Each item in the list uses the da_item.xml format as defined in the issue adapter class
 */

public class Get_Issue  extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(issue_list);
        populateUsersList();
    }

    public void showPopup(View v) {
    PopupMenu popup = new PopupMenu(this, v);
    MenuInflater inflater = popup.getMenuInflater();
    inflater.inflate(R.menu.popup_listview, popup.getMenu());
        popup.show();
    }

    private void populateUsersList() {
        // Construct the data source
        ArrayList<Issue> arrayOfUsers = ProfileActivity.getAllIssueList();
        // Create the adapter to convert the array to views
        issueAdapter adapter = new issueAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView
        final ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                final Issue selectedItem = (Issue) parent.getItemAtPosition(position);
                Log.d("clicked",selectedItem.getIssueID());

              PopupMenu popup = new PopupMenu(Get_Issue.this, view);
                //Inflating the Popup using xml file
                MenuInflater inflater = popup.getMenuInflater();
                popup.getMenuInflater().inflate(R.menu.popup_listview, popup.getMenu());
                popup.show();
               // showPopup(view);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if(id==R.id.one){
                            Log.d("clicked close for: ",selectedItem.getIssueID());
                            selectedItem.setStatus("Closed");
                           mDatabase = FirebaseDatabase.getInstance().getReference();
                            mDatabase.child("Issue").child(selectedItem.getIssueID()).child("status").setValue("closed");
                            Log.d("clicked close for: ",mDatabase.toString());

                        }if(id==R.id.two) {
                            Log.d("clicked Assign for: ",selectedItem.getIssueID());
                        }
                        return true;
                    }
                });
            }
        });
    }



    @Override
    public void onClick(View v) {

    }
}
