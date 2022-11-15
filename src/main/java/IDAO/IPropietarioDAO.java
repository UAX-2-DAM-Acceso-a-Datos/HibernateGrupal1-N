package IDAO;

import pojo.Propietario;

public interface IPropietarioDAO {
	void addPropietario(Propietario p);
	void updatePropietario(Propietario p);
	void deletePropietario(Propietario p);
	void getAllPropietario();
}
