package es.salesianos.model;

import java.util.Date;

public class Persona {
	
	private int codPersona;
	private String  nombre;
	private Date anonacimiento;
	private int curso;
	
	public int getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(int codPersona) {
		this.codPersona = codPersona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getAnonacimiento() {
		return anonacimiento;
	}
	public void setAnonacimiento(Date anonacimiento) {
		this.anonacimiento = anonacimiento;
	}
	public int getCurso() {
		return curso;
	}
	public void setCurso(int curso) {
		this.curso = curso;
	}
	
	

}
