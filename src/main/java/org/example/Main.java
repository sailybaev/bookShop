package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        String URL = "jdbc:postgresql://localhost:5432/endka";
        String USER = "postgres";
        String PASS = "1602";

        Connection connection = DriverManager.getConnection(URL , USER , PASS);

        Statement stmt = connection.createStatement();
        Scanner scan =new Scanner(System.in);
        createtable(connection);

        while(true) {
            System.out.println("---MENU---");
            System.out.println("1. View all books");
            System.out.println("2. Add book");
            System.out.println("3. Delete Book");
            System.out.println("4. Update book");
            System.out.println("5. EXIT");

            int choose = scan.nextInt();

            if(choose == 1) {
                allbooks(connection);
            }

            else if(choose == 2) {
                String s = scan.next();
                addbook(connection , s);
            }

            else if(choose == 3) {
                int id = scan.nextInt();
                delBook(connection , id);
            }
            else if(choose == 4) {
                String s = scan.next();
                int id = scan.nextInt();
                updBook(connection,id,s);
            }
            else if(choose == 5) {
                break;
            }

        }

    }

    public static void allbooks(Connection connection) throws Exception {
        Statement stmt = connection.createStatement();
        String q = "select * from users order by id asc";
        ResultSet results = stmt.executeQuery(q);

        while (results.next() == true) {
            System.out.print("Id: " + results.getInt(1));
            System.out.print("Name: " + results.getString(2));
            System.out.println();

        }
    }

    public static void createtable(Connection connection) throws Exception {
        Statement stmt = connection.createStatement();
        String q = "create table if not exists users (id serial primary key,name varchar(20),sname varchar(20),gender varchar(20));";
        ResultSet results = stmt.executeQuery(q);


    }

    public static void addbook(Connection connection , String Name) throws Exception{
        PreparedStatement pst = connection.prepareStatement("insert into users(name) values (?)");
        pst.setString(1, Name);
        pst.executeUpdate();
    }

    public static void delBook(Connection connection , int Id) throws Exception{
        PreparedStatement pst = connection.prepareStatement("delete from users where id = ?");
        pst.setInt(1, Id);
        pst.executeUpdate();
    }

    public static void updBook(Connection connection , int Id , String Name) throws Exception{
        PreparedStatement pst = connection.prepareStatement("update users set name = ? where id = ?");
        pst.setInt(2, Id);
        pst.setString(1,Name);
        pst.executeUpdate();
    }


}