package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "vehiculos")
public class Vehiculo {
	
	@Id
	@Column(name = "matricula")
	@Size(min=7, max=7)
	private String matricula;
	
	@Column(name = "marca")
	@NotNull
	@Max(50)
	private String marca;
	
	@Column(name = "modelo")
	@NotNull
	@Max(50)
	private String modelo;
	
	@ManyToOne
	@JoinColumn(name="dni")
	private Propietario propietario;
	
	public Vehiculo() {
		super();
	} 

	public Vehiculo(String matricula2) {
		matricula = matricula2;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	@Override
	public String toString() {
		return "Vehiculo [matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", propietario="
				+ propietario + "]";
	}
	
	
	
	
}