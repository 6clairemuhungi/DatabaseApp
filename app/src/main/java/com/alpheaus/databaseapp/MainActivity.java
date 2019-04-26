package com.alpheaus.databaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.inputName)
    EditText inputName;
    @BindView(R.id.inputNumber)
    EditText inputNumber;
    @BindView(R.id.inputBalance)
    EditText inputBalance;
    @BindView(R.id.textViewCount)
    TextView txtCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    public void Save(View view) {
         String name = inputName.getText().toString().trim();
         String number = inputNumber.getText().toString().trim();
         String bal = inputBalance.getText().toString().trim();

         if(name.isEmpty() || number.isEmpty() || bal.isEmpty())
         {
             return;
         }

         double balance = Double.parseDouble(bal);//convgert string to double
         Account x =new Account();
         x.setName(name);
         x.setAccnumber(number);
         x.setBalance(balance);

         //save to db
        OurDatabase.getInstance(this).accountDao().insertAccount(x);

        int count = OurDatabase.getInstance(this).accountDao().getAllAccounts().size();
        txtCount.setText("Count is" +count);
        //clear
        inputName.setText("");
        inputBalance.setText("");
        inputNumber.setText("");




    }

    public void accounts(View view) {
        Intent x = new Intent(this, DisplayActivity.class);
        startActivity(x);
    }
}
