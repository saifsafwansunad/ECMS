package com.ecms.ndmecms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecms.ndmecms.Fragments.myDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Sent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Sent extends Fragment implements View.OnClickListener, myDialog.DialogListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Sent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Sent.
     */
    // TODO: Rename and change types and number of parameters
    public static Sent newInstance(String param1, String param2) {
        Sent fragment = new Sent();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_sent,
                container, false);
        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.floating_action_button);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myDialog dialogFragment = new myDialog();
                dialogFragment.show(getActivity().getSupportFragmentManager(),"");
                Bundle bundle = new Bundle();
                bundle.putBoolean("notAlertDialog", true);

                dialogFragment.setArguments(bundle);            }
        }); return view;    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.floating_action_button:
                myDialog dialogFragment = new myDialog();
                dialogFragment.show(getActivity().getSupportFragmentManager(),"");
                Bundle bundle = new Bundle();
                bundle.putBoolean("notAlertDialog", true);

                dialogFragment.setArguments(bundle);

              //  FragmentTransaction ft = gets().beginTransaction();
               // Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");

                break;

        }
    }

    @Override
    public void onFinishEditDialog(String inputText) {

    }
}