package com.gashe.myappmap;

import android.location.Geocoder;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.location.Address;

import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMapClickListener(this);

    }

    @Override
    public void onMapClick(LatLng latLng) {

        MarkerOptions marker = new MarkerOptions();
        marker.position(latLng);
        marker.title("HE TOCADO AQUÍ");
        marker.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_round));


        googleMap.addMarker(marker);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

        /*
        CameraPosition camPos = new CameraPosition.Builder()
                .target(latLng)   //Centramos el mapa en Madrid
                .zoom(19)         //Establecemos el zoom en 19
                .bearing(45)      //Establecemos la orientación con el noreste arriba
                .tilt(70)         //Bajamos el punto de vista de la cámara 70 grados
                .build();

        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);

        googleMap.animateCamera(camUpd3);*/

        Location location = new Location("prueba");
        location.setLatitude(latLng.latitude);
        location.setLongitude(latLng.longitude);

        traduceDir(location);

    }

    private void traduceDir(Location location){
        Geocoder geocoder = null;
        Address address = null;
        List<Address> addressList = null;

        if(Geocoder.isPresent()){
            Log.d(getClass().getCanonicalName(), "GEOCODER OK");
            geocoder = new Geocoder(this);

            try{
                addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if(addressList.size() >= 1){
                    address = addressList.get(0);
                    Log.d(getClass().getCanonicalName(), "DIRECCIÓN OBTENIDA: " +address);
                }
            }catch (IOException e){
                Log.e(getClass().getCanonicalName(), "ERROR: ", e);
            }

        }

    }


}
