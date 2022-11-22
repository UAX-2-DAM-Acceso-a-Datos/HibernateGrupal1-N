package pojo;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import jakarta.validation.constraints.Max;

	@Entity
	@Table(name = "Propietario")
	public class Propietario {

		@Id
		@Column(name = "dni")
		private String dni;
		
		@Column(name = "nombre")
		@Max(30)
		private String nombre;

		@Column(name = "apellido")
		@Max(50)
		private String apellido;

		@OneToMany(mappedBy = "propietario")
		private Set<Vehiculo> vehiculo;
		
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

		public Set<Vehiculo> getVehiculo() {
			return vehiculo;
		}

		public void setVehiculo(Set<Vehiculo> vehiculo) {
			this.vehiculo = vehiculo;
		}

	
}