package com.example.musicP3.repository;


import com.example.musicP3.entities.Music_info;
import org.springframework.data.repository.CrudRepository;

public interface MusicRepositoryInterface extends CrudRepository<Music_info, String> {

}
