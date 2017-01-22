package bike.pl.bikemap;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import bike.pl.bikemap.model.Network;

/**
 * Created by szymon on 19.01.2017.
 */

public class GMapFragment extends Fragment implements GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private static GoogleMap mMap;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        LatLng sydney = new LatLng(-33.852, 151.211);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    public static void updateMap(List<Network> nets) {
        for (Network net : nets) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(net.getLocation().getLatitude(), net.getLocation().getLongitude()))
                    .title(net.getName()));
        }
    }

    public static void updateView(LatLng latLng) {
        Log.i("MAP", "We can't find a bike without your permission");
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    /**
     * Called when the user clicks a marker.
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this.getActivity(),
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Log.i("GMapFragment", "Here");
    }

}
