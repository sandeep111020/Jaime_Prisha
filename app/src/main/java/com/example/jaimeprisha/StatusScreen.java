package com.example.jaimeprisha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.jaimeprisha.Models.BookingModel;
import com.example.jaimeprisha.Models.StatusModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StatusScreen extends AppCompatActivity {
    TextView comment,date;
    private DatabaseReference databaseRef1;
    String currentuser,itemid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_screen);
        comment=findViewById(R.id.comment);
        date=findViewById(R.id.date);
        itemid=getIntent().getStringExtra("itemid");
        currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseRef1 = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser).child("Status");
        databaseRef1.addListenerForSingleValueEvent(new ValueEventListener() {
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