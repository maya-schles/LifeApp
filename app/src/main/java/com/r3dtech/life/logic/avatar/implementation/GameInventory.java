package com.r3dtech.life.logic.avatar.implementation;


import com.r3dtech.life.logic.avatar.Inventory;

public class GameInventory implements Inventory {
    private int money;

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public void addMoney(int amountToAdd) {
        money += amountToAdd;
    }

    @Override
    public void payMoney(int amountToPay) {
        money -= amountToPay;
    }
}
