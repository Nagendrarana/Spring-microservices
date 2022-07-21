package com.database.database.controller;
import com.database.database.model.TutorialModel;
import com.database.database.services.TutorialServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
public class APITutorialController {
    @Autowired
    private TutorialServices tutorialServices;

    @GetMapping("/tutorials")
    public ResponseEntity<List<TutorialModel>> getAllTutorials(@RequestParam(required=false) String title){
        return tutorialServices.getalltutorial(title);
    }



    @PostMapping("/tutorials")
    public ResponseEntity<TutorialModel> putTutorials(@RequestBody TutorialModel tm ) {

        return tutorialServices.puttutorials(tm);
    }



    @GetMapping("/tutorials/{id}")
    public ResponseEntity<TutorialModel> getTutorials(@PathVariable long id){

        return tutorialServices.gettutorials(id);

    }


    @PutMapping("/tutorials/{id}")
    public ResponseEntity<TutorialModel> updateTutorial(@PathVariable long id ,@RequestBody TutorialModel tm){

        return tutorialServices.updatetutorial(id,tm);
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {

        return tutorialServices.deletetutorial(id);
    }


    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {

        return tutorialServices.deletealltutorials();
    }


    @GetMapping("/tutorials/published")
    public ResponseEntity<List<TutorialModel>> findByPublished() {
        return tutorialServices.findbypublished();
    }



}

