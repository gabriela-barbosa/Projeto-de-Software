package modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "Usuario.recuperaUsuario", query = "select p from Usuario p LEFT JOIN FETCH p.perfis WHERE p.conta = ?1"),
        @NamedQuery(name = "Usuario.recuperaListaDeUsuarios", query = "select distinct p from Usuario p LEFT JOIN FETCH p.perfis"),
})
@Entity
@Table(name = "usuario")

public class Usuario {

    private String conta;
    private String senha;

    private List<Perfil> perfis = new ArrayList<Perfil>();

    // Um usuário possui vários perfis

    // private List<> equipes = new ArrayList<Equipe>();

    public Usuario() {
    }

    public Usuario(String conta, String senha) {
        this.conta = conta;
        this.senha = senha;
    }

    // ********* Métodos do Tipo Get *********

    @Id
    @Column(name = "CONTA")
    public String getConta() {
        return conta;
    }

    @Column(name = "SENHA")
    public String getSenha() {
        return senha;
    }

    // ********* Métodos do Tipo Set *********

    public void setConta(String conta) {
        this.conta = conta;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // ********* Métodos para Associações *********

    @OneToMany(mappedBy = "usuario")
    @OrderBy
    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

}