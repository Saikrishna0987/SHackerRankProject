package com.company.service;

import com.company.model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Performoperation {
    private static final Scanner sc=new Scanner(System.in);
    public static void performOperation(List<Ingredients> ingredientsList, List<Receipe> receipes, Account account, int n, List<Order> orderList, List<UserOrders> userOrders) {

        try {
            switch (n) {
                case 1:
                    System.out.println("Available Ingredients in restaurant ");
                    for (Ingredients ingredients : ingredientsList) {
                        System.out.println("IngredientName" + " " + ingredients.getItem());
                        System.out.println("Quantity Available" + ingredients.getQuantity());
                    }
                    break;
                case 2:
                    System.out.println("You Can oder one ingredient  only...!");
                    System.out.println("Enter the ingredient :");
                    String item = sc.next();
                    System.out.println("Enter the quantity(minimum quantity is 1) :");
                    double quantity = sc.nextDouble();
                    Order order;
                    List<String> ingredientNames = new ArrayList<>();
                    for (Ingredients ingredient : ingredientsList) {
                        ingredientNames.add(ingredient.getItem());
                    }
                    if (ingredientNames.contains(item) && quantity != 0) {
                        order = new Order();
                        order.setItem(item);
                        order.setQuantity(quantity);
                        double purchase = getTheEstimationOfCost(ingredientsList, order);
                        boolean check = checkWhetherSufficientMoneyIsAvailableToOrder(purchase, account);
                        if (check) {
                            increaseTheQuantity(ingredientsList, order);
                            orderList.add(order);
                        } else {
                            System.out.println("Insufficient balance To order");
                        }
                    } else {
                        System.out.println("Appropriate oder !!!");
                    }
                    break;
                case 3:
                    System.out.println("Total Sales");
                    double sales = 0;
                    if (!userOrders.isEmpty()) {
                        for (UserOrders orders : userOrders) {
                            System.out.println(orders.getDishName() + "  " + orders.getQuantity() + "  " + orders.getCost());
                            sales += orders.getCost();
                            System.out.println("Total sales :-" + sales);
                        }
                    } else {
                        System.out.println("Oders are not placed...!!");
                    }
                    break;
                case 4:
                    System.out.println("Ingredients Order Placed ..!");
                    double totalExpenses = 0;
                    if (!orderList.isEmpty()) {
                        for (Order orders : orderList) {
                            System.out.println("Item" + "  " + "Quantity" + "  " + "Cost");
                            System.out.println(orders.getItem() + orders.getQuantity() + orders.getAmount());
                            totalExpenses += orders.getAmount();
                        }
                    } else {
                        System.out.println("No OderAt placed");
                    }
                    System.out.println("Total Expenditure :" + totalExpenses);
                    break;
                case 5:
                    double totalExpenditure = 0;
                    if (!orderList.isEmpty()) {
                        for (Order orders : orderList) {
                            totalExpenditure += orders.getAmount();
                        }
                    }
                    double gains = 0;
                    if (!userOrders.isEmpty()) {
                        for (UserOrders orders : userOrders) {
                            gains += orders.getCost();
                        }
                    }
                    System.out.println("InvestedAmount :" + account.getInitialAmount());
                    System.out.println("Total Expenditure Today : " + totalExpenditure);
                    System.out.println("Total Sales : " + gains);
                    double netProfit = account.getInitialAmount() + gains - totalExpenditure;
                    if (account.getInitialAmount() < netProfit) {
                        System.out.println("Profits" + (netProfit - account.getInitialAmount()));
                    } else {
                        System.out.println("Money in hand" + (netProfit - account.getInitialAmount()));

                    }


                    break;
                case 6:
                    System.out.println("Recipes Served In our Restaurant..!!");
                    List<String> receipeNames = new ArrayList<>();
                    for (Receipe receipe : receipes) {
                        System.out.println(receipe.getDish());
                        receipeNames.add(receipe.getDish());
                    }
                    System.out.println("Please enter your dish name (* you can oder one dish at once..!)");
                    String oderItem = sc.next();
                    double noOfItems = sc.nextDouble();
                    if (receipeNames.contains(oderItem)) {
                        Transaction transaction = chechkWhetherIngredientsAreSufficient(ingredientsList, receipes, oderItem, noOfItems);
                        if (transaction.isResult()) {
                            UserOrders userOrder = new UserOrders();
                            userOrder.setDishName(transaction.getMsg());
                            userOrder.setCost(transaction.getCostOftheOder());
                            userOrder.setQuantity(noOfItems);
                            userOrders.add(userOrder);
                            System.out.println("Your Oder is accepted...!!!");
                        } else {
                            System.out.println("Do you wish to Continue..?(yes/no)");
                            String userResponse = sc.next();
                            if (userResponse.equalsIgnoreCase("yes")){
                                 System.out.println("Do you wish to oder ingredients for "+oderItem+"yes/no?");
                                 String responseFromRetailer=sc.next();
                                 if(responseFromRetailer.equalsIgnoreCase("YES")){
                                      if(oderItem.equalsIgnoreCase("Coffee")||oderItem.equalsIgnoreCase("filterCoffee")){
                                         System.out.println("please oder Milk and Coffee");
                                          System.out.println("please enter no milk packets :");
                                          double milkQuantity=sc.nextDouble();
                                          System.out.println("please enter no Coffee packets :");
                                           double coffeePackets=sc.nextDouble();
                                           boolean res= purchaseTheOderForTwoIngredients("Milk",milkQuantity,"CoffeePowder",coffeePackets,ingredientsList,orderList,account);
                                           if(res){
                                              System.out.println("Ingredients oder is placed !!!");
                                           }else{
                                               System.out.println("Ingredients oder is not placed !!!");
                                               int returns= displayMessage();
                                              performOperation(ingredientsList,receipes,account,returns,orderList,userOrders);
                                           }

                                      }else if(oderItem.equalsIgnoreCase("TeaLeaves")){
                                          System.out.println("please oder Milk and TeaLeaves");
                                          System.out.println("please enter no milk packets :");
                                          double milkQuantity=sc.nextDouble();
                                          System.out.println("please enter no Tea packets :");
                                          double teaPackets=sc.nextDouble();
                                          boolean res= purchaseTheOderForTwoIngredients("Milk",milkQuantity,"TeaLeaves",teaPackets,ingredientsList,orderList,account);
                                          if(res){
                                              System.out.println("Ingredients oder is placed !!!");
                                          }else{
                                              System.out.println("Ingredients oder is not placed !!!");
                                              int returns= displayMessage();
                                              performOperation(ingredientsList,receipes,account,returns,orderList,userOrders);
                                          }
                                      }else if(oderItem.equalsIgnoreCase("Sandwich")){
                                          System.out.println("please oder Capsicum,Bread,Cheese and Tomato");
                                          System.out.println("please enter no quantity Capsicum required :");
                                          double capsiumQuantity=sc.nextDouble();
                                          System.out.println("please enter no quantity Bread required :");
                                          double breadQuantity=sc.nextDouble();
                                          System.out.println("please enter no quantity Cheese required :");
                                          double cheeseQuantity=sc.nextDouble();
                                          System.out.println("please enter no quantity Tomato required :");
                                          double tomatoQuantity=sc.nextDouble();
                                     boolean res =     purchaseTheOderForFourIngredients("Capsicum",capsiumQuantity,"Bread",breadQuantity,"Cheese",cheeseQuantity,"Tomato",tomatoQuantity,ingredientsList,orderList,account);
                                          if(res){
                                              System.out.println("Ingredients oder is placed !!!");
                                          }else{
                                              System.out.println("Ingredients oder is not placed !!!");
                                              int returns= displayMessage();
                                              performOperation(ingredientsList,receipes,account,returns,orderList,userOrders);
                                          }


                                      }else if(oderItem.equalsIgnoreCase("MasalaDosa")){
                                          System.out.println("please oder Batter,Spices,Onions,Potato");
                                          System.out.println("please enter no quantity Batter required :");
                                          double batterQuantity=sc.nextDouble();
                                          System.out.println("please enter no quantity Spices required :");
                                          double spicesQuantity=sc.nextDouble();
                                          System.out.println("please enter no quantity Onions required :");
                                          double onionsQuantity=sc.nextDouble();
                                          System.out.println("please enter no quantity Potato required :");
                                          double potatoQuantity=sc.nextDouble();
                                          boolean res =purchaseTheOderForFourIngredients("Batter",batterQuantity,"Spices",spicesQuantity,"Onions",onionsQuantity,"Potato",potatoQuantity,ingredientsList,orderList,account);
                                          if(res){
                                              System.out.println("Ingredients oder is placed !!!");
                                          }else{
                                              System.out.println("Ingredients oder is not placed !!!");
                                              int returns= displayMessage();
                                              performOperation(ingredientsList,receipes,account,returns,orderList,userOrders);
                                          }
                                      }else{
                                          System.out.println("BackToMainMenu");
                                          int returnMenu=displayMessage();
                                          performOperation(ingredientsList,receipes,account,returnMenu,orderList,userOrders);
                                      }
                                 }else{
                                     System.out.println("Oder is not placed ... ! items are sold out..!");
                                     int returnMenu=displayMessage();
                                     performOperation(ingredientsList,receipes,account,returnMenu,orderList,userOrders);
                                 }
                            }else{
                                int backToMenu=displayMessage();
                                performOperation(ingredientsList,receipes,account,backToMenu,orderList,userOrders);
                            }
                        }

                    } else {
                        System.out.println("Wrong oder Placed...!!");
                    }
                   break;
                case 7:
                    System.exit(0);
                default:
                    throw new IllegalStateException("Unexpected value: " + n);
            }
           int repeat= displayMessage();
            performOperation(ingredientsList,receipes,account,repeat,orderList,userOrders);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static boolean purchaseTheOderForFourIngredients(String capsicum, double capsiumQuantity, String bread, double breadQuantity, String cheese, double cheeseQuantity, String tomato, double tomatoQuantity, List<Ingredients> ingredientsList, List<Order> orderList, Account account) {
        boolean success=false;
        try{
            Order order1=new Order();
            order1.setItem(capsicum);
            order1.setQuantity(capsiumQuantity);
            double purchasedAmount=getTheEstimationOfCost(ingredientsList,order1);
            boolean check1= checkWhetherSufficientMoneyIsAvailableToOrder(purchasedAmount, account);
            if(check1){
                increaseTheQuantity(ingredientsList, order1);
                orderList.add(order1);
                System.out.println(order1.getItem()+"is placed successFully");
                success=true;
            }else{
                System.out.println("Can't place oder ....!!!");
                return success;
            }
            Order order2=new Order();
            order2.setItem(bread);
            order2.setQuantity(breadQuantity);
            double purchasedAmount2=getTheEstimationOfCost(ingredientsList,order2);
            boolean check2=checkWhetherSufficientMoneyIsAvailableToOrder(purchasedAmount2, account);
            if(check2){
                increaseTheQuantity(ingredientsList, order2);
                orderList.add(order2);
                System.out.println(order2.getItem()+"is placed successFully");
                success=true;
            }else{
                System.out.println("Can't place oder ....!!!");
                success=false;
            }
            Order order3=new Order();
            order3.setItem(cheese);
            order3.setQuantity(cheeseQuantity);
            double purchasedAmount3=getTheEstimationOfCost(ingredientsList,order3);
            boolean check3= checkWhetherSufficientMoneyIsAvailableToOrder(purchasedAmount3, account);
            if(check3){
                increaseTheQuantity(ingredientsList, order3);
                orderList.add(order3);
                System.out.println(order3.getItem()+"is placed successFully");
                success=true;
            }else{
                System.out.println("Can't place oder ....!!!");
                return success;
            }
            Order order4=new Order();
            order4.setItem(tomato);
            order4.setQuantity(tomatoQuantity);
            double purchasedAmount4=getTheEstimationOfCost(ingredientsList,order4);
            boolean check4=checkWhetherSufficientMoneyIsAvailableToOrder(purchasedAmount4, account);
            if(check4){
                increaseTheQuantity(ingredientsList, order2);
                orderList.add(order4);
                System.out.println(order4.getItem()+"is placed successFully");
                success=true;
            }else{
                System.out.println("Can't place oder ....!!!");
                success=false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }

    private static boolean purchaseTheOderForTwoIngredients(String milk, double milkQuantity, String coffeePowder, double coffeePackets, List<Ingredients> ingredientsList, List<Order> orderList,Account account) {
   boolean success=false;
    try{
        Order order1=new Order();
        order1.setItem(milk);
        order1.setQuantity(milkQuantity);
        double purchasedAmount=getTheEstimationOfCost(ingredientsList,order1);
        boolean check1= checkWhetherSufficientMoneyIsAvailableToOrder(purchasedAmount, account);
        if(check1){
            increaseTheQuantity(ingredientsList, order1);
            orderList.add(order1);
            System.out.println(order1.getItem()+"is placed successFully");
            success=true;
        }else{
            System.out.println("Can't place oder ....!!!");
           return success;
        }
        Order order2=new Order();
        order2.setItem(coffeePowder);
        order2.setQuantity(coffeePackets);
        double purchasedAmount1=getTheEstimationOfCost(ingredientsList,order2);
        boolean check2=checkWhetherSufficientMoneyIsAvailableToOrder(purchasedAmount1, account);
        if(check2){
            increaseTheQuantity(ingredientsList, order2);
            orderList.add(order2);
            System.out.println(order1.getItem()+"is placed successFully");
            success=true;
        }else{
            System.out.println("Can't place oder ....!!!");
            success=false;
        }
    }catch (Exception e){
        e.printStackTrace();
    }
    return success;
    }


    private static Transaction chechkWhetherIngredientsAreSufficient(List<Ingredients> ingredientsList, List<Receipe> receipes, String oderItem, double noOfOrders) {
        Transaction transaction=null;
        try {
            for (Receipe receipe : receipes) {
                if (receipe.getDish().equals(oderItem)) {

                    if (receipe.getDish().equals("Coffee") || receipe.getDish().equals("FilterCoffee ") || receipe.getDish().equals("Tea")) {
                        String ingredient1 = receipe.getIngredient1();
                        double quantity1 = receipe.getUnits1() * noOfOrders;
                        String ingredient2 = receipe.getIngredient2();
                        double quantity2 = receipe.getUnits2() * noOfOrders;
                        boolean result1 = checkTheQuantityIsSufficient(ingredient1, quantity1, ingredientsList);
                        boolean result2 = checkTheQuantityIsSufficient(ingredient2, quantity2, ingredientsList);
                        if (result1 && result2) {
                            Ingredients ingredientUpdated1 = decreaseTheQuantity(ingredient1, quantity1, ingredientsList);
                            double costOfIngredientUpdated1=ingredientUpdated1.getPrice()*quantity1;
                            updatedIngredientsList(ingredientUpdated1, ingredientsList, ingredient1);
                            Ingredients ingredientUpdated2 = decreaseTheQuantity(ingredient2, quantity2, ingredientsList);
                            double costOfIngredientUpdated2=ingredientUpdated1.getPrice()*quantity2;
                            updatedIngredientsList(ingredientUpdated2, ingredientsList, ingredient2);
                            double cost=costOfIngredientUpdated1+costOfIngredientUpdated2;
                            transaction=new Transaction();
                            transaction.setMsg(receipe.getDish());
                            transaction.setResult(true);
                            transaction.setCostOftheOder(cost);
                            return transaction;
                        } else {
                            System.out.println("Insufficient Ingredients...!!!");
                            transaction=new Transaction();
                            transaction.setMsg("Insufficient Ingredients...!!!");
                            transaction.setResult(false);
                            transaction.setCostOftheOder(0);
                            return transaction;

                        }
                    } else if (receipe.getDish().equals("MasalaDosa") || receipe.getDish().equals("Sandwich")) {
                        String ingredient1 = receipe.getIngredient1();
                        double quantity1 = receipe.getUnits1() * noOfOrders;
                        String ingredient2 = receipe.getIngredient2();
                        double quantity2 = receipe.getUnits2() * noOfOrders;
                        String ingredient3 = receipe.getIngredient3();
                        double quantity3 = receipe.getUnits3() * noOfOrders;
                        String ingredient4 = receipe.getIngredient4();
                        double quantity4 = receipe.getUnits4() * noOfOrders;
                        boolean result1 = checkTheQuantityIsSufficient(ingredient1, quantity1, ingredientsList);
                        boolean result2 = checkTheQuantityIsSufficient(ingredient2, quantity2, ingredientsList);
                        boolean result3 = checkTheQuantityIsSufficient(ingredient3, quantity3, ingredientsList);
                        boolean result4 = checkTheQuantityIsSufficient(ingredient4, quantity4, ingredientsList);
                        if (result1 && result2 && result3 && result4) {
                            Ingredients ingredientUpdated1 = decreaseTheQuantity(ingredient1, quantity1, ingredientsList);
                            double costOfIngredientUpdated1=ingredientUpdated1.getPrice()*quantity1;
                            updatedIngredientsList(ingredientUpdated1, ingredientsList, ingredient1);
                            Ingredients ingredientUpdated2 = decreaseTheQuantity(ingredient2, quantity2, ingredientsList);
                            double costOfIngredientUpdated2=ingredientUpdated2.getPrice()*quantity2;
                            updatedIngredientsList(ingredientUpdated2, ingredientsList, ingredient2);
                            Ingredients ingredientUpdated3 = decreaseTheQuantity(ingredient3, quantity3, ingredientsList);
                            double costOfIngredientUpdated3=ingredientUpdated3.getPrice()*quantity3;
                            updatedIngredientsList(ingredientUpdated3, ingredientsList, ingredient3);
                            Ingredients ingredientUpdated4 = decreaseTheQuantity(ingredient4, quantity4, ingredientsList);
                            double costOfIngredientUpdated4=ingredientUpdated4.getPrice()*quantity4;
                            updatedIngredientsList(ingredientUpdated4, ingredientsList, ingredient4);
                            double costOfTheProduct=costOfIngredientUpdated1+costOfIngredientUpdated2+costOfIngredientUpdated3+costOfIngredientUpdated4;
                            transaction=new Transaction();
                            transaction.setMsg(receipe.getDish());
                            transaction.setResult(true);
                            transaction.setCostOftheOder(costOfTheProduct);
                            return transaction;
                        }else{
                            System.out.println("Invalid items ..!!");
                            System.out.println("InValidOder...!!!");
                            transaction=new Transaction();
                            transaction.setMsg("InValidOder...!!!");
                            transaction.setResult(false);
                            transaction.setCostOftheOder(0);
                            return transaction;
                        }
                    } else {
                        System.out.println("InValidOder...!!!");
                        transaction=new Transaction();
                        transaction.setMsg("InValidOder...!!!");
                        transaction.setResult(false);
                        transaction.setCostOftheOder(0);
                        return transaction;
                    }


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transaction;
    }




    private static void updatedIngredientsList(Ingredients ingredientC, List<Ingredients> ingredientsList, String ingredient) {
        try {
            Iterator<Ingredients> ingredientsIterator = ingredientsList.listIterator();
            while (ingredientsIterator.hasNext()) {
                Ingredients ingredient23 = ingredientsIterator.next();
                if (ingredient23.getItem().equals(ingredient)) {
                    ingredientsIterator.remove();
                    break;
                }
            }
            ingredientsList.add(ingredientC);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Ingredients decreaseTheQuantity(String ingredient1, double quantity1, List<Ingredients> ingredientsList) {
        try {

            for (Ingredients ingredients : ingredientsList) {
                if (ingredients.getItem().equals(ingredient1)) {
                    double updatedQuantity = ingredients.getQuantity() - quantity1;
                    ingredients.setQuantity(updatedQuantity);
                    return ingredients;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean checkTheQuantityIsSufficient(String ingredient1, double quantity1, List<Ingredients> ingredientsList) {
        boolean sucess = false;
        try {
            for (Ingredients ingredients : ingredientsList) {
                if (ingredients.getItem().equals(ingredient1)) {
                    double intialIngredients = ingredients.getQuantity();
                    if (intialIngredients > quantity1) {
                        sucess = true;
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sucess;
    }

    private static void increaseTheQuantity(List<Ingredients> ingredientsList, Order order) {
        try {
            for (Ingredients ingredients : ingredientsList) {
                if (ingredients.getItem().equals(order.getItem())) {
                    double quantity = ingredients.getQuantity() + order.getQuantity();
                    ingredients.setQuantity(quantity);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean checkWhetherSufficientMoneyIsAvailableToOrder(double purchase, Account account) {
        boolean success = false;
        try {
            double amount = account.getInitialAmount() - purchase;
            if (amount > 0) {
                account.setInitialAmount(amount);
                success = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    private static double getTheEstimationOfCost(List<Ingredients> ingredientsList, Order order) {
        double cost = 0;
        try {
            for (Ingredients ingredients : ingredientsList) {
                if ((ingredients.getItem().equals(order.getItem()))) {
                    double unitcost = ingredients.getPrice();
                    cost = unitcost * order.getQuantity();
                    order.setAmount(cost);
                    break;
                }
            }
            return cost;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cost;
    }
    public static  int displayMessage(){
        System.out.println("Menu");
        System.out.println("1. View Available Ingredients\n2. Order Specific Ingredients\n3. View Total Sales\n4. View Total Expenses\n5. View Net Profit\n6. Place Order\n7. Exit from the program.\n");
        System.out.println("Please select  your option in number *");
        int n=sc.nextInt();
        return n;
    }
}
