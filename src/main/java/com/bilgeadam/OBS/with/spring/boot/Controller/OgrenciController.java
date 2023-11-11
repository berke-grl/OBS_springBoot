package com.bilgeadam.OBS.with.spring.boot.Controller;

import com.bilgeadam.OBS.with.spring.boot.Entity.Ogrenci;
import com.bilgeadam.OBS.with.spring.boot.Entity.Ogretmen;
import com.bilgeadam.OBS.with.spring.boot.Repository.OgrenciRepository;
import com.bilgeadam.OBS.with.spring.boot.Repository.OgretmenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ogrenci")
public class OgrenciController {
    private OgrenciRepository repository;

    @Autowired
    public OgrenciController(OgrenciRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/getAll", produces = "application/json;charset=UTF-8")
    public List<Ogrenci> getAll() {
        return repository.getAll();
    }

    @GetMapping(value = "/getById/{id}",produces = "application/json;charset=UTF-8")
    public Ogrenci getById(@PathVariable long id) {
        return repository.findById(id);
    }

    @DeleteMapping("deleteById/{id}")
    public String deleteById(@PathVariable("id")long id){
        boolean result = repository.deleteById(id);
        if (result)
            return "Ogrenci Silindi !";
        else
            return "Ogrenci Silme BAŞARISIZ !";
    }

    @PostMapping("/save")
    public String save(@RequestBody Ogrenci ogrenci){
        boolean result = repository.save(ogrenci);
        if (result)
            return "Yeni Ogrenci Eklendi !";
        else
            return "Yeni Ogrenci Ekleme BAŞARISIZ !";
    }
}
