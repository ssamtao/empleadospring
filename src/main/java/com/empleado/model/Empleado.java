package com.empleado.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "empleados")
public class Empleado {
  @Id
  private String id;

  private int    noempleado;
  private String nombre;
  private float  sueldo;
  private String empresa;

  public Empleado() {

  }
  
  

  public Empleado(String id, int noempleado, String nombre, float sueldo, String empresa) {
	super();
	this.id = id;
	this.noempleado = noempleado;
	this.nombre = nombre;
	this.sueldo = sueldo;
	this.empresa = empresa;
  }

  public String getId() {
    return id;
  }

  
  
  public int getNoempleado() {
	return noempleado;
}



public void setNoempleado(int noEmpleado) {
	this.noempleado = noEmpleado;
}



public String getNombre() {
	return nombre;
}



public void setNombre(String nombre) {
	this.nombre = nombre;
}



public float getSueldo() {
	return sueldo;
}



public void setSueldo(float sueldo) {
	this.sueldo = sueldo;
}



public String getEmpresa() {
	return empresa;
}



public void setEmpresa(String empresa) {
	this.empresa = empresa;
}



public void setId(String id) {
	this.id = id;
}



@Override
  public String toString() {
    return "Emoleado [id=" + id + ", nombre=" + nombre+ ", sueldo=" + sueldo + ", empresa=" + empresa + "]";
  }
}