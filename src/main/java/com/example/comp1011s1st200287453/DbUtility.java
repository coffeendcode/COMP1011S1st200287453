package com.example.comp1011s1st200287453;

import javafx.scene.chart.XYChart;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;

public class DbUtility {

    private static String uName = "student";
    private static String pass = "student";
    private static String connectionURL = "jdbc:mysql://127.0.0.1:3306/F22midterm";

    public static ArrayList<CarSold> getCarsSold()
    {
        ArrayList<CarSold> carsSold = new ArrayList<>();

        String sql = "SELECT * " +
                     "FROM carsales";

        try(
                Connection con = DriverManager.getConnection(connectionURL, uName, pass);
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                )
        {
            while (resultSet.next()) {
                int carId = resultSet.getInt("carID");
                int modelYear = resultSet.getInt("modelYear");
                String make = resultSet.getString("make");
                String model = resultSet.getString("model");
                float price = resultSet.getFloat("price");
                LocalDate dateSold = resultSet.getDate("dateSold").toLocalDate();

                CarSold carSold = new CarSold(carId, modelYear, make, model, price, dateSold);
                carsSold.add(carSold);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return carsSold;
    }

    public static ArrayList<Integer> getCarYears()
    {
        ArrayList<Integer> carYearList = new ArrayList<>();

        String sql = "SELECT DISTINCT modelYear " +
                     "FROM carsales " +
                     "ORDER BY carsales.modelYear DESC";

        try (
                Connection con = DriverManager.getConnection(connectionURL, uName, pass);
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                )
        {
            while(resultSet.next())
            {
                int modelYear = resultSet.getInt("modelYear");

                carYearList.add(modelYear);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return carYearList;
    }

    public static int getTotalCarSales()
    {
        int carSales = 0;

        String sql = "SELECT * " +
                "FROM carsales;";

        try (
                Connection con = DriverManager.getConnection(connectionURL, uName, pass);
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            while (resultSet.next())
            {
                carSales++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return carSales;
    }

    public static int getNetCarSales()
    {
        int salesTotal = 0;

        String sql = "SELECT * " +
                "FROM carsales;";

        try (
                Connection con = DriverManager.getConnection(connectionURL, uName, pass);
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            while (resultSet.next())
            {
                salesTotal += resultSet.getFloat("price");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return salesTotal;
    }
}
