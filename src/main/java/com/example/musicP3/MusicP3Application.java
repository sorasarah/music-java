package com.example.musicP3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootApplication
@EnableJdbcRepositories
public class MusicP3Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MusicP3Application.class, args);
		DataSource ds = context.getBean(DataSource.class);
		Connection connection = null;

		try{
			connection = ds.getConnection();
			System.out.println("Connexion OK");
			ResultSet rs = connection.createStatement().executeQuery("SELECT music_id, title, description FROM music_info");
			while (rs.next()) {
				System.out.println(rs.getLong("music_id" )+ " | " + rs.getString("title")+ " | " + rs.getString("description"));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		finally {
			try{
				if (connection != null) {
					connection.close();
					System.out.println("connexion closed");
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

}
