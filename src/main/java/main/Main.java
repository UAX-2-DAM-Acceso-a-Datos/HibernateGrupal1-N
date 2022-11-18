package main;

import pojo.Propietario;
import pojo.Vehiculo;
import DAO.VehiculoDAO;

public class Main {

	public static void main(String[] args) {
		
		//Insertar un vehiculo y su propietario	
		Vehiculo v1 = new Vehiculo();
		Propietario p1 = new Propietario();
		p1.setDni("09143628J");
		p1.setNombre("Edu");
		p1.setApellido("L");
		v1.setMarca("Yamaha");
		v1.setMatricula("AEddI 83df97");
		v1.setModelo("YZF");
		v1.setPropietario(p1);
		
		VehiculoDAO v2 = new VehiculoDAO();
		v2.getAllVehiculos();

	}
}