package bike.pl.bikemap;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = this.getClass().getSimpleName();
    private Boolean exit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Navigation Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new GMapFragment())
                .commit();
    }


    @Override
    protected void onStart() {
        super.onStart();
        serverRequest();
    }

    private void serverRequest() {
        if (isConnectingToInternet(getApplicationContext())) {
            ConnectSingleton connect = ConnectSingleton.getInstnce(this);
            connect.sendRequest();
        } else {
            showDialog();
        }
    }

    public static boolean isConnectingToInternet(Context context) {

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cannot connect to the Internet")
                .setMessage("This one's your fault, not ours. Check your settings and retry.")
                .setCancelable(false)
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (isConnectingToInternet(getApplicationContext())) {
                            ConnectSingleton connect = ConnectSingleton.getInstnce(getApplicationContext());
                            connect.sendRequest();
                        } else {
                            showDialog();
                        }
                    }
                })
                .setNegativeButton("Connect", new DialogInterface.OnClickListener() {
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager fm = getFragmentManager();

        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            fm.beginTransaction().replace(R.id.container, new GMapFragment()).commit();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_send) {
            ConnectSingleton connect = ConnectSingleton.getInstnce(this);
            connect.sendRequest();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
            Toast.makeText(this, R.string.pressBackToExit, Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }

}
