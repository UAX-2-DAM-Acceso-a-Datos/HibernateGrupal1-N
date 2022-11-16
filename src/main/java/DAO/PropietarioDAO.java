package DAO;
import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.ManyToOne;
	import javax.persistence.Table;

import IDAO.IPropietarioDAO;
import pojo.Propietario;

	@Entity
	@Table(name = "Propietario")
	public class PropietarioDAO implements IPropietarioDAO{

		@Id
		@Column(name = "dni")
		private String dni;

		@Column(name = "nombre")
		private String nombre;

		@Column(name = "apellido")
		private String apellido;

		@ManyToOne
		@JoinColumn(name = "vehiculo", nullable = false)
		private VehiculoDAO vehiuclo;
		
		public PropietarioDAO() {
			
		}

		public String getDni() {
			return dni;
		}

		public void setDni(String dni) {
			this.dni = dni;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellido() {
			return apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
		}

		public VehiculoDAO getVehiuclo() {
			return vehiuclo;
		}

		public void setVehiuclo(VehiculoDAO vehiuclo) {
			this.vehiuclo = vehiuclo;
		}

		public void addPropietario(Propietario p) {
			// TODO Auto-generated method stub
			
		}

		public void updatePropietario(Propietario p) {
			// TODO Auto-generated method stub
			
		}

		public void deletePropietario(Propietario p) {
			// TODO Auto-generated method stub
			
		}

		public void getAllPropietario() {
			// TODO Auto-generated method stub
			
		}
	
}
