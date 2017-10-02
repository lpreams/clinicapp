package net.carolinaphoenix.clinicappdemo;


import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class PatientFragment extends Fragment {


    public PatientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ArrayList<Patient> patientList = new ArrayList<>();
        ArrayList<String> patientStringList = new ArrayList<>();

        String patientsJSON = null;

        AsyncTask<String, Void, String> patientsJSONTask = new GetJSON().execute("patients");

        try {
            patientsJSON = patientsJSONTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ArrayList jsonList = new Gson().fromJson(patientsJSON, ArrayList.class);

        if (patientsJSON == null) {
            Log.d("JSON", "patientsJSON was null");
            patientStringList.add("Unable to connect");
        }else for (Object s : jsonList) {
            Patient p = new Gson().fromJson(s.toString(), Patient.class);
            Log.d("JSON", "added patient " + p);
            patientList.add(p);
            patientStringList.add(p.getFirst() + " " + p.getLast());
        }

        View v = inflater.inflate(R.layout.fragment_patient, container, false);
        ListView patientListView = (ListView) v.findViewById(R.id.patient_list);
        ArrayAdapter<String> patientAdapter = new ArrayAdapter<>(v.getContext(), android.R.layout.simple_list_item_1, patientStringList);
        patientListView.setAdapter(patientAdapter);

        return v;
    }

    private static class Patient {

        private final int id;
        private String fName;
        private String mName;
        private String lName;

        public Patient(int uid, String first, String mid, String last) {
            id = uid;
            fName = first;
            mName = mid;
            lName = last;
        }

        public int getId() {
            return id;
        }

        public String getFirst() {
            return fName;
        }

        public String getMiddle() {
            return mName;
        }

        public String getLast() {
            return lName;
        }

        public String toString() {
            return id + " " + fName + " " + mName + " " + lName;
        }
    }

}
