/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

/**
 *
 * @author Admin
 */
import dao.UserDAO;
import dao.transbibitDAO;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import model.Plant;
import model.User;

public class Main {

    public static void main(String[] args, Plant s) {
        int pilihan;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("1.SIGN UP");
        System.out.println("2.LOGIN");
        System.out.print("tentukan pilihan : ");
        pilihan = sc.nextInt();
        
        if(pilihan == 1)
        {
            System.out.print("masukkan username : ");
            String username = sc.nextLine();
            
            System.out.print("masukkan password : ");
            String password = sc.nextLine();
            
            User user = new User(username, password);
            UserDAO.registerUser(user);
        }
        else if (pilihan == 2)
        {
            System.out.println("username : ");
            String username = sc.nextLine();
            
            System.out.println("password : ");
            String password = sc.nextLine();
            
            User validateUser = UserDAO.validate(username, password);
            if(validateUser != null)
            {
                System.out.println("");
                System.out.println("PILIH TANAMAN ANDA!!!");
                System.out.println("1.ANGGREK");
                System.out.println("2.MAWAR");
                System.out.print("tentukan pilihan : ");
                pilihan = sc.nextInt();
                if(pilihan == 1)
                {
                   
                }
                else if (pilihan == 2)
                {
                    
                }
            }else {
                System.out.println("login gagal!!");
            }
        }
    }
}
