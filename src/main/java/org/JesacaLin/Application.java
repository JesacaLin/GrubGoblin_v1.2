package org.JesacaLin;

import org.JesacaLin.daos.AvailabilityDAO;
import org.JesacaLin.daos.DealDAO;
import org.JesacaLin.daos.PlaceDAO;
import org.JesacaLin.daos.ReviewDAO;
import org.JesacaLin.models.*;
import org.apache.commons.dbcp2.BasicDataSource;

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
                seeDeals();
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

    public static void seeDeals() {
        while (true) {
            String dealMenu = ("""
                    -------------------------------------------------
                    |        Deals Menu            |
                    -------------------------------------------------
                    | 1: See all deals in the directory               |
                    | 2: Find deals for a specific day               |
                    | 3: See all places with deals                   |
                    | 4: See deals based on rating                 |
                    | 5: Exit to main menu                           |
                    -------------------------------------------------
                    """);
            String menuInput = UserInput.getStringInput(dealMenu);

            if (menuInput.equals("1")) {
                List<FullDealDetails> dealDetails = dealDAO.getAllDealDetails();
                if (!dealDetails.isEmpty()) {
                    for (FullDealDetails deal : dealDetails) {
                        System.out.println(deal);
                    }
                } else {
                    System.out.println("\n*** No results ***");
                }
            } else if (menuInput.equals("5")) {
                break;
            } else {
                System.out.println("Please select a valid menu option!");
            }
        }
    }

    public static void addDeal(){
        while (true) {
            String steps = ("""
                    -------------------------------------------------
                    |        Steps to adding a deal            |
                    -------------------------------------------------
                    | Step 1: Add the place               |
                    | Step 2: Add details of the deal             |
                    | Step 3: Add availability                   |
                    | Step 4: Add a review                   |
                    -------------------------------------------------
                    | Enter "1" to start "5" to return to main menu  |
                    -------------------------------------------------
                    """);
            String menuInput = UserInput.getStringInput(steps);
            if (menuInput.equals("1")) {
                //ADD PLACE
                System.out.println("Let's start by adding details about the place");
                System.out.println();
                Place newPlace = new Place();
                newPlace.setPlaceName(UserInput.getStringInput("Name of the place?"));
                newPlace.setAddress(UserInput.getStringInput("Address?"));
                newPlace.setLatitude(UserInput.getDoubleInput("Latitude?"));
                newPlace.setLongitude(UserInput.getDoubleInput("Longitude?"));
                newPlace.setGoogleRating(UserInput.getDoubleInput("Google rating?"));
                newPlace = placeDAO.createPlace(newPlace);

                //ADD DEAL DETAILS
                System.out.println("Now let's add details about the deal");
                System.out.println();
                Deal newDeal = new Deal();
                newDeal.setPlaceId(newPlace.getPlaceId());
                newDeal.setTypeOfDeal(UserInput.getStringInput("What kind of deal? Enter 'food deal', 'drinks deal', 'grocery deal', 'other'"));
                newDeal.setDealDescription(UserInput.getStringInput("Please describe the deal"));
                newDeal = dealDAO.createDeal(newDeal);

                //ADD AVAILABILITY
                System.out.println("Next please add when this deal is available");
                System.out.println();
                Availability newAvailability = new Availability();
                newAvailability.setDayOfWeek(UserInput.getIntInput("Day of week? (Enter 1 for Monday, 2 for Tuesday, etc.)"));
                newAvailability.setStartTime(UserInput.getTimeInput("Start time? (Enter in HH:MM format)"));
                newAvailability.setEndTime(UserInput.getTimeInput("End time? (Enter in HH:MM format)"));
                newAvailability = availabilityDAO.createAvailability(newAvailability, newDeal.getDealId());


                //ADD REVIEW
                System.out.println("Lastly, please add a review about the deal. Please enter 'null' if review is not available yet");
                System.out.println();
                Review newReview = new Review();
                newReview.setDealId(newDeal.getDealId());
                newReview.setStars(UserInput.getDoubleInput("Rate deal (Enter in 00:00 format, between 1.0 to 5.0)"));
                newReview.setReviewDescription(UserInput.getStringInput("Please write a review for this deal"));
                newReview = reviewDAO.createReview(newReview);

            } else if (menuInput.equals("5")) {
                break;
            } else {
                System.out.println("Please select a valid menu option!");
            }
        }
    }
    public static void updateDeal(){
        while (true) {
            String update = ("""
                    -------------------------------------------------
                    |        What would you like to update?            |
                    -------------------------------------------------
                    | 1: Add details of the place               |
                    | 2: Add details of the deal             |
                    | 3: Add availability                   |
                    -------------------------------------------------
                    |        Enter "1" to start or "5" to exit           |
                    -------------------------------------------------
                    """);
            String menuInput = UserInput.getStringInput(update);
        }
    }
    public static void deleteDeal(){}

}