package com.example.jaimeprisha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jaimeprisha.Models.StatusModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddStatus extends AppCompatActivity {
    EditText comment,date;
    private DatabaseReference databaseRef1;
    String currentuser,itemid;
    Button submit;
    CheckBox check;
    private String Scheck;
    private DatabaseReference databaseRef2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_status);
        comment=findViewById(R.id.comment);
        date=findViewById(R.id.date);
        submit=findViewById(R.id.submitbutton);
        check=findViewById(R.id.delivered);
        itemid=getIntent().getStringExtra("itemid");
        currentuser=getIntent().getStringExtra("userid");
        databaseRef1 = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser).child("Status");
        Scheck="false";
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()){
                    Scheck="true";

                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(comment.getText().toString())){
                    Toast.makeText(AddStatus.this,"Please add comment",Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(date.getText().toString())){
                    Toast.makeText(AddStatus.this,"Please add date",Toast.LENGTH_SHORT).show();

                }
                else {
                    StatusModel model;
                    if (Scheck.equals("true")){
                        model = new StatusModel(itemid, "Item Delivered", date.getText().toString());
                    }
                    else {
                        model = new StatusModel(itemid, comment.getText().toString(), date.getText().toString());
                    }
                    databaseRef1.child(itemid).setValue(model);
                    Toast.makeText(AddStatus.this,"Successfully Submitted",Toast.LENGTH_SHORT).show();


                }
            }
        });

        databaseRef2 = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser).child("Status");
        databaseRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                comment.setText(snapshot.child(itemid).child("comment").getValue(String.class));
                date.setText(snapshot.child(itemid).child("date").getValue(String.class));



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}