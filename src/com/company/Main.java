package com.company;

import com.company.model.Account;
import com.company.model.Ingredients;
import com.company.model.Receipe;
import com.company.service.FileService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
     List<Ingredients> ingredientsList= FileService.getIngredientsFromText();
     List<Receipe> receipes=FileService.getReceipesFromText();
     Account account= FileService.getIntialAmountFromText();
     System.out.println(ingredientsList.toString());
     System.out.println(account.getInitialAmount());
     System.out.println(receipes.toString());

     System.out.println("Menu");
     System.out.println("1. View Available Ingredients\n2. Order Specific Ingredients\n3. View Total Sales\n4. View Total Expenses\n5. View Net Profit\n6. Place Order\n7. Exit from the program.\n");
        Scanner sc =new Scanner(System.in);
        System.out.println("Please select  your option in number *");
        int n =sc.nextInt();
        switch (n){
          case  1:
                System.out.println("Available Ingredients in restaurant ");
                for(Ingredients ingredients: ingredientsList){
                    System.out.println("IngredientName"+" "+ingredients.getItem());
                    System.out.println("Quantity Available"+ingredients.getQuantity());
                }

        }



    }


}
