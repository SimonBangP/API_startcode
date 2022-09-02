package dat3.cars.api;


import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

   // @Autowired
CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarResponse> getCars (){

        return carService.getCars();
    }

    @GetMapping(path = "/{id}")
    public CarResponse getCarById(@PathVariable int id) throws Exception {
    CarResponse response = carService.findCarById(id);
    return response;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public CarResponse addCar(@RequestBody CarRequest body) {
        return carService.addCar(body);
    }


    @DeleteMapping(path = "/{id}")
    public void deteleCarById(@PathVariable int id){
        carService.deleteCarById(id);
    }
}
