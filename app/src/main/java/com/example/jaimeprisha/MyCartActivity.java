package com.example.jaimeprisha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.jaimeprisha.Adapters.CartAdapter;
import com.example.jaimeprisha.Models.ItemModel;
import com.example.jaimeprisha.Notifications.FcmNotificationsSender;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class MyCartActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String currentuser;
    Button proceed;
    LottieAnimationView lottieAnimationView;
    private CartAdapter adapter;
    private DatabaseReference databaseRef5,db;
    TextView test;
    String price,count;
    ArrayList<String> users = new ArrayList<String>();
    String totalprice;
    private DatabaseReference databaseRef1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        recyclerView = findViewById(R.id.idRVItems);
        proceed=findViewById(R.id.proceed);
        test=findViewById(R.id.test);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lottieAnimationView=findViewById(R.id.lav_actionBar);
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyCartActivity.this,BookingScreen.class);
                i.putExtra("price",price);
                i.putExtra("count",count);
                startActivity(i);
            }
        });
        /*db= FirebaseDatabase.getInstance().getReference().child("Tracking").child(currentuser);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Intent i = new Intent(CartActiviity.this,TrackingScreen.class);
                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        databaseRef5 = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser);
        databaseRef5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                if (snapshot.child("MyCart").getChildrenCount() == 0) {

                    //FinalValue finval= new FinalValue("0");
                    //databaseRef5.child("Final").setValue(finval);
                    lottieAnimationView.setVisibility(View.VISIBLE);
                    lottieAnimationView.playAnimation();
                    test.setText("No Items In Your Cart");
                }else{
                    proceed.setVisibility(View.VISIBLE);
                }
               // totalprice = snapshot.child("Final").child("finalprice").getValue(String.class);
               // place.setText(totalprice);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseRef1 = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser).child("MyCart");
        databaseRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String tempname= snapshot1.child("itemname").getValue(String.class);
                    String tempimage= snapshot1.child("itemimage").getValue(String.class);
                    String tempdesc= snapshot1.child("itemdesc").getValue(String.class);
                    String tempprice= snapshot1.child("itemprice").getValue(String.class);
                    String temptype= snapshot1.child("itemtype").getValue(String.class);
                    users.add("ItemName"+tempname+"   "+tempdesc+"   "+"ItemPrice"+tempprice+"   "+"ItemType: "+temptype+"   "+tempimage);

                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
/*

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(CartActiviity.this,BookingScreen.class);
                i.putExtra("price",totalprice);
                i.putExtra("booked",users);
                startActivity(i);
            }
        });
*/


        FirebaseRecyclerOptions<ItemModel> options =
                new FirebaseRecyclerOptions.Builder<ItemModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Users").child(currentuser).child("MyCart"), ItemModel.class)
                        .build();

        adapter = new CartAdapter(options,getApplicationContext());
        recyclerView.setAdapter(adapter);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,new IntentFilter("message_subject_intent"));




    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            price= intent.getStringExtra("name");
            count = intent.getStringExtra("count");
            proceed.setText("Pay "+"₹"+price+"/-");
        }
    };
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, HomeActivity.class));

    }
}