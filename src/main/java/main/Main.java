package main;

import pojo.Propietario;
import pojo.Vehiculo;
import DAO.VehiculoDAO;

public class Main {

	public static void main(String[] args) {
		
		//Insertar un vehiculo y su propietario	
		System.out.println("Insertar un vehiculo y su propietario");
		Vehiculo v1 = new Vehiculo();
		Propietario p1 = new Propietario();
		p1.setDni("2");
		p1.setNombre("Sergi");
		p1.setApellido("L");
		v1.setMarca("Honda");
		v1.setMatricula("2");
		v1.setModelo("YZF");
		v1.setPropietario(p1);
		
		VehiculoDAO v2 = new VehiculoDAO();
		v2.addVehiculo(v1);
		
		System.out.println();
		
		//Actualizar un vehiculo y su propietario
		System.out.println("Actualizar un vehiculo y su propietario");
		Vehiculo v3 = new Vehiculo();
		Propietario p2 = new Propietario();
		p2.setDni("091436sdsdsd2d8J");
		p2.setNombre("Edu");
		p2.setApellido("L");
		v3.setMarca("Hondaaaa");
		v3.setMatricula("hola 83dfdsf97");
		v3.setModelo("YZFf");
		v3.setPropietario(p1);
		
		VehiculoDAO v4 = new VehiculoDAO();
		v4.updateVehiculo(v3);
		
		System.out.println();
//		
		//Eliminar un vehiculo y su propietario
		System.out.println("Eliminar un vehiculo y su propietario");
		VehiculoDAO v6 = new VehiculoDAO();
		v6.deleteVehiculo("hola 83dfdsf97");
		
		System.out.println();
		
		//Obtener lista de todos los vehiculos
		System.out.println("Obtener lista de vehiculos");
		VehiculoDAO v7 = new VehiculoDAO();
		v7.getAllVehiculos();
	}
}