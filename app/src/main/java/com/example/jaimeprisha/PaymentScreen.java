package com.example.jaimeprisha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.jaimeprisha.Models.BookingModel;
import com.example.jaimeprisha.Models.StatusModel;
import com.example.jaimeprisha.Notifications.FcmNotificationsSender;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

//check payment real
public class PaymentScreen extends AppCompatActivity {
    public static final String PAYTM_PACKAGE_NAME = "net.one97.paytm";
    private LottieAnimationView lottieAnimationView;
    ImageView addressclick,shippingclick;
    int i=0,j=0;
    TextView Fname,Fnumber,Faddress,Fcity,Fstate,Fpin,Famount,Fshippingprice,Ftotalamount;
    String name=" Pratyush Kumar",upi=" 9599824600@paytm",msg="hi",amount="0";
    Button pay;
    String number,address,area, city,state,pin,items;
    LinearLayout shippinglay,addressalay;
    private Uri uri;
    int finalamount;
    String itemid;
    TextView msgtxt;
    private String status;
    private DatabaseReference databaseRef1,databaseRef,databaseRef3;
    String currentuser;
    private DatabaseReference databaseRef5;
    private DatabaseReference databaseRef6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_screen);
        lottieAnimationView=findViewById(R.id.lav_actionBar);
        addressclick=findViewById(R.id.addressclick);
        shippingclick=findViewById(R.id.shippingclick);
        shippinglay=findViewById(R.id.shippinglayout);
        addressalay=findViewById(R.id.addresslayout);
        Fname=findViewById(R.id.name);
        Fnumber=findViewById(R.id.number);
        Faddress=findViewById(R.id.address);
        Fcity=findViewById(R.id.city);
        Fstate=findViewById(R.id.state);
        Fpin=findViewById(R.id.pin);
        Famount=findViewById(R.id.amount);
        Fshippingprice=findViewById(R.id.shippingprice);
        Ftotalamount=findViewById(R.id.totalamount);
        pay=findViewById(R.id.paybutton);
        msgtxt=findViewById(R.id.msgtxt);
        lottieAnimationView.playAnimation();
        name=getIntent().getStringExtra("name");
        number=getIntent().getStringExtra("number");
        address=getIntent().getStringExtra("address");
        area=getIntent().getStringExtra("area");
        city=getIntent().getStringExtra("city");
        state=getIntent().getStringExtra("state");
        pin=getIntent().getStringExtra("pin");
        amount=getIntent().getStringExtra("ammount");
        items =getIntent().getStringExtra("itemname");
        currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseMessaging.getInstance().subscribeToTopic("all");

        Fname.setText("Name: "+name);
        Fnumber.setText("Number: "+number);
        Faddress.setText("Address: "+address);
        Fcity.setText("City: "+city);
        Fstate.setText("State: "+state);
        Fpin.setText("Pin Code: "+pin);
        Famount.setText(amount);
        Fshippingprice.setText("50");
        finalamount=Integer.parseInt(amount)+50;
        Ftotalamount.setText(finalamount+"");
        pay.setText(finalamount+"");
        addressclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                if (i%2!=0) {

                    addressclick.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    addressalay.setVisibility(View.VISIBLE);
                }
                else if (i%2==0){
                    addressclick.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    addressalay.setVisibility(View.GONE);
                }
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uri = getPayTmUri(name, upi, msg, finalamount+"");
                payWithPayTm(PAYTM_PACKAGE_NAME);
            }
        });
        shippingclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j++;
                if (j%2!=0){
                    shippingclick.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    shippinglay.setVisibility(View.VISIBLE);
                } else if(j%2==0){
                    shippingclick.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    shippinglay.setVisibility(View.GONE);
                }

            }
        });
    }

    private static Uri getPayTmUri(String name, String upiId, String note, String amount) {
        return new Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();
    }

    private void payWithPayTm(String packageName) {

        if (isAppInstalled(this, packageName)) {

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(uri);
            i.setPackage(packageName);
            startActivityForResult(i, 0);

        } else {
            Toast.makeText(this, "Paytm is not installed Please install and try again.", Toast.LENGTH_SHORT).show();
           /* senddata();
          *//*  String to ="prabishaapp@gmail.com";
            String subject="New Oder by "+name;
            String message=amount+" rupees are paid to your account for jewlley order";*//*
          *//*  databaseRef5 = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser);
            databaseRef5.child("Buffer").removeValue();
            databaseRef5.child("MyCart").removeValue();*//*
      *//*      Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
            email.putExtra(Intent.EXTRA_SUBJECT, subject);
            email.putExtra(Intent.EXTRA_TEXT, message);

            //need this to prompts email client only
            email.setType("message/rfc822");

            startActivity(Intent.createChooser(email, "Choose an Email client :"));*//*

            FcmNotificationsSender notificationsSender=new FcmNotificationsSender("/topics/all",name+"Makes an Order",
                    amount+"is Paid through UPI for "+items,getApplicationContext(),PaymentScreen.this);

            notificationsSender.SendNotifications();
            AlertDialog.Builder builder = new AlertDialog.Builder(PaymentScreen.this);
            builder.setMessage("Congratulations! Your order has been placed successfully");
            builder.setTitle("Success");
            builder.setCancelable(false);


            builder
                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which)
                        {
                            databaseRef5 = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser);
                            databaseRef5.child("Buffer").removeValue();
                            databaseRef5.child("MyCart").removeValue();
                            Intent i = new Intent(PaymentScreen.this,HomeActivity.class);

                            startActivity(i);
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();*/
        }


    }

    // Hi all today we are going to integrate paytm payment with android app.
    // without using any sdk.
    // so lets have the demo first.
    // so lets get started.
    // Please like share and dont forget to subscribe.
    // so transaction successful and we also got the transac ref no
    // Thanks for watching see you in the next video


    //cheking paytm app is install or not

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            status = data.getStringExtra("Status").toLowerCase();
        }
        if ((RESULT_OK == resultCode) && status.equals("success")) {
            Toast.makeText(PaymentScreen.this, "Transaction successful." + data.getStringExtra("ApprovalRefNo"), Toast.LENGTH_SHORT).show();
            msgtxt.setText("Transaction successful of ₹" + amount);
            msgtxt.setTextColor(Color.GREEN);
            senddata();
            FcmNotificationsSender notificationsSender=new FcmNotificationsSender("/topics/all",name+"   Makes an Order",
                    amount+"/- is Paid through UPI for "+items,getApplicationContext(),PaymentScreen.this);

            notificationsSender.SendNotifications();
            AlertDialog.Builder builder = new AlertDialog.Builder(PaymentScreen.this);
            builder.setMessage("Congratulations! Your order has been placed successfully");
            builder.setTitle("Success");
            builder.setCancelable(false);


            builder
                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which)
                        {
                            databaseRef5 = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser);
                            databaseRef5.child("Buffer").removeValue();
                            databaseRef5.child("MyCart").removeValue();
                            Intent i = new Intent(PaymentScreen.this,HomeActivity.class);

                            startActivity(i);
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        } else {
            Toast.makeText(PaymentScreen.this, "Transaction cancelled or failed please try again.", Toast.LENGTH_SHORT).show();
            msgtxt.setText("Transaction Failed of ₹" + amount);
            msgtxt.setTextColor(Color.RED);
        }

    }
    public void senddata(){

        databaseRef1 = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser).child("Buffer");
        databaseRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String tempname= snapshot1.child("itemname").getValue(String.class);
                    String tempimage= snapshot1.child("itemimage").getValue(String.class);
                    String tempdesc= snapshot1.child("itemdesc").getValue(String.class);
                    String tempprice= snapshot1.child("sellingprice").getValue(String.class);
                    String temptype= snapshot1.child("itemtype").getValue(String.class);
                    String tempmodel= snapshot1.child("model").getValue(String.class);
                    String tempcount= snapshot1.child("count").getValue(String.class);
                    BookingModel model= new BookingModel(tempname,tempdesc,temptype,tempimage,tempmodel,tempprice,tempcount,name,number,address,area,city,state,pin,currentuser);
                    databaseRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser).child("Orders");
                    databaseRef6 = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser).child("Status");
                    databaseRef3 = FirebaseDatabase.getInstance().getReference().child("NewOrders");
                    String uploadid=databaseRef.push().getKey();
                    databaseRef.child(uploadid).setValue(model);
                    databaseRef3.child(uploadid).setValue(model);
                    itemid=uploadid;
                    StatusModel statusModel = new StatusModel(uploadid,"Order is Placed","Order placed within 3 days");

                    databaseRef6.child(uploadid).setValue(statusModel);


                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}