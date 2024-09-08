package com.hypad.motogp.RestfulMotoGP.controller;

import com.hypad.motogp.RestfulMotoGP.model.MotoGPRider;
import com.hypad.motogp.RestfulMotoGP.service.MotoGPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/motogp")
public class RestGPController {
    private final MotoGPService motoGPService;

    @Autowired
    public RestGPController(MotoGPService motoGPService) {
        this.motoGPService = motoGPService;
    }
    @GetMapping("/rider")
    public MotoGPRider findRiderByName(@RequestParam String riderName){
        if(motoGPService.findRider(riderName).isPresent()){
            return motoGPService.findRider(riderName).get();
        }else {
            return null;
        }
    }
    @PostMapping("/deleteRider")
    public void deleteRider(@RequestBody MotoGPRider motoGPRider){
        motoGPService.deleteRider(motoGPRider);
    }
    @PostMapping("/addRider")
    public ResponseEntity<MotoGPRider> addRider(@RequestBody MotoGPRider motoGPRider){
        motoGPService.addRider(motoGPRider);
        return new ResponseEntity<>(motoGPRider, HttpStatus.CREATED);
    }
    @PutMapping("/updateRider")
    public ResponseEntity<MotoGPRider> updateRider(@RequestBody MotoGPRider updatedRider){
        motoGPService.updateRider(updatedRider);
        return new ResponseEntity<>(updatedRider, HttpStatus.OK);
    }
    @GetMapping("/getByWins")
    @ResponseStatus(HttpStatus.OK)
    public List<MotoGPRider> getByWins(@RequestParam Integer wins){
        return motoGPService.getByWins(wins);
    }
    @GetMapping("/getByPos")
    @ResponseStatus(HttpStatus.OK)
    public List<MotoGPRider> getByPos(@RequestParam Integer pos){
        return motoGPService.getRidersByPosition(pos);
    }
    @GetMapping("/getByFilters")
    @ResponseStatus(HttpStatus.OK)
    public List<MotoGPRider> getByFilters(@RequestParam String riderName,
                                          @RequestParam Integer pos,
                                          @RequestParam Integer wins){
        return motoGPService.getRidersByFilters(riderName,pos,wins);
    }

}
