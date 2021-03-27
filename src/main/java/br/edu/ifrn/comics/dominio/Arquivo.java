package br.edu.ifrn.comics.dominio;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/** A classe Arquivo providencia métodos necessários para um arquivo.*/
@Entity
public class Arquivo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nomeArquivo;

  private String tipoArquivo;

  @Lob
  @Basic(fetch = FetchType.LAZY)
  private byte[] dados;

  public Arquivo(Long id, String nomeArquivo, String tipoArquivo, byte[] dados) {
    super();
    this.id = id;
    this.nomeArquivo = nomeArquivo;
    this.tipoArquivo = tipoArquivo;
    this.dados = dados;
  }

  public Arquivo() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNomeArquivo() {
    return nomeArquivo;
  }

  public void setNomeArquivo(String nomeArquivo) {
    this.nomeArquivo = nomeArquivo;
  }

  public String getTipoArquivo() {
    return tipoArquivo;
  }

  public void setTipoArquivo(String tipoArquivo) {
    this.tipoArquivo = tipoArquivo;
  }

  public byte[] getDados() {
    return dados;
  }

  public void setDados(byte[] dados) {
    this.dados = dados;
  }
}