package com.example.peterdowling.logmate;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

/**
 * Created by peterdowling on 08/04/2018.
 */
public class Log_itActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText issueTitle;
    private EditText issueDescription;
    private Spinner issuePriority;
    private Button submitIssueButton;
    private Button gps;

    private FirebaseAuth firebaseAuth;

    /**
     * The Database object.
     */
    DatabaseReference databaseIssue;

    /**
     * The Location manager.
     */
    LocationManager locationManager;

    /**
     * The Location listener.
     */
    LocationListener locationListener;

    /**
     * The Current latitude.
     */
    public double currentLatitude;
    /**
     * The Current longitude.
     */
    public double currentLongitude;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        }

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_log_it);

        databaseIssue = FirebaseDatabase.getInstance().getReference("Issue");
        firebaseAuth = FirebaseAuth.getInstance();

        issueTitle = findViewById(R.id.issueTitle);
        issueDescription = findViewById(R.id.issueDescription);
        issuePriority = findViewById(R.id.issuePriority);
        submitIssueButton = findViewById(R.id.submitIssueButton);


        submitIssueButton.setOnClickListener(this);

        //Location Manager code modified version of code found in Udemy course complete-android-n-developer-course section 6 by Rob Percival

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {


            @Override
            public void onLocationChanged(Location location) {

                Log.i("Location", location.toString());
            }
            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }
            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }


        };

        // If device is running SDK < 23
        if (Build.VERSION.SDK_INT < 23) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // ask for permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


            } else {
                // we have permission!
                //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

                locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000L,500.0f, locationListener);
                Location location = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                currentLatitude = location.getLatitude();
                currentLongitude = location.getLongitude();
                Log.i("We have permission",String.valueOf(currentLatitude));

            }

        }

    }

    /**
     * Location.
     */
    public void location() {
    }

    private void submitIssue() {
        String title = issueTitle.getText().toString().trim();
        String des = issueDescription.getText().toString().trim();
        String priority = issuePriority.getSelectedItem().toString();
        String issueID = databaseIssue.push().getKey();
        String timeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String createdBy = user.getEmail();
        String assignee = user.getEmail();
        String status = "open";
        String latitude = String.valueOf(currentLatitude);
        String longtitude = String.valueOf(currentLongitude);


        Log.d("Title ",title);
        Log.d("Description ",des);
        Log.d("Priority ",priority);
        Log.d("Created by",createdBy);
        Log.d("This is where I am",String.valueOf(latitude));


        if(title.equals("")){
            Toast.makeText(Log_itActivity.this,"Please enter a title",Toast.LENGTH_LONG).show();
        }
       else if(des.equals("")){
            Toast.makeText(Log_itActivity.this,"Please enter a description",Toast.LENGTH_LONG).show();
        }
        else if(des.equals("")){
            Toast.makeText(Log_itActivity.this,"Please select a status",Toast.LENGTH_LONG).show();
        }
        else {
            Issue issue = new Issue(issueID,title,des,priority,timeDate,createdBy,assignee,status,longtitude,latitude);
                databaseIssue.child(issueID).setValue(issue);
               Toast.makeText(Log_itActivity.this, "Issue logged ID: " + issueID.toString(), Toast.LENGTH_LONG).show();
                issueTitle.setText("");
                issueDescription.setText("");
        /*fdfd*/


        }

    }

    @Override
    public void onClick(View view) {
       if(view == submitIssueButton){
           submitIssue();
       }
        if(view == gps){
            location();
        }
    }
}