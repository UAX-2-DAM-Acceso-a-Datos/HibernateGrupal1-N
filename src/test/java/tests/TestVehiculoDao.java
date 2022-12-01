package tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DAO.VehiculoDAO;
import pojo.Propietario;
import pojo.Vehiculo;

public class TestVehiculoDao {
	
	static Vehiculo v;
	static VehiculoDAO vdao;
	static Propietario p;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		p = new Propietario();
		p.setDni("12345678H");
		p.setNombre("fsafsa");
		p.setApellido("dfdsfs");
	
		v = new Vehiculo();
		v.setMatricula("0000AAA");
		v.setMarca("Toyota");
		v.setModelo("3080ti");
		v.setPropietario(p);
		vdao= new VehiculoDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllVehiculos() {
	}

	@Test
	public void testDeleteVehiculo() {
	}

	@Test 
	public void testAddVehiculo() {			
		assertTrue(vdao.addVehiculo(v));
	}

	@Test
	public void testUpdateVehiculo() {

	}
}
