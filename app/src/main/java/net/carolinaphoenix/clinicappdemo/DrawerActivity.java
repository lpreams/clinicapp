package net.carolinaphoenix.clinicappdemo;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DrawerActivity extends AppCompatActivity {

    private ArrayList<String> menuList;
    private DrawerLayout drawer;
    private ListView listView;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);

        menuList = new ArrayList<>();
        menuList.add("Patients");
        menuList.add("Studies");
        menuList.add("Financial");
        menuList.add("Administration");
        menuList.add("Log out");

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.left_drawer);
        listView.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_textview, menuList));
        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
        drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close){
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.addDrawerListener(drawerToggle);
        //required for navigation drawer button
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getFragmentManager().beginTransaction().replace(R.id.content_frame, new PatientFragment()).commit();
        setTitle(menuList.get(0));
    }

    /**
     * required for navigation drawer button
     * @param savedInstanceState state
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    /**
     * Required for navigation drawer button
     * @param newConfig new config
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Required for navigation drawer button
     * @param item selected item
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        /*
        if (drawerToggle.onOptionsItemSelected(item)) return true;
        return super.onOptionsItemSelected(item);
        */
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position

        Fragment fragment;

        switch (position) {
            case 0 : // patients
                fragment = new PatientFragment();
                break;

            case 1 : // studies
                fragment = new StudyFragment();
                break;

            case 2: // financial
                fragment = new FinanceFragment();
                break;

            case 3: // admin
                fragment = new AdminFragment();
                break;

            case 4: // log out
                startActivity(new Intent(this, LogoutActivity.class));
                finish();
            default:
                return;
        }

        // Insert the fragment by replacing any existing fragment
        getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();

        // Highlight the selected item, update the title, and close the drawer
        listView.setItemChecked(position, true);
        setTitle(menuList.get(position));
        drawer.closeDrawer(listView);
    }
}
