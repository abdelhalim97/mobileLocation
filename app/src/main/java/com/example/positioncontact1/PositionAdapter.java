package com.example.positioncontact1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

public class PositionAdapter extends BaseAdapter {
    ArrayList<PositionContact> data;
    Context con;
    EditText edNewNum;
    public PositionAdapter(Context con, ArrayList<PositionContact> data){
        this.con=con;
        this.data=data;

    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        LayoutInflater inf=LayoutInflater.from(con);
        View v =inf.inflate(R.layout.view_position,null);
        TextView tvnum=v.findViewById(R.id.tv_num_view);
        TextView tvpseudo=v.findViewById(R.id.tv_pseudo_view);
        TextView tvlong=v.findViewById(R.id.tvlon_view);
        TextView tvlat=v.findViewById(R.id.tvlat_view);
        TextView tvid=v.findViewById(R.id.tv_id_view);
        ImageButton call=v.findViewById(R.id.imageButton_call);
        ImageButton sms=v.findViewById(R.id.imageButton2_sms);
        ImageButton map=v.findViewById(R.id.imageButton3_map);
        ImageButton edit=v.findViewById(R.id.imageButton5_edit);
        ImageButton sup=v.findViewById(R.id.imgbtn_sup);
        ImageButton sup2=v.findViewById(R.id.imgbtn_sup2);
        ImageButton fav=v.findViewById(R.id.imageButton_fav);
        edNewNum=v.findViewById(R.id.edNewNum);
        PositionContact pc=data.get(position);
        tvid.setText("id position: "+pc.id);
        tvnum.setText(pc.numero);
        tvpseudo.setText(pc.pseudo);
        tvlong.setText(pc.longitude);

        tvlat.setText(pc.latitude);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fav.setImageResource(android.R.drawable.btn_star_big_on);

                File f=new File(Environment.getExternalStorageDirectory().getPath(),"favoris.csv");
                try {
                    FileWriter writer=new FileWriter(f,true);
                    writer.write(pc.numero+","+pc.longitude+","+pc.pseudo+","+pc.latitude+"\n");
                    writer.close();//
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.gps_permission) {
                    Intent i = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("geo" + pc.longitude + "," + pc.latitude));
                    con.startActivity(i);
                }
            }
        });
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.sms_permission)
                {
                    Intent i=new Intent();

                    i.setAction(Intent.ACTION_SENDTO);
                    i.setData(Uri.parse("SMS:"+pc.numero));
                    i.putExtra("sms_body","ou etesvius?");
                    con.startActivity(i);
                }
            }
        });
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PositionContactManager pm=new PositionContactManager(con);
                pm.supprimerSelonId(pc.id);

                data.remove(position);
                notifyDataSetChanged();
            }
        });
        sup2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                PositionContactManager pm=new PositionContactManager(con);
                pm.supprimerSelonnom(pc.numero);

                data.remove(position);
                notifyDataSetChanged();
            }
        });
        edit.setOnClickListener(new View.OnClickListener(){
           public void onClick(View view){
               String num=edNewNum.getText().toString();//
               PositionContactManager pm=new PositionContactManager(con);
               pm.modifier(pc.id,num);
               notifyDataSetChanged();
           }
        });

        return v;
    }
}
