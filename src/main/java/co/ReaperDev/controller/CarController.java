package co.ReaperDev.controller;

import co.ReaperDev.dto.CarDTO;
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

    @RequestMapping(value = "/getCarsByUser", method = RequestMethod.GET)
    public ResponseEntity getCarsByUserId(@RequestParam ("userId") int id){
        log.info("/getCarsByUser endpoint hit");
        carService.getCarsByUser(id);
        return ResponseEntity.ok(carService.getCarsByUser(id));
    }

    @RequestMapping(value = "/deleteCar", method = RequestMethod.DELETE)
    public ResponseEntity deleteCar(@RequestParam ("carId") int carId){
        log.info("/deleteCar endpoint hit");
        carService.deleteCar(carId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value = "/getCostOfServices", method = RequestMethod.GET)
    public ResponseEntity getCostOfServices(@RequestParam ("carId") int carId){
        log.info("/getCostOfServices endpoint hit");
        return ResponseEntity.ok(serviceService.getCostOfServices(carId));
    }

}
