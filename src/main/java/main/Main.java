package main;

import java.util.ArrayList;
import java.util.List;

import DAO.PropietarioDAO;
import DAO.VehiculoDAO;
import pojo.Propietario;
import pojo.Vehiculo;

public class Main {

	public static void main(String[] args) {
		
		VehiculoDAO daoVehiculo = new VehiculoDAO();
		PropietarioDAO daoPropietario = new PropietarioDAO();
		
		//Insertar un vehiculo y su propietario	
		System.out.println("Insertar un vehiculo y su propietario");
		
		
		Propietario p1 = new Propietario();
		p1.setDni("12345678N");
		p1.setNombre("Sergi");
		p1.setApellido("L");

		Vehiculo v1 = new Vehiculo();
		v1.setMarca("Honda");
		v1.setMatricula("83dfds21");
		v1.setModelo("YZF");
		v1.setPropietario(p1);
		
		//Añadimos los vehiculos en una lista
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		vehiculos.add(v1);
		
		//Asignamos los vehiculos al propietario
		p1.setVehiculo(vehiculos);
		
				
		//Se añade el propietario con sus vehiculos
		daoPropietario.addPropietario(p1);
		
		daoVehiculo.addVehiculo(v1);
		
		System.out.println();
		
		
		//Actualizar un vehiculo y su propietario
		System.out.println("Actualizar un vehiculo y su propietario");
		
		Propietario p2 = new Propietario();
		p2.setDni("12345678N");
		p2.setNombre("Edu");
		p2.setApellido("L");
		
		Vehiculo v3 = new Vehiculo();
		v3.setMarca("Hondaaaa");
		v3.setMatricula("83dfds21");
		v3.setModelo("YZFf");
		v3.setPropietario(p2);
		
		daoVehiculo.updateVehiculo(v3);
		
		System.out.println();
		
		//Eliminar un vehiculo y su propietario
		System.out.println("Eliminar un vehiculo y su propietario");
		daoVehiculo.deleteVehiculo("83dfds21");
		
		System.out.println();
		
		//Obtener lista de todos los vehiculos
		System.out.println("Obtener lista de vehiculos");
		daoVehiculo.getAllVehiculos();
	}
}