package gui;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author ASUS
 */
public class KhachSanAnNhien_GUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame_Login login = null;
        try {
            login = new JFrame_Login();
        } catch (Exception ex) {
            Logger.getLogger(KhachSanAnNhien_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        login.setVisible(true);
    }

}
