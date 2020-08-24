package com.empleado.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.CompletionStage;

import javax.validation.Valid;

import org.springframework.http.MediaType;

import com.empleado.model.Empleado;
import com.empleado.repository.EmpleadoRepository;


@RestController
@RequestMapping("/api")
public class EmpleadoController {

  @Autowired
  EmpleadoRepository empleadoRepository;

  @GetMapping("/empleados")
  public ResponseEntity<List<Empleado>> getAllEmpleados(@RequestParam(required = false) String title) {
	  try {
		    List<Empleado> empleados = new ArrayList<Empleado>();
		    if (title == null)
		      empleadoRepository.findAll().forEach(empleados::add);
	
		    if (empleados.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    return new ResponseEntity<>(empleados, HttpStatus.OK);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
  }

  @GetMapping("/empleados/{id}")
  public ResponseEntity<Empleado> getEmpleadoById(@PathVariable("id") String id) {
	  
	  Optional<Empleado> empleadoData = empleadoRepository.findById(id);
	

	  if (empleadoData.isPresent()) {
	    return new ResponseEntity<>(empleadoData.get(), HttpStatus.OK);
	  } else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
  }

  @PostMapping("/empleados")
  public ResponseEntity<Empleado> createEmpleado(@RequestBody Empleado empleado) {
	  try {
		  
		    Empleado _empleado = empleadoRepository.save(new Empleado(empleado.getId(),empleado.getNoempleado(),
		    		empleado.getNombre(),empleado.getSueldo(),empleado.getEmpresa()));
		    return new ResponseEntity<>(_empleado, HttpStatus.CREATED);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
    
  }

  @PutMapping("/empleados")
  //public ResponseEntity<Empleado> updateEmpleado(@PathVariable("id") String id, @Valid @RequestBody Empleado empleado) {
  public ResponseEntity<Empleado> updateEmpleado(@Valid @RequestBody Empleado empleado) {
	  Optional<Empleado> empleadolData = empleadoRepository.findById(empleado.getId());
	  System.out.println("Entrando a la modificacion :"+empleado);
	  if (empleadolData.isPresent()) {
	    Empleado _empleado= empleadolData.get();
	    _empleado.setNombre(empleado.getNombre());
	    _empleado.setEmpresa(empleado.getEmpresa());
	    _empleado.setNoempleado(empleado.getNoempleado());
	    _empleado.setSueldo(empleado.getSueldo());
	    return new ResponseEntity<>(empleadoRepository.save(_empleado), HttpStatus.OK);
	  } else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
  }

  @DeleteMapping("/empleados/{id}")
  public ResponseEntity<HttpStatus> deleteEmpleado(@PathVariable("id") String id) {
	  try {
		    empleadoRepository.deleteById(id);
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
  }

  @DeleteMapping("/empleados")
  public ResponseEntity<HttpStatus> deleteAllEmpleados() {
	  try {
		    empleadoRepository.deleteAll();
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		  }
  }


}