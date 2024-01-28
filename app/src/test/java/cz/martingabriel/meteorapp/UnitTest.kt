package cz.martingabriel.meteorapp

import cz.martingabriel.meteorapp.model.Geolocation
import cz.martingabriel.meteorapp.model.MeteoriteLandingInfo
import org.junit.Assert.assertEquals
import org.junit.Test

class UnitTest {
    @Test
    fun getMassStringTest() {
        assertEquals("1 kg", getMassString("1000"))
        assertEquals("2 kg", getMassString("2123"))
        assertEquals("10 g", getMassString("10"))
        assertEquals("1 ton", getMassString("1000000"))
        assertEquals("2 tons", getMassString("2000000"))
        assertEquals("3 tons", getMassString("3456789"))
        assertEquals("invalid", getMassString("0"))
        assertEquals("invalid", getMassString("-1"))
    }

    @Test
    fun getGeolocationStringTest() {
        val mockLandingData = MeteoriteLandingInfo(
            name = "Acapulco",
            id = "10",
            nametype = "Valid",
            recclass = "Acapulcoite",
            mass = "1914",
            fall = "Fell",
            year = "1976-01-01T00:00:00.000",
            reclat = "16.883330",
            reclong = "-99.900000",
            geolocation = Geolocation(
                latitude = "16.88333",
                longitude = "-99.9",
                countryName = "Example country name 4"
            )
        )

        val mockGeolocationData = Geolocation(
            latitude = "-17.16667",
            longitude = "-43.83333",
            countryName = "test"
        )
        assertEquals("Latitude: 16.88333 | Longitude: -99.9", getGeolocationString(mockLandingData.geolocation))
        assertEquals("Latitude: -17.16667 | Longitude: -43.83333", getGeolocationString(mockGeolocationData))
    }

    @Test
    fun getShortYearTest() {
        assertEquals("1861", getShortYear("1861-01-01T00:00:00.000"))
        assertEquals("2002", getShortYear("2002-01-01T00:00:00.000"))
        assertEquals("2010", getShortYear("2010-01-01T00:00:00.000"))
        assertEquals("2135", getShortYear("2135-01-01T00:00:00.000"))
        assertEquals("1625", getShortYear("1625-06-07T00:00:00.000"))
        assertEquals("", getShortYear(""))
        assertEquals("unknown", getShortYear(null))
    }
}