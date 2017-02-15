package bike.pl.bikemap;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by szymon on 15.02.2017.
 */

public class AdapterView extends RecyclerView.Adapter<AdapterView.MyViewHolder> {

    static AdapterView adapterView;
    List<String> ls = new ArrayList<>();
    Context context;


    private AdapterView(Context context) {
        this.context = context;
        ls.add("SD");
        ls.add("SsD");
    }

    public static AdapterView newInstance(Context context) {
        if (adapterView == null) {
            return new AdapterView(context);
        }
        return adapterView;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        Log.d("Szymon", "onCreateViewHolder");
        return (new MyViewHolder(view));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d("Szymon", "setText");
        holder.textView.setText(ls.get(position));
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.station_name);
        }
    }
}
