package com.maugarciaf.finalprojectbymau.addeditdoctor;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.maugarciaf.finalprojectbymau.R;
import com.maugarciaf.finalprojectbymau.model.Doctors;
import com.maugarciaf.finalprojectbymau.data.LawyersDbHelper;

public class AddEditLawyerFragment extends Fragment {
    private static final String ARG_LAWYER_ID = "arg_lawyer_id";
    private String mAvatarUri;
    private String mLawyerId;

    private LawyersDbHelper mLawyersDbHelper;

    private FloatingActionButton mSaveButton;
    private TextInputEditText mNameField;
    private TextInputEditText mPhoneNumberField;
    private TextInputEditText mSpecialtyField;
    private TextInputEditText mBioField;
    private TextInputEditText mDirField;
    private TextInputLayout mNameLabel;
    private TextInputLayout mPhoneNumberLabel;
    private TextInputLayout mSpecialtyLabel;
    private TextInputLayout mBioLabel;
    private TextInputLayout mDirLabel;


    public AddEditLawyerFragment() {
        // Required empty public constructor
    }

    public static AddEditLawyerFragment newInstance(String lawyerId) {
        AddEditLawyerFragment fragment = new AddEditLawyerFragment ();
        Bundle args = new Bundle ();
        args.putString (ARG_LAWYER_ID, lawyerId);
        fragment.setArguments (args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        if (getArguments () != null) {
            mLawyerId = getArguments ().getString (ARG_LAWYER_ID);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate (R.layout.fragment_add_edit_lawyer, container, false);

        // Referencias UI
        mSaveButton = (FloatingActionButton) getActivity ().findViewById (R.id.fab);
        mNameField = (TextInputEditText) root.findViewById (R.id.et_name);
        mPhoneNumberField = (TextInputEditText) root.findViewById (R.id.et_phone_number);
        mSpecialtyField = (TextInputEditText) root.findViewById (R.id.et_specialty);
        mBioField = (TextInputEditText) root.findViewById (R.id.et_bio);
        mDirField = (TextInputEditText) root.findViewById (R.id.et_direction);
        mNameLabel = (TextInputLayout) root.findViewById (R.id.til_name);
        mPhoneNumberLabel = (TextInputLayout) root.findViewById (R.id.til_phone_number);
        mSpecialtyLabel = (TextInputLayout) root.findViewById (R.id.til_specialty);
        mBioLabel = (TextInputLayout) root.findViewById (R.id.til_bio);
        mDirLabel = (TextInputLayout) root.findViewById (R.id.til_direction);

        // Eventos
        mSaveButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                addEditLawyer ();
            }
        });

        mLawyersDbHelper = new LawyersDbHelper (getActivity ());

        // Carga de datos
        if (mLawyerId != null) {
            loadLawyer ();
        }

        return root;
    }

    private void loadLawyer() {
        new GetLawyerByIdTask ().execute ();
    }

    private void addEditLawyer() {
        boolean error = false;

        String name = mNameField.getText ().toString ();
        String phoneNumber = mPhoneNumberField.getText ().toString ();
        String specialty = mSpecialtyField.getText ().toString ();
        String bio = mBioField.getText ().toString ();
        String dir = mDirField.getText ().toString ();

        if (TextUtils.isEmpty (name)) {
            mNameLabel.setError (getString (R.string.field_error));
            error = true;
        }

        if (TextUtils.isEmpty (phoneNumber)) {
            mPhoneNumberLabel.setError (getString (R.string.field_error));
            error = true;
        }

        if (TextUtils.isEmpty (specialty)) {
            mSpecialtyLabel.setError (getString (R.string.field_error));
            error = true;
        }


        if (TextUtils.isEmpty (bio)) {
            mBioLabel.setError (getString (R.string.field_error));
            error = true;
        }
        if (TextUtils.isEmpty (dir)) {
            mDirLabel.setError (getString (R.string.field_error));
            error = true;
        }

        if (error) {
            return;
        }

        Doctors doctors = new Doctors (name, specialty, phoneNumber, bio, dir,  mAvatarUri);


        new AddEditLawyerTask ().execute (doctors);

    }

    private void showLawyersScreen(Boolean requery) {
        if (!requery) {
            showAddEditError ();
            getActivity ().setResult (Activity.RESULT_CANCELED);
        } else {
            getActivity ().setResult (Activity.RESULT_OK);
        }

        getActivity ().finish ();
    }

    private void showAddEditError() {
        Toast.makeText (getActivity (),
                "Error al agregar nueva información", Toast.LENGTH_SHORT).show ();
    }

    private void showLawyer(Doctors doctors) {
        mNameField.setText (doctors.getName ());
        mPhoneNumberField.setText (doctors.getPhoneNumber ());
        mSpecialtyField.setText (doctors.getSpecialty ());
        mBioField.setText (doctors.getBio ());
        mAvatarUri = doctors.getAvatarUri ();
        mDirField.setText (doctors.getDirection ());
    }

    private void showLoadError() {
        Toast.makeText (getActivity (),
                "Error al editar Doctor", Toast.LENGTH_SHORT).show ();
    }

    private class GetLawyerByIdTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mLawyersDbHelper.getLawyerById (mLawyerId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToLast ()) {
                showLawyer (new Doctors (cursor));
            } else {
                showLoadError ();
                getActivity ().setResult (Activity.RESULT_CANCELED);
                getActivity ().finish ();
            }
        }

    }

    private class AddEditLawyerTask extends AsyncTask<Doctors, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Doctors... doctors) {
            if (mLawyerId != null) {
                return mLawyersDbHelper.updateLawyer (doctors[0], mLawyerId) > 0;
            } else {
                return mLawyersDbHelper.saveLawyer (doctors[0]) > 0;
            }

        }

        @Override
        protected void onPostExecute(Boolean result) {
            showLawyersScreen (result);
        }

    }

}
