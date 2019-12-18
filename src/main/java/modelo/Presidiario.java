package modelo;

import util.Util;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NamedQueries({@NamedQuery(name = "Presidiario.recuperaPresidiarios", query = "select distinct a from Presidiario a LEFT JOIN FETCH a.alocacoes order by a.id"),
        @NamedQuery(name = "Presidiario.recuperaPrimeiroPresidiario", query = "select a from Presidiario a where a.id = ?1 order by a.id desc"),
        @NamedQuery(name = "Presidiario.recuperaPresidiario", query = "select distinct a from Presidiario a LEFT JOIN FETCH a.alocacoes where a.id = ?1")})


@Entity
@Table(name = "presidiario")

public class Presidiario {

    private Long id;
    private String nome;
    private List<Alocacao> alocacoes = new ArrayList<Alocacao>();
    private Date dataPrisao;
    private Date dataSoltura;
    private int versao;

    // ********* Construtores *********

    public Presidiario(String nome,
                       Date dataPrisao,
                       Date dataSoltura) {
        this.nome = nome;
        this.dataPrisao = dataPrisao;
        this.dataSoltura = dataSoltura;
    }

    public Presidiario() {

    }

    // ********* Métodos do Tipo Get *********
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @SuppressWarnings("unused")
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NOME")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "DATA_PRISAO")
    public Date getDataPrisao() {
        return dataPrisao;
    }

    @Transient
    public String getDataPrisaoMasc() {
        return Util.dateToStr(this.dataPrisao);
    }

    public void setDataPrisao(Date dataPrisao) {
        this.dataPrisao = dataPrisao;
    }

    @Column(name = "DATA_SOLTURA")
    public Date getDataSoltura() {
        return dataSoltura;
    }

    @Transient
    public String getDataSolturaMasc() {
        return Util.dateToStr(this.dataSoltura);

    }

    public void setDataSoltura(Date dataSoltura) {
        this.dataSoltura = dataSoltura;
    }

    @Version
    @Column(name = "VERSAO")
    public int getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
        this.versao = versao;
    }


    @OneToMany(mappedBy = "presidiario")
    @OrderBy
    public List<Alocacao> getAlocacoes() {
        return alocacoes;
    }

    public void setAlocacoes(List<Alocacao> alocacoes) {
        this.alocacoes = alocacoes;
    }

}

