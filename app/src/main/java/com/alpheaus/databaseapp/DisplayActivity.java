package com.alpheaus.databaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayActivity extends AppCompatActivity {

    @BindView(R.id.list_accounts)
    ListView listView;

    List<Account> data;

    BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        ButterKnife.bind(this);

        data = OurDatabase.getInstance(this).accountDao().getAllAccounts();

        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Object getItem(int position) {
                return data.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_item,parent,false);
                TextView tvName = v.findViewById(R.id.textViewName);
                TextView tvBalance = v.findViewById(R.id.textViewBalance);
                TextView tvNumber = v.findViewById(R.id.textViewAccNumber);

                ImageView imgDelete = v.findViewById(R.id.imageViewDelete);
                ImageView imgEdit = v.findViewById(R.id.imageViewEdit);

                final Account x = data.get(position);
                tvName.setText(x.getName());
                tvNumber.setText(x.getAccnumber());
                tvBalance.setText("Ksh"+x.getBalance());

                imgDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OurDatabase.getInstance(getBaseContext()).accountDao().delete(x);
                        data.remove(x);
                        notifyDataSetChanged();
                    }
                });

                imgEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO add update code
                    }
                });
                return v;
            }
        };

        listView.setAdapter(adapter);

    }
}
