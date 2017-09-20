package net.carolinaphoenix.clinicappdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DrawerActivity extends AppCompatActivity {

    private ArrayList<String> menuList;
    private DrawerLayout drawer;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        menuList = new ArrayList<>();
        menuList.add("Patients");
        menuList.add("Studies");
        menuList.add("Financial");
        menuList.add("Administration");
        menuList.add("Log out");

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.left_drawer);
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_textview, menuList));
        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
        getFragmentManager().beginTransaction().replace(R.id.content_frame, new PatientFragment()).commit();
    }

    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position

        Fragment fragment = null;

        switch (position) {
            case 0 : // patients
                fragment = new PatientFragment();
                break;

            case 1 : // studies
            default:
                fragment = new StudyFragment();
        }

        // Insert the fragment by replacing any existing fragment
        getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();

        // Highlight the selected item, update the title, and close the drawer
        listView.setItemChecked(position, true);
        setTitle(menuList.get(position));
        drawer.closeDrawer(listView);
    }
}
