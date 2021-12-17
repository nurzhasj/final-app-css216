package com.hfad.finalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ColorAdapter extends ArrayAdapter<ColorItem> {

    public ColorAdapter(Context context, ArrayList<ColorItem> colorList){
        super(context, 0, colorList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.color_spinner_row, parent, false
            );
        }

        TextView color = convertView.findViewById(R.id.color);
        TextView colorName = convertView.findViewById(R.id.colorName);

        ColorItem curItem = getItem(position);

        if(curItem != null){
            color.setBackgroundResource(curItem.getColor());
            colorName.setText(curItem.getColorName());
        }

        return convertView;
    }
}
