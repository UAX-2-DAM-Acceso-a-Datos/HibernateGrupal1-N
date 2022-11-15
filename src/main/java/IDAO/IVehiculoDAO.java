package IDAO;

import pojo.Vehiculo;

public interface IVehiculoDAO {
	void addVehiculo(Vehiculo v);
	void updateVehiculo(Vehiculo v);
	void deleteVehiculo(Vehiculo v);
	void getAllVehiculo();
}
