package com.bilgeadam.OBS.with.spring.boot.Controller;

import com.bilgeadam.OBS.with.spring.boot.Entity.Ogretmen;
import com.bilgeadam.OBS.with.spring.boot.Repository.OgretmenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ogretmen")
public class OgretmenController {
    private OgretmenRepository repository;

    @Autowired
    public OgretmenController(OgretmenRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/getAll", produces = "application/json;charset=UTF-8")
    public List<Ogretmen> getAll() {
        return repository.getAll();
    }

    @GetMapping(value = "/getById/{id}",produces = "application/json;charset=UTF-8")
    public Ogretmen getById(@PathVariable long id) {
        return repository.findById(id);
    }

    @DeleteMapping("deleteById/{id}")
    public String deleteById(@PathVariable("id")long id){
        boolean result = repository.deleteById(id);
        if (result)
            return "Öğretmen Silindi !";
        else
            return "Öğretmen Silme BAŞARISIZ !";
    }

    @PostMapping("/save")
    public String save(@RequestBody Ogretmen ogretmen){
        boolean result = repository.save(ogretmen);
        if (result)
            return "Yeni Öğretmen Eklendi !";
        else
            return "Yeni Öğretmen Ekleme BAŞARISIZ !";
    }
}
