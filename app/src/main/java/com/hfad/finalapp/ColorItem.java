package com.hfad.finalapp;

public class ColorItem {
    int color;
    String colorName;

    public ColorItem(int color, String colorName){
        this.color = color;
        this.colorName = colorName;
    }

    public int getColor(){
        return this.color;
    }

    public String getColorName(){
        return this.colorName;
    }
}
