package com.example.positioncontact1.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.positioncontact1.PositionContactManager;
import com.example.positioncontact1.R;

public class DashboardFragment extends Fragment {
    EditText ednumero,edpseudo,edlatitude,edlongitude;
    Button btnajout;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //partie recuperation
        ednumero=root.findViewById(R.id.ednumero);
        edpseudo=root.findViewById(R.id.edpseudo);
        edlongitude=root.findViewById(R.id.edlongitude);
        edlatitude=root.findViewById(R.id.edlatitude);
        btnajout=root.findViewById(R.id.btnajout);
        //partie action
        btnajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num=ednumero.getText().toString();
                String ps=edpseudo.getText().toString();
                String lon=edlongitude.getText().toString();
                String lat=edlatitude.getText().toString();
                if(num.isEmpty()||ps.isEmpty()||lon.isEmpty()||lat.isEmpty())
                    Toast.makeText(DashboardFragment.this.getActivity(),"un cham est vide",Toast.LENGTH_SHORT).show();
                else {
                    PositionContactManager pm=new PositionContactManager(getActivity());
                    long a=pm.inserer(num,ps,lon,lat);
                    if(a>0)
                        Toast.makeText(DashboardFragment.this.getActivity(),"Insertion avec succes",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DashboardFragment.this.getActivity(),"Echec insertion",Toast.LENGTH_SHORT).show();


                }
            }
        });



        return root;
    }
}