package com.sheela.cab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class DriversMapActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {


    private GoogleMap mMap;
    GoogleApiClient googleApiClient;
    Location lastLocation;
    LocationRequest locationRequest;
    private Button LogoutDriverButton;
    private Button SettingsDriverButton;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Boolean currentLogoutDriverStatus = false;
    private DatabaseReference AssignedCustomerRef, AssignedCustomerPickUpRef;
    private String driverID, customerID="";
    Marker PickUpMarker;
   private ValueEventListener  AssignedCustomerPickUpRefListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drivers_map);
        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        driverID= mAuth.getCurrentUser().getUid();
        LogoutDriverButton=findViewById(R.id.driver_logout_btn);
        SettingsDriverButton=findViewById(R.id.driver_setting_btn);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        SettingsDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DriversMapActivity.this, SettingsActivity.class);
                intent.putExtra("type", "Drivers");

                startActivity(intent);
            }
        });
        LogoutDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentLogoutDriverStatus= true;
                DisconnectDriver();
                mAuth.signOut();
                LogoutDriver();

            }
        });

      GetAssignedCustomerRequest();
    }

    private void GetAssignedCustomerRequest() {
    AssignedCustomerRef= FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(driverID)
            .child("CustomerRideId");
    AssignedCustomerRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                customerID = dataSnapshot.getValue().toString();
                GetAssignedCustomerPickUpLocation();
            }
            else{
                customerID="";
                if(PickUpMarker !=null){
                    PickUpMarker.remove();
                }
                if(AssignedCustomerPickUpRefListner !=null){
                    AssignedCustomerPickUpRef.removeEventListener(AssignedCustomerPickUpRefListner);
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

    }

    private void GetAssignedCustomerPickUpLocation() {
        AssignedCustomerPickUpRef = FirebaseDatabase.getInstance().getReference().child("Customer Requests")
                .child(customerID).child("l");
       AssignedCustomerPickUpRefListner= AssignedCustomerPickUpRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    List<Object> customerlocationMap= (List<Object>) dataSnapshot.getValue();
                    double LocationLat=0;
                    double LocationLng=0;
                    if(customerlocationMap.get(0) != null) {
                        LocationLat = Double.parseDouble(customerlocationMap.get(0).toString());
                    }
                    if(customerlocationMap.get(1) != null) {
                        LocationLng = Double.parseDouble(customerlocationMap.get(1).toString());
                    }
                    LatLng DriverLatlng= new LatLng(LocationLat,LocationLng);
                    mMap.addMarker(new MarkerOptions().position(DriverLatlng).title("PickUp Location"));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        buildGoogleApiClient();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);


    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(locationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest,
                this);
    }
    @Override
    public void onLocationChanged(@NonNull Location location)
    {
   if(getApplicationContext() != null) {
       lastLocation = location;
       LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
       mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
       mMap.animateCamera(CameraUpdateFactory.zoomTo(13));
       String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
       DatabaseReference DriverAvailabiltyRef = FirebaseDatabase.getInstance().getReference().child("Drivers Availabile");
       GeoFire geoFireAvailability = new GeoFire(DriverAvailabiltyRef);

       DatabaseReference DriverWorkingRef = FirebaseDatabase.getInstance().getReference().child("Drivers Working");
       GeoFire geoFireWorking = new GeoFire(DriverWorkingRef);
       switch (customerID) {
           case "":
               geoFireWorking.removeLocation(userID, new GeoFire.CompletionListener() {
                   @Override
                   public void onComplete(String key, DatabaseError error) {

                   }
               });
               geoFireAvailability.setLocation(userID,
                       new GeoLocation(location.getLatitude(), location.getLongitude()),
                       new GeoFire.CompletionListener() {
                           @Override
                           public void onComplete(String key, DatabaseError error) {


                           }
                       });
               break;
           default:
               geoFireAvailability.removeLocation(userID, new GeoFire.CompletionListener() {
                   @Override
                   public void onComplete(String key, DatabaseError error) {

                   }
               });
               geoFireWorking.setLocation(userID,
                       new GeoLocation(location.getLatitude(), location.getLongitude()),
                       new GeoFire.CompletionListener() {
                           @Override
                           public void onComplete(String key, DatabaseError error) {


                           }
                       });
               break;
       }
   }

        }




    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this).
                addConnectionCallbacks(this).addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(!currentLogoutDriverStatus){
            DisconnectDriver();
        }

    }






    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void DisconnectDriver() {
        String userID =FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference DriverAvailabiltyRef= FirebaseDatabase.getInstance().getReference().child("Drivers Availabile");
        GeoFire geoFire= new GeoFire(DriverAvailabiltyRef);
      geoFire.removeLocation(userID, new GeoFire.CompletionListener() {
          @Override
          public void onComplete(String key, DatabaseError error) {

          }
      });
      LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);
    }
    private void LogoutDriver() {
        Intent intent= new Intent(DriversMapActivity.this, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}