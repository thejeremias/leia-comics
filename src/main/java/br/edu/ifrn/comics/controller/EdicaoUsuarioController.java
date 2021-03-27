package br.edu.ifrn.comics.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.comics.dominio.Usuario;
import br.edu.ifrn.comics.repository.UsuarioRepository;

/** A classe EdicaoUsuarioController providencia métodos para busca, edição e remoção de usuários.*/
@Controller
@RequestMapping("/usuario")
public class EdicaoUsuarioController {

  @Autowired
  private UsuarioRepository usuarioRepository;

  /** O método entrarEdicaoUsuario retorna a página edicaoUsuario.*/
  @GetMapping("/edicaoUsuario")
  public String entrarEdicaoUsuario() {
    return "usuario/edicaoUsuario";
  }

  /** O método buscar() faz a busca de um usuário no banco de dados.*/
  @GetMapping("/buscar")
  public String buscar(
    @RequestParam(name = "nome", required = false) String nome,
    @RequestParam(name = "email", required = false) String email,
    @RequestParam(name = "mostarTodosDados", required = false) Boolean mostrarDados, HttpSession sessao,
    ModelMap model) {

    List < Usuario > usuariosEncontrados = usuarioRepository.findByEmailAndNome(email, nome);

    model.addAttribute("usuariosEncontrados", usuariosEncontrados);

    if (mostrarDados != null) {
      model.addAttribute("mostarTodosDados", true);
    }

    return "usuario/edicaoUsuario";
  }

  /** O método iniciarEdicao() retorna o usuário que será editado.*/
  @GetMapping("/editar/{id}")
  public String iniciarEdicao(
    @PathVariable("id") Integer idUsuario,
    ModelMap model,
    HttpSession sessao
  ) {

    Usuario u = usuarioRepository.findById(idUsuario).get();

    model.addAttribute("usuario", u);

    return "/usuario/cadastro";
  }

  /** O método remover() faz a remoção de um usuário do banco de dados.*/
  @GetMapping("/remover/{id}")
  public String remover(
    @PathVariable("id") Integer idUsuario,
    HttpSession sessao,
    RedirectAttributes attr
  ) {

    usuarioRepository.deleteById(idUsuario);

    attr.addFlashAttribute("msgSucesso", "Usuario removido com Sucesso!");
    return "redirect:/usuario/buscar";
  }
}