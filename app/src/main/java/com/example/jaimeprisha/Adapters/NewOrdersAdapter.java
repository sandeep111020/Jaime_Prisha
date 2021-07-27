package com.example.jaimeprisha.Adapters;
import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.airbnb.lottie.Lottie;
import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

import com.example.jaimeprisha.AddStatus;
import com.example.jaimeprisha.Models.BookingModel;
import com.example.jaimeprisha.Models.ItemModel;
import com.example.jaimeprisha.R;
import com.example.jaimeprisha.StatusScreen;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class NewOrdersAdapter extends FirebaseRecyclerAdapter<BookingModel, com.example.jaimeprisha.Adapters.NewOrdersAdapter.myviewholder>{


    Context context;
    DatabaseReference databaseReference;
    private DatabaseReference databaseRef5;


    public NewOrdersAdapter(@NonNull FirebaseRecyclerOptions<BookingModel> options, Context context) {
        super(options);
        this.context = context;


    }




    @NonNull
    @Override
    public com.example.jaimeprisha.Adapters.NewOrdersAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.neworderlayout, parent, false);

        return new com.example.jaimeprisha.Adapters.NewOrdersAdapter.myviewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull BookingModel model) {
        //holder.image.setText("Link: "+model.getItemimage());

        holder.name.setText(" "+model.getItemname());
        holder.desc.setText(" "+model.getItemdesc());
        holder.price.setText("₹"+model.getSellingprice()+"/-");
        holder.personname.setText("Name: "+model.getName());
        holder.number.setText("Number: "+model.getNumber());
        holder.address.setText("Address: "+model.getAddress());
        holder.area.setText("Area: "+model.getArea());
        holder.city.setText("City: "+model.getCity());
        holder.state.setText("State: "+model.getState());
        holder.pin.setText("Pin: "+model.getPin());
        holder.count.setText("Count: "+model.getCount());

        Glide.with(context).load(model.getItemimage()).into(holder.image);
        final DatabaseReference itemRef = getRef(position);
        final String myKey = itemRef.getKey();
        holder.place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, AddStatus.class);
                i.putExtra("userid",model.getUserid());
                i.putExtra("itemid",myKey);
                i.setFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });





    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView name, desc,price,personname,number,address,area,city,state,pin,count;

        ImageView image;
        Button place;
        LottieAnimationView lottie;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.Specname);
            desc = (TextView) itemView.findViewById(R.id.Specdesc);
            place=itemView.findViewById(R.id.place);
            personname=itemView.findViewById(R.id.personname);
            number=itemView.findViewById(R.id.personnumber);
            address=itemView.findViewById(R.id.personaddress);
            area=itemView.findViewById(R.id.personarea);
            city=itemView.findViewById(R.id.personcity);
            state=itemView.findViewById(R.id.personstate);
            pin=itemView.findViewById(R.id.personpin);
            count=itemView.findViewById(R.id.count);
            price = (TextView) itemView.findViewById(R.id.Specprice);
            image= (ImageView) itemView.findViewById(R.id.specimage);






        }
    }



}