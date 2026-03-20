package com.practice.employeemanagement.state;

import com.practice.vendormanagement.exceptions.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StateService {

    StateRepository stateRepository;

    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public State createState(State state){
        if(stringInvalid(state.name)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid name.");

        if(stringInvalid(state.code)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid code.");

        if(!Boolean.TRUE.equals(state.active) && !Boolean.FALSE.equals(state.active)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid active value.");

        return stateRepository.saveAndFlush(state);
    }

    public ResponseEntity<?> getStateById(long stateId){
        State state = stateRepository.findById(stateId)
                                    .orElseThrow(() -> new NotFoundException("State not found with ID"));

        return ResponseEntity.ok(state);
    }

    public List<State> getAllStates(int pageNumber, int pageSize){
        List<State> states = stateRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();

        return states;
    }

    private Boolean stringInvalid(String stringValue){
        return stringValue == null || stringValue.isBlank();
    }

    public State updateState(State state){
        State stateToUpdate = stateRepository.findById(state.getId()).orElseThrow(() -> new NotFoundException("State not found with ID."));

        if(state.name != null) {
            if(state.name.isBlank()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid name.");
        }

        if(state.code != null) {
            if(state.code.isBlank()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid code.");
        }

        if(state.active != null) {
            if (!Boolean.TRUE.equals(state.active) && !Boolean.FALSE.equals(state.active))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid active value.");
        }

        stateToUpdate.setName(state.getName());
        stateToUpdate.setCode(state.getCode());
        stateToUpdate.setActive(state.getActive());

        return stateRepository.saveAndFlush(stateToUpdate);
    }
}
