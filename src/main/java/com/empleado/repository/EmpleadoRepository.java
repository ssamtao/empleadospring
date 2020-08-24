package com.empleado.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.empleado.model.Empleado;


public interface EmpleadoRepository extends MongoRepository<Empleado, String> {
  List<Empleado> findByNoempleado(int noEmpleado);
}