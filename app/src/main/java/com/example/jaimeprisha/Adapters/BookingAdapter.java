package com.example.jaimeprisha.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jaimeprisha.Models.ItemModel;
import com.example.jaimeprisha.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookingAdapter extends FirebaseRecyclerAdapter<ItemModel, com.example.jaimeprisha.Adapters.BookingAdapter.myviewholder> {


    Context context;
    private DatabaseReference databaseReference;
    String totalprice;
    int i=1;
    private DatabaseReference databaseRef5;


    public BookingAdapter(@NonNull FirebaseRecyclerOptions<ItemModel> options, Context context) {
        super(options);
        this.context = context;


    }




    @NonNull
    @Override
    public com.example.jaimeprisha.Adapters.BookingAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitemslayout, parent, false);

        return new com.example.jaimeprisha.Adapters.BookingAdapter.myviewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull ItemModel model) {
        //holder.image.setText("Link: "+model.getItemimage());
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        holder.name.setText(" "+model.getItemname());
        holder.price.setText("₹"+model.getSellingprice()+"/-");
        holder.count.setText(model.getCount());






        Glide.with(context).load(model.getItemimage()).into(holder.image);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(currentuser);
        final DatabaseReference itemRef = getRef(position);
        final String myKey = itemRef.getKey();




    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView name,price,delete,minus,plus,count;

        ImageView image;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.Specname);

            price = (TextView) itemView.findViewById(R.id.Specprice);
            image= (ImageView) itemView.findViewById(R.id.specimage);
            delete=itemView.findViewById(R.id.itemdelete);
            minus=itemView.findViewById(R.id.minus);
            count=itemView.findViewById(R.id.count);

            plus=itemView.findViewById(R.id.plus);
            delete.setVisibility(View.GONE);
            minus.setVisibility(View.GONE);
            plus.setVisibility(View.GONE);


        }
    }



}