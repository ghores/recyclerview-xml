package com.example.colortablerecycler.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colortablerecycler.R;
import com.example.colortablerecycler.adapter.ColorRecyclerAdapter;
import com.example.colortablerecycler.helper.SampleColorData;
import com.example.colortablerecycler.struct.ColorIndex;
import com.example.colortablerecycler.struct.ColorInfo;
import com.example.colortablerecycler.struct.ColorRecycleStruct;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView list_colors = findViewById(R.id.list_colors);
        list_colors.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<ColorRecycleStruct> colors = new ArrayList<>();

        char index = '-';
        LinkedHashMap<String, String> sampleColorData = SampleColorData.get();
        Set<String> keyset = sampleColorData.keySet();
        for (String key : keyset) {
            char firstChar = key.charAt(0);
            if (index != firstChar) {
                colors.add(new ColorIndex(firstChar));
                index = firstChar;
            }
            String value = sampleColorData.get(key);
            colors.add(new ColorInfo(key, value));
        }
        ColorRecyclerAdapter adapter = new ColorRecyclerAdapter(colors);
        list_colors.setAdapter(adapter);
    }
}
