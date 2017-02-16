package bike.pl.bikemap;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bike.pl.bikemap.model.Stations;
import bike.pl.bikemap.network.MapProcessor;

/**
 * Created by szymon on 15.02.2017.
 */

public class AdapterView extends RecyclerView.Adapter<AdapterView.MyViewHolder> {

    static AdapterView adapterView;

    private AdapterView() {
    }

    public static AdapterView newInstance() {
        if (adapterView == null)
            adapterView = new AdapterView();
        return adapterView;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (MapProcessor.stations != null & MapProcessor.stations.size() > 0) {
            Stations.StationsBean sb = MapProcessor.stations.get(0).getStations().get(position);
            holder.stationName.setText(sb.getName());
            holder.bikesAvailability.setText("Bikes: " + sb.getFree_bikes()
                    + ",  slots: " + sb.getEmpty_slots());
        }
    }

    @Override
    public int getItemCount() {
        if (MapProcessor.stations != null & MapProcessor.stations.size() > 0)
            return MapProcessor.stations.get(0).getStations().size();
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView stationName;
        TextView bikesAvailability;

        public MyViewHolder(View itemView) {
            super(itemView);
            stationName = (TextView) itemView.findViewById(R.id.station_name);
            bikesAvailability = (TextView) itemView.findViewById(R.id.free_bikes);
        }
    }
}
