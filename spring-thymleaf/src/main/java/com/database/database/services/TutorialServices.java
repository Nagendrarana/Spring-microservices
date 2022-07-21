package com.database.database.services;
import com.database.database.exception.ResourceNotFoundException;
import com.database.database.model.TutorialModel;
import com.database.database.model.Users;
import com.database.database.repository.TutorialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TutorialServices {
    @Autowired
    private TutorialRepository tutorialrepository;
    private static final Logger log = LoggerFactory.getLogger(TutorialServices.class);



    public ResponseEntity<List<TutorialModel>> getalltutorial(String title){
    List<TutorialModel> tutorials = new ArrayList<TutorialModel>();
    if (title == null) {

        tutorialrepository.findAll().forEach(tutorials::add);
        log.info("Title is NULL ");
    }
    else{
        tutorialrepository.findByTitleContaining(title).forEach(tutorials::add);
        log.info("Table is not empty");
    }
    if (tutorials.isEmpty()){
        log.info("Table is empty");
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    log.info("Data Fetched from Database");

    return new ResponseEntity<>(tutorials,HttpStatus.OK);
}
    public ResponseEntity<TutorialModel> puttutorials(TutorialModel tm ) {
        try {
            TutorialModel tutorial = tutorialrepository.save(new TutorialModel(tm.getTitle(), tm.getDescription(), false));
            ResponseEntity<TutorialModel> responseEntity = new ResponseEntity<>(tutorial,
                    HttpStatus.OK);
            log.info("Data added to database");
            return responseEntity;

        } catch (Exception e) {
            ResponseEntity<TutorialModel> responseEntity = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Internal Server Error occurred ");
            return responseEntity;

        }
    }
    public ResponseEntity<TutorialModel> gettutorials(long id) {
        /*return "Should return Data on the basis of the id " +id;*/
        Optional<TutorialModel> tutorialdata = tutorialrepository.findById(id);

        if (tutorialdata.isPresent()) {
            ResponseEntity<TutorialModel> responseEntity = new ResponseEntity<>(tutorialdata.get(), HttpStatus.OK);
            log.info("Returned by taking the id");
            return responseEntity;
        } else {
            log.error("Internal Server Error occurred ");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<TutorialModel> updatetutorial(long id ,TutorialModel tm){

        Optional<TutorialModel> tutorialdata = tutorialrepository.findById(id);
        if(tutorialdata.isPresent()){
            TutorialModel tutorial = tutorialdata.get();
            tutorial.setTitle(tm.getTitle());
            tutorial.setDescription((tm.getDescription()));
            tutorial.setPublished(tm.isPublished());
            log.info("Data updated Successfully");
            return new ResponseEntity<>(tutorialrepository.save(tutorial),HttpStatus.OK);
        }
        else{
            log.error("Internal Server Error occurred ");
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    public ResponseEntity<HttpStatus> deletetutorial(long id) {
        try {
            tutorialrepository.deleteById(id);
            log.info("Data with id = {} deleted",id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("Internal Server Error occurred ");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<HttpStatus> deletealltutorials() {
        try {

            tutorialrepository.deleteAll();
            log.warn("Deleted all the data");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("Internal Server Error occurred ");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<TutorialModel>> findbypublished() {
        try {
            List<TutorialModel> tutorials = tutorialrepository.findByPublished(true);
            if (tutorials.isEmpty()) {
                log.warn("Table is Empty");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.info("Data returned on the basis of publication");
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Internal Server Error occurred ");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public String delete(Model model, TutorialModel tutorialModel){
        List<TutorialModel> tutorial = new ArrayList<>();
        tutorialrepository.findAll().forEach(tutorial::add);
        model.addAttribute("message",tutorial);
        return "delete";
    }
    public String deletebyweb(Model model, TutorialModel tutorialModel){
        if(tutorialrepository.findById(tutorialModel.getId()).isPresent()) {

            tutorialrepository.deleteById(tutorialModel.getId());
            log.info("Data deleted with the help of web");

            return "redirect:/delete";
        }
        else{
            log.error("Id is not present");
            return "redirect:/delete";
        }



    }
    public String update(Model model,TutorialModel tutorialModel){
        List<TutorialModel> tutorial = new ArrayList<>();
        tutorialrepository.findAll().forEach(tutorial::add);
        model.addAttribute("message",tutorial);
        return "update";
    }
    public String save(Model model, TutorialModel tutorialModel){
        tutorialrepository.save(tutorialModel);
        log.info("Data inserted using Web");
        return "redirect:/insert";
    }
    public String insert(Model model, TutorialModel tutorialModel){

        List<TutorialModel> tutorial = new ArrayList<>();
        tutorialrepository.findAll().forEach(tutorial::add);
        model.addAttribute("message",tutorial);
        return "insert";
    }
    public String get_data(Model model){
        List<TutorialModel> tutorial = new ArrayList<>();
        tutorialrepository.findAll().forEach(tutorial::add);
        model.addAttribute("message",tutorial);
        log.info("Data fetched from Browser");
        return "get_data";
    }
    public String practice( Model model){
        List<TutorialModel> tutorial = new ArrayList<>();

        tutorialrepository.findAll().forEach(tutorial::add);
        int id =1;
        String test = tutorialrepository.findByTitleContaining(" new demo2").toString();
        model.addAttribute("message",tutorial);

        return "practice";
    }
    public  String index(Model model){
        try{
            log.info("Index Loaded");
            return "index";
        }
        catch (Exception e){
            log.error("Could not load index");
            System.out.println(e);
        }

        return "index";
    }

    public String updatebyweb(Model model, TutorialModel tutorialModel) throws ResourceNotFoundException,Exception, TutorialServiceException {
        try {
            if (tutorialrepository.findById(tutorialModel.getId()).isPresent()) {
                tutorialrepository.save(tutorialModel);
                log.info("Table updated using Browser");
                return "redirect:/update";
            }
            else{
                log.error("Tried to update a field that doesn't exist");
                throw new ResourceNotFoundException("Data not found");
            }
        }
        catch (Exception e){
                throw new TutorialServiceException("Internal error");
        }

        finally {
            return "redirect:/update";
        }



    }
}
