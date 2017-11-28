package com.example.gracp.myapplication;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mMap;

    boolean mapType = true;

    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference myRef1;

    //List<Event> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        database = FirebaseDatabase.getInstance();
        final EditText searchText = findViewById(R.id.searchEdit);
        final ImageButton searchButton = findViewById(R.id.searchButton);
        searchButton.setEnabled(false);

        searchText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence)) {
                    searchButton.setEnabled(true);
                    searchButton.setImageResource(R.drawable.ic_search_black1_24dp);
                }
                else {
                    searchButton.setEnabled(false);
                    searchButton.setImageResource(R.drawable.ic_search_black_24dp);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Event event = new Event("description2","+375447533487","title2",53.1,43.1);
                mMap.clear();
                mMap.setInfoWindowAdapter(new CustomWindowAdapter(MapsActivity.this));
                String child = searchText.getText().toString();
                myRef = database.getReference("Cities").child(child);
//                myRef1 = database.getReference("Users");
//                myRef1.child(child).setValue(event);


                final Query query = myRef;
                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Event event = dataSnapshot.getValue(Event.class);
                        //events.add(event);
                        String name = event.getTitle();

                        String phone ="\n"+event.getPhone();
                        String description = event.getDescription();
                        LatLng coordinates = new LatLng(event.getV(), event.getV1());
                        mMap.addMarker(new MarkerOptions().position(coordinates).title(name).snippet(description+phone));

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

////                for(Event i: events){
////                    String name = i.getTitle();
////                    String description = i.getDescription();
////                    LatLng coordinats = new LatLng(i.getV(), i.getV1());
////                    mMap.addMarker(new MarkerOptions().position(coordinats).title(name));
////                }
//               // events.clear();

                //Event event = new Event("description2","+375447533487","title2",53.1,43.1);
                //myRef.push().setValue(event);

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_setting1:
               if (mapType)  {
                   mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                   mapType = false;
                   item.setIcon(R.drawable.ic_map_black_24dp);
               }
               else {
                   mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                   mapType = true;
                   item.setIcon(R.drawable.ic_map_black1_24dp);
               }

        }
        return super.onOptionsItemSelected(item);
}

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}
