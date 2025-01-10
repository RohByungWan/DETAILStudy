package com.example.detailstudy;

import static android.content.ContentValues.TAG;

import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

        public class googleMapActivity extends AppCompatActivity implements OnMapReadyCallback {

            private GoogleMap mMap;
            private FragmentManager fragmentManager;
            private MapFragment mapFragment;
            //    private Marker currentMarker = null;
            LocationManager locationManager;
            LocationListener locationListener;

            private CameraUpdateFactory CamearUpdateFactory;

            protected void onCreate(Bundle savedInstanceStateState) {
                super.onCreate(savedInstanceStateState);
                setContentView(R.layout.activity_google_map);

                fragmentManager = getFragmentManager();
                mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);
                mapFragment.getMapAsync(this);

                locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                        double lat = location.getLatitude(); // 위도
                        double lng = location.getLongitude(); //경도

                        Log.i("AAA","위도 : "+ lat);
                        Log.i("AAA","경도 : "+ lng);
                        Log.i("AAA"," ");

                    }
                };
//                //권한 허용하는 코드
//
//                if(ActivityCompat.checkSelfPermission(googleMapActivity.this,
//                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
//                        ActivityCompat.checkSelfPermission(googleMapActivity.this,
//                                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//
//                    ActivityCompat.requestPermissions(googleMapActivity.this,
//                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
//                                    Manifest.permission.ACCESS_COARSE_LOCATION},
//                            100);
//                    return;
//                }


                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                if (mapFragment != null) {
                    mapFragment.getMapAsync(this);
                    
                    Log.d(TAG, "googleMapActivity - onCreate() called");

                    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.map), (v, insets) -> {
                        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                        return insets;
                    });

                }

            }

            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                mMap = googleMap;

                LatLng KANGWANDO = new LatLng(37.860800, 127.73042);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.title("DETAIL#");
//        markerOptions.snippet("디테일샵");
                markerOptions.snippet("춘천시 역전옛길20 (퇴계동 375-14)");
                markerOptions.position(KANGWANDO);
                googleMap.addMarker(markerOptions);

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(KANGWANDO, 16));

                Log.d(TAG, "TestMap - onMapReady() Maps");
            }

    }
