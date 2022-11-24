package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DAO.VehiculoDAO;
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
	}

	@Test
	public void testAddVehiculo() {
	}

	@Test
	public void testUpdateVehiculo() {

	}
}
