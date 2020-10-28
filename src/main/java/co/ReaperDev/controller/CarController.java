package co.ReaperDev.controller;

import co.ReaperDev.dto.CarDTO;
import co.ReaperDev.dto.UserDTO;
import co.ReaperDev.service.CarService;
import co.ReaperDev.service.ServiceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/car")
public class CarController {
    private CarService carService;
    private ServiceService serviceService;

    @PostMapping(value = "/addCar", consumes = "application/json")
    public ResponseEntity createNewCar(@RequestBody CarDTO carDTO) {
        log.info("/createCar endpoint hit");
        carService.createCar(carDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/getCarsByUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity getCarsByUserName(@RequestBody UserDTO userDTO){
        log.info("/getCarsByUser endpoint hit");
        carService.getCarsByUser(userDTO);
        return ResponseEntity.ok(carService.getCarsByUser(userDTO));
    }

    @PostMapping(value = "/deleteCar", consumes = "application/json")
    public ResponseEntity deleteCar(@RequestBody CarDTO carDTO){
        log.info("/deleteCar endpoint hit");
        carService.deleteCar(carDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping(value = "/getCostOfServices", consumes = "application/json")
    public ResponseEntity getCostOfServices(@RequestBody CarDTO carDTO){
        log.info("/getCostOfServices endpoint hit");
        return ResponseEntity.ok(serviceService.getCostOfServices(carDTO));
    }

}
