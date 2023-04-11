package com.example.musicP3.service;

import com.example.musicP3.entities.Music_info;
import com.example.musicP3.repository.MusicRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicService {
    @Autowired
    private MusicRepositoryInterface musicRepositoryInterface;

    public Iterable<Music_info> getMusicList() {
        return musicRepositoryInterface.findAll();
    }

    public Music_info add(Music_info music_info) {
        return musicRepositoryInterface.save(music_info);
    }


}

