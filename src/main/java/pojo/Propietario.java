package pojo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import jakarta.validation.constraints.*;

	@Entity
	@Table(name = "Propietario")
	public class Propietario {

		@Id
		@Column(name = "dni")
		@Max(9)
		@Min(9)
		private String dni;
		
		@Column(name = "nombre")
		@Max(30)
		private String nombre;

		@Column(name = "apellido")
		@Max(50)
		private String apellido;

		@ManyToOne
		@JoinColumn(name = "vehiculo", nullable = false)
		private Vehiculo vehiuclo;
		
		public Propietario() {
			
		} 
		
		public Propietario(String dni) {
			this.dni=dni;
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

		public Vehiculo getVehiuclo() {
			return vehiuclo;
		}

		public void setVehiuclo(Vehiculo vehiuclo) {
			this.vehiuclo = vehiuclo;
		}
	
}
