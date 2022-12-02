package tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DAO.VehiculoDAO;
import pojo.Propietario;
import pojo.Vehiculo;
import utils.HibernateUtils;

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
		Vehiculo vehiculo3 = new Vehiculo();

		propietario.setDni("12345678H");
		propietario.setNombre("fsafsa");
		propietario.setApellido("dfdsfs");

		propietario2.setDni("12345678H");
		propietario2.setNombre("fsafsa");
		propietario2.setApellido("dfdsfs");

		vehiculo.setMatricula("0000AAA");
		vehiculo.setMarca("Toyota");
		vehiculo.setModelo("3080ti");
		vehiculo.setPropietario(propietario);

		vehiculo2.setMatricula("0000BBB");
		vehiculo2.setMarca("Audi");
		vehiculo2.setModelo("Q3");
		vehiculo2.setPropietario(propietario);

		vehiculo3.setMatricula("1111AAA");
		vehiculo3.setMarca("Kia");
		vehiculo3.setModelo("Ceed");
		vehiculo3.setPropietario(propietario2);

		vehiculos.add(vehiculo);
		vehiculos.add(vehiculo2);
		vehiculos.add(vehiculo3);

		vdao = new VehiculoDAO();
		for (Vehiculo v : vehiculos) {
			vdao.addVehiculo(v);
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		HibernateUtils.shutdown();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 *  comparo lo que hay en la base de datos con el array vehiculos
	 * 
	 */
	@Test
	public void testGetAllVehiculos_success() {
		List<Vehiculo> obtenidos = vdao.getAllVehiculos();
		
		Vehiculo[] esperado = new Vehiculo[1];
		Vehiculo[] obtenido = new Vehiculo[1];
		
		esperado = vehiculos.toArray(esperado);
		obtenido = obtenidos.toArray(obtenido);
		
		assertArrayEquals(esperado, obtenido);
	}

	@Test
	public void testDeleteVehiculo_success() {
	}

	@Test
	public void testAddVehiculo_success() {
		assertTrue(vdao.addVehiculo(vehiculos.get(0)));
	}

	@Test
	public void testUpdateVehiculo_success() {
		
	}
}
