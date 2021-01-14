package com.udacity.vehicles.domain.manufacturer;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Declares class to hold car manufacturer information.
 */
@Entity
public class Manufacturer {

    @Id
    @Schema(description = "Manufacturer code.",
        example = "101", required = true)
    private Integer code;
    @Schema(description = "Manufacturer name.",
        example = "Chevrolet", required = true)
    private String name;

    public Manufacturer() { }

    public Manufacturer(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
