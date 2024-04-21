package org.JesacaLin;

import com.google.gson.internal.NonNullElementWrapperList;
import org.JesacaLin.daos.DealDAO;
import org.JesacaLin.models.Deal;
import org.JesacaLin.models.FullDealDetails;
import org.JesacaLin.models.Place;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DealDAOTests extends BaseDaoTests {
    private DealDAO dealDAO;
    @Before
    public void setup() {
        this.dealDAO = new DealDAO(dataSource);
    }

    private static final Deal DEAL_1 = new Deal(1, 1, "drinks", "$8 cocktails, 2 types");
    private static final Deal DEAL_2 = new Deal(7, 2, "drinks", "$11 cocktails");
    private static final FullDealDetails FULL_DEAL_DETAILS_1 = new FullDealDetails(1,	"elsa",	"136 Atlantic Ave, Brooklyn, NY 11201",	"drinks",	"$8 cocktails, 2 types",	1, LocalTime.parse("17:00:00", DateTimeFormatter.ofPattern("HH:mm:ss")),	3.9, "The happy hour drinks were ok, their full price cocktails are much better!");
    private static final FullDealDetails FULL_DEAL_DETAILS_2 = new FullDealDetails(7,	"vine",	"81 Fleet Pl, Brooklyn, NY 11201",	"drinks",	"$11 cocktails",	7, LocalTime.parse("17:00:00", DateTimeFormatter.ofPattern("HH:mm:ss")),	4.5, "Solid cocktails and friendly service. Wish it was a dollar or two less. The charcuterie is worth getting.");

    @Test
    public void getDealById_returns_correct_deal() {
        Deal actualDeal = dealDAO.getDealById(1);
        assertDealsMatch(DEAL_1, actualDeal);
    }

    @Test
    public void getAllDeals_returns_all_deals() {
        List<Deal> actualDeals = dealDAO.getAllDeals();
        Assert.assertNotNull(actualDeals);
        Assert.assertEquals(7, actualDeals.size());
        assertDealsMatch(DEAL_1, actualDeals.get(0));
        assertDealsMatch(DEAL_2, actualDeals.get(6));
    }

    @Test
    public void getAllDealByKeyword_returns_all_deals() {
        List<FullDealDetails> actualDeals = dealDAO.getAllDealByKeyword("cocktails");
        Assert.assertNotNull(actualDeals);
        Assert.assertEquals(7, actualDeals.size());
        assertFullDetailsMatch(FULL_DEAL_DETAILS_1, actualDeals.get(0));
        assertFullDetailsMatch(FULL_DEAL_DETAILS_2, actualDeals.get(6));
    }
    @Test
    public void getAllDealDetails_returns_all_deals() {
        List<FullDealDetails> actualDeals = dealDAO.getAllDealDetails();
        Assert.assertNotNull(actualDeals);
        Assert.assertEquals(7, actualDeals.size());
        assertFullDetailsMatch(FULL_DEAL_DETAILS_1, actualDeals.get(0));
        assertFullDetailsMatch(FULL_DEAL_DETAILS_2, actualDeals.get(6));
    }
    @Test
    public void createDeal_should_create_deal() {
        Deal newDeal = new Deal();
        newDeal.setDealId(8);
        newDeal.setPlaceId(2);
        newDeal.setTypeOfDeal("food");
        newDeal.setDealDescription("3 tacos for $10");
        Deal expectedDeal = dealDAO.createDeal(newDeal);
        int newId = expectedDeal.getDealId();
        Deal actualDeal = dealDAO.getDealById(newId);
        assertDealsMatch(expectedDeal, actualDeal);
    }

    @Test
    public void updateDeal_updates_deal() {
        Deal existingDeal = dealDAO.getDealById(1);
        existingDeal.setDealId(1);
        existingDeal.setPlaceId(1);
        existingDeal.setTypeOfDeal("food");
        existingDeal.setDealDescription("The happy hour drinks were ok, their full price cocktails are much better!");
        Deal updatedDeal = dealDAO.updateDeal(existingDeal);
        Deal actualDeal = dealDAO.getDealById(1);
        assertDealsMatch(updatedDeal, actualDeal);
    }

    @Test
    public void deleteDealById_deletes_correct_Deal() {
        int rowsAffected = dealDAO.deleteDealById(1);
        Assert.assertEquals(1, rowsAffected);

        Deal retrievedDeal = dealDAO.getDealById(1);
        Assert.assertNull(retrievedDeal);
    }


    private void assertDealsMatch(Deal expected, Deal actual) {
        Assert.assertEquals(expected.getDealId(), actual.getDealId());
        Assert.assertEquals(expected.getPlaceId(), actual.getPlaceId());
        Assert.assertEquals(expected.getTypeOfDeal(), actual.getTypeOfDeal());
        Assert.assertEquals(expected.getDealDescription(), actual.getDealDescription());
    }

    private void assertFullDetailsMatch(FullDealDetails expected, FullDealDetails actual) {
        Assert.assertEquals(expected.getDealId(), actual.getDealId());
        Assert.assertEquals(expected.getPlaceName(), actual.getPlaceName());
        Assert.assertEquals(expected.getAddress(), actual.getAddress());
        Assert.assertEquals(expected.getTypeOfDeal(), actual.getTypeOfDeal());
        Assert.assertEquals(expected.getDealDescription(), actual.getDealDescription());
        Assert.assertEquals(expected.getDayOfWeek(), actual.getDayOfWeek());
        Assert.assertEquals(expected.getStartTime(), actual.getStartTime());
        Assert.assertEquals(expected.getStars(), actual.getStars(), 0.1);
        Assert.assertEquals(expected.getReviewDescription(), actual.getReviewDescription());
    }

}
