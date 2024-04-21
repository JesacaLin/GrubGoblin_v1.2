package org.JesacaLin;

import org.JesacaLin.daos.AvailabilityDAO;
import org.JesacaLin.models.Availability;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class availabilityDAOTests extends BaseDaoTests{
    private AvailabilityDAO availabilityDAO;
    @Before
    public void setup() {
        this.availabilityDAO = new AvailabilityDAO(dataSource);
    }

    private static final Availability AVAIL_1 = new Availability(1, 1, LocalTime.parse("17:00:00", DateTimeFormatter.ofPattern("HH:mm:ss")), LocalTime.parse("19:00:00", DateTimeFormatter.ofPattern("HH:mm:ss")));
    private static final Availability AVAIL_2 = new Availability(7, 7, LocalTime.parse("17:00:00", DateTimeFormatter.ofPattern("HH:mm:ss")), LocalTime.parse("20:00:00", DateTimeFormatter.ofPattern("HH:mm:ss")));

    @Test
    public void getAvailabilityId_returns_correct_availability() {
        Availability actualAvailability = availabilityDAO.getAvailabilityById(1);
        assertAvailabilityMatch(AVAIL_1, actualAvailability);
    }

    @Test
    public void getAllAvailabilities_returns_all_Availabilities() {
        List<Availability> availabilities = availabilityDAO.getAllAvailabilities();
        Assert.assertNotNull(availabilities);
        Assert.assertEquals(7, availabilities.size());
        assertAvailabilityMatch(AVAIL_1, availabilities.get(0));
        assertAvailabilityMatch(AVAIL_2, availabilities.get(6));
    }

    private void assertAvailabilityMatch(Availability expected, Availability actual) {
        Assert.assertEquals(expected.getAvailabilityId(), actual.getAvailabilityId());
        Assert.assertEquals(expected.getDayOfWeek(), actual.getDayOfWeek());
        Assert.assertEquals(expected.getStartTime(), actual.getStartTime());
        Assert.assertEquals(expected.getEndTime(), actual.getEndTime());
    }
}
