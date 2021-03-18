package br.edu.ifrn.comics.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifrn.comics.dominio.Usuario;
import br.edu.ifrn.comics.repository.UsuarioRepository;

/** A classe UsuarioService providencia métodos para um service.*/
@Service
public class UsuarioService implements UserDetailsService {

  @Autowired
  private UsuarioRepository repository;

  /** O método loadUserByUsername faz uma busca de usuário no banco de dados 
   * para verificar se o usuário informado fornecido existe.*/
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = repository.findByEmail(username)
      .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));

    return new User(
      usuario.getEmail(),
      usuario.getSenha(),
      AuthorityUtils.createAuthorityList(usuario.getPerfil())
    );

  }

}