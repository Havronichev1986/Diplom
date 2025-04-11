package com.diploma.diploma.services;

import com.diploma.diploma.models.Automobile;
import com.diploma.diploma.repositories.AutomobileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AutomobileService {

    @Autowired
    private AutomobileRepository automobileRepository;

    public List<Automobile> findAll(){return automobileRepository.findAll();}
    public Optional<Automobile> findById(Long id){return automobileRepository.findById(id);}
    public Automobile save(Automobile automobile){return automobileRepository.save(automobile);}
    @Transactional
    public List<Automobile> createAutomobile(List<Automobile> automobileList){return automobileRepository.saveAll(automobileList);}
    public void deleteById(Long id){automobileRepository.deleteById(id);}
}
