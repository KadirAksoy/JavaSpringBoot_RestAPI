package com.kadiraksoy.ilService.Controller;


import com.kadiraksoy.ilService.Exception.IlAllReadyExistsException;
import com.kadiraksoy.ilService.Exception.IlNotFoundException;
import com.kadiraksoy.ilService.Model.Il;
import com.kadiraksoy.ilService.Service.IlService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iller")
@AllArgsConstructor

public class ILController {

    private final IlService ilService;

    @GetMapping
    public ResponseEntity<List<Il>> getIller(@RequestParam(required = false) String name){
        return new ResponseEntity<>(ilService.getIller(name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Il> getIl(@PathVariable String id){
        return new ResponseEntity<>(ilService.getIlById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Il> createIl(@RequestBody Il newIl){
        return new ResponseEntity<>(ilService.createIl(newIl),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> getIl(@PathVariable String id, @RequestBody Il newIl){
        ilService.updateIl(id, newIl);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIl(@PathVariable String id) {
        ilService.deleteIl(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private Il getIlById(String id){
        return ilService.getIlById(id);
    }

    @ExceptionHandler(IlNotFoundException.class)
    public ResponseEntity<String> handleIlNotFoundException(IlNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IlAllReadyExistsException.class)
    public ResponseEntity<String> handleIlIlAlreadyExistsException(IlAllReadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }


}
