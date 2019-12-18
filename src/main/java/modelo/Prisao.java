package modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NamedQueries({@NamedQuery(name = "Prisao.recuperaPrisoes", query = "select a from Prisao a order by a.id"),
        @NamedQuery(name = "Prisao.recuperaPrimeiraPrisao", query = "select a from Prisao a where a.id = ?1 order by a.id desc"),
        @NamedQuery(name = "Prisao.recuperaPrisao", query = "select a from Prisao a LEFT JOIN FETCH a.celas where a.id = ?1")})


@Entity
@Table(name = "prisao")
public class Prisao {


    private Long id;
    private int capacidade;
    private String endereco;
    private String nivelSeguranca;
    private int versao;
    private List<Cela> celas = new ArrayList<Cela>();

    public Prisao(int capacidade, String endereco, String nivelSeguranca) {
        this.capacidade = capacidade;
        this.endereco = endereco;
        this.nivelSeguranca = nivelSeguranca;
    }

    public Prisao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "CAPACIDADE")
    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    @Column(name = "ENDERECO")
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Column(name = "NIVEL_SEGURANCA")
    public String getNivelSeguranca() {
        return nivelSeguranca;
    }

    public void setNivelSeguranca(String nivelSeguranca) {
        this.nivelSeguranca = nivelSeguranca;
    }

    @Version
    public int getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
        this.versao = versao;
    }

    @OneToMany(mappedBy = "prisao")
    public List<Cela> getCelas() {
        return celas;
    }

    public void setCelas(List<Cela> celas) {
        this.celas = celas;
    }
}
