package com.bilgeadam.OBS.with.spring.boot.Controller;

import com.bilgeadam.OBS.with.spring.boot.Entity.Ders;
import com.bilgeadam.OBS.with.spring.boot.Repository.DersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ders")
public class DersController {
    private DersRepository repository;

    @Autowired
    public DersController(DersRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/getAll", produces = "application/json;charset=UTF-8")
    public List<Ders> getAll() {
        return repository.getAll();
    }

    @GetMapping(value = "/getById/{id}",produces = "application/json;charset=UTF-8")
    public Ders getById(@PathVariable long id) {
        System.out.println(repository.findById(id).toString());
        return repository.findById(id);
    }

    @DeleteMapping("deleteById/{id}")
    public String deleteById(@PathVariable("id")long id){
        boolean result = repository.deleteById(id);
        if (result)
            return "Ders Silindi !";
        else
            return "Ders Silme BAŞARISIZ !";
    }

    @PostMapping("/save")
    public String save(@RequestBody Ders ders){
        boolean result = repository.save(ders);
        if (result)
            return "Yeni Ders Eklendi !";
        else
            return "Yeni Ders Ekleme BAŞARISIZ !";
    }
}