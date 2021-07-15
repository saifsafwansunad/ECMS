package com.example.ecms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CorrespondenceDetailsActivity extends AppCompatActivity {
ImageView imageViewAddAttachment,imageViewEditCorrespondence;
    private DatePickerDialog mDatePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correspondence_details);
        imageViewAddAttachment=(ImageView)findViewById(R.id.add_attaahment_imageview);
        imageViewEditCorrespondence=(ImageView)findViewById(R.id.edit_correspo_imageview);

        imageViewAddAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAttachmentDialog addAttachmentDialog = new AddAttachmentDialog();
addAttachmentDialog.showDialog(CorrespondenceDetailsActivity.this,"");
            }
        });
        imageViewEditCorrespondence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditCorrespondenceDialog editCorrespondenceDialog=new EditCorrespondenceDialog();
                editCorrespondenceDialog.showDialog(CorrespondenceDetailsActivity.this,"");
            }
        });

    }

    public class AddAttachmentDialog {

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
            ArrayAdapter<String> corTypeAdapter = new ArrayAdapter<String>(CorrespondenceDetailsActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Correspondent_Type));
            ArrayAdapter<String> departmentadapter = new ArrayAdapter<String>(CorrespondenceDetailsActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Deapartment));
            ArrayAdapter<String> sourceadapter = new ArrayAdapter<String>(CorrespondenceDetailsActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Deapartment));
            ArrayAdapter<String> docthruadapter = new ArrayAdapter<String>(CorrespondenceDetailsActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Document_Through));
            ArrayAdapter<String> doctypeadapter = new ArrayAdapter<String>(CorrespondenceDetailsActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Document_Type));
            ArrayAdapter<String> meetingadapter = new ArrayAdapter<String>(CorrespondenceDetailsActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Meeting_Type));
            ArrayAdapter<String> priorityadapter = new ArrayAdapter<String>(CorrespondenceDetailsActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Priority));
            ArrayAdapter<String> closingstatusadapter = new ArrayAdapter<String>(CorrespondenceDetailsActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Closing_Status));


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
        mDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

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
//public void setupSpinner(){
//    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item , getResources().getStringArray(R.array.Correspondent_Type));
//
//    AutoCompleteTextView mTextView = (AutoCompleteTextView) findViewById(R.id.type_actv);
//
//    mTextView.setAdapter(adapter);
//    mTextView.setKeyListener(null);
//    mTextView.setOnTouchListener(new View.OnTouchListener(){
//        @Override
//        public boolean onTouch(View v, MotionEvent event){
//            ((AutoCompleteTextView) v).showDropDown();
//            return false;
//        }
//    });
//}
}