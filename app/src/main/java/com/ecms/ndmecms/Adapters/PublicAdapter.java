package com.ecms.ndmecms.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecms.ndmecms.Models.PublicModels;
import com.ecms.ndmecms.R;

import java.util.ArrayList;

public class PublicAdapter extends RecyclerView.Adapter<PublicAdapter.ViewHolder> {
    private ArrayList<PublicModels> publicModels;
    ImageView imageView;
    private Context context;


    public PublicAdapter(ArrayList<PublicModels> publicModels, Context context) {
        this.publicModels = publicModels;
        this.context = context;
    }


    @NonNull
    @Override
    public PublicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_public_list, parent, false);

        return new PublicAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicAdapter.ViewHolder holder, int position) {

        holder.textViewHeader.setText(publicModels.get(position).getPublicHeader());
        holder.textViewSubHeader.setText(publicModels.get(position).getPublicSubHeader());

    }


    @Override
    public int getItemCount() {
        return publicModels.size();
    }

//    public void removeItem(int position) {
//        cartObjects.remove(position);
//        notifyItemRemoved(position);
//    }
//    public void restoreItem(String item, int position) {
//        cartObjects.add(position,item );
//        notifyItemInserted(position);
//    }

    //    public ArrayList<CartObjects> getData() {
//        return cartObjects;
//    }
    class ViewHolder extends RecyclerView.ViewHolder {


        TextView textViewHeader, textViewSubHeader;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHeader = (TextView) itemView.findViewById(R.id.text_view_name);
            textViewSubHeader = (TextView) itemView.findViewById(R.id.second_text_view);



        }
    }
}
