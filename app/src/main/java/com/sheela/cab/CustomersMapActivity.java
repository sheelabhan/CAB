package com.sheela.cab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
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
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomersMapActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {


    GoogleApiClient googleApiClient;
    Location lastLocation;
    LocationRequest locationRequest;
    private GoogleMap mMap;
    private Button CustomerLogoutButton, SettingsButton;
    private Button CallCabButton;
    private String customerID;
    private LatLng CustomerPickupLocation;
    private int radius = 1;
    private Boolean driverFound = false, requestType = false;
    private String driverFoundID;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    Marker DriverMarker, PickUpMarker;
    private DatabaseReference CustomerDatabaseRef;
    private DatabaseReference DriverAvailableRef;
    private DatabaseReference DriversRef;
    private DatabaseReference DriverlocationRef;
    GeoQuery geoQuery;
    private ValueEventListener DriverLocationRefListner;
    private TextView txtName, txtPhone, txtCarName;
    private CircleImageView profilePic;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_map);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        customerID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        CustomerDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Customers Request");
        DriverAvailableRef = FirebaseDatabase.getInstance().getReference().child("Drivers Available");
        DriverlocationRef = FirebaseDatabase.getInstance().getReference().child("Drivers Working");
        SettingsButton = findViewById(R.id.customer_setting_btn);
        CustomerLogoutButton = findViewById(R.id.customer_logout_btn);
        CallCabButton = findViewById(R.id.customer_callcab_btn);
        txtName = findViewById(R.id.name_driver);
        txtPhone = findViewById(R.id.phone_driver);
        profilePic = findViewById(R.id.profile_image_driver);
        txtCarName = findViewById(R.id.car_name__driver);

        relativeLayout = findViewById(R.id.rell);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        SettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomersMapActivity.this, SettingsActivity.class);
                intent.putExtra("type", "Customers");

                startActivity(intent);
            }
        });
        CustomerLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                LogoutCustomer();

            }
        });
        CallCabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (requestType) {
                    requestType = false;
                    geoQuery.removeAllListeners();
                    DriverlocationRef.removeEventListener(DriverLocationRefListner);
                    if (driverFound != null) {
                        DriversRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers")
                                .child(driverFoundID);
                        DriversRef.setValue(true);
                        driverFoundID = null;
                    }
                    driverFound = false;
                    radius = 1;
                    String customerID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    GeoFire geoFire = new GeoFire(CustomerDatabaseRef);
                    geoFire.removeLocation(customerID, new GeoFire.CompletionListener() {
                        @Override
                        public void onComplete(String key, DatabaseError error) {

                        }
                    });
                    //change
                    if (PickUpMarker != null) {
                        PickUpMarker.remove();
                    }
                    CallCabButton.setText("Call a cab");
                    relativeLayout.setVisibility(View.GONE);
                } else {
                    requestType = true;
                    String customerID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    GeoFire geoFire = new GeoFire(CustomerDatabaseRef);
                    geoFire.setLocation(customerID, new GeoLocation(lastLocation.getLatitude(),
                            lastLocation.getLongitude()), new GeoFire.CompletionListener() {
                        @Override
                        public void onComplete(String key, DatabaseError error) {

                            CustomerPickupLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(CustomerPickupLocation).title("Pickup Customer from here"));
                            CallCabButton.setText("Getting Your Driver");
                            GetClosestDriverCab();
                        }
                    });
                }
//                GeoFire geoFire = new GeoFire(CustomerDatabaseRef);


            }
        });
    }

    private void GetClosestDriverCab() {
        GeoFire geoFire = new GeoFire(DriverAvailableRef);
        GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation(CustomerPickupLocation.latitude,
                CustomerPickupLocation.longitude), radius);
        geoQuery.removeAllListeners();

        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                if (!driverFound && requestType) {
                    driverFound = true;
                    driverFoundID = key;
                    DriversRef = FirebaseDatabase.getInstance().getReference().child("Users")
                            .child("Drivers").child(driverFoundID);
                    HashMap driverMap = new HashMap();
                    driverMap.put("CustomerRideId", customerID);
                    DriversRef.updateChildren(driverMap);
                    GettingDriverLocation();
                    CallCabButton.setText("Looking for driver location");
                }
            }

            @Override
            public void onKeyExited(String key) {

            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {

            }

            @Override
            public void onGeoQueryReady() {
                if (!driverFound) {
                    radius = radius + 1;
                    GetClosestDriverCab();
                }
            }

            @Override
            public void onGeoQueryError(DatabaseError error) {

            }
        });

    }

    private void GettingDriverLocation() {
        DriverLocationRefListner = DriverlocationRef.child(driverFoundID).child("l").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && requestType) {
                    List<Object> driverLocationMap = (List<Object>) dataSnapshot.getValue();
                    double LocationLat = 0;
                    double LocationLng = 0;
                    CallCabButton.setText("Driver Found");
                    relativeLayout.setVisibility(View.VISIBLE);
                    getAssignedDriverInformation();
                    if (driverLocationMap.get(0) != null) {
                        LocationLat = Double.parseDouble(driverLocationMap.get(0).toString());
                    }
                    if (driverLocationMap.get(1) != null) {
                        LocationLng = Double.parseDouble(driverLocationMap.get(1).toString());
                    }
                    LatLng DriverLatlng = new LatLng(LocationLat, LocationLng);
                    if (DriverMarker != null) {
                        DriverMarker.remove();
                    }
                    Location location1 = new Location("");
                    location1.setLatitude(CustomerPickupLocation.latitude);
                    location1.setLatitude(CustomerPickupLocation.longitude);


                    Location location2 = new Location("");
                    location2.setLatitude(DriverLatlng.latitude);
                    location2.setLatitude(DriverLatlng.longitude);
                    float Distance = location1.distanceTo(location2);
                    if (Distance < 90) {
                        CallCabButton.setText("Driver's Reached");
                    } else {
                        CallCabButton.setText("Driver Found" + String.valueOf(Distance));
                    }


                    DriverMarker = mMap.addMarker(new MarkerOptions().position(DriverLatlng).title("Your Driver is Here"));

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

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        buildGoogleApiClient();
        mMap.setMyLocationEnabled(true);
    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void LogoutCustomer() {
        Intent intent = new Intent(CustomersMapActivity.this, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
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
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));


    }

    private void getAssignedDriverInformation() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("Users").child("Drivers").child(driverFoundID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    String name = dataSnapshot.child("name").getValue().toString();
                    String phone = dataSnapshot.child("phone").getValue().toString();
                    String car = dataSnapshot.child("car").getValue().toString();
                    txtName.setText(name);
                    txtPhone.setText(phone);
                   txtCarName.setText(car);

                    if (dataSnapshot.hasChild("image")) {
                        String image = dataSnapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(profilePic);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}