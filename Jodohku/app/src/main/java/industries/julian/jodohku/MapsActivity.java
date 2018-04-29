package industries.julian.jodohku;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.Manifest;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.firebase.geofire.core.GeoHash;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
<<<<<<< Updated upstream
=======
import com.google.android.gms.maps.model.Marker;
>>>>>>> Stashed changes
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

<<<<<<< Updated upstream
import java.util.Arrays;
import java.util.HashMap;
=======
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
>>>>>>> Stashed changes
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    private FirebaseAuth mAuth;
    private Button btnCari;
    LocationRequest mLocationRequest;
    private FusedLocationProviderClient mFusedLocationClient;
    private  LocationCallback mLocationCallback;
    private LatLng pickupLocation;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        }

        mAuth = FirebaseAuth.getInstance();

        btnCari = (Button)findViewById(R.id.cariUser);

<<<<<<< Updated upstream
        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = mAuth.getCurrentUser().getUid();
                DatabaseReference cari = FirebaseDatabase.getInstance().getReference().child("pencarian");
                GeoFire geoFire = new GeoFire(cari);
                geoFire.setLocation(userId, new GeoLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude()));
                pickupLocation = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                mMap.addMarker(new MarkerOptions().position(pickupLocation).title("Here"));
            }
        });
=======

>>>>>>> Stashed changes

    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                ) {//Can add more as per requirement

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
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

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    @Override
    public void onLocationChanged(Location location) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mLastLocation = location;
        final LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18));
        String userId = mAuth.getCurrentUser().getUid();
<<<<<<< Updated upstream
        DatabaseReference users = FirebaseDatabase.getInstance().getReference().child("usersAvailable");
        GeoFire geoFire = new GeoFire(users);
        geoFire.setLocation(userId, new GeoLocation(location.getLatitude(), location.getLongitude()));

        //GeoHash geoHash = new GeoHash(new GeoLocation(location.getLatitude(), location.getLongitude()));
        Map newGeo = new HashMap<>();
        //newGeo.put("g", geoHash.getGeoHashString());
        //newGeo.put("l", Arrays.asList(location.getLatitude(), location.getLongitude()));
        users.updateChildren(newGeo);

=======
        final DatabaseReference users = FirebaseDatabase.getInstance().getReference().child("usersAvailable");
        GeoFire geoFire = new GeoFire(users);
        geoFire.setLocation(userId, new GeoLocation(location.getLatitude(), location.getLongitude()));

        //GeoHash geoHash = new GeoHash(new GeoLocation(location.getLatitude(), location.getLongitude()));
        Map newGeo = new HashMap<>();
        //newGeo.put("g", geoHash.getGeoHashString());
        //newGeo.put("l", Arrays.asList(location.getLatitude(), location.getLongitude()));
        users.updateChildren(newGeo);

        final List<Marker> markers = new ArrayList<Marker>();
        GeoQuery q = geoFire.queryAtLocation(new GeoLocation(location.getLatitude(), location.getLongitude()), 10000);
        //GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation(location.getLongitude(), location.getLatitude()), 10000);
        q.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                for(Marker markerIt : markers){
                    if(markerIt.getTag().equals(key))
                        return;
                }
                Marker mUserMarker = mMap.addMarker(new MarkerOptions().position(latLng).title(key).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_foreground)));
                mUserMarker.setTag(key);

                markers.add(mUserMarker);
            }

            @Override
            public void onKeyExited(String key) {
                for(Marker markerIt : markers){
                    if(markerIt.getTag().equals(key)){
                        markerIt.remove();
                    }
                }
            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {
                for(Marker markerIt : markers){
                    if(markerIt.getTag().equals(key)){
                        markerIt.setPosition(new LatLng(location.latitude, location.longitude));
                    }
                }
            }

            @Override
            public void onGeoQueryReady() {

            }

            @Override
            public void onGeoQueryError(DatabaseError error) {

            }
        });
>>>>>>> Stashed changes
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        // Retrieve the user’s last known location//
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            onLocationChanged(location);
                        }
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {

                // If the request is cancelled, the result array will be empty (0)//
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // If the user has granted your permission request, then your app can now perform all its
                    // location-related tasks, including displaying the user’s location on the map//
                    if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    // If the user has denied your permission request, then at this point you may want to
                    // disable any functionality that depends on this permission//
                }
                return;
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void onStop(){
        super.onStop();
        String userId = mAuth.getCurrentUser().getUid();
        DatabaseReference users = FirebaseDatabase.getInstance().getReference().child("usersAvailable");
        GeoFire geoFire = new GeoFire(users);
        geoFire.removeLocation(userId);

        DatabaseReference cari = FirebaseDatabase.getInstance().getReference().child("pencarian");
        GeoFire geoFire2 = new GeoFire(cari);
        geoFire2.removeLocation(userId);
    }

}
