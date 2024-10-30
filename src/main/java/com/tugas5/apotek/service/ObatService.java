package com.tugas5.apotek.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tugas5.apotek.models.Obat;
import com.tugas5.apotek.repositories.ObatRepository;

@Service
public class ObatService {
    @Autowired
    private ObatRepository obatRepository;

    public void save(Obat obat){
        obatRepository.save(obat);
    }

    public Obat findByObats(String namaObat, Long price, String description){
        List <Obat> obat = obatRepository.findObatByNamaObatAndPrice(namaObat, price);
        return obat.stream()
                    .findAny()
                    .orElse(null);
    }

    public void deleteById(Long id){
        obatRepository.deleteById(id);
    }

    public List<Obat> getAllObat(){
        return obatRepository.findAll();
    }
}
