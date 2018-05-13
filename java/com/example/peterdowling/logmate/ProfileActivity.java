package com.example.peterdowling.logmate;

/**
 * Created by peterdowling on 18/03/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


/**
 * The type Profile activity.
 */
public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private Button lowPriorityButton;
    private Button mediumPriorityButton;
    private Button highPriorityButton;
    private Button closed_issues;
    private Button LogIssueButton;
    private Button getData;
    /**
     * The Database issue.
     */
    DatabaseReference databaseIssue;

    /**
     * The constant allIssueList.
     */
    public static ArrayList<Issue> allIssueList = new ArrayList<>();

    /**
     * Gets all issue list.
     *
     * @return the all issue list
     */
    public static ArrayList<Issue> getAllIssueList() {
        return allIssueList;
    }

    /**
     * The constant currentUser.
     */
    public static String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if (firebaseAuth.getCurrentUser() == null) {
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        LogIssueButton = (Button) findViewById(R.id.LogIssueButton);
        getData = (Button) findViewById(R.id.getData);

        lowPriorityButton = (Button) findViewById(R.id.lowPriorityButton);
        mediumPriorityButton = (Button) findViewById(R.id.mediumPriorityButton);
        highPriorityButton = (Button) findViewById(R.id.highPriorityButton);
        closed_issues = (Button) findViewById(R.id.closed_issues);

        databaseIssue = FirebaseDatabase.getInstance().getReference("Issue");

        //displaying logged in user name
        textViewUserEmail.setText("Logged in as " + user.getEmail());
        currentUser =  user.getEmail();
        //adding listener to button
        lowPriorityButton.setOnClickListener(this);
        mediumPriorityButton.setOnClickListener(this);
        highPriorityButton.setOnClickListener(this);
        closed_issues.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);
        LogIssueButton.setOnClickListener(this);
        getData.setOnClickListener(this);

    }

    private void getTheData(){
        databaseIssue.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                allIssueList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Issue getData = postSnapshot.getValue(Issue.class);
                    allIssueList.add(getData);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("The read failed: ", Integer.toString(databaseError.getCode()));
            }
        });

    }

    private void getIssueByPriority(final String thePriority,final String theStatus){
        databaseIssue.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                allIssueList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Issue thisData = postSnapshot.getValue(Issue.class);
                    String priority = thisData.getPriority();
                    String theAssignee = thisData.getAssignee();
                    String status = thisData.getStatus();
                    //String convertedToString = String.valueOf(getData);
                    //Log.e("Priority: ", priority);
                    //Adds elements to the list if they meet the conditions
                    if(priority.equals(thePriority) && theAssignee.equals(currentUser) && status.equals(theStatus)){
                        allIssueList.add(thisData);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("The read failed: ", Integer.toString(databaseError.getCode()));
            }
        });

    }
    private void getByStatus(final String theStatus){
        databaseIssue.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                allIssueList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Issue thisData = postSnapshot.getValue(Issue.class);
                    String status = thisData.getStatus();
                    if(status.equals(theStatus)){
                        allIssueList.add(thisData);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("The read failed: ", Integer.toString(databaseError.getCode()));
            }
        });

    }


    @Override
    public void onClick(View view) {
        //if logout is pressed
        if(view == buttonLogout){
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }
        //if my low prioirty is pressed
        if(view == lowPriorityButton){
            Log.d("Button Pressed",allIssueList.toString() );
            getIssueByPriority("Low","open");
            //below code added as workaround for issue with activity loading without data.
            //the 250 miliseconds gives time for the above method to populate the ArrayList
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            startActivity(new Intent(this, Get_Issue.class));
        }
        if(view == mediumPriorityButton){
            Log.d("Button Pressed",allIssueList.toString() );
            getIssueByPriority("Medium","open");
            //below code added as workaround for issue with activity loading without data.
            //the 250 miliseconds gives time for the above method to populate the ArrayList
            try {
                TimeUnit.MILLISECONDS.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            startActivity(new Intent(this, Get_Issue.class));
        }
        if(view == highPriorityButton){
            Log.d("Button Pressed",allIssueList.toString() );
            getIssueByPriority("High","open");
            //below code added as workaround for issue with activity loading without data.
            //the 250 miliseconds gives time for the above method to populate the ArrayList
            try {
                TimeUnit.MILLISECONDS.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            startActivity(new Intent(this, Get_Issue.class));
        }
        if(view == closed_issues){
            Log.d("Button Pressed",allIssueList.toString() );
            getByStatus("closed");
            //below code added as workaround for issue with activity loading without data.
            //the 250 miliseconds gives time for the above method to populate the ArrayList
            try {
                TimeUnit.MILLISECONDS.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            startActivity(new Intent(this, Get_Issue.class));
        }
        if(view == LogIssueButton){
            startActivity(new Intent(this, Log_itActivity.class));
        }
        if(view == getData){
            getTheData();
            //below code added as workaround for issue with activity loading without data.
            //the 250 miliseconds gives time for the above method to populate the ArrayList
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            startActivity(new Intent(this, Get_Issue.class));
          }
    }
}
