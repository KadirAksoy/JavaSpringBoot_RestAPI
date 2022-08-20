package com.kadiraksoy.ilService.Service;

import com.kadiraksoy.ilService.Exception.IlAllReadyExistsException;
import com.kadiraksoy.ilService.Exception.IlNotFoundException;
import com.kadiraksoy.ilService.Model.Il;
import com.kadiraksoy.ilService.Repository.IlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class IlService {

    private final IlRepository ilRepository;


    public List<Il> getIller(String name) {
        if(name == null){
            return ilRepository.findAll();
        }else {
            return  ilRepository.findAllByName(name);
        }
    }


    public Il createIl(Il newIl){
        Optional<Il> ilByName = ilRepository.findByName(newIl.getName());
        if (ilByName.isPresent()) {
            throw new IlAllReadyExistsException("Il already exists with name:" + newIl.getName());
        }
        return ilRepository.save(newIl);
    }


    public void deleteIl(String id){
        ilRepository.deleteById(id);
    }


    public Il getIlById(String id){
        return ilRepository.findById(id)
                .orElseThrow(() -> new IlNotFoundException("Il not found with id:" + id));
    }


    public void updateIl(String id ,Il newIl){
        Il oldIl = getIlById(id);
        oldIl.setName(newIl.getName());
        ilRepository.save(oldIl);
    }
}
