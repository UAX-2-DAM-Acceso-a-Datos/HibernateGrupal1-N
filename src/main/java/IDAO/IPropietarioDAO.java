package IDAO;

import java.util.List;

import pojo.Propietario;

public interface IPropietarioDAO {
	void addPropietario(Propietario p);
	void updatePropietario(Propietario p);
	void deletePropietario(String dni);
	List<Propietario> getAllPropietario();
}
