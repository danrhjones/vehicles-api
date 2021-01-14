package com.udacity.vehicles.service;

import com.udacity.vehicles.VehiclesApiApplication;
import com.udacity.vehicles.client.maps.MapsClient;
import com.udacity.vehicles.client.prices.PriceClient;
import com.udacity.vehicles.domain.Location;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.CarRepository;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Implements the car service create, read, update or delete
 * information about vehicles, as well as gather related
 * location and price data when desired.
 */
@Service
public class CarService {
    @Autowired
    private Environment env;

    private VehiclesApiApplication vehiclesApiApplication;
    private final CarRepository repository;
    private PriceClient priceClient;
    private MapsClient mapsClient;



    public CarService(CarRepository repository) {
        vehiclesApiApplication = new VehiclesApiApplication();
        this.repository = repository;
        this.priceClient = priceClient;
        this.mapsClient = mapsClient;
    }

    /**
     * Gathers a list of all vehicles
     * @return a list of all vehicles in the CarRepository
     */
    public List<Car> list() {
        List<Car> cars=  repository.findAll();

        String priceEndpoint = env.getProperty("pricing.endpoint");
        WebClient pricing = vehiclesApiApplication.webClientPricing(priceEndpoint);

        for(Car car : cars) {
            Long id = car.getId();
            priceClient = new PriceClient(pricing);
            car.setPrice(priceClient.getPrice(id));
        }
        return cars;
    }

    /**
     * Gets car information by ID (or throws exception if non-existent)
     * @param id the ID number of the car to gather information on
     * @return the requested car's information, including location and price
     */
    public Car findById(Long id) {

        Car car;
        Optional<Car> optionalCar = repository.findById(id);
        if(optionalCar.isPresent()) {
            car = optionalCar.get();
        }
        else {
            throw new CarNotFoundException();
        }

        String priceEndpoint = env.getProperty("pricing.endpoint");
        WebClient pricing = vehiclesApiApplication.webClientPricing(priceEndpoint);
        priceClient = new PriceClient(pricing);
        car.setPrice(priceClient.getPrice(id));

        return car;
    }

    /**
     * Either creates or updates a vehicle, based on prior existence of car
     * @param car A car object, which can be either new or existing
     * @return the new/updated car is stored in the repository
     */
    public Car save(Car car) {
        if (car.getId() != null && car.getId() != 0) {
            return repository.findById(car.getId())
                    .map(carToBeUpdated -> {
                        carToBeUpdated.setCondition(car.getCondition());
                        carToBeUpdated.setDetails(car.getDetails());
                        carToBeUpdated.setLocation(mapsClient.getAddress(getMappedAddress(car)));
                        return repository.save(carToBeUpdated);
                    }).orElseThrow(CarNotFoundException::new);
        }
        Location location = getMappedAddress(car);
        car.setLocation(mapsClient.getAddress(location));
        return repository.save(car);
    }

    private Location getMappedAddress(Car car) {
        WebClient maps = vehiclesApiApplication.webClientMaps(env.getProperty("maps.endpoint"));
        mapsClient = new MapsClient(maps, new ModelMapper());
        return new Location(car.getLocation().getLat(), car.getLocation().getLon());

    }

    /**
     * Deletes a given car by ID
     * @param id the ID number of the car to delete
     */
    public void delete(Long id) {
        repository.delete(findById(id));
    }
}
