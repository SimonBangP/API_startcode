package dat3.cars.dto;

import dat3.cars.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CarRequest {

    int id;
    String brand;
    String model;
    double privePrDay;
    double bestDiscount;
    LocalDateTime created;
    LocalDateTime edited;

    public static Car getCarEntity (CarRequest c){

        return new Car(c.id, c.brand, c.model, c.privePrDay, c.bestDiscount);
    }
}
