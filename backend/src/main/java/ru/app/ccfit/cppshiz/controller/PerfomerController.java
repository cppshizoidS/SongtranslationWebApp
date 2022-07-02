package ru.appp.ccfit.cppshiz.controller;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.app.ccfit.cppshiz.model.Performer;
import ru.app.ccfit.cppshiz.repo.PerformerRepository;

@RestController
@RequestMapping("/api")
public class PerfomerController {
    @Autowired
    PerformerRepository performerRepository;  

    @GetMapping("/get_all_performer")
    public ResponseEntity<Set<Performer>> getAllPerfomers() {
        Set<Performer> performer = new HashSet<Performer>();
        performerRepository.findAll().forEach(performer::add);
        if (performer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(performer, HttpStatus.OK);
    }

    @GetMapping("/get_performer_by_name")
    public ResponseEntity<Performer> getPerfomerByName(@RequestParam(name = "performer_name") String performerName) {
        Performer perfomer =performerRepository.findByPerformerName(performerName);

        if (perfomer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(perfomer, HttpStatus.OK);

    }

    
    @PostMapping("/add_performers")
    public ResponseEntity<Performer> addPerfomer(@RequestBody Performer performerRequest) {
        Performer performer = performerRepository.save(new Performer(performerRequest.getPerformerName()));
        return new ResponseEntity<>(performer, HttpStatus.CREATED);   

    }

    @DeleteMapping("/delete_perfomer")
    public ResponseEntity<HttpStatus> deletePerfomer(@RequestParam(name = "performer_id") long id) {
        performerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/performer")
    public ResponseEntity<HttpStatus> deleteAllPerfomers() {
        performerRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

   
}
