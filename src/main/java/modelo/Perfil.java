package modelo;

import javax.persistence.*;


@Entity
@Table(name = "perfil")

public class Perfil {

	private Long id;
	private String perfil;
    private Usuario usuario;
	
	public Perfil() {
	}

	public Perfil(String perfil, Usuario usuario) {
		this.perfil = perfil;
		this.usuario = usuario;
	}

	// ********* Métodos do Tipo Get *********

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	@Column(name = "PERFIL")
	public String getPerfil() {
		return perfil;
	}
	
	// ********* Métodos do Tipo Set *********

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	// ********* Métodos para Associações *********

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_usuario")
    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

}