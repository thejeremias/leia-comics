package br.edu.ifrn.comics.dominio;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/** A classe Hq providencia métodos necessários para uma HQ.*/
@Entity
public class Hq {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String titulo;

  @Column(nullable = false)
  private String descricao;

  @Column(nullable = false)
  private String nome;

  @ManyToOne(optional = false)
  private Editora editora;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private Arquivo capa;

  public Editora getEditora() {
    return editora;
  }

  public void setEditora(Editora editora) {
    this.editora = editora;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Arquivo getCapa() {
    return capa;
  }

  public void setCapa(Arquivo capa) {
    this.capa = capa;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Hq other = (Hq) obj;
    if (id != other.id)
      return false;
    return true;
  }

}