package IDAO;

import java.util.List;

import pojo.Vehiculo;

public interface IVehiculoDAO {
	void addVehiculo(Vehiculo v);
	void updateVehiculo(Vehiculo v);
	void deleteVehiculo(String Matricula);
	List<Vehiculo> getAllVehiculos();
}
