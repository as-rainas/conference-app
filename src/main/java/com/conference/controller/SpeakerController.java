package com.conference.controller;

import com.conference.model.Speaker;
import com.conference.repositories.SpeakerRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/speaker")
public class SpeakerController {

    @Autowired
    private SpeakerRepository speakerRepository;


    @GetMapping
    public List<Speaker> findAll(){
        return speakerRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public Speaker get(@PathVariable Long id){
        return speakerRepository.getOne(id);
    }

    @PostMapping
    public Speaker create(@RequestBody final Speaker speaker){
        return speakerRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker){
        Speaker existingSpeaker = speakerRepository.getOne(id);
        BeanUtils.copyProperties( speaker, existingSpeaker);
        return speakerRepository.saveAndFlush(existingSpeaker);
    }

}
