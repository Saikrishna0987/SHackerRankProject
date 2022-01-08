package com.company.model;

public class Receipe {
    private String dish;
    private  String ingredient1;
    private  double units1;
    private  String ingredient2;
    private  double units2;
    private  String ingredient3;
    private  double units3;
    private  String ingredient4;
    private  double units4;

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getIngredient1() {
        return ingredient1;
    }

    public void setIngredient1(String ingredient1) {
        this.ingredient1 = ingredient1;
    }

    public double getUnits1() {
        return units1;
    }

    public void setUnits1(double units1) {
        this.units1 = units1;
    }

    public String getIngredient2() {
        return ingredient2;
    }

    public void setIngredient2(String ingredient2) {
        this.ingredient2 = ingredient2;
    }

    public double getUnits2() {
        return units2;
    }

    public void setUnits2(double units2) {
        this.units2 = units2;
    }

    public String getIngredient3() {
        return ingredient3;
    }

    public void setIngredient3(String ingredient3) {
        this.ingredient3 = ingredient3;
    }

    public double getUnits3() {
        return units3;
    }

    public void setUnits3(double units3) {
        this.units3 = units3;
    }

    public String getIngredient4() {
        return ingredient4;
    }

    public void setIngredient4(String ingredient4) {
        this.ingredient4 = ingredient4;
    }

    public double getUnits4() {
        return units4;
    }

    public void setUnits4(double units4) {
        this.units4 = units4;
    }

    @Override
    public String toString() {
        return "Receipe{" +
                "dish='" + dish + '\'' +
                ", ingredient1='" + ingredient1 + '\'' +
                ", units1=" + units1 +
                ", ingredient2='" + ingredient2 + '\'' +
                ", units2=" + units2 +
                ", ingredient3='" + ingredient3 + '\'' +
                ", units3=" + units3 +
                ", ingredient4='" + ingredient4 + '\'' +
                ", units4=" + units4 +
                '}';
    }
}
