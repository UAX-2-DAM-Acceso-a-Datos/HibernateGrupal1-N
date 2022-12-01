package pojo;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

	@Entity
	@Table(name = "Propietarios")
	public class Propietario {

		@Id
		@NotBlank
		@Column(name = "dni")
		@Size(min=9, max=9)
		private String dni;
		
		@Column(name = "nombre")
		@Size(min=1, max=30)
		@NotNull
		private String nombre;

		@Column(name = "apellido")
		@Size(min=1, max=50)
		@NotNull
		private String apellido;

		@OneToMany(mappedBy = "propietario")
		private List<Vehiculo> vehiculo;
		
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

		public List<Vehiculo> getVehiculo() {
			return vehiculo;
		}

		public void setVehiculo(List<Vehiculo> vehiculo) {
			this.vehiculo = vehiculo;
		}

	
}