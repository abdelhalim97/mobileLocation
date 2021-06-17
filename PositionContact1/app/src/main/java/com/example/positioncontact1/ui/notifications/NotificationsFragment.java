package com.example.positioncontact1.ui.notifications;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.positioncontact1.PositionAdapter;
import com.example.positioncontact1.PositionContact;
import com.example.positioncontact1.PositionContactManager;
import com.example.positioncontact1.R;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    ListView lv;
    EditText edrech;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        lv=root.findViewById(R.id.lv);
        edrech=root.findViewById(R.id.edrecherche);
        PositionContactManager pm=new PositionContactManager(getActivity());
        ArrayList<PositionContact> data=pm.getAllPosition();//chedhom les donnes mta3 base kol ba3d bech naffichehom f liste d'adabter lezem adabter
       PositionAdapter ad = new PositionAdapter(this.getActivity(),data);
       lv.setAdapter(ad);

        edrech.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        return  root;
    }
}