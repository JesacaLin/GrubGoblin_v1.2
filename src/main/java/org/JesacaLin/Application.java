package org.JesacaLin;

import org.JesacaLin.daos.AvailabilityDAO;
import org.JesacaLin.daos.DealDAO;
import org.JesacaLin.daos.PlaceDAO;
import org.JesacaLin.daos.ReviewDAO;
import org.JesacaLin.models.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public class Application {
    public static BasicDataSource basicDataSource;
    public static PlaceDAO placeDAO;
    public static DealDAO dealDAO;
    public static AvailabilityDAO availabilityDAO;
    public static ReviewDAO reviewDAO;
    public static void main(String[] args) {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:postgresql://localhost:5432/GrubGoblin");
        basicDataSource.setUsername("postgres");
        basicDataSource.setPassword("postgres1");

        placeDAO = new PlaceDAO(basicDataSource);
        dealDAO = new DealDAO(basicDataSource);
        availabilityDAO = new AvailabilityDAO(basicDataSource);
        reviewDAO = new ReviewDAO(basicDataSource);

        mainMenu();
    }
    public static void mainMenu() {
        while (true) {
            String menu = ("""
                    -------------------------------------------------
                    |    GRUB GOBLIN: Your Food Deals Directory     |
                    -------------------------------------------------
                    |        Please select from the menu            |
                    -------------------------------------------------
                    | 1: Access Deals vault                    |
                    | 2: Add a deal                                 |
                    | 3: Update a deal                              |
                    | 4: Delete a deal                              |
                    | 5: Exit the program                           |
                    -------------------------------------------------
                    """);
            String menuInput = UserInput.getStringInput(menu);

            if (menuInput.equals("1")) {
                seeAllDeals();
            } else if (menuInput.equals("2")) {
                addDeal();
            } else if (menuInput.equals("3")) {
                updateDeal();
            } else if (menuInput.equals("4")) {
                deleteDeal();
            } else if (menuInput.equals("5")) {
                break;
            } else {
                System.out.println("Please select a valid menu option!");
            }
        }
    }

    public static void seeAllDeals() {
        while (true) {
            String dealMenu = ("""
                    -------------------------------------------------
                    |        Deals Menu            |
                    -------------------------------------------------
                    | 1: See all deals in the directory               |
                    | 2: Find deals for a specific day               |
                    | 3: See all places with deals                   |
                    | 4: See deals rating (based on deal_id)          |
                    | 5: Exit the program                           |
                    -------------------------------------------------
                    """);
            String menuInput = UserInput.getStringInput(dealMenu);

            if (menuInput.equals("1")) {
                List<FullDealDetails> dealDetails = dealDAO.getAllDealDetails();
                for (FullDealDetails deal : dealDetails) {
                    System.out.println(deal);
                }
            } else if (menuInput.equals("5")) {
                break;
            }
        }
    }

    public static void addDeal(){}
    public static void updateDeal(){}
    public static void deleteDeal(){}

}