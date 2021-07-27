package com.example.jaimeprisha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jaimeprisha.Notifications.FcmNotificationsSender;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    CircleImageView profile;
    private FirebaseAuth mAuth;
    TextView userName,userEmail,userId;
    TextView collections,categ,cart,cartcount;
    RelativeLayout card1,card2,card3,car4,car21,car22,card23,card24;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
    private DatabaseReference databaseRef5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        profile=findViewById(R.id.profileimage);
        userName=findViewById(R.id.Nametext);
        cart=findViewById(R.id.cart);
        cartcount=findViewById(R.id.cartcount);
        collections=findViewById(R.id.jewellerycollectionsscreen);
        categ=findViewById(R.id.categoriesscreen);
        card1=findViewById(R.id.card_container);
        card2=findViewById(R.id.card_container2);
        card3=findViewById(R.id.card_container3);
        car4=findViewById(R.id.card_container4);
        car21=findViewById(R.id.card_container21);
        car22=findViewById(R.id.card_container22);
        card23=findViewById(R.id.card_container23);
        card24=findViewById(R.id.card_container24);
        mAuth = FirebaseAuth.getInstance();
        FirebaseMessaging.getInstance().subscribeToTopic("all");


        gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,DisplayItems.class);
                i.putExtra("type","EarRings");
                startActivity(i);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,DisplayItems.class);
                i.putExtra("type","Necklace");

                startActivity(i);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,DisplayItems.class);
                i.putExtra("type","Pendant");

                startActivity(i);
            }
        });
        car4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,DisplayItems.class);
                i.putExtra("type","Sets");

                startActivity(i);
            }
        });
        car21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,DisplayItems.class);
                i.putExtra("type","LatestTrends");

                startActivity(i);
            }
        });
        car22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,DisplayItems.class);
                i.putExtra("type","AgateNaturalStones");

                startActivity(i);
            }
        });
        card23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,DisplayItems.class);
                i.putExtra("type","FashionJewelry");

                startActivity(i);
            }
        });
        card24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,DisplayItems.class);
                i.putExtra("type","IndianEthnicjewelry");

                startActivity(i);
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomeActivity.this,MyCartActivity.class);
                startActivity(i);
            }
        });
        categ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,CategoriesScreen.class);
                startActivity(i);
            }
        });
        collections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,CollectionsScreen.class);
                startActivity(i);
            }
        });
        FirebaseUser user = mAuth.getCurrentUser();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,ProfileActivity.class);
                startActivity(i);
            }
        });
        databaseRef5 = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser).child("MyCart");
        databaseRef5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



              cartcount.setText(""+snapshot.getChildrenCount());



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr= Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(opr.isDone()){
            GoogleSignInResult result=opr.get();
            handleSignInResult(result);
        }else{
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }
    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount account=result.getSignInAccount();
            userName.setText("Hi!.."+account.getGivenName());
           // userEmail.setText(account.getEmail());
          //  userId.setText(account.getId());
            try{
                Glide.with(this).load(account.getPhotoUrl()).into(profile);
            }catch (NullPointerException e){
                Toast.makeText(getApplicationContext(),"image not found",Toast.LENGTH_LONG).show();
            }

        }else{

        }
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}