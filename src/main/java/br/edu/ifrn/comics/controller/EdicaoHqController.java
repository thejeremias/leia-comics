package br.edu.ifrn.comics.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.comics.dominio.Hq;
import br.edu.ifrn.comics.repository.HqRepository;

/** A classe EdicaoHqController providencia métodos para edição de uma HQ.*/
@Controller
@RequestMapping("/usuario")
public class EdicaoHqController {

  @Autowired
  private HqRepository hqRepository;

  /** O método entrarEdicaoHq retorna a página edicaoHq.*/
  @GetMapping("/edicaoHq")
  public String entrarEdicaoHq() {
    return "usuario/edicaoHq";
  }

  /** O método buscarHq() faz a busca de uma HQ no banco de dados.*/
  @GetMapping("/buscarHq")
  public String buscarhq(
    @RequestParam(name = "titulo", required = false) String titulo,
    @RequestParam(name = "editora", required = false) String editora,
    @RequestParam(name = "mostarTodosDados2", required = false) Boolean mostrarDados2, HttpSession sessao2,
    ModelMap model2) {

    List < Hq > hqsEncontrados = hqRepository.findByTituloAndEditora(titulo, editora);

    model2.addAttribute("hqsEncontrados", hqsEncontrados);

    if (mostrarDados2 != null) {
      model2.addAttribute("mostarTodosDados2", true);
    }

    return "usuario/edicaoHq";
  }

  /** O método iniciarEdicaoHq() retorna a HQ que será editada.*/
  @GetMapping("/editarhq/{id2}")
  public String iniciarEdicaohq(
    @PathVariable("id2") Integer id2,
    ModelMap model2,
    HttpSession sessao2
  ) {

    Hq h = hqRepository.findById(id2).get();

    model2.addAttribute("hq", h);

    return "usuario/publicacao";
  }

  /** O método removerHq() faz a remoção de uma HQ do banco de dados.*/
  @GetMapping("/removerhq/{id}")
  public String removerhq(
    @PathVariable("id") Integer idHq,
    HttpSession sessao2,
    RedirectAttributes attr2
  ) {

    hqRepository.deleteById(idHq);
    attr2.addFlashAttribute("msgSucesso", "HQ removida com sucesso!");

    return "redirect:/usuario/buscarhq";
  }
}