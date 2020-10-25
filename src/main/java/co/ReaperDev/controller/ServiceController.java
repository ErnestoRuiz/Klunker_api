package co.ReaperDev.controller;

import co.ReaperDev.dto.CarDTO;
import co.ReaperDev.dto.ServiceDTO;
import co.ReaperDev.dto.UserDTO;
import co.ReaperDev.service.ServiceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/service")
public class ServiceController {
    private ServiceService service;

    @PostMapping(value = "/createService", consumes = "application/json")
    public ResponseEntity createService(@RequestBody ServiceDTO serviceDTO){
        log.info("createService endpoint hit");
        service.createService(serviceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/getServicesByCar", consumes = "application/json")
    public ResponseEntity getServicesByCar(@RequestBody CarDTO carDTO){
        log.info("getServicesByCarId endpoint hit");
        return ResponseEntity.ok(service.getServicesByCar(carDTO));
    }

    @GetMapping(value = "/getServicesByUser", consumes = "application/json")
    public ResponseEntity getServicesByUserId(@RequestBody UserDTO userDTO){
        log.info("getServicesByUserId endpoint hit");
        return ResponseEntity.ok(service.getServicesByUser(userDTO));
    }

    @DeleteMapping(value = "/deleteService", consumes = "application/json")
    public ResponseEntity deleteService(@RequestBody ServiceDTO serviceDTO){
        log.info("deleteService endpoint hit");
        service.deleteService(serviceDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
