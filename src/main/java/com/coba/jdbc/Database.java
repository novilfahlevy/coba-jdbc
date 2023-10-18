/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coba.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author novil
 */
public class Database {
    protected Connection connection = null;
    
    protected Statement statement = null;
    protected PreparedStatement preparedStatement = null;
    protected ResultSet resultSet = null;
    
    public void bukaKoneksi() {
        try {
            String url = "jdbc:mysql://localhost/db_mahasiswa?user=root&password=";
            
            // buat koneksi
            this.connection = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.out.println("Mysqlnya error: " + ex.getMessage());
        }
    }
    
    public void tutupKoneksi() {
        try {
            if (this.connection != null) this.connection.close();
            if (this.statement != null) this.statement.close();
            if (this.preparedStatement != null) this.preparedStatement.close();
            if (this.resultSet != null) this.resultSet.close();
            
            this.connection = null;
            this.statement = null;
            this.preparedStatement = null;
            this.resultSet = null;
        } catch (SQLException ex) {
            System.out.println("Mysqlnya error: " + ex.getMessage());
        }
    }
    
    public void hapusDataMahasiswa() {
        try {
            // buat dulu query SQL nya
            String sql = "DELETE FROM data_mahasiswa WHERE nim = ?";
            
            // menyiapkan preparedStatement untuk query SQL di atas
            this.preparedStatement = this.connection.prepareStatement(sql);
            
            // masukkan data NIM ke dalam preparedStatementnya
            this.preparedStatement.setString(1, "2109116343");
            
            // menyimpan hasil dari eksekusi preparedStatement
            int result = this.preparedStatement.executeUpdate();
            
            // tampilkan result
            System.out.println(result);
            
            // tampilkan pesan berhasil JIKA preparedStatementnya berhasil dijalankan
            if (result > 0) {
                System.out.println("Berhasil menghapus data Firzian");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void ambilDataMahasiswa() {
        try {
            // buat statement dari koneksi
            this.statement = this.connection.createStatement();

            // ambil resultset dari statement
            this.resultSet = this.statement.executeQuery("SELECT * FROM data_mahasiswa");

            // pindahin kursor resultset 1 langkah
            this.resultSet.next();

            // ambil column "nama"
            String nama = this.resultSet.getString("nama");
            
            System.out.println(nama);
        } catch (SQLException sQLException) {
            
        }
    }
    
    public void tambahMahasiswa() {
        try {
            this.statement = this.connection.createStatement();
            
            String query = "INSERT INTO data_mahasiswa "
                    + "(NIM, nama, program_studi, fakultas) "
                    + "VALUES ('2109116095', 'Fazry Suhada', 'Sistem Informasi', 'Teknik')";
            
            int result = this.statement.executeUpdate(query);
            
            if (result > 0) {
                System.out.println("Berhasil menambah mahasiswa");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
