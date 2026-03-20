package com.practice.employeemanagement.state;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/state")
public class StateController {

    StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @PostMapping("createState")
    public State createState(State state){
        return stateService.createState(state);
    }

    @GetMapping("stateById/{stateId}")
    @ResponseBody
    public ResponseEntity<?> getState(@PathVariable("stateId") long stateId){
        return stateService.getStateById(stateId);
    }

    //List
    @GetMapping("allStates")
    public List<State> getAllStates(@RequestParam int pageNumber, @RequestParam int pageSize){
        return stateService.getAllStates(pageNumber, pageSize);
    }

    @PutMapping("updateState")
    public State updateState(State state){
        return stateService.updateState(state);
    }
}
