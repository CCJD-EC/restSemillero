package com.clearminds.cca.servicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.clearminds.cca.dtos.Estudiante;
import com.clearminds.cca.excepciones.BDDException;

@Path("/estudiantes")
public class RestEstudiantes {

	@GET
	@Path("/imprimir")
	@Produces(MediaType.APPLICATION_JSON)
	public Estudiante getHello() {
		return new Estudiante("Marco", "milano");
	}

	@POST
	@Path("/insertar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertar(Estudiante estudiante) throws IOException {
		File f = new File("conexion.properties");
		System.out.println("truasa: "+f.getAbsolutePath());
		ServicioEstudiante servicio = new ServicioEstudiante();
		try {
			servicio.insertarEstudiante(estudiante);
			System.out.println(estudiante.toString());
		} catch (BDDException e) {
			e.printStackTrace();
		}
	}
	
	@PUT
	@Path("/actualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	public void actualizar(Estudiante estudiante) throws IOException {
		ServicioEstudiante servicio = new ServicioEstudiante();
		try {
			servicio.actualizarEstudiante(estudiante);
			System.out.println(estudiante.toString());
		} catch (BDDException e) {
			e.printStackTrace();
		}
	}
}
