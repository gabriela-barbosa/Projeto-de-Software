package modelo;

import javax.persistence.*;
import java.sql.Date;

@NamedQueries({@NamedQuery(name = "Alocacao.recuperaAlocacoes", query = "select distinct a from Alocacao a left join fetch a.presidiario left join fetch a.cela order by a.id"),
        @NamedQuery(name = "Alocacao.recuperaUltimaAlocacao", query = "select a from Alocacao a where a.id = ?1 order by a.id desc"),
        @NamedQuery(name = "Alocacao.recuperaUmaAlocacaoERelacionados", query = "select a from Alocacao a where a.id = ?1")})


@Entity
@Table(name = "alocacao")
public class Alocacao {

    private Long id;
    private Cela cela;
    private Presidiario presidiario;
    private Date dataEntrada;
    private Date dataSaida;
    private int versao;

    public Alocacao(Date dataEntrada, Date dataSaida, Cela cela, Presidiario presidiario) {
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.presidiario = presidiario;
        this.cela = cela;
    }

    public Alocacao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setCela(Cela cela) {
        this.cela = cela;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cela")
    public Cela getCela() {
        return cela;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_presidiario")

    public Presidiario getPresidiario() {
        return presidiario;
    }

    public void setPresidiario(Presidiario presidiario) {
        this.presidiario = presidiario;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
        this.id = id;
    }

    @Column(name = "DATA_ENTRADA")
    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    @Column(name = "DATA_SAIDA")
    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    @Version
    public int getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
        this.versao = versao;
    }
}
