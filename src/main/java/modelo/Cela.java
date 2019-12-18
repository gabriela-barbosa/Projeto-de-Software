package modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({@NamedQuery(name = "Cela.recuperaCelas", query = "select distinct a from Cela a left join fetch a.prisao order by a.id"),
        @NamedQuery(name = "Cela.recuperaPrimeiraCela", query = "select a from Cela a where a.id = ?1 order by a.id desc"),
        @NamedQuery(name = "Cela.recuperaCela", query = "select a from Cela a LEFT JOIN FETCH a.alocacoes LEFT JOIN FETCH a.prisao where a.id = ?1")})



@Entity
@Table(name = "cela")
public class Cela {


    private Long id;
    private int capacidade;
    private List<Alocacao> alocacoes = new ArrayList<Alocacao>();
    private String tipo;
    private int versao;
    private Prisao prisao;

    public Cela(int capacidade, String tipo, Prisao prisao) {
        this.capacidade = capacidade;
        this.tipo = tipo;
        this.prisao = prisao;
    }

    public Cela() {
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

    @Column(name = "TIPO")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Version
    public int getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
        this.versao = versao;
    }

    @OneToMany(mappedBy = "cela")
    @OrderBy
    public List<Alocacao> getAlocacoes() {
        return this.alocacoes;
    }

    public void setAlocacoes(List<Alocacao> alocacoes) {
        this.alocacoes = alocacoes;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prisao")
    public Prisao getPrisao() {
        return prisao;
    }

    public void setPrisao(Prisao prisao) {
        this.prisao = prisao;
    }
}
