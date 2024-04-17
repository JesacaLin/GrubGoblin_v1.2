package org.JesacaLin.daos;

import org.JesacaLin.exception.DaoException;
import org.JesacaLin.models.Place;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class PlaceDAO {
    private JdbcTemplate jdbcTemplate;
    public PlaceDAO(BasicDataSource basicDataSource) {
        jdbcTemplate = new JdbcTemplate(basicDataSource);
    }

    public Place getPlaceById(int id) {
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM place WHERE place_id = ?", id);
            if (rowSet.next()) {
                return mapRowToPlace(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return null;
    }

    public List<Place> getAllPlaces() {
        List<Place> places = new ArrayList<>();
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM place");
            while(rowSet.next()) {
                places.add(mapRowToPlace(rowSet));
            }
            return places;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    public Place createPlace(Place place) {
        Place newPlace = null;
        String sql = "INSERT INTO place (place_name, address, latitude, longitude, google_rating)" + "VALUES (?, ?, ?, ?, ? ) RETURNING place_id";
        try {
            int placeId = jdbcTemplate.queryForObject(sql, int.class, place.getPlaceName(), place.getAddress(), place.getLatitude(), place.getLongitude(), place.getGoogleRating());
            newPlace = getPlaceById(placeId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newPlace;
    }

    public Place updatePlace(Place place) {
        Place updatedPlace = null;
        String sql = "UPDATE place SET place_name = ?, address = ?, latitude = ?, longitude = ?, google_rating = ?";

        try {
            int numOfRows = jdbcTemplate.update(sql, place.getPlaceName(), place.getAddress(), place.getLatitude(), place.getLongitude(), place.getGoogleRating(), place.getPlaceId());
            if (numOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedPlace = getPlaceById(place.getPlaceId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedPlace;
    }

    public int deletePlaceById(int placeId) {
        int numOfRows = 0;
        try {
            numOfRows = jdbcTemplate.update("DELETE FROM place WHERE place_id = ?;", placeId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numOfRows;
    }

    public Place mapRowToPlace (SqlRowSet rowSet) {
        Place place = new Place();
        place.setPlaceId("Unable to set place id", rowSet.getInt("place_id"));
        place.setPlaceName(rowSet.getString("place_name"));
        place.setAddress(rowSet.getString("address"));
        place.setLatitude("Unable to set latitude", rowSet.getDouble("latitude"));
        place.setLongitude("Unable to set longitude", rowSet.getDouble("longitude"));
        place.setGoogleRating("Unable to set Google rating", rowSet.getDouble("google_rating"));
        return place;
    }
}
