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
public class Medicine {
    private String name;
    private UUID mid;
    private int healthIncrease;
    
    public Medicine(String name, int healthIncrease){
        UUID uuid = UUID.randomUUID();
        this.setName(name);
        this.setHealthIncrease(healthIncrease);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getMid() {
        return mid;
    }

    public void setMid(UUID mid) {
        this.mid = mid;
    }

    public int getHealthIncrease() {
        return healthIncrease;
    }

    public void setHealthIncrease(int healthIncrease) {
        this.healthIncrease = healthIncrease;
    }
    
}
