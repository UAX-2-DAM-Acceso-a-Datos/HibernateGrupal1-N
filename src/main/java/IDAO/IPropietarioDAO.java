package IDAO;

import java.util.List;

import pojo.Propietario;

public interface IPropietarioDAO {
	boolean addPropietario(Propietario p);
	boolean updatePropietario(Propietario p);
	boolean deletePropietario(String dni);
	List<Propietario> getAllPropietario();
}
