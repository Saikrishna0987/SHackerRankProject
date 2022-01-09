package com.company;

import com.company.model.*;
import com.company.service.FileService;
import com.company.service.Performoperation;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        try {
            List<Ingredients> ingredientsList = FileService.getIngredientsFromText();
            List<Receipe> receipes = FileService.getReceipesFromText();
            Account account = FileService.getIntialAmountFromText();
            System.out.println(ingredientsList.toString());
            System.out.println(account.getInitialAmount());
            System.out.println(receipes.toString());
            int n = Performoperation.displayMessage();
            List<Order> orderList = new ArrayList<>();
            List<UserOrders>userOrders=new ArrayList<>();
             Performoperation.performOperation(ingredientsList,receipes,account,n,orderList,userOrders);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }






}
