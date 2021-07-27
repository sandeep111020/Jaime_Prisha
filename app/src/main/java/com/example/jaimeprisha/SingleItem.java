package com.example.jaimeprisha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jaimeprisha.Models.ItemModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jsibbold.zoomage.ZoomageView;

public class SingleItem extends AppCompatActivity {

    String name,image,desc,price,sellingprice,model,type,percent,key;
    private ZoomageView ImageZoomageView;
    private BottomSheetDialog bottomSheetDialog;
    Button view;
    private DatabaseReference databaseReference;
    private String itemkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);
        view=findViewById(R.id.buttombottm);
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(currentuser);



//        getSupportActionBar().hide();

        name=getIntent().getStringExtra("name");
        image=getIntent().getStringExtra("image");
        desc=getIntent().getStringExtra("desc");
        price=getIntent().getStringExtra("price");
        sellingprice=getIntent().getStringExtra("sellingprice");
        model=getIntent().getStringExtra("model");
        type=getIntent().getStringExtra("type");
        key=getIntent().getStringExtra("key");

        int per =100 - (Integer.parseInt(sellingprice)*100)/Integer.parseInt(price);
        percent=String.valueOf(per);

        itemkey=key;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog=new BottomSheetDialog(SingleItem.this,R.style.BottomSheetTheme);
                View sheetview= LayoutInflater.from(getApplicationContext()).inflate(R.layout.itembottomsheet,null);

                TextView tname = sheetview.findViewById(R.id.name);
                TextView tdesc = sheetview.findViewById(R.id.desc);
                TextView tprice = sheetview.findViewById(R.id.price);
                TextView tsellingprice = sheetview.findViewById(R.id.sellingprice);
                TextView ttype = sheetview.findViewById(R.id.type);
                TextView tmodel = sheetview.findViewById(R.id.model);
                TextView perc = sheetview.findViewById(R.id.percentage);



                perc.setText(percent+"% Off");
                tname.setText(name+"");
                tdesc.setText(""+desc);
                tprice.setText(""+price);
                tsellingprice.setText(sellingprice+"");
                ttype.setText(type+"");
                tmodel.setText(model+"");
                sheetview.findViewById(R.id.gotosecond).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SingleItem.this,"Programming3 is Clicked",Toast.LENGTH_SHORT).show();
                        ItemModel userProfileInfo = new ItemModel(name,desc,price,type,"true",image,model,sellingprice,"1");
                        String ImageUploadId = databaseReference.push().getKey();
                        databaseReference.child("MyCart").child(itemkey).setValue(userProfileInfo);


                        Toast.makeText(SingleItem.this,"Item Added to MyCart",Toast.LENGTH_SHORT).show();
                    }
                });
                bottomSheetDialog.setContentView(sheetview);
                bottomSheetDialog.show();
            }
        });


        ImageZoomageView = findViewById(R.id.imageViewImageFullScreen);
        Glide.with(this).load(image).into(ImageZoomageView);




            bottomSheetDialog=new BottomSheetDialog(this,R.style.BottomSheetTheme);
            View sheetview= LayoutInflater.from(getApplicationContext()).inflate(R.layout.itembottomsheet,null);

        TextView tname = sheetview.findViewById(R.id.name);
        TextView tdesc = sheetview.findViewById(R.id.desc);
        TextView tprice = sheetview.findViewById(R.id.price);
        TextView tsellingprice = sheetview.findViewById(R.id.sellingprice);
        TextView ttype = sheetview.findViewById(R.id.type);
        TextView tmodel = sheetview.findViewById(R.id.model);
        TextView perc = sheetview.findViewById(R.id.percentage);



        perc.setText(percent+"% Off");
        tname.setText(name+"");
        tdesc.setText(""+desc);
        tprice.setText(""+price);
        tsellingprice.setText(sellingprice+"");
        ttype.setText(type+"");
        tmodel.setText(model+"");

        sheetview.findViewById(R.id.gotosecond).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemModel userProfileInfo = new ItemModel(name,desc,price,type,"true",image,model,sellingprice,"1");
                String ImageUploadId = databaseReference.push().getKey();
                databaseReference.child("MyCart").child(itemkey).setValue(userProfileInfo);


                Toast.makeText(SingleItem.this,"Item Added to MyCart",Toast.LENGTH_SHORT).show();
            }
        });
          /*  sheetview.findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SingleItem.this,"Share is Clicked",Toast.LENGTH_SHORT).show();
                }
            });
            sheetview.findViewById(R.id.comment).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SingleItem.this,"Comment is Clicked",Toast.LENGTH_SHORT).show();
                }
            });
            sheetview.findViewById(R.id.programming).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SingleItem.this,"Programming is Clicked",Toast.LENGTH_SHORT).show();
                }
            });
            */

            bottomSheetDialog.setContentView(sheetview);
            bottomSheetDialog.show();


    }
}