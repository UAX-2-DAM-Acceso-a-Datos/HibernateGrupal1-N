package IDAO;

import java.util.List;

import pojo.Vehiculo;

public interface IVehiculoDAO {
	boolean addVehiculo(Vehiculo v);
	boolean updateVehiculo(Vehiculo v);
	boolean deleteVehiculo(String Matricula);
	List<Vehiculo> getAllVehiculos();
}
