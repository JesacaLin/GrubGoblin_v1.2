package org.JesacaLin;
import com.google.maps.model.PlaceEditorialSummary;
import org.JesacaLin.daos.PlaceDAO;
import org.JesacaLin.models.Place;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PlaceDAOTests extends BaseDaoTests {
    private static final Place PLACE_1 = new Place(1, "elsa", "136 Atlantic Ave, Brooklyn, NY 11201", 40.690239, -73.995361, 4.7);
    private static final Place PLACE_2 = new Place(2, "vine", "81 Fleet Pl, Brooklyn, NY 11201", 40.693211, -73.981331, 4.9);
    private PlaceDAO placeDAO;

    @Before
    public void setup() {
        this.placeDAO = new PlaceDAO(dataSource);
    }

    @Test
    public void getPlaceById_returns_correct_place() {
        Place actualPlace = placeDAO.getPlaceById(1);
        assertPlacesMatch(PLACE_1, actualPlace);
    }

    @Test
    public void getAllPlaces_returns_all_places() {
        List<Place> actualPlaces = placeDAO.getAllPlaces();
        Assert.assertNotNull(actualPlaces);
        Assert.assertEquals(2, actualPlaces.size());
        assertPlacesMatch(PLACE_1, actualPlaces.get(0));
        assertPlacesMatch(PLACE_2, actualPlaces.get(1));
    }

    @Test
    public void createPlace_should_create_place() {
        Place newPlace = new Place();
        newPlace.setPlaceId(3);
        newPlace.setPlaceName("taqueria al pastor & bar");
        newPlace.setAddress("119 court st, brooklyn, ny 11201");
        newPlace.setLatitude(40.690450);
        newPlace.setLongitude(-73.991786);
        newPlace.setGoogleRating(4.7);

        Place expectedPlace = placeDAO.createPlace(newPlace);
        int newId = expectedPlace.getPlaceId();
        Place actualPlace = placeDAO.getPlaceById(newId);
        assertPlacesMatch(expectedPlace, actualPlace);
    }

    @Test
    public void updatePlace_updates_place() {
        Place existingPlace = placeDAO.getPlaceById(1);
        existingPlace.setPlaceId(1);
        existingPlace.setPlaceName("elsa's");
        existingPlace.setAddress("136 Atlantic Ave, Brooklyn, NY 11201");
        existingPlace.setLatitude(40.690450);
        existingPlace.setLongitude(-73.991786);
        existingPlace.setGoogleRating(4.7);
        Place updatedPlace = placeDAO.updatePlace(existingPlace);
        Place actualPlace = placeDAO.getPlaceById(1);
        assertPlacesMatch(updatedPlace, actualPlace);
    }

    @Test
    public void deletePLaceById_deletes_correct_place() {
        int rowsAffected = placeDAO.deletePlaceById(1);
        Assert.assertEquals(1, rowsAffected);

        Place retrievedPlace = placeDAO.getPlaceById(1);
        Assert.assertNull(retrievedPlace);
    }

    private void assertPlacesMatch(Place expected, Place actual) {
        Assert.assertEquals(expected.getPlaceId(), actual.getPlaceId());
        Assert.assertEquals(expected.getPlaceName(), actual.getPlaceName());
        Assert.assertEquals(expected.getAddress(), actual.getAddress());
        Assert.assertEquals(expected.getLatitude(), actual.getLatitude(), 0.1);
        Assert.assertEquals(expected.getLongitude(), actual.getLongitude(), 0.01);
        Assert.assertEquals(expected.getGoogleRating(), actual.getGoogleRating(), 0);

    }

}
