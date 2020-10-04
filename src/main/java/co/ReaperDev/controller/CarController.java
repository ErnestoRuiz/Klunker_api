package co.ReaperDev.controller;

import co.ReaperDev.dto.CarDTO;
import co.ReaperDev.dto.UserDTO;
import co.ReaperDev.service.CarService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/car")
public class CarController {
    private CarService service;

    @PostMapping(value = "/addCar", consumes = "application/json")
    public ResponseEntity createNewCar(@RequestBody CarDTO carDTO) {
        log.info("/createCar endpoint hit");
        service.createCar(carDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/getCarsByUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity getCarsByUserName(@RequestBody UserDTO userDTO){
        log.info("/getCarsByUser endpoint hit");
        service.getCarsByUser(userDTO);
        return ResponseEntity.ok(service.getCarsByUser(userDTO));
    }

    @DeleteMapping(value = "/deleteCar", consumes = "application/json")
    public ResponseEntity deleteCar(@RequestBody CarDTO carDTO){
        log.info("/deleteCar endpoint hit");
        service.deleteCar(carDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
