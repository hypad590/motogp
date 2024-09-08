package com.hypad.motogp.RestfulMotoGP.service;

import com.hypad.motogp.RestfulMotoGP.model.MotoGPRider;
import com.hypad.motogp.RestfulMotoGP.repository.MotoGPRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MotoGPService {
    private final MotoGPRepository motoGPRepository;

    @Autowired
    public MotoGPService(MotoGPRepository motoGPRepository) {
        this.motoGPRepository = motoGPRepository;
    }
    public Optional<MotoGPRider> findRider(String riderName){
        return motoGPRepository.findByRiderName(riderName);
    }
    @Transactional
    public void deleteRider(MotoGPRider rider){
        motoGPRepository.delete(rider);
        log.info("Rider deleted: {}",rider);
    }
    public void addRider(MotoGPRider rider){
        motoGPRepository.save(rider);
        log.info("Rider saved: {}",rider);
    }
    public void updateRider(MotoGPRider updatedRider){
        MotoGPRider riderToBeUpdated = null;
        if(updatedRider!=null){
             if(motoGPRepository.findByRiderName(updatedRider.getRiderName()).isPresent()){
                 riderToBeUpdated = motoGPRepository.findByRiderName(updatedRider.getRiderName()).get();
                 riderToBeUpdated.setRiderName(updatedRider.getRiderName());
                 riderToBeUpdated.setWins(updatedRider.getWins());
                 riderToBeUpdated.setPosition(updatedRider.getPosition());
             }
             else {
                 log.error("No rider with name: {}", updatedRider.getRiderName());
             }
        }
        else {
            log.error("Requested entity is null");
        }
    }
    public List<MotoGPRider> getByWins(Integer wins){
        return motoGPRepository.findAll()
                .stream()
                    .filter(rider -> Objects.equals(rider.getWins(), wins))
                        .collect(Collectors.toList());
    }
    public List<MotoGPRider> getRidersByPosition(Integer pos){
        return motoGPRepository.findAll()
                .stream()
                    .filter(rider -> Objects.equals(rider.getPosition(), pos))
                        .collect(Collectors.toList());
    }
    public List<MotoGPRider> getRidersByFilters(String riderName,Integer pos,Integer wins){
        return motoGPRepository.findAll()
                .stream()
                    .filter(rider -> (riderName == null || rider.getRiderName().equalsIgnoreCase(riderName)))
                    .filter(rider -> (pos == -1 || Objects.equals(rider.getPosition(), pos)))
                    .filter(rider -> (wins == -1 || Objects.equals(rider.getWins(), wins)))
                        .collect(Collectors.toList());
    }
}
