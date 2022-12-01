package tests;

import static org.junit.Assert.*;
import DAO.VehiculoDAO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DAO.VehiculoDAO;
import pojo.Propietario;
import pojo.Vehiculo;

public class TestVehiculoDao {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Vehiculo v = new Vehiculo();
		VehiculoDAO vdao= new VehiculoDAO();
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
		VehiculoDAO v2 = new VehiculoDAO();
		Vehiculo v1 = new Vehiculo();
		Propietario p1 = new Propietario();
		p1.setDni("2");
		p1.setNombre("Sergi");
		p1.setApellido("L");
		v1.setMarca("Honda");
		v1.setMatricula("1234566");
		v1.setModelo("YZF");
		v1.setPropietario(p1);
		v2.addVehiculo(v1);
		assertTrue(v2.deleteVehiculo("1234566"));
		

	}

	@Test
	public void testAddVehiculo() {
	}

	@Test
	public void testUpdateVehiculo() {

	}
}
