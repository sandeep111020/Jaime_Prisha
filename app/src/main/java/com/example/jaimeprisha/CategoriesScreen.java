package com.example.jaimeprisha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.example.jaimeprisha.Adapters.ItemTypeAdapter;
import com.example.jaimeprisha.Models.ItemTypeModel;

import java.util.ArrayList;

public class CategoriesScreen extends AppCompatActivity {

    private GridView coursesGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_screen);
        coursesGV = findViewById(R.id.gridview);

        ArrayList<ItemTypeModel> ModelArrayList = new ArrayList<ItemTypeModel>();
        ModelArrayList.add(new ItemTypeModel("EarRings",R.drawable.earrings,"Earrings"));
        ModelArrayList.add(new ItemTypeModel("Necklace",R.drawable.necklace,"Necklace"));

        ModelArrayList.add(new ItemTypeModel("FingerRings",R.drawable.ring,"Finger Rings"));
        ModelArrayList.add(new ItemTypeModel("HairBandsAndClips",R.drawable.hairbands,"Hair Bands"));
        ModelArrayList.add(new ItemTypeModel("NosePin",R.drawable.noepin,"Nose Pin"));
        ModelArrayList.add(new ItemTypeModel("KeyRings",R.drawable.keyrings__1_,"Key Rings"));
        ModelArrayList.add(new ItemTypeModel("Sets",R.drawable.sets,"Sets"));
        ModelArrayList.add(new ItemTypeModel("Pendant",R.drawable.pendant,"Pendant"));



        ItemTypeAdapter adapter = new ItemTypeAdapter(this, ModelArrayList);
        coursesGV.setAdapter(adapter);
    }
}