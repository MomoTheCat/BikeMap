package bike.pl.bikemap.map;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bike.pl.bikemap.R;
import bike.pl.bikemap.model.Network;
import bike.pl.bikemap.model.Stations;
import bike.pl.bikemap.network.MapProcessor;

/**
 * Created by szymon on 19.01.2017.
 */

public class GMapFragment extends Fragment implements
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback,
        GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private static GoogleMap mMap;
    private final int MY_LOCATION_REQUEST_CODE = 90;

    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        requestLocation();
        mMap.setInfoWindowAdapter(new InfoWindows(getActivity()));
    }


    public void requestLocation() {
        Log.d("Szymon", "requestLocation");
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            Log.d("Szymon", "mMap.setMyLocationEnabled(true)");
        } else {
            requestPermission();
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_LOCATION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_LOCATION_REQUEST_CODE:
                if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        mMap.setMyLocationEnabled(true);
                        updateView();
                        Log.i("Szymon", "onRequestPermisssionResult");
                        obtainLocation();
                    } catch (SecurityException e) {
                        e.getMessage();
                    }
                } else {
                    Toast.makeText(getActivity(), R.string.location_denied, Toast.LENGTH_LONG).show();
                }
                return;
        }
    }

    public void showLocationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.cannot_gps_title)
                .setMessage(R.string.cannot_gps_message)
                .setCancelable(false)
                .setPositiveButton(R.string.retry, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        obtainLocation();
                    }
                })
                .setNegativeButton(R.string.settings, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void obtainLocation() {
        Log.i("Szymon", "obtainLocation");
        if (!isLocationEnabled(getActivity()))
            showLocationDialog();

        String href = checkNearestStations(MapProcessor.networks);
        if (!("".equals(href)))
            new MapProcessor(getActivity()).prepareStationsMap(href);
    }

    public static boolean isLocationEnabled(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int locationMode;
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(),
                        Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        } else {
            String locationProviders = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

            return !TextUtils.isEmpty(locationProviders);
        }
    }

    public static void updateMapWithStations(List<Stations> stations) {
        if (stations != null && stations.size() > 0) {
            Log.i("Szymon", stations.get(0).getName());
            for (Stations.StationsBean station : stations.get(0).getStations()) {
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(station.getLatitude(), station.getLongitude()))
                        .title(station.getName())
                        .snippet("Free bikes: " + station.getFree_bikes() +
                                "\nFree slots: " + station.getEmpty_slots()));
            }
        }
    }

    protected String checkNearestStations(List<Network> nets) {
        if (mLastLocation == null) Log.i("Szymon", "Location is  null");
        if (nets == null) Log.i("Szymon", "nets is null");

        if (mLastLocation != null && nets != null) {
            float[] results = new float[nets.size()];
            List<Float> distance = new ArrayList<>();

            for (Network net : nets) {
                Location.distanceBetween(
                        mLastLocation.getLatitude(),
                        mLastLocation.getLongitude(),
                        net.getLocation().getLatitude(),
                        net.getLocation().getLongitude(),
                        results);
                distance.add(results[0]);
            }
            if (distance.size() > 0) {
                int index = distance.indexOf(Collections.min(distance));
                return nets.get(index).getHref();
            }
        }
        return "";
    }

    public void updateView() {
        if (mLastLocation != null & mMap != null) {
            LatLng latLng = new LatLng(mLastLocation.getLatitude(),
                    mLastLocation.getLongitude());
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng)
                    .zoom(16)
                    .build();
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
        }
    }

    public static void updateMapWithNetworks(List<Network> nets) {
        for (Network net : nets) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(net.getLocation().getLatitude(), net.getLocation().getLongitude()))
                    .title(net.getName())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        updateView();
        obtainLocation();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("Szymon", "onConnectionFailed:" + connectionResult.getErrorCode() + "," + connectionResult.getErrorMessage());
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
