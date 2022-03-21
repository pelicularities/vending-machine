package com.company;

import java.util.Map;
import java.util.Optional;

public class VendingMachine {
    private Map<Drink, Integer> drinks;
    private final int max;

    public VendingMachine(int max) {
        this.max = max;
    }

    public Map<Drink, Integer> getDrinks() {
        return drinks;
    }

    public void stockDrinks(Map<Drink, Integer> drinks) throws Exception {
        if (drinks.size() > max) {
            throw new Exception("Vending machine cannot hold more than " + max + " types of drinks");
        }
        this.drinks = drinks;
    }

    public void purchase(String drinkName) {
        Drink drink = getDrinkByName(drinkName).orElseThrow(() -> {
            throw new RuntimeException("No such drink available in vending machine");
        });
        int quantity = drinks.get(drink);
        drinks.put(drink, --quantity);
    }

    protected Optional<Drink> getDrinkByName(String drinkName) {
        for (Map.Entry<Drink, Integer> drinkEntry: drinks.entrySet()) {
            Drink drink = drinkEntry.getKey();
            if (drinkName.equals(drink.getName())) {
                return Optional.of(drink);
            }
        }
        return Optional.empty();
    }
}
