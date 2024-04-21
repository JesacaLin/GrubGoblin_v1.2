package org.JesacaLin;

import org.JesacaLin.daos.AvailabilityDAO;
import org.JesacaLin.daos.DealDAO;
import org.JesacaLin.daos.PlaceDAO;
import org.JesacaLin.daos.ReviewDAO;
import org.JesacaLin.models.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLOutput;
import java.util.ArrayList;
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
                |                  MAIN MENU                    |
                -------------------------------------------------
                | 1: Browse deals vault                         |
                | 2: Contribute a deal                          |
                | 3: Modify an existing deal                    |
                | 4: Remove a deal                              |
                -------------------------------------------------
                | Enter "1", "2", "3", or "4" to proceed        |
                | Enter "0" to exit the program                 |
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
            } else if (menuInput.equals("0")) {
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
                    |        DEAL BROWSING MENU                     |
                    -------------------------------------------------
                    | 1: View All Deals in the Directory (works)    |
                    | 2: Search Deals by Day                        |
                    | 3: Search Deals by keyword (works)            |
                    | 4: Discover Top-Rated Deals                   |
                    -------------------------------------------------
                    | Enter "1", "2", "3", or "4" to proceed        |
                    | Enter "0" to return to Main Menu              |
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


            } else if (menuInput.equals("3")) {
                String keywordInput = UserInput.getStringInput("Enter the keyword you would like to search for.");
                
                List<FullDealDetails> dealsByKeyword = dealDAO.getAllDealByKeyword(keywordInput);
                if (!dealsByKeyword.isEmpty()) {
                    for (FullDealDetails deal : dealsByKeyword) {
                        System.out.println(deal);
                    }
                } else {
                    System.out.println("\n*** No results ***");
                }

            } else if (menuInput.equals("0")) {
                break;
            } else {
                System.out.println("Please select a valid menu option!");
            }
        }
    }

    public static void addDeal(){
        //To do - convert the address to lat/long and set that as the values
        //how to handle multiple days without retyping? after the user selects 1, I should ask: is this deal avilable multiple days?
        //what about deals with no start time?
        while (true) {
            String steps = ("""
                    -------------------------------------------------
                    | Ready to contribute a deal? Let's go!         |
                    -------------------------------------------------
                    | Enter "1" to begin                            |
                    | Enter "0" to return to Main Menu              |
                    -------------------------------------------------
                    """);
            String menuInput = UserInput.getStringInput(steps);
            if (menuInput.equals("1")) {
                //-------------------ADD PLACE-------------------
                System.out.println("First, let's gather some information about the establishment.");
                System.out.println();
                Place newPlace = new Place();
                newPlace.setPlaceName(UserInput.getStringInput("What's the name of the place?"));

                //-------------------CONVERTING ADDRESS-------------------
                String address = UserInput.getStringInput("What's its address?");
                try {
                    double[] coordinateArray = AddressConverter.convertAddress(address);
                    newPlace.setAddress(address);
                    assert coordinateArray != null;
                    newPlace.setLatitude(coordinateArray[0]);
                    newPlace.setLongitude(coordinateArray[1]);
                } catch (RuntimeException e) {
                    System.err.println(e.getMessage());
                    continue;
                }

                newPlace.setGoogleRating(UserInput.getDoubleInput("What's its Google rating?"));
                newPlace = placeDAO.createPlace(newPlace);

                //-------------------ADD DEAL DETAILS-------------------
                System.out.println("Great! Now let's add some details about the deal.");
                System.out.println();
                Deal newDeal = new Deal();
                newDeal.setPlaceId(newPlace.getPlaceId());
                newDeal.setTypeOfDeal(UserInput.getStringInput("What type of deal is it? (Options: 'food', 'drinks', 'grocery', 'other')"));
                newDeal.setDealDescription(UserInput.getStringInput("Can you describe the deal?"));
                newDeal = dealDAO.createDeal(newDeal);

                //-------------------ADD AVAILABILITY-------------------
                System.out.println("Next, let's specify when this deal is available.");
                System.out.println();
                Availability newAvailability = new Availability();
                newAvailability.setDayOfWeek(UserInput.getIntInput("Which day of the week? (Enter 1 for Monday, 2 for Tuesday, etc. If available all week, enter 8"));
                newAvailability.setStartTime(UserInput.getTimeInput("What's the start time? (Please enter in HH:MM format), if not applicable, enter 00:00"));
                newAvailability.setEndTime(UserInput.getTimeInput("What's the end time? (Please enter in HH:MM format), if not applicable, enter 00:00"));
                newAvailability = availabilityDAO.createAvailability(newAvailability, newDeal.getDealId());


                //-------------------ADD REVIEW-------------------
                System.out.println("Finally, please enter your review of the deal.");
                System.out.println();
                Review newReview = new Review();
                newReview.setDealId(newDeal.getDealId());
                newReview.setStars(UserInput.getDoubleInput("How would you rate this deal? (Enter a number between 1.0 to 5.0)"));
                newReview.setReviewDescription(UserInput.getStringInput("Please share your review. If review is pending, write 'null'"));
                newReview = reviewDAO.createReview(newReview);

                System.out.println("\nYou just added this deal to the vault:\n" +
                        newPlace + "\n" +
                        newDeal + "\n" +
                        newAvailability + "\n" +
                        newReview + "\n");


            } else if (menuInput.equals("0")) {
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
               |        What would you like to update?         |
               -------------------------------------------------
               | 1: Update an existing place (works)           |
               | 2: Update an existing deal                    |
               | 3: Update availability                        |
               | 4: Update an existing review                  |
               -------------------------------------------------
               | Enter "1", "2", "3", or "4" to proceed        |
               | Enter "0" to return to Main Menu              |
               -------------------------------------------------
               """);
            String menuInput = UserInput.getStringInput(update);
            if (menuInput.equals("1")) {
                List<Place> places = placeDAO.getAllPlaces();
                if (!places.isEmpty()) {
                    for (Place place : places) {
                        System.out.println(place);
                    }
                } else {
                    System.out.println("\n*** No results ***");
                }
            System.out.println();
            int id = UserInput.getIntInput("Please enter the id of the place you want to update.");

            Place placeToUpdate = placeDAO.getPlaceById(id);

            String placeColumns = ("""
               -------------------------------------------------
               |    What column do you want to update?         |
               -------------------------------------------------
               | 1: Update place name (works)                  |
               | 2: Update Google Rating (works)               |
               -------------------------------------------------
               | Enter "1", or "2" to proceed             |
               | Enter "0" to return to Main Menu              |
               -------------------------------------------------
               """);
            String placeInput = UserInput.getStringInput(placeColumns);
            switch(placeInput) {
                case "1":
                    String newPlaceName = UserInput.getStringInput("Enter new name");
                    placeToUpdate.setPlaceName(newPlaceName);
                    System.out.println("\nThe place name was updated!\n" +
                            placeToUpdate + "\n");
                    break;
                case "2":
                    double newRating = UserInput.getDoubleInput("Enter new Google rating");
                    placeToUpdate.setGoogleRating(newRating);
                    System.out.println("\nThe Google rating was updated!\n" +
                            placeToUpdate + "\n");
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Please make a selection");
                }
            } else if (menuInput.equals("0")) {
                break;
            } else {
                System.out.println("Please select a valid menu option!");
            }
        }
    }
    public static void deleteDeal(){
        while (true) {
            String delete = ("""
               -------------------------------------------------
               |        What would you like to delete?         |
               -------------------------------------------------
               | 1: Delete an existing place (works)           |
               | 2: Delete an existing deal                    |
               -------------------------------------------------
               | Enter "1", or "2" to proceed        |
               | Enter "0" to return to Main Menu              |
               -------------------------------------------------
               """);
            String menuInput = UserInput.getStringInput(delete);

            if (menuInput.equals("1")) {
                List<Place> places = placeDAO.getAllPlaces();
                if (!places.isEmpty()) {
                    for (Place place : places) {
                        System.out.println(place);
                    }
                } else {
                    System.out.println("\n*** No results ***");
                }
                System.out.println();
                int id = UserInput.getIntInput("Please enter the id of the place you want to delete.");

                int numDeleted = placeDAO.deletePlaceById(id);
                if (numDeleted == 1) {
                    System.out.println("Successfully deleted place");
                } else {
                    System.out.println("Place was NOT deleted");
                }

            } else if (menuInput.equals("0")) {
                break;
            } else {
                System.out.println("Please select a valid menu option!");
            }
        }
    }

}