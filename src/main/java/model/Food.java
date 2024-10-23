/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.UUID;


/**
 *
 * @author Admin
 */
public class Food {
    private String name;
    private UUID fid;
    private int hungerIncrease;
    private int price;
   
    public Food(String name, int hungerIncrease, int price) {
        UUID uuid = UUID.randomUUID();
        this.setName(name);
        this.setHungerIncrease(hungerIncrease);
        this.setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getFid() {
        return fid;
    }

    public void setFid(UUID fid) {
        this.fid = fid;
    }

    public int getHungerIncrease() {
        return hungerIncrease;
    }

    public void setHungerIncrease(int hungerIncrease) {
        this.hungerIncrease = hungerIncrease;
    }
    
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}


