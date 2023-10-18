/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.coba.jdbc;

/**
 *
 * @author novil
 */
public class CobaJdbc2 {

    public static void main(String[] args) {
        Database db = new Database();
        
        db.bukaKoneksi();
        db.hapusDataMahasiswa();
        db.tutupKoneksi();
    }
}
