package com.example.jaimeprisha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.example.jaimeprisha.Adapters.ItemTypeAdapter;
import com.example.jaimeprisha.Models.ItemTypeModel;

import java.util.ArrayList;

public class CollectionsScreen extends AppCompatActivity {

    private GridView coursesGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections_screen);
        coursesGV = findViewById(R.id.gridview);
        ArrayList<ItemTypeModel> ModelArrayList = new ArrayList<ItemTypeModel>();
        ModelArrayList.add(new ItemTypeModel("SemiPrecious",R.drawable.semiprecious,"Semi Precious"));
        ModelArrayList.add(new ItemTypeModel("LatestTrends",R.drawable.latest,"Latest Trends"));
        ModelArrayList.add(new ItemTypeModel("FashionJewelry",R.drawable.fashionjwel,"Fashion Jewelry"));
        ModelArrayList.add(new ItemTypeModel("BeatenSilver",R.drawable.beatensilver,"Beaten Silver"));
        ModelArrayList.add(new ItemTypeModel("Kundan",R.drawable.kundan,"Kundan"));
        ModelArrayList.add(new ItemTypeModel("Minakari",R.drawable.minkari,"Minakari"));
        ModelArrayList.add(new ItemTypeModel("IndianEthnicjewelry",R.drawable.indian,"Indian Ethnic Jewelry"));
        ModelArrayList.add(new ItemTypeModel("AgateNaturalStones",R.drawable.agrigatenaturastones,"Agate Natural Stones"));




        ItemTypeAdapter adapter = new ItemTypeAdapter(this, ModelArrayList);
        coursesGV.setAdapter(adapter);
    }
}