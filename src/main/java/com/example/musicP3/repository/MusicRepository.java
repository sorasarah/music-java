package com.example.musicP3.repository;

import com.example.musicP3.entities.Music_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class MusicRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Music_info> list(){
        return jdbcTemplate.query("SELECT music_id, title, description FROM music_info",
                (rs, rowNum) -> new Music_info(
                        String.valueOf(rs.getLong("music_id")),
                        rs.getString("title"),
                        rs.getString("description")
                ));
    }


    //ajouter
    public int add( Music_info music_info){
        return jdbcTemplate.update("INSERT INTO music_info (title, description) VALUES (?,?)",
               new Object[]{
                    music_info.getTitle(),
                    music_info.getDescription()
                });
    }

    //vue d'un élément
    public Music_info show(String id) {
        return jdbcTemplate.queryForObject("SELECT * FROM music_info WHERE music_id = ?", new Object[]{id},
                (rs, rowNum) -> new Music_info(
                        String.valueOf(rs.getLong("music_id")),
                        rs.getString("title"),
                        rs.getString("description")
                ));
    }

    //
    public Music_info update( Music_info music_info){
        jdbcTemplate.update("UPDATE music_info SET title = ?, description = ? WHERE music_id=?",
                music_info.getTitle(), music_info.getDescription(), music_info.getMusic_id());
                return music_info;
    }


    //suppression
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM music_info WHERE music_id = ?", id);
    }
}
