package dat3.cars.service;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReservationService {


    ReservationRepository reservationRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;

    public ReservationService(ReservationRepository reservationRepository, MemberRepository memberRepository, CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
    }

    public void reserveCar(String username, int carId, LocalDate rentalDate){
        Member member = memberRepository.findById(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "User not found"));
        Car car = carRepository.findById(carId).orElseThrow(()-> new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Car not found"));
        
       if (reservationRepository.existsByCar_IdAndRentalDate(carId, rentalDate)){
         throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Car already reserved for this date");

       }

        Reservation reservation = new Reservation(member, car, rentalDate);
        reservationRepository.save(reservation);

    }
}
