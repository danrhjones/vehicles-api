package com.udacity.vehicles.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * Stores information about a given location.
 * Latitude and longitude must be provided, while other
 * location information must be gathered each time from
 * the maps API.
 */
@Embeddable
public class Location {

    @NotNull
    @Schema(description = "Location latitude.",
        example = "37.3999133", required = true)
    private Double lat;

    @NotNull
    @Schema(description = "Location longitude.",
        example = "-122.1105521", required = true)
    private Double lon;

    private String address;

    private String city;

    private String state;

    private String zip;

    public Location() {
    }

    public Location(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
