package es.salesianos.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.salesianos.connection.AbstractConnection;
import es.salesianos.connection.H2Connection;
import es.salesianos.model.Pelicula;
import es.salesianos.model.Persona;
import es.salesianos.model.Actor;
import es.salesianos.model.Director;

public class ActoresRepository {

	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test;INIT=RUNSCRIPT FROM 'classpath:scripts/create.sql'";
	AbstractConnection manager = new H2Connection();


	public void insert(Persona persona) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		Calendar call = null;
		try {
			preparedStatement = conn
					.prepareStatement("INSERT INTO PERSONAS(NOMBRE, ANONACIMIENTO, CURSO) VALUES (?, ?, ?)");
			preparedStatement.setString(1, persona.getNombre());
			preparedStatement.setDate(2, persona.getAnonacimiento() ,call);
			preparedStatement.setInt(3, persona.getCurso());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
		}

		manager.close(conn);
	}
	
	public List<Actor> selectActoresBetweenYear(int ano1, int ano2){
		List<Actor> actores= new ArrayList<Actor>();
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = conn
					.prepareStatement("SELECT * FROM Actor AS A WHERE A.YEAROFBIRTHDATE BETWEEN ? AND ?;");
			preparedStatement.setInt(1, ano1);
			preparedStatement.setInt(2, ano2);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Actor actorenDataBase = new Actor();
				actorenDataBase.setCod(resultSet.getInt(1));
				actorenDataBase.setName(resultSet.getString(2));
				actorenDataBase.setYearofbirthdate((resultSet.getInt(3)));
				
				actores.add(actorenDataBase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
		}

		manager.close(conn);
		return actores;
	}
	
	public void insertPet(Pelicula mascota) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn
					.prepareStatement("INSERT INTO MASCOTAS(nomMascota,CodPersona) VALUES (?, ?)");
		//	preparedStatement.setString(1, mascota.getNomMascota());
		//	preparedStatement.setInt(2, mascota.getCodPersona());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
		}

		manager.close(conn);
	}
	
	public List<Actor>searchAll() {
		List<Actor> actores = new ArrayList<Actor>();
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = conn
					.prepareStatement("SELECT * FROM Actor");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Actor actorInDatabase = new Actor();
				actorInDatabase.setCod(resultSet.getInt(1));
				actorInDatabase.setName(resultSet.getString(2));
				actorInDatabase.setYearofbirthdate((resultSet.getInt(3)));
				
				actores.add(actorInDatabase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
		}

		manager.close(conn);
		return actores;
		
	}
	
	public Actor selectOneActor(int CodPersona) {
		Actor actorInDatabase = null;
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = conn
					.prepareStatement("SELECT * FROM Actor WHERE Cod = ?");
			preparedStatement.setInt(1, CodPersona);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				actorInDatabase = new Actor();
				actorInDatabase.setCod(resultSet.getInt(1));
				actorInDatabase.setName(resultSet.getString(2));
				actorInDatabase.setYearofbirthdate((resultSet.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
		}

		manager.close(conn);
		return actorInDatabase;
	}
	
	public void updatePerson(Actor persona) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn
					.prepareStatement("UPDATE Personas SET nombre = ?, apellido = ? WHERE CodPersona = ?");
			preparedStatement.setString(1, persona.getName());
		//	preparedStatement.setString(2, persona.getApellido());
		//	preparedStatement.setInt(3, persona.getCodPersona());
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}
		
	}
	
	public void borrarActor(Actor actor) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn
					.prepareStatement("DELETE FROM Actor WHERE COD = ?");	
			preparedStatement.setInt(1, actor.getCod());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			manager.close(preparedStatement);
			manager.close(conn);
		}
	}
	
}
