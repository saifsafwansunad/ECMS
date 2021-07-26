package com.example.ecms.Fragments.CorrespondenceDetailFragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.ecms.CorrespondenceDetailsActivity;
import com.example.ecms.R;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CorrespondenceDetailsFragment extends Fragment {

    ImageView imageViewAddAttachment,imageViewEditCorrespondence;
    private DatePickerDialog mDatePickerDialog;

    public CorrespondenceDetailsFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_correspondence_details, container, false);

        imageViewAddAttachment=(ImageView)view.findViewById(R.id.add_attaahment_imageview);
        imageViewEditCorrespondence=(ImageView)view.findViewById(R.id.edit_correspo_imageview);

        imageViewAddAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CorrespondenceDetailsFragment.AddAttachmentDialog addAttachmentDialog = new CorrespondenceDetailsFragment.AddAttachmentDialog();
                addAttachmentDialog.showDialog(getActivity(),"");
            }
        });
        imageViewEditCorrespondence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CorrespondenceDetailsFragment.EditCorrespondenceDialog editCorrespondenceDialog=new CorrespondenceDetailsFragment.EditCorrespondenceDialog();
                editCorrespondenceDialog.showDialog(getActivity(),"");
            }
        });

        return view;
    }

    public static class AddAttachmentDialog {

        public void showDialog(Activity activity, String msg) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            dialog.setContentView(R.layout.layout_for_addattachment_dialog);

//            TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
//            text.setText(msg);

            MaterialButton dialogButton = (MaterialButton) dialog.findViewById(R.id.cancel_dialog_btn);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();

        }
    }


    public class EditCorrespondenceDialog {

        public void showDialog(Activity activity, String msg) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


            dialog.setContentView(R.layout.layout_for_edit_correspondence);

            setDateTimeField();
            ArrayAdapter<String> corTypeAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Correspondent_Type));
            ArrayAdapter<String> departmentadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Deapartment));
            ArrayAdapter<String> sourceadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Deapartment));
            ArrayAdapter<String> docthruadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Document_Through));
            ArrayAdapter<String> doctypeadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Document_Type));
            ArrayAdapter<String> meetingadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Meeting_Type));
            ArrayAdapter<String> priorityadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Priority));
            ArrayAdapter<String> closingstatusadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Closing_Status));


            RelativeLayout relativeLayout=(RelativeLayout)dialog.findViewById(R.id.crealayout);

            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDatePickerDialog.show();
                }
            });
            Spinner spinnerCorType = (Spinner)dialog.findViewById(R.id.type_spinner);
            Spinner spinnerDepartment = (Spinner)dialog.findViewById(R.id.department_spinner);
            Spinner spinnerSource= (Spinner)dialog.findViewById(R.id.source_spinner);
            Spinner spinnerDocuThru = (Spinner)dialog.findViewById(R.id.document_throu_spinner);
            Spinner spinnerDocuType = (Spinner)dialog.findViewById(R.id.document_typre_spinner);
            Spinner spinnerMeetingType = (Spinner)dialog.findViewById(R.id.meeting_spinner);
            Spinner spinnerPriority = (Spinner)dialog.findViewById(R.id.priority_spinner);
            Spinner spinnerClosingStatus = (Spinner)dialog.findViewById(R.id.closestatus_spinner);

            spinnerCorType.setAdapter(corTypeAdapter);
            spinnerDepartment.setAdapter(departmentadapter);
            spinnerSource.setAdapter(sourceadapter);
            spinnerDocuThru.setAdapter(docthruadapter);
            spinnerDocuType.setAdapter(doctypeadapter);
            spinnerMeetingType.setAdapter(meetingadapter);
            spinnerPriority.setAdapter(priorityadapter);
            spinnerClosingStatus.setAdapter(closingstatusadapter);

//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(CorrespondenceDetailsActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Correspondent_Type));
//
//            AutoCompleteTextView mTextView = (AutoCompleteTextView)dialog.findViewById(R.id.type_actv);
//
//            mTextView.setAdapter(adapter);
//            mTextView.setKeyListener(null);
//            mTextView.setOnTouchListener(new View.OnTouchListener(){
//                @Override
//                public boolean onTouch(View v, MotionEvent event){
//                    ((AutoCompleteTextView) v).showDropDown();
//                    return false;
//                }
//            });
//            TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
//            text.setText(msg);
//setupSpinner();
            MaterialButton dialogButton = (MaterialButton) dialog.findViewById(R.id.cancel_dialog_btn);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();

        }
    }

    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
                final Date startDate = newDate.getTime();
                String fdate = sd.format(startDate);

//                edDate.setText(fdate);

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }
}