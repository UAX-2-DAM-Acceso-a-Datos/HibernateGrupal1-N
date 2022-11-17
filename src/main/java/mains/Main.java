package mains;

import pojo.Vehiculo;
import DAO.VehiculoDAO;

public class Main {

	public static void main(String[] args) {
		Vehiculo v1 = new Vehiculo();
		v1.setMarca("Honda");
		v1.setMatricula("GEI 234");
		
		VehiculoDAO v2 = new VehiculoDAO();
		v2.addVehiculo(v1);

	}

}
