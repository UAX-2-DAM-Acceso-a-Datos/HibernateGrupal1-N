package main;

import pojo.Propietario;
import pojo.Vehiculo;
import DAO.VehiculoDAO;

public class Main {

	public static void main(String[] args) {
		
		//Insertar un vehiculo y su propietario	
		Vehiculo v1 = new Vehiculo();
		Propietario p1 = new Propietario();
		p1.setDni("09143628N");
		p1.setNombre("Sergio");
		p1.setApellido("L");
		v1.setMarca("Yamaha");
		v1.setMatricula("AEdI 83df97");
		v1.setModelo("YZF");
		v1.setPropietario(p1);
		
		VehiculoDAO v2 = new VehiculoDAO();
		v2.addVehiculo(v1);

	}
}