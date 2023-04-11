package com.example.musicP3.api;

import com.example.musicP3.entities.Music_info;
import com.example.musicP3.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;


@Controller
public class MusicRessource {
    @Autowired
    private MusicRepository musicRepository;

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("apiList")
    public @ResponseBody List<Music_info> list(){
        return (List<Music_info>)  musicRepository.list();
    }

    @GetMapping("api/{id}")
    @ResponseBody
    public Optional<Music_info> get(@PathVariable("id") String id){
        return Optional.ofNullable(musicRepository.show(id));
    }

}
