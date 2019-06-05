package com.maugarciaf.finalprojectbymau.doctordetail;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.maugarciaf.finalprojectbymau.R;
import com.maugarciaf.finalprojectbymau.addeditdoctor.AddEditDoctorActivity;
import com.maugarciaf.finalprojectbymau.data.DoctorsDbHelper;
import com.maugarciaf.finalprojectbymau.model.Doctors;
import com.maugarciaf.finalprojectbymau.doctors.DoctorsActivity;
import com.maugarciaf.finalprojectbymau.doctors.DoctorsFragment;

import java.util.Objects;

public class DoctorDetailFragment extends Fragment {
    private static final String ARG_DOCTOR_ID = "lawyerId";

    private String mDoctorId;

    private CollapsingToolbarLayout mCollapsingView;
    private ImageView mAvatar;
    private TextView mPhoneNumber;
    private TextView mSpecialty;
    private TextView mBio;
    private TextView mDirection;


    private DoctorsDbHelper mDoctorsDbHelper;


    public DoctorDetailFragment() {
        // Required empty public constructor
    }

    public static DoctorDetailFragment newInstance(String lawyerId) {
        DoctorDetailFragment fragment = new DoctorDetailFragment ();
        Bundle args = new Bundle();
        args.putString(ARG_DOCTOR_ID, lawyerId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mDoctorId = getArguments().getString(ARG_DOCTOR_ID);
        }

        setHasOptionsMenu(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lawyer_detail, container, false);
        mCollapsingView = (CollapsingToolbarLayout) Objects.requireNonNull (getActivity ()).findViewById(R.id.toolbar_layout);
        mAvatar = (ImageView) getActivity().findViewById(R.id.iv_avatar);
        mPhoneNumber = (TextView) root.findViewById(R.id.tv_phone_number);
        mSpecialty = (TextView) root.findViewById(R.id.tv_specialty);
        mBio = (TextView) root.findViewById(R.id.tv_bio);
        mDirection = (TextView) root.findViewById (R.id.tv_direction) ;

        mDoctorsDbHelper = new DoctorsDbHelper (getActivity());

        loadLawyer();

        return root;
    }

    private void loadLawyer() {
        new GetDoctorByIdTask().execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                showEditScreen();
                break;
            case R.id.action_delete:
                new DeleteDoctorTask().execute();
                Toast.makeText(getActivity(),
                        getResources ().getString(R.string.doctor_deleted), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DoctorsFragment.REQUEST_UPDATE_DELETE_DOCTOR) {
            if (resultCode == Activity.RESULT_OK) {
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
            }
        }
    }

    private void showLawyer(Doctors doctors) {
        mCollapsingView.setTitle(doctors.getName());
        Glide.with(this)
                .load(Uri.parse("file:///android_asset/" + doctors.getAvatarUri()))
                .centerCrop()
                .into(mAvatar);
        mPhoneNumber.setText(doctors.getPhoneNumber());
        mSpecialty.setText(doctors.getSpecialty());
        mBio.setText(doctors.getBio());
        mDirection.setText(doctors.getDirection());
    }

    private void showEditScreen() {
        Intent intent = new Intent (getActivity(), AddEditDoctorActivity.class);
        intent.putExtra(DoctorsActivity.EXTRA_DOCTOR_ID, mDoctorId);
        startActivityForResult(intent, DoctorsFragment.REQUEST_UPDATE_DELETE_DOCTOR);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showDoctorsScreen(boolean requery) {
        if (!requery) {
            showDeleteError();
        }
        Objects.requireNonNull (getActivity ()).setResult(requery ? Activity.RESULT_OK : Activity.RESULT_CANCELED);
        getActivity().finish();
    }

    private void showLoadError() {
        Toast.makeText(getActivity(),
                "Error al cargar información", Toast.LENGTH_SHORT).show();
    }

    private void showDeleteError() {
        Toast.makeText(getActivity(),
                "Error al eliminar Doctor", Toast.LENGTH_SHORT).show();
    }

    private class GetDoctorByIdTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mDoctorsDbHelper.getDoctorById (mDoctorId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToLast()) {
                showLawyer(new Doctors (cursor));
            } else {
                showLoadError();
            }
        }

    }

    private class DeleteDoctorTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {

            return mDoctorsDbHelper.deleteDoctor(mDoctorId);
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(Integer integer) {
            showDoctorsScreen(integer > 0);
        }

    }

}
