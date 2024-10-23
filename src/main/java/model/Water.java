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
public class Water {
    private String name;
    private UUID wid;
    private int thristIncrease;
    private int price;

public Water(String name, int thristIncrease, int price){
    UUID uuid = UUID.randomUUID();
    this.setName(name);
    this.setThristIncrease(thristIncrease);
    this.setPrice(price);
}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getWid() {
        return wid;
    }

    public void setWid(UUID wid) {
        this.wid = wid;
    }

    public int getThristIncrease() {
        return thristIncrease;
    }

    public void setThristIncrease(int thristIncrease) {
        this.thristIncrease = thristIncrease;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    

}