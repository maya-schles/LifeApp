package com.r3dtech.life.logic.avatar;

import java.io.Serializable;

public interface Inventory extends Serializable{
    int getMoney();
    void addMoney(int amountToAdd);
    void payMoney(int amountToPay);
}
