package com.bilgeadam.OBS.with.spring.boot.Controller;
import com.bilgeadam.OBS.with.spring.boot.Entity.Konu;
import com.bilgeadam.OBS.with.spring.boot.Repository.KonuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/konu")
public class KonuController {
    private KonuRepository repository;

    @Autowired
    public KonuController(KonuRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/getAll", produces = "application/json;charset=UTF-8")
    public List<Konu> getAll() {
        return repository.getAll();
    }

    @GetMapping(value = "/getById/{id}",produces = "application/json;charset=UTF-8")
    public Konu getById(@PathVariable long id) {
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
    public String save(@RequestBody Konu konu){
        boolean result = repository.save(konu);
        if (result)
            return "Yeni Konu Eklendi !";
        else
            return "Yeni Konu Ekleme BAŞARISIZ !";
    }
}
