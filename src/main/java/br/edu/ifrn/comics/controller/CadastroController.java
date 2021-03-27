package br.edu.ifrn.comics.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.comics.dominio.Arquivo;
import br.edu.ifrn.comics.dominio.Genero;
import br.edu.ifrn.comics.dominio.Usuario;
import br.edu.ifrn.comics.dto.AutoCompleteDTO;
import br.edu.ifrn.comics.repository.ArquivoRepository;
import br.edu.ifrn.comics.repository.GeneroRepository;
import br.edu.ifrn.comics.repository.UsuarioRepository;

/** A classe CadastroController 
 * providencia métodos para salvar usuários, validar dados e completar automaticamente campos.*/
@Controller
@RequestMapping("/usuario")
public class CadastroController {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private GeneroRepository generoRepository;

  @Autowired
  private ArquivoRepository arquivoRepository;

  @GetMapping("/cadastro")

  public String entrarCadastro(ModelMap model) {
    model.addAttribute("usuario", new Usuario());

    return "usuario/cadastro";
  }

  /** O método salvar() salva o usuário no banco de dados.*/
  @PostMapping("/salvar")
  @Transactional(readOnly = false)
  public String salvar(Usuario usuario, RedirectAttributes attr,
    @RequestParam("file") MultipartFile arquivo,
    HttpSession sessao, ModelMap model) {
    try {

      if (arquivo != null && !arquivo.isEmpty()) {

        String nomeArquivo = StringUtils.cleanPath(arquivo.getOriginalFilename());

        Arquivo arquivoBD = new Arquivo(null, nomeArquivo, arquivo.getContentType(), arquivo.getBytes());

        arquivoRepository.save(arquivoBD);

        if (usuario.getFoto() != null && usuario.getFoto().getId() != null && usuario.getFoto().getId() > 0) {
          arquivoRepository.delete(usuario.getFoto());
        }

        usuario.setFoto(arquivoBD);

      } else {
        usuario.setFoto(null);
      }

      List < String > msgValidacao = validarDados(usuario);

      if (!msgValidacao.isEmpty()) {
        model.addAttribute("msgErro", msgValidacao);
        return "usuario/cadastro";
      }

      String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
      usuario.setSenha(senhaCriptografada);

      usuarioRepository.save(usuario);
      attr.addFlashAttribute("msgSucesso", "Operação realizada com sucesso");

    } catch (IOException e) {
      e.printStackTrace();
    }

    return "redirect:/usuario/cadastro";
  }

  /** O método validarDados valida os dados fornecidos no formulário.*/
  private List < String > validarDados(Usuario usuario) {

    List < String > msgs = new ArrayList < > ();

    if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
      msgs.add("O campo nome é obrigatorio");
    }
    if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
      msgs.add("O campo Email é obrigatorio");
    }
    if (usuario.getDataNascimento() == null || usuario.getDataNascimento().isEmpty()) {
      msgs.add("O campo Data é obrigatorio");
    }
    if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
      msgs.add("O campo senha é obrigatorio");
    }

    return msgs;
  }

  /** O método autoCompleteGeneros completa automaticamente o campo gênero do formulário.*/
  @GetMapping("/autocompleteGeneros")
  @Transactional(readOnly = true)
  @ResponseBody
  public List < AutoCompleteDTO > autoCompleteGeneros(
    @RequestParam("term") String termo) {

    List < Genero > generos = generoRepository.findByNome(termo);

    List < AutoCompleteDTO > resultados = new ArrayList < > ();

    generos.forEach(e -> resultados.add(
      new AutoCompleteDTO(e.getNome(), e.getId())
    ));

    return resultados;
  }

  /** O método addGenero() faz a adição de uma gênero.*/
  @PostMapping("/addGeneros")
  public String addGenero(Usuario usuario, ModelMap model) {

    if (usuario.getGeneros() == null) {
      usuario.setGeneros(new ArrayList < > ());
    }

    usuario.getGeneros().add(usuario.getGen());

    return "usuario/cadastro";

  }

  /** O método removerGenero() faz a remoção de um gênero.*/
  @PostMapping("/removerGenero/{id}")
  public String removerGenero(Usuario usuario, ModelMap model,
    @PathVariable("id") Integer idGenero) {

    Genero gen = new Genero();
    gen.setId(idGenero);

    usuario.getGeneros().remove(gen);
    return "usuario/cadastro";

  }

}