package com.example.musicP3.controller;

import com.example.musicP3.entities.Music_info;
import com.example.musicP3.form.CreateMusicForm;
import com.example.musicP3.repository.MusicRepository;
import com.example.musicP3.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class MusicController {
    @Autowired
    private MusicRepository musicRepository;

    //@Autowired
    //private MusicService musicService;

    @RequestMapping("/")
    public String displayHome(){

        return "index";
    }

    //METHODE LISTE
   @RequestMapping("/list")
    public @ModelAttribute("musics") List<Music_info> displayList() {
        List<Music_info> musics = musicRepository.list();
        return musics;
    }

   // @RequestMapping("/list")
   // public @ModelAttribute("musics") Iterable<Music_info> displayList() {
     //  Iterable<Music_info> musics = musicService.list();
     //  return musics;
    //}


    //METHODE SHOW
    @GetMapping("/view-music/{id}")
    public String showMusic(@PathVariable("id") String id, Model model){
        try{
            Music_info music = musicRepository.show(id);
            //ajouter le template modèle
            model.addAttribute("music", music);
            return "view-music";
        }catch (DataAccessException e){
            e.printStackTrace();
            return "erreurId";
        }
    }

    //METHODE UPDATE

    @GetMapping("edit-music/{id}")
    public String editMusic(@PathVariable("id") String id, Model model){
        System.out.println("i am here in Get");
        Music_info music = musicRepository.show(id);
        CreateMusicForm createMusicForm = new CreateMusicForm(); //initialiser l'objet CreateMusicForm
        createMusicForm.setTitle(music.getTitle());
        createMusicForm.setDescription((music.getDescription()));
        model.addAttribute("createMusicForm", music);
        return "edit-music";
    }

    @PostMapping("edit/{id}")
    public String edit(@ModelAttribute CreateMusicForm createMusicForm, @PathVariable("id") String id)  {
        System.out.println("i am here in Post");
        Music_info music = musicRepository.show(id);
        music.setTitle(createMusicForm.getTitle());
        music.setDescription(createMusicForm.getDescription());
        musicRepository.update(music);
        return "successForm";
    }

    //METHODE DELETE
    @PostMapping(path = "{id}")
    public String deleteMusic(@PathVariable int id){
        musicRepository.delete(id);
        return "list";
    }

    //METHODE AJOUT
    @GetMapping("create-music")
    public String displayAdd(@ModelAttribute CreateMusicForm createMusicForm) {
        return "create-music";
    }

    //METHODE CREER
    @PostMapping("/createMusic")
    public String createMusic(@ModelAttribute CreateMusicForm createMusicForm) {
        System.out.println("je suis là");
        //créer un nouvel objet
        Music_info music = new Music_info();
        music.setTitle(createMusicForm.getTitle());
        music.setDescription(createMusicForm.getDescription());
        musicRepository.add(music);
        return "successForm";
        //return "list"
    }
}
