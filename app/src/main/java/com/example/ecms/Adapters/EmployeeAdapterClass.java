package com.example.ecms.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecms.DatabaseHelperClass;
import com.example.ecms.LoginActivity;
import com.example.ecms.Models.EmployeeModelClass;
import com.example.ecms.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EmployeeAdapterClass extends RecyclerView.Adapter<EmployeeAdapterClass.ViewHolder> {

    List<EmployeeModelClass> employee;
    Context context;
    DatabaseHelperClass databaseHelperClass;
    private Context activity;
    static Integer id;
    static int position_No;

    public EmployeeAdapterClass(List<EmployeeModelClass> employee, Context context) {
        this.employee = employee;
        this.context = context;
        databaseHelperClass = new DatabaseHelperClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.employee_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final EmployeeModelClass employeeModelClass = employee.get(position);

        holder.textViewID.setText(Integer.toString(employeeModelClass.getId()));
        holder.editText_Name.setText(employeeModelClass.getName());
        holder.editText_Email.setText(employeeModelClass.getEmail());
        String path=employeeModelClass.getName();


        holder.editText_Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= FileProvider.getUriForFile(context,"com.example.ecms"+".provider", new File(path));
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri,"application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                context.startActivity(intent);

            }
        });

        holder.button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmployeeAdapterClass.ForgotPasswordDialog forgotPasswordDialog=new EmployeeAdapterClass.ForgotPasswordDialog();
                forgotPasswordDialog.showDialog(EmployeeAdapterClass.this,"Forgot Password");
                id=employeeModelClass.getId();
                position_No=position;
                /*databaseHelperClass.deleteEmployee(employeeModelClass.getId());
                employee.remove(position);
                notifyDataSetChanged();*/
            }
        });

    }

    private Context getActivity() {

        return activity;
    }

    @Override
    public int getItemCount() {
        return employee.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText editText_Name;
        TextView editText_Email;
        Button button_view;
        ImageButton button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_Name = itemView.findViewById(R.id.edittext_name);
            editText_Email = itemView.findViewById(R.id.edittext_email);
            button_delete = itemView.findViewById(R.id.button_delete);
            button_view = itemView.findViewById(R.id.button_view);

        }
    }

    public class ForgotPasswordDialog {

        public void showDialog(EmployeeAdapterClass activity, String msg){
            final Dialog dialog = new Dialog(activity.context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

//            dialog.setCancelable(false);
            dialog.setContentView(R.layout.confirm_dialog_layout);
            Button buttonOk=(Button) dialog.findViewById(R.id.dialog_ok_btn);
            buttonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    databaseHelperClass.deleteEmployee(id);
                    employee.remove(position_No);
                    notifyDataSetChanged();
                    dialog.dismiss();
                }
            });


            dialog.show();


        }
    }

}
