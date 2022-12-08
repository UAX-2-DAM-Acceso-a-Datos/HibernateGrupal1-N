package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "vehiculos")
public class Vehiculo implements Comparable<Vehiculo>{

	@Id
	@Column(name = "matricula")
	@NotBlank
	@Size(min = 6, max = 8)
	private String matricula;

	@Column(name = "marca")
	@NotNull
	@Size(min = 1, max = 50)
	private String marca;

	@Column(name = "modelo")
	@NotNull
	@Size(min = 1, max = 50)
	private String modelo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dni", referencedColumnName = "dni", nullable = false)
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

	/**
	 * Compara solo por matricula
	 * 
	 * @return -1 si el objeto tiene menor matricula que el parametro, 0 si matriculas iguales, 1 si el objeto tiene mayor matricula que el parametro		
	 * @param o el vehiculo con el que compararse
	 */
	public int compareTo(Vehiculo o) {
		return matricula.compareToIgnoreCase(o.matricula);
	}
	
}