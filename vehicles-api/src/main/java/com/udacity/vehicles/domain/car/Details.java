package com.udacity.vehicles.domain.car;

import com.udacity.vehicles.domain.manufacturer.Manufacturer;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Declares the additional detail variables for each Car object,
 * along with related methods for access and setting.
 */
@Embeddable
public class Details {

    @NotBlank
    @Schema(description = "Body",
        example = "Sedan", required = true)
    private String body;

    @NotBlank
    @Schema(description = "Models",
        example = "Imapla", required = true)
    private String model;

    @NotNull
    @ManyToOne
    private Manufacturer manufacturer;

    @Schema(description = "Number of doors",
        example = "5", required = false)
    private Integer numberOfDoors;
    @Schema(description = "Fuel type.",
        example = "petrol", required = false)
    private String fuelType;
    @Schema(description = "Engine.",
        example = "6l turbo", required = false)
    private String engine;
    @Schema(description = "Fuel type.",
        example = "petrol", required = false)
    private Integer mileage;
    @Schema(description = "Model year",
        example = "1978", required = false)
    private Integer modelYear;
    @Schema(description = "Production year",
        example = "1978", required = false)
    private Integer productionYear;
    @Schema(description = "External colour",
        example = "Beige", required = false)
    private String externalColor;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public String getExternalColor() {
        return externalColor;
    }

    public void setExternalColor(String externalColor) {
        this.externalColor = externalColor;
    }
}
