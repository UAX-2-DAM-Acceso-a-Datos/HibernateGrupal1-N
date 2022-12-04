package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DAO.PropietarioDAO;
import DAO.VehiculoDAO;
import pojo.Propietario;
import pojo.Vehiculo;

public class TestVehiculoDao {

	static ArrayList<Vehiculo> vehiculos;
	static VehiculoDAO vdao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		vehiculos = new ArrayList<Vehiculo>();
		Propietario propietario = new Propietario();
		Propietario propietario2 = new Propietario();
		Vehiculo vehiculo = new Vehiculo();
		Vehiculo vehiculo2 = new Vehiculo();

		propietario.setDni("12345678H");
		propietario.setNombre("fsafsa");
		propietario.setApellido("dfdsfs");

		propietario2.setDni("23456789H");
		propietario2.setNombre("Lauri");
		propietario2.setApellido("Lacaustra");

		vehiculo.setMatricula("0000AAA");
		vehiculo.setMarca("Toyota");
		vehiculo.setModelo("3080ti");
		vehiculo.setPropietario(propietario);

		vehiculo2.setMatricula("0000BBB");
		vehiculo2.setMarca("Audi");
		vehiculo2.setModelo("Q3");
		vehiculo2.setPropietario(propietario2);

		vehiculos.add(vehiculo);
		vehiculos.add(vehiculo2);

		vdao = new VehiculoDAO();
		for (Vehiculo v : vehiculos) {
			vdao.addVehiculo(v);
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		PropietarioDAO pdao = new PropietarioDAO();
		for (Vehiculo v : vehiculos) {
			pdao.deletePropietario(v.getPropietario().getDni());
			vdao.deleteVehiculo(v.getMatricula());
		}
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 *  comparo lo que hay en la base de datos con el array vehiculos.
	 *  a veces el orden de obtenidos coincide con el de vehiculos y a veces no
	 *  por lo que a veces falla y a veces esta bien. 
	 *  Para ello se ordena y por eso pojo vehiculo tiene compare to.
	 */
	@Test
	public void testGetAllVehiculos_success() {
		List<Vehiculo> obtenidos = vdao.getAllVehiculos();
		Collections.sort(obtenidos);
		assertEquals(vehiculos.size(), obtenidos.size());		
		for (int i = 0; i < obtenidos.size(); i++) {
			assertEquals(vehiculos.get(i).getMatricula(), obtenidos.get(i).getMatricula());
		}
	}

	@Test
	public void testDeleteVehiculo_success() {
	}

	@Test
	public void testAddVehiculo_success() {
	}

	@Test
	public void testUpdateVehiculo_success() {
		
	}
}
