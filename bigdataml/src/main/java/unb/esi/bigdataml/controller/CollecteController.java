package unb.esi.bigdataml.controller;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unb.esi.bigdataml.dto.ApiResponse;
import unb.esi.bigdataml.service.CollecteService;

import java.time.LocalDate;
import java.util.Date;

@RestController
@CrossOrigin
public class CollecteController {

//juste pour voir les données à partir d'une date dans postman
    private final CollecteService collecteService;

    public CollecteController(CollecteService collecteService) {
        this.collecteService = collecteService;
    }

    @GetMapping("/collecte")
    ResponseEntity<ApiResponse> collecter(@RequestParam
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut)
    {
    return ResponseEntity.ok(collecteService.collecte(dateDebut));
    }

}
