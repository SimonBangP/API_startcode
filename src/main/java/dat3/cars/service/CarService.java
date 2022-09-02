package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private CarRepository carRepository;

    public CarService (CarRepository carRepository){
        this.carRepository = carRepository;
    }


public List<CarResponse> getCars (){

    List<Car> cars = carRepository.findAll();

    List<CarResponse> response = cars.stream().map(car -> new CarResponse(car, false)).collect(Collectors.toList());
    return response;
}

public CarResponse findCarById(@PathVariable int id) throws Exception{
        Car found = carRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id not found"));

        return new CarResponse(found, false);
}

public CarResponse addCar(CarRequest carRequest){

        Car newCar = CarRequest.getCarEntity(carRequest);
        newCar = carRepository.save(newCar);
        return new CarResponse(newCar, false);
}


public void deleteCarById(int id){
        carRepository.deleteById(id);
}
}
