package edu.curtin.vehicleapp;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

class VehicleAppTest
{
    @TempDir
    protected static Path tempDir;

    @Test
    protected void servicesTest() throws IOException, FileParseException
    {
        var path = tempDir.resolve("testservices.txt");
        Files.write(
            path,
            List.of(
                "Service Name:Service Description",
                "Service Name 2:Service Description 2",
                "Service Name 3:Service Description 3"
            ));

        var services = new ServicesFileIO().readServices(path.toString());

        assertEquals("Service Name",          services.get(0).getName());
        assertEquals("Service Description",   services.get(0).getDescription());
        assertEquals("Service Name 2",        services.get(1).getName());
        assertEquals("Service Description 2", services.get(1).getDescription());
        assertEquals("Service Name 3",        services.get(2).getName());
        assertEquals("Service Description 3", services.get(2).getDescription());
    }

    @Test
    protected void requestsTest() throws IOException, FileParseException
    {
        var path = tempDir.resolve("testservices.txt");

        var req0 = "taxi,CBD,Airport,2024-02-02T02:00:00 ; nseats,13 ; entertainment";
        var req1 = "tour,Hotel Central,2026-12-01T08:00:00,Hilltop+Islands+Lunch+Crystal Cave+Riverside; nseats,25 ; storage_kg,500";
        var req2 = "rent,2025-04-01T12:00:00,2025-04-20T12:00:00 ; wheelchair ; storage_kg,80 ; nseats,4";
        Files.write(path, List.of(req0, req1, req2));

        var requests = new RequestsFileIO().readRequests(path.toString());

        String fmt0 = requests.get(0).format().toLowerCase();
        String fmt1 = requests.get(1).format().toLowerCase();
        String fmt2 = requests.get(2).format().toLowerCase();

        assertContains(
            List.of("taxi", "cbd", "airport", "seat", "13", "entertainment"),
            List.of("wheelchair", "kg"),
            req0, fmt0);

        assertContains(
            List.of("tour", "hotel central", "hilltop", "islands", "lunch", "crystal cave",
                    "riverside", "seat", "25", "kg", "500"),
            List.of("entertainment", "wheelchair"),
            req1, fmt1);

        assertContains(
            List.of("rent", "wheelchair", "seat", "4", "kg", "80"),
            List.of("entertainment"),
            req2, fmt2);

        assertSatisfiedBy(
            "13 seat van with entertainment system",
            List.of(
                "12 seat van with entertainment system",
                "van with entertainment system",
                "13 seat van"
            ),
            req0,
            requests.get(0));

        assertSatisfiedBy(
            "bus, 25 seats, 500 kg storage",
            List.of(
                "bus, 24 seats, 500 kg storage",
                "bus, 500 kg storage",
                "bus, 25 seats, 499 kg storage",
                "bus, 25 seats"
            ),
            req1,
            requests.get(1));

        assertSatisfiedBy(
            "car with 4 seatsl, wheelchair access and 80 kg storage",
            List.of(
                "car with 3 seats, wheelchair access and 80 kg storage",
                "car with wheelchair access and 80 kg storage",
                "car with 4 seats and 80 kg storage",
                "car with 4 seats, wheelchair access and 79 kg storage",
                "car with 4 seats and wheelchair access"
            ),
            req2,
            requests.get(2));
    }

    private void assertContains(List<String> expected, List<String> unexpected, String original, String formatted)
    {
        for(var s : expected)
        {
            assertTrue(
                formatted.contains(s),
                String.format(
                    "Formatted description of '%s' expected to contain '%s', but is '%s'",
                    original, s, formatted));
        }

        for(var t : unexpected)
        {
            assertFalse(
                formatted.contains(t),
                String.format(
                    "Formatted description of '%s' expected _not_ to contain '%s', but is '%s'",
                    original, t, formatted));
        }
    }

    private void assertSatisfiedBy(String validService, List<String> invalidServices,
                                   String reqLine, CustomerRequest req)
    {
        assertTrue(
            req.satisfiedBy(validService),
            String.format(
                "satisfiedBy() should return true for request '%s' given service '%s'",
                reqLine, validService));

        for(var invalidService : invalidServices)
        {
            assertFalse(
                req.satisfiedBy(invalidService),
                String.format(
                    "satisfiedBy() should return false for request '%s' given service '%s'",
                    reqLine, invalidService));
        }
    }
}
