package com.example.jaimeprisha.Adapters;




import android.content.Context;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import com.example.jaimeprisha.Models.ItemModel;
import com.example.jaimeprisha.R;
import com.example.jaimeprisha.SingleItem;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ItemsAdapter extends FirebaseRecyclerAdapter<ItemModel, ItemsAdapter.myviewholder> {


    Context context;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    String Sdate;
    int i=0;
    String itemkey;

    private DatabaseReference databaseRef,databaseRef4;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseRef5;


    public ItemsAdapter(@NonNull FirebaseRecyclerOptions<ItemModel> options, Context context) {
        super(options);
        this.context = context;


    }




    @NonNull
    @Override
    public com.example.jaimeprisha.Adapters.ItemsAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdisplaylayout, parent, false);

        return new com.example.jaimeprisha.Adapters.ItemsAdapter.myviewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull ItemModel model) {
        holder.name.setText(" "+model.getItemname());
        holder.price.setText("₹"+model.getSellingprice()+"/-");

        itemkey= getRef(position).getKey();

        Glide.with(context).load(model.getItemimage()).into(holder.image);
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(currentuser);
        holder.addfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                if (i%2!=0){
                    holder.addfav.setImageResource(R.drawable.ic_baseline_favorite_24);
                    ItemModel userProfileInfo = new ItemModel(model.getItemname(),model.getItemdesc(),model.getItemprice(),model.getItemtype(),model.getItemcheck(), model.getItemimage(),model.getModel(),model.getSellingprice(),"1");
                    String ImageUploadId = databaseReference.push().getKey();
                    databaseReference.child("Fav").child(itemkey).setValue(userProfileInfo);
                    Toast.makeText(context,"Item Added to Favorites",Toast.LENGTH_SHORT).show();
                }
                else if (i%2==0){
                    holder.addfav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    String ImageUploadId = databaseReference.push().getKey();
                    databaseReference.child("Fav").child(itemkey).removeValue();
                    Toast.makeText(context,"Item Removed From  Favorites",Toast.LENGTH_SHORT).show();
                }

            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, SingleItem.class);
                intent.putExtra("name",model.getItemname());
                intent.putExtra("image",model.getItemimage());
                intent.putExtra("desc",model.getItemdesc());
                intent.putExtra("price",model.getItemprice());
                intent.putExtra("sellingprice",model.getSellingprice());
                intent.putExtra("model",model.getModel());
                intent.putExtra("type",model.getItemtype());
                intent.putExtra("key",itemkey);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });
        holder.addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemModel userProfileInfo = new ItemModel(model.getItemname(),model.getItemdesc(),model.getItemprice(),model.getItemtype(),model.getItemcheck(), model.getItemimage(),model.getModel(),model.getSellingprice(),"1");
                String ImageUploadId = databaseReference.push().getKey();
                databaseReference.child("MyCart").child(itemkey).setValue(userProfileInfo);


                Toast.makeText(context,"Item Added to MyCart",Toast.LENGTH_SHORT).show();
            }
        });


    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView name, desc,price;

        ImageView addcart,addfav;

        ImageView image;
        LinearLayout layout;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.Specname);
            layout=itemView.findViewById(R.id.layoutclick);
            price = (TextView) itemView.findViewById(R.id.Specprice);
            image=  itemView.findViewById(R.id.imageview);
            addfav=(ImageView) itemView.findViewById(R.id.addfav);
            addcart=(ImageView) itemView.findViewById(R.id.addcartbtton);



        }
    }



}