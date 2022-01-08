package com.company.service;

import com.company.model.Account;
import com.company.model.Ingredients;
import com.company.model.Receipe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FileService {
    public static List<Ingredients> getIngredientsFromText() {
        List<Ingredients> ingredients = null;
        try {
            FileReader fileReader = new FileReader("C:\\Users\\Kapture\\Downloads\\ingredients.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            reader.readLine();
            String txt;
            ingredients = new ArrayList<>();
            while ((txt = reader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(txt);
                Ingredients ingredient = new Ingredients();
                ingredient.setItem(stringTokenizer.nextToken());
                ingredient.setQuantity(Double.parseDouble(stringTokenizer.nextToken()));
                ingredient.setPrice(Double.parseDouble(stringTokenizer.nextToken()));
                ingredients.add(ingredient);
            }
            reader.close();
            return ingredients;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    public static Account getIntialAmountFromText() {
        Account account = null;
        try {
            FileReader fileReader = new FileReader("C:\\Users\\Kapture\\Downloads\\accounts.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            String txt = reader.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(txt);
            account = new Account();
            account.setInitialAmount(Double.parseDouble(stringTokenizer.nextToken()));
            reader.close();
            return account;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    public static List<Receipe> getReceipesFromText() {
        List<Receipe> receipes = null;
        try {
            FileReader fileReader = new FileReader("C:\\Users\\Kapture\\Downloads\\receipe.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            String txt;
            receipes = new ArrayList<>();
            while ((txt = reader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(txt);
                Receipe receipe = new Receipe();
                receipe.setDish(stringTokenizer.nextToken());
                receipe.setIngredient1(stringTokenizer.nextToken());
                receipe.setUnits1(Double.parseDouble(stringTokenizer.nextToken()));
                receipe.setIngredient2(stringTokenizer.nextToken());
                receipe.setUnits2(Double.parseDouble(stringTokenizer.nextToken()));
                receipe.setIngredient3(stringTokenizer.hasMoreTokens()?stringTokenizer.nextToken():"");
                receipe.setUnits3(stringTokenizer.hasMoreTokens()?Double.parseDouble(stringTokenizer.nextToken()):0);
                receipe.setIngredient4(stringTokenizer.hasMoreTokens()?stringTokenizer.nextToken():"");
                receipe.setUnits4(stringTokenizer.hasMoreTokens()?Double.parseDouble(stringTokenizer.nextToken()):0);
                receipes.add(receipe);
            }
            return receipes;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return receipes;
    }
}
