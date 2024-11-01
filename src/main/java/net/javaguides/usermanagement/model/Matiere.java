package net.javaguides.usermanagement.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@XmlRootElement
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Matiere  implements Serializable{
    
 
	private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
	private long idMatiere;
	private String nomMatiere;
    private double coefficient;
    
    public Matiere(long idMatiere) {

		this.idMatiere = idMatiere;
	}

	public Matiere(String nomMatiere, double coefficient) {
		super();
		this.nomMatiere = nomMatiere;
		this.coefficient = coefficient;
	}

}
