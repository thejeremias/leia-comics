package br.edu.ifrn.comics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.ifrn.comics.dominio.Usuario;
import br.edu.ifrn.comics.repository.UsuarioRepository;

/** A classe IndexController providencia métodos para entrar em páginas e buscar o usuário logado.*/
@Controller
public class IndexController {

  private Usuario usuario;
  @Autowired
  private UsuarioRepository repository;

  /** O método entrarIndex() retorna a página index.*/
  @GetMapping("/")
  public String entrarIndex(ModelMap model) {
    buscarUsuarioLogado();

    if (usuario != null) {
      model.addAttribute("usuario", "Bem-Vindo, " + usuario.getNome());
    }
    return "index";
  }
  
  /** O método buscarUsuario() busca o usuário logado.*/
  private void buscarUsuarioLogado() {
    Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();

    if (!(autenticado instanceof AnonymousAuthenticationToken)) {
      String email = autenticado.getName();
      usuario = repository.findByEmailLogin(email).get(0);

    }
  }

  /** O método login() retorna a página login.*/
  @GetMapping("/login")
  public String login() {
    return "usuario/login";
  }

  /** O método loginError retorna a página login com mensagens de erro.*/
  @GetMapping("/login-error")
  public String loginError(ModelMap model) {

    model.addAttribute("msgErro", "Login ou senha incorretos. Tente novamente!");
    return "usuario/login";
  }

}