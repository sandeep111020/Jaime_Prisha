package com.example.jaimeprisha.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.jaimeprisha.DisplayItems;
import com.example.jaimeprisha.Models.ItemTypeModel;
import com.example.jaimeprisha.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemTypeAdapter extends ArrayAdapter<ItemTypeModel> {
    public ItemTypeAdapter(@NonNull Context context, ArrayList<ItemTypeModel> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.categorielayout, parent, false);
        }
        ItemTypeModel model = getItem(position);
        TextView TV = listitemView.findViewById(R.id.texview);
        CircleImageView IV = listitemView.findViewById(R.id.profile_image);
        CardView cardView =listitemView.findViewById(R.id.card_login);
        String type=model.getName();
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), DisplayItems.class);
                i.putExtra("type",type);
                getContext().startActivity(i);
            }
        });
        TV.setText(model.getTitle());
        IV.setImageResource(model.getImgid());

        return listitemView;
    }
}