
package model;
/**
 *
 * @author Aknes ;3
 */
public class GameStore {
    private Food food;
    private Water water;
    private Medicine medicine;
    
    public GameStore(Food food, Water water, Medicine medicine) {
        this.food = food;
        this.water = water;
        this.medicine = medicine;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Water getWater() {
        return water;
    }

    public void setWater(Water water) {
        this.water = water;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
    
    
    public void displaystore(){
        System.out.println("Item yang Bisa Dibeli :3");
        System.out.println( food.getName() + food.getPrice());
        System.out.println( water.getName() + water.getPrice());
    }   //System.out.println( medicine.getName() + medicine.getPrice());
    
    
    }
    
    //kayaknya bebenran harus pake interface deh aakkkk

