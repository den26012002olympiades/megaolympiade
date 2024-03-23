package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;

@SpringBootApplication
@RestController("/api")
public class MegaolympiadeApplication {
	String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
	String username = "postgres";
	String password = "1";

	public static void main(String[] args) {
		SpringApplication.run(MegaolympiadeApplication.class, args);
	}

	@GetMapping("/api/movies")
	public ArrayList<Movie> getMovies() {

		try {
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
			String insertQuery = "SELECT * FROM movies;";
			PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
			//preparedStatement.setString(1, "John");
			//preparedStatement.setString(2, "Doe");
			var queryResult = preparedStatement.executeQuery();

			var arr = new ArrayList<Movie>();
			while (queryResult.next()) {
				arr.add(new Movie(
								queryResult.getInt(1),
								queryResult.getString(2),
								queryResult.getInt(3),
								queryResult.getInt(4),
								queryResult.getTime(5),
								queryResult.getInt(6)));
			}

			preparedStatement.close();
			connection.close();
			return arr;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new ArrayList<Movie>();
	}

	@GetMapping("/api/movies/{id}")
	public Movie getMovie(@PathVariable("id") Integer id) {
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
			String insertQuery = "SELECT * FROM movies WHERE id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setInt(1, id);
			//preparedStatement.setString(2, "Doe");
			var queryResult = preparedStatement.executeQuery();

			var movie = new Movie(0, "0", 0, 0, new Time(0), 0);
			if (queryResult.next()) {
				movie = new Movie(
						queryResult.getInt(1),
						queryResult.getString(2),
						queryResult.getInt(3),
						queryResult.getInt(4),
						queryResult.getTime(5),
						queryResult.getInt(6));
			}

			preparedStatement.close();
			connection.close();
			return movie;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new Movie(0, "0", 0, 0, new Time(0), 0);
	}
	@PostMapping("/api/movies")
	public void addMovie(@RequestBody Movie movie) {
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
			String insertQuery = "INSERT INTO movies(id, title, year, director, length, rating) VALUES(?, ?, ?, ?, ?, ?);";
			PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setInt(1, movie.id());
			preparedStatement.setString(2, movie.title());
			preparedStatement.setInt(3, movie.year());
			preparedStatement.setInt(4, movie.director());
			preparedStatement.setTime(5, movie.length());
			preparedStatement.setInt(6, movie.rating());
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@PatchMapping("/api/movies/{id}")
	public void patchMovie(@PathVariable("id") Integer id, @RequestBody Movie newMovie) {
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
			String insertQuery = "UPDATE movies SET (title, year, director, length, rating) = (?, ?, ?, ?, ?) WHERE id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setString(1, newMovie.title());
			preparedStatement.setInt(2, newMovie.year());
			preparedStatement.setInt(3, newMovie.director());
			preparedStatement.setTime(4, newMovie.length());
			preparedStatement.setInt(5, newMovie.rating());
			preparedStatement.setInt(6, id);
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping("/api/movies/{id}")
	public void deleteMovie(@PathVariable("id") Integer id) {
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
			String insertQuery = "DELETE FROM movies WHERE id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*
	* 2. GET /api/movies/:id - информация о кинофильме с указанным id
	* 3. POST /api/movies - добавление новой записи о кинофильме
	* 4. PATCH /api/movies/:id - изменение информации о кинофильме с указанным id
	* 5. DELETE /api/movies/:id - удаление записи с указанным id
	* */
}
