package model;
import java.util.UUID;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Plant {
    private String name;
    private UUID tanaman_id;
    private int umur;
    private int lapar;
    private int haus;
    private int kesehatan;
    
    
public Plant(String name, UUID tanaman_id, int umur, int lapar, int haus, int kesehatan){
    UUID uuid = UUID.randomUUID();
    this.setName(name);
    this.setTanaman_id(uuid);
    this.setUmur(umur);
    this.setLapar(lapar);
    this.setHaus(haus);
    this.setKesehatan(kesehatan);
    
    
   }  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getTanaman_id() {
        return tanaman_id;
    }

    public void setTanaman_id(UUID tanaman_id) {
        this.tanaman_id = tanaman_id;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public int getLapar() {
        return lapar;
    }

    public void setLapar(int lapar) {
        this.lapar = lapar;
    }

    public int getHaus() {
        return haus;
    }

    public void setHaus(int haus) {
        this.haus = haus;
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public void setKesehatan(int kesehatan) {
        this.kesehatan = kesehatan;
    }

        
}
