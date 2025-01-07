package model;
import java.util.UUID;
import dao.StatusDAO;
import dao.TanamanDAO;
import javafx.scene.image.Image;

public class Tanaman {
    private String nama;
    private int tanaman_id;
    private int umur;
    
    private int batasHaus;
    private int batasLapar;
    private int batasSehat;
    
    private int haus;
    private int kelaparan;
    private int kesehatan;
    
    private int hargaBeli;
    private int hargaJual;
    

    public Tanaman(int tanaman_id, int umur, int haus, int kelaparan, int kesehatan){
        this.tanaman_id = tanaman_id;
        this.haus = haus;
        this.umur = umur;
        this.kelaparan = kelaparan;
        this.kesehatan = kesehatan;
        this.nama = TanamanDAO.cekNamaTanaman(tanaman_id);
        int[] harga = TanamanDAO.cekHarga(tanaman_id);
        setHarga(harga[0],harga[1]);
        
        int[] batasStatus = TanamanDAO.cekBatasStatus(tanaman_id);
        setBatasStatus(batasStatus[0],batasStatus[1], batasStatus[2]);
    }
    public Tanaman(int tanaman_id){
        this.nama = TanamanDAO.cekNamaTanaman(tanaman_id);
        
        int[] harga = TanamanDAO.cekHarga(tanaman_id);
        setHarga(harga[0],harga[1]);
        
        int[] batasStatus = TanamanDAO.cekBatasStatus(tanaman_id);
        setBatasStatus(batasStatus[0],batasStatus[1], batasStatus[2]);
    }

    public String getNama() {
        return nama;
    }
    
    public int getHargaBeli() {
        return hargaBeli;
    }

    public int getHargaJual() {
        return hargaJual;
    }

    public void setHarga(int harga_beli, int harga_jual){
        this.hargaBeli = harga_beli;
        this.hargaJual = harga_jual;
    }
    public int getHaus() {
        return haus;
    }

    public void setHaus(int haus, UUID uid){
        this.haus = haus;
        StatusDAO.setStatusHaus(haus, uid);
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur, UUID uid) {
        this.umur = umur;
        StatusDAO.setStatusUmur(umur, uid);
    }

    public int getTanaman_id() {
        return tanaman_id;
    }

    public void setTanaman_id(int tanaman_id) {
        this.tanaman_id = tanaman_id;
    }

    public int getKelaparan() {
        return kelaparan;
    }

    public void setKelaparan(int kelaparan,UUID uid) {
        this.kelaparan = kelaparan;
        StatusDAO.setStatusLapar(kelaparan, uid);
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public void setKesehatan(int kesehatan, UUID uid) {
        this.kesehatan = kesehatan;
        StatusDAO.setStatusSehat(kesehatan, uid);
    }
    
    public int getBatasHaus() {
        return batasHaus;
    }
    public int getBatasLapar() {
        return batasLapar;
    }
    public int getBatasSehat() {
        return batasSehat;
    }

    public void setBatasStatus(int batasHaus, int batasLapar, int batasSehat) {
        this.batasHaus = batasHaus;
        this.batasLapar = batasLapar;
        this.batasSehat = batasSehat;
    }

}
