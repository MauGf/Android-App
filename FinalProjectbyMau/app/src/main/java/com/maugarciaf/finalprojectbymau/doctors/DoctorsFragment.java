package com.maugarciaf.finalprojectbymau.doctors;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.maugarciaf.finalprojectbymau.R;
import com.maugarciaf.finalprojectbymau.addeditdoctor.AddEditDoctorActivity;
import com.maugarciaf.finalprojectbymau.data.DoctorsContract;
import com.maugarciaf.finalprojectbymau.data.DoctorsDbHelper;
import com.maugarciaf.finalprojectbymau.doctordetail.DoctorDetailActivity;

public class DoctorsFragment extends Fragment {
    public static final int REQUEST_UPDATE_DELETE_DOCTOR = 2;

    private DoctorsDbHelper mDoctorsDbHelper;

    private ListView mDoctorsList;
    private DoctorsCursorAdapter mDoctorsAdapter;
    private FloatingActionButton mAddButton;


    public DoctorsFragment() {
        // Required empty public constructor
    }

    public static DoctorsFragment newInstance() {
        return new DoctorsFragment ();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lawyers, container, false);

        // Referencias UI
        mDoctorsList = (ListView) root.findViewById(R.id.lawyers_list);
        mDoctorsAdapter = new DoctorsCursorAdapter (getActivity(), null);
        mAddButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);

        // Setup
        mDoctorsList.setAdapter(mDoctorsAdapter);

        // Eventos
        mDoctorsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor currentItem = (Cursor) mDoctorsAdapter.getItem(i);
                String currentLawyerId = currentItem.getString(
                        currentItem.getColumnIndex(DoctorsContract.DoctorEntry.ID));

                showDetailScreen(currentLawyerId);
            }
        });
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddScreen();
            }
        });


        // getActivity().deleteDatabase(DoctorsDbHelper.DATABASE_NAME);

        // Instancia de helper
        mDoctorsDbHelper = new DoctorsDbHelper (getActivity());

        // Carga de datos
        loadLawyers();

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Activity.RESULT_OK == resultCode) {
            switch (requestCode) {
                case AddEditDoctorActivity.REQUEST_ADD_DOCTOR:
                    showSuccessfullSavedMessage();
                    loadLawyers();
                    break;
                case REQUEST_UPDATE_DELETE_DOCTOR:
                    Toast.makeText (getActivity (), "Se actualizo la Bd.", Toast.LENGTH_SHORT).show ();
                    loadLawyers();
                    break;
            }
        }
    }

    private void loadLawyers() {
        new LawyersLoadTask().execute();
    }

    private void showSuccessfullSavedMessage() {
        Toast.makeText(getActivity(),
                "Doctor guardado correctamente", Toast.LENGTH_SHORT).show();
    }

    private void showAddScreen() {
        Intent intent = new Intent(getActivity(), AddEditDoctorActivity.class);
        startActivityForResult(intent, AddEditDoctorActivity.REQUEST_ADD_DOCTOR);
    }

    private void showDetailScreen(String lawyerId) {
        Intent intent = new Intent(getActivity(), DoctorDetailActivity.class);
        intent.putExtra(DoctorsActivity.EXTRA_DOCTOR_ID, lawyerId);
        startActivityForResult(intent, REQUEST_UPDATE_DELETE_DOCTOR);
    }

    private class LawyersLoadTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mDoctorsDbHelper.getAllDoctors();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                mDoctorsAdapter.swapCursor(cursor);
            } else {
                // Mostrar empty state
                mDoctorsAdapter.swapCursor(null); // SOLUCION
            }
        }
    }

}
