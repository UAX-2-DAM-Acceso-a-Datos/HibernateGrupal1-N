package IDAO;

import java.util.List;

import pojo.Propietario;
import pojo.Vehiculo;

public interface IPropietarioDAO {
	boolean addPropietario(Propietario p);
	boolean updatePropietario(Propietario p);
	boolean deletePropietario(String dni);
	List<Propietario> getAllPropietarios();
	List<Vehiculo> getVehiculosPropietario(Propietario p);
}
