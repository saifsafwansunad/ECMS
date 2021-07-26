package com.example.ecms.Fragments.FusionTestTitleFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ecms.Fragments.CorrespondenceDetailFragment.CorrespondenceDetailsFragment;
import com.example.ecms.R;

public class CorrespondenceAttachmentFragment extends Fragment {

    ImageView imageViewAddAttachment;


    public CorrespondenceAttachmentFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_correspondence_attachment, container, false);
        imageViewAddAttachment=(ImageView)view.findViewById(R.id.add_imageview);

        imageViewAddAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CorrespondenceDetailsFragment.AddAttachmentDialog addAttachmentDialog = new CorrespondenceDetailsFragment.AddAttachmentDialog();
                addAttachmentDialog.showDialog(getActivity(),"");
            }
        });

        return view ;
    }
}