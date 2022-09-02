package dat3.cars.service;

import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CarService {

    private CarRepository carRepository;


public List<CarResponse> getCars (){

    List<Car> cars = carRepository.findAll();
    List<CarResponse> response = cars.stream().map(car -> new CarResponse(car, false)).collect(Collectors.toList());

    return response;
}
}
