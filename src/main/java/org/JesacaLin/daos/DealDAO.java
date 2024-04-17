package org.JesacaLin.daos;

import org.JesacaLin.exception.DaoException;
import org.JesacaLin.models.Deal;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class DealDAO {
    private JdbcTemplate jdbcTemplate;
    public DealDAO(BasicDataSource basicDataSource) {
        jdbcTemplate = new JdbcTemplate(basicDataSource);
    }

    public Deal getDealById (int dealId) {
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM deal WHERE deal_id = ?", dealId);
            if (rowSet.next()) {
                return mapRowToDeal(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return null;
    }
    public List<Deal> getAllDeals() {
        List<Deal> deals = new ArrayList<>();
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM deal");
            while(rowSet.next()) {
                deals.add(mapRowToDeal(rowSet));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return deals;
    }

    public Deal createDeal(Deal deal) {
        Deal newDeal = null;
        String sql = "INSERT INTO deal (place_id, type_of_deal, deal_description)" + "VALUES (?, ?, ? ) RETURNING deal_id";
        try {
            int dealId = jdbcTemplate.queryForObject(sql, int.class, deal.getPlaceId(), deal.getTypeOfDeal(), deal.getDealDescription());
            newDeal = getDealById(dealId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newDeal;
    }

    public Deal updateDeal(Deal deal) {
        Deal updatedDeal = null;
        String sql = "UPDATE deal SET place_id = ?, type_of_deal = ?, deal_description = ?";

        try {
            int numOfRows = jdbcTemplate.update(sql, deal.getPlaceId(), deal.getTypeOfDeal(), deal.getDealDescription());
            if (numOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedDeal = getDealById(deal.getDealId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedDeal;
    }

    public int deleteDealById(int dealId) {
        int numOfRows = 0;
        try {
            numOfRows = jdbcTemplate.update("DELETE FROM deal WHERE deal_id = ?;", dealId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numOfRows;
    }


    public Deal mapRowToDeal (SqlRowSet rowSet) {
        Deal deal = new Deal();
        deal.setDealId("Unable to set deal id", rowSet.getInt("deal_id"));
        deal.setPlaceId("Unable to set place id", rowSet.getInt("place_id"));
        deal.setTypeOfDeal(rowSet.getString("type_of_deal"));
        deal.setDealDescription((rowSet.getString("deal_description")));
        return deal;
    }
}
