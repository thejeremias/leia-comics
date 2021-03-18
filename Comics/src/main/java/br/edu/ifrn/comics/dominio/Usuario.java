package br.edu.ifrn.comics.dominio;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/** A classe Usuario providencia métodos necessários para um usuário.*/
@Entity
public class Usuario {

  public static final String ADMIN = "ADMIN";
  public static final String USUARIO_COMUM = "COMUM";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String senha;

  @Column(nullable = false)
  private String dataNascimento;

  @ManyToMany
  private List < Genero > generos;

  @Transient
  private Genero gen;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private Arquivo foto;

  @Column(nullable = false)
  private String perfil = USUARIO_COMUM;

  public List < Genero > getGeneros() {
    return generos;
  }

  public void setGeneros(List < Genero > generos) {
    this.generos = generos;
  }

  public Genero getGen() {
    return gen;
  }

  public void setGen(Genero gen) {
    this.gen = gen;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(String dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public Arquivo getFoto() {
    return foto;
  }

  public void setFoto(Arquivo foto) {
    this.foto = foto;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getPerfil() {
    return perfil;
  }

  public void setPerfil(String perfil) {
    this.perfil = perfil;
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
    Usuario other = (Usuario) obj;
    if (id != other.id)
      return false;
    return true;
  }

}