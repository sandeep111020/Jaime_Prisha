package com.example.jaimeprisha.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
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

public class CartAdapter extends FirebaseRecyclerAdapter<ItemModel, com.example.jaimeprisha.Adapters.CartAdapter.myviewholder> {


    Context context;
    private DatabaseReference databaseReference;
    String totalprice;
    int i=1;
    int total=0;
    int itemcount=0;
    String itemkey;
    private DatabaseReference databaseRef5;


    public CartAdapter(@NonNull FirebaseRecyclerOptions<ItemModel> options, Context context) {
        super(options);
        this.context = context;


    }




    @NonNull
    @Override
    public com.example.jaimeprisha.Adapters.CartAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitemslayout, parent, false);

        return new com.example.jaimeprisha.Adapters.CartAdapter.myviewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull ItemModel model) {
        //holder.image.setText("Link: "+model.getItemimage());
        itemkey= getRef(position).getKey();

        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        holder.name.setText(" "+model.getItemname());
        holder.desc.setText(" "+model.getItemdesc());
        holder.price.setText("₹"+model.getSellingprice()+"/-");
        holder.count.setText(model.getCount());






        Glide.with(context).load(model.getItemimage()).into(holder.image);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(currentuser);
        final DatabaseReference itemRef = getRef(position);
        final String myKey = itemRef.getKey();
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i<=1){

                } else {
                    i--;
                    itemcount--;
                    Integer temp = Integer.parseInt(model.getSellingprice());
                    temp=((temp*(i+1))-temp);
                    holder.price.setText("₹"+String.valueOf(temp)+"/-");
                    total=total-Integer.parseInt(model.getSellingprice());
                    Intent intent = new Intent("message_subject_intent");
                    intent.putExtra("name" , String.valueOf(total));
                    intent.putExtra("count",String.valueOf(itemcount));
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    holder.count.setText(String.valueOf(i));
                    ItemModel userProfileInfo = new ItemModel(model.getItemname(),model.getItemdesc(),model.getItemprice(),model.getItemtype(),model.getItemcheck(), model.getItemimage(),model.getModel(),model.getSellingprice(),String.valueOf(i));
                    databaseReference.child("Buffer").child(itemkey).setValue(userProfileInfo);
           /*         ItemModel userProfileInfo = new ItemModel(model.getItemname(),model.getItemdesc(),model.getItemprice(),model.getItemtype(),model.getItemcheck(), model.getItemimage(),model.getModel(),model.getSellingprice(),String.valueOf(i));
                    databaseReference.child("MyCart").child(itemkey).setValue(userProfileInfo);*/

                }

            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                itemcount++;
                Integer temp = Integer.parseInt(model.getSellingprice());
                temp=temp*i;
                holder.price.setText("₹"+String.valueOf(temp)+"/-");
                holder.count.setText(String.valueOf(i));
                total=total+Integer.parseInt(model.getSellingprice());
                Intent intent = new Intent("message_subject_intent");
                intent.putExtra("name" , String.valueOf(total));
                intent.putExtra("count",String.valueOf(itemcount));
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                ItemModel userProfileInfo = new ItemModel(model.getItemname(),model.getItemdesc(),model.getItemprice(),model.getItemtype(),model.getItemcheck(), model.getItemimage(),model.getModel(),model.getSellingprice(),String.valueOf(i));
                databaseReference.child("Buffer").child(itemkey).setValue(userProfileInfo);
 /*               ItemModel userProfileInfo = new ItemModel(model.getItemname(),model.getItemdesc(),model.getItemprice(),model.getItemtype(),model.getItemcheck(), model.getItemimage(),model.getModel(),model.getSellingprice(),String.valueOf(i));
                databaseReference.child("MyCart").child(itemkey).setValue(userProfileInfo);*/
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* databaseRef5 = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser).child("Final");
                databaseRef5.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        String val= snapshot.child("finalprice").getValue(String.class);
                        int ival = Integer.parseInt(val) - Integer.parseInt(model.getItemprice());
                        totalprice=String.valueOf(ival);
                        FinalValue finalval= new FinalValue(totalprice);
                        databaseReference.child("Final").setValue(finalval);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/
                itemcount--;
                total=total-Integer.parseInt(model.getSellingprice());
                Intent intent = new Intent("message_subject_intent");
                intent.putExtra("count",String.valueOf(itemcount));
                intent.putExtra("name" , String.valueOf(total));
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                databaseReference.child("MyCart").child(myKey).removeValue();
                Toast.makeText(context,model.getItemname()+"  is Deleted From Your Cart",Toast.LENGTH_SHORT).show();

            }
        });

        itemcount++;
        total=total+Integer.parseInt(model.getSellingprice());
        Intent intent = new Intent("message_subject_intent");
        intent.putExtra("name" , String.valueOf(total));
        intent.putExtra("count",String.valueOf(itemcount));
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        ItemModel userProfileInfo = new ItemModel(model.getItemname(),model.getItemdesc(),model.getItemprice(),model.getItemtype(),model.getItemcheck(), model.getItemimage(),model.getModel(),model.getSellingprice(),String.valueOf(i));
        databaseReference.child("Buffer").child(itemkey).setValue(userProfileInfo);


    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView name, desc,price,delete,minus,plus,count;

        ImageView image;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.Specname);
            desc = (TextView) itemView.findViewById(R.id.Specdesc);

            price = (TextView) itemView.findViewById(R.id.Specprice);
            image=  itemView.findViewById(R.id.specimage);
            delete=(TextView) itemView.findViewById(R.id.itemdelete);
            minus=itemView.findViewById(R.id.minus);
            plus=itemView.findViewById(R.id.plus);
            count=itemView.findViewById(R.id.count);


        }
    }



}