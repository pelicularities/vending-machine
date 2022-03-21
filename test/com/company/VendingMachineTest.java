package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {
    int max;

    Map<Drink, Integer> drinksMap;

    VendingMachine vendingMachine;

    @BeforeEach
    void setUp() {
        int max = 5;
        drinksMap = new HashMap<>();
        drinksMap.put(new Drink("Coca-Cola", 150), 12);
        drinksMap.put(new Drink("Sprite", 150), 12);
        drinksMap.put(new Drink("Root Beer", 200), 12);
        vendingMachine = new VendingMachine(max);
    }

    @Test
    void shouldStockDrinksSuccessfully() throws Exception {
        vendingMachine.stockDrinks(drinksMap);

        assertEquals(drinksMap, vendingMachine.getDrinks());
    }

    @Test
    void shouldNotStockMoreThanMaxNumberOfDrinks() {
        drinksMap.put(new Drink("Ice Lemon Tea", 150), 12);
        drinksMap.put(new Drink("Qoo Grape", 150), 12);
        drinksMap.put(new Drink("Ribena", 200), 12);

        VendingMachine vendingMachine = new VendingMachine(max);

        assertThrows(Exception.class, () -> vendingMachine.stockDrinks(drinksMap));
    }

    @Test
    void shouldDecrementQuantityWhenDrinkIsPurchased() throws Exception {
        String drinkName = "Coca-Cola";
        vendingMachine.stockDrinks(drinksMap);

        vendingMachine.purchase(drinkName);

        Map<Drink, Integer> actualDrinksMap = vendingMachine.getDrinks();
        Drink cocaCola = vendingMachine.getDrinkByName(drinkName).get();
        int cocaColaQuantity = actualDrinksMap.get(cocaCola);
        assertEquals(11, cocaColaQuantity);
    }
}