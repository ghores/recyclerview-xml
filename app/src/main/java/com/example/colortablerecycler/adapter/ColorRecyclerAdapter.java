package com.example.colortablerecycler.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.colortablerecycler.R;
import com.example.colortablerecycler.struct.ColorIndex;
import com.example.colortablerecycler.struct.ColorInfo;
import com.example.colortablerecycler.struct.ColorRecycleStruct;

import java.util.ArrayList;

public class ColorRecyclerAdapter extends RecyclerView.Adapter<ColorRecyclerAdapter.ViewHolder> {

    public static final int TYPE_COLOR = 0;
    public static final int TYPE_INDEX = 1;

    private ArrayList<ColorRecycleStruct> list;

    public ColorRecyclerAdapter(ArrayList<ColorRecycleStruct> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        ColorRecycleStruct item = list.get(position);
        if (item instanceof ColorIndex) {
            return TYPE_INDEX;
        } else {
            return TYPE_COLOR;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = TYPE_COLOR;
        switch (viewType) {
            case TYPE_COLOR:
                layoutId = R.layout.adapter_color;
                break;
            case TYPE_INDEX:
                layoutId = R.layout.adapter_index;
                break;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ColorRecycleStruct item = list.get(position);
        int type = holder.getItemViewType();
        switch (type) {
            case TYPE_COLOR:
                ColorInfo colorObject = (ColorInfo) item;
                int color = Color.parseColor("#" + colorObject.value);
                holder.root.setBackgroundColor(color);
                holder.txt_name.setText(colorObject.name);
                holder.txt_value.setText(colorObject.value);
                break;
            case TYPE_INDEX:
                ColorIndex indexObject = (ColorIndex) item;
                holder.txt_name.setText("" + indexObject.index);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewGroup root;
        TextView txt_name;
        TextView txt_value;

        public ViewHolder(View view) {
            super(view);
            root = (ViewGroup) view;
            txt_name = view.findViewById(R.id.txt_name);
            txt_value = view.findViewById(R.id.txt_value);
        }
    }
}
