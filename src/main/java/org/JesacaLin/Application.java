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
                |                  MAIN MENU                    |
                -------------------------------------------------
                | 1: Browse Deals Vault                         |
                | 2: Contribute a Deal                          |
                | 3: Modify an Existing Deal                    |
                | 4: Remove a Deal                              |
                | 5: Exit the Program                           |
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
                    |        DEAL BROWSING MENU                     |
                    -------------------------------------------------
                    | 1: View All Deals in the Directory            |
                    | 2: Search Deals by Day                        |
                    | 3: Explore Places with Deals                  |
                    | 4: Discover Top-Rated Deals                   |
                    | 5: Return to Main Menu                        |
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
        //To do - convert the address to lat/long and set that as the values
        //how to handle multiple days without retyping? after the user selects 1, I should ask: is this deal avilable multiple days?
        //what about deals with no start time?
        while (true) {
            String steps = ("""
                    -------------------------------------------------
                    | Ready to contribute a deal? Let's go!         |
                    -------------------------------------------------
                    | 1: Begin                                      |
                    | 2: Return to Main Menu                        |
                    -------------------------------------------------
                    """);
            String menuInput = UserInput.getStringInput(steps);
            if (menuInput.equals("1")) {
                //-------------------ADD PLACE-------------------
                System.out.println("First, let's gather some information about the place.");
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
                newAvailability.setDayOfWeek(UserInput.getIntInput("Which day of the week? (Enter 1 for Monday, 2 for Tuesday, etc."));
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

            } else if (menuInput.equals("2")) {
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