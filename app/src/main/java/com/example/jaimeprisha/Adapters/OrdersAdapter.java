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


public class OrdersAdapter extends FirebaseRecyclerAdapter<BookingModel, com.example.jaimeprisha.Adapters.OrdersAdapter.myviewholder>{


    Context context;
    DatabaseReference databaseReference;
    private DatabaseReference databaseRef5;


    public OrdersAdapter(@NonNull FirebaseRecyclerOptions<BookingModel> options, Context context) {
        super(options);
        this.context = context;


    }




    @NonNull
    @Override
    public com.example.jaimeprisha.Adapters.OrdersAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favoritesitemslayout, parent, false);

        return new com.example.jaimeprisha.Adapters.OrdersAdapter.myviewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull BookingModel model) {
        //holder.image.setText("Link: "+model.getItemimage());
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        holder.name.setText(" "+model.getItemname());
        holder.desc.setText(" "+model.getItemdesc());
        holder.price.setText("₹"+model.getSellingprice()+"/-");

        Glide.with(context).load(model.getItemimage()).into(holder.image);

        



        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(currentuser);
        final DatabaseReference itemRef = getRef(position);
        final String myKey = itemRef.getKey();
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Fav").child(myKey).removeValue();
                Toast.makeText(context,model.getItemname()+"  is Deleted From Your Favorites",Toast.LENGTH_SHORT).show();
            }
        });
        holder.show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, StatusScreen.class);
                i.putExtra("itemid",myKey);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView name, desc,price,delete;

        ImageView image;
        Button show;
        LottieAnimationView lottie;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.Specname);
            desc = (TextView) itemView.findViewById(R.id.Specdesc);

            price = (TextView) itemView.findViewById(R.id.Specprice);
            image= (ImageView) itemView.findViewById(R.id.specimage);
            delete=(TextView)itemView.findViewById(R.id.itemdelete);
            show=itemView.findViewById(R.id.showstatus);
            delete.setVisibility(View.GONE);
            show.setVisibility(View.VISIBLE);


        }
    }



}