package bike.pl.bikemap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import bike.pl.bikemap.map.GMapFragment;
import bike.pl.bikemap.network.MapProcessorImpl;

import static bike.pl.bikemap.R.id.container;

public class MainActivity extends AppCompatActivity {

    private final String FIRST_OPEN = "firstOpen";
    public static final String MAP_FRAGMENT_NAME = "MAP";

    private final String TAG = this.getClass().getSimpleName();
    private boolean exit = false;
    private boolean firstOpen = true;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupNavigationDrawer(toolbar, savedInstanceState);
        setRecyclerView();

        getFragmentManager().beginTransaction()
                .replace(container, new GMapFragment())
                .commit();
    }

    private void setupNavigationDrawer(Toolbar toolbar, Bundle savedInstanceState) {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (firstOpen) {
            drawer.openDrawer(Gravity.LEFT);
            firstOpen = false;
        }
    }

    public void setRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);

        recyclerView.setAdapter(AdapterView.newInstance());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        findViewById(R.id.refresh_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(container, new GMapFragment(), MAP_FRAGMENT_NAME)
                        .commit();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        serverRequest();
    }

    private void serverRequest() {
        if (isConnectingToInternet(getApplicationContext())) {
            MapProcessorImpl mapProcessor = new MapProcessorImpl(this);
            mapProcessor.prepareNetworkMap();
        } else {
            showNetworkDialog();
        }
    }

    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }

    private void showNetworkDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.cannot_connect_title)
                .setMessage(R.string.cannot_connect_message)
                .setCancelable(false)
                .setPositiveButton(R.string.retry, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        serverRequest();
                    }
                })
                .setNegativeButton(R.string.settings, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            closeAfterDoubleClick();
        }
    }

    private void closeAfterDoubleClick() {
        if (exit) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, R.string.press_back_exit, Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3000);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle icicle) {
        super.onSaveInstanceState(icicle);
        icicle.putBoolean(FIRST_OPEN, firstOpen);
    }

}
