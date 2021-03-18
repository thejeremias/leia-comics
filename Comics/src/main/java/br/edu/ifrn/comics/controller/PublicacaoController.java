package br.edu.ifrn.comics.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.comics.dominio.Arquivo;
import br.edu.ifrn.comics.dominio.Editora;
import br.edu.ifrn.comics.dominio.Hq;

import br.edu.ifrn.comics.dto.AutoCompleteDTO;
import br.edu.ifrn.comics.repository.ArquivoRepository;
import br.edu.ifrn.comics.repository.EditoraRepository;
import br.edu.ifrn.comics.repository.HqRepository;

/** A classe PublicacaoController providencia métodos para salvar HQ e validar dados.*/
@Controller
@RequestMapping("/usuario")
public class PublicacaoController {

  @Autowired
  private HqRepository hqRepository;

  @Autowired
  private EditoraRepository editoraRepository;

  @Autowired
  private ArquivoRepository arquivoRepository;

  /** O método entrarPublicacao retorna a página publicacao.*/
  @GetMapping("/publicacao")
  public String entrarPublicacao(ModelMap model) {

    model.addAttribute("hq", new Hq());
    return "usuario/publicacao";
  }

  /** O método salvarHq() salva a HQ no banco de dados.*/
  @PostMapping("/salvarhq")
  @Transactional(readOnly = false)
  public String salvar(Hq hq, RedirectAttributes attr,
    @RequestParam("file") MultipartFile arquivo, ModelMap model) {
    try {
      if (arquivo != null && !arquivo.isEmpty()) {

        String nomeArquivo = StringUtils.cleanPath(arquivo.getOriginalFilename());

        Arquivo arquivoBD = new Arquivo(null, nomeArquivo, arquivo.getContentType(), arquivo.getBytes());

        arquivoRepository.save(arquivoBD);

        if (hq.getCapa() != null && hq.getCapa().getId() != null && hq.getCapa().getId() > 0) {
          arquivoRepository.delete(hq.getCapa());
        }

        hq.setCapa(arquivoBD);

      } else {
        hq.setCapa(null);
      }

      List < String > msgValidacao = validarDados(hq);

      if (!msgValidacao.isEmpty()) {
        model.addAttribute("msgErro", msgValidacao);
        return "usuario/publicar";
      }

      hqRepository.save(hq);
      attr.addFlashAttribute("msgSucesso", "Operação realizada com sucesso");

    } catch (IOException e) {
      e.printStackTrace();
    }

    return "redirect:/usuario/publicar";
  }

  /** O método validarDados valida os dados fornecidos no formulário.*/
  private List < String > validarDados(Hq hq) {

    List < String > msgs2 = new ArrayList < > ();

    if (hq.getTitulo() == null || hq.getTitulo().isEmpty()) {
      msgs2.add("O campo Titulo é obrigatorio");
    }
    if (hq.getDescricao() == null || hq.getDescricao().isEmpty()) {
      msgs2.add("O campo descrição é obrigatorio");
    }
    if (hq.getNome() == null || hq.getNome().isEmpty()) {
      msgs2.add("O campo Nome é obrigatorio");
    }

    return msgs2;
  }

  
  /** O método autoCompleEditora completa automaticamente o campo editora do formulário.*/
  @GetMapping("/autocompleteEditora")
  @Transactional(readOnly = true)
  @ResponseBody
  public List < AutoCompleteDTO > autoCompleteEditora(
    @RequestParam("term") String termo) {

    List < Editora > editora = editoraRepository.findByNome(termo);

    List < AutoCompleteDTO > resultados = new ArrayList < > ();

    editora.forEach(e -> resultados.add(
      new AutoCompleteDTO(e.getNome(), e.getId())
    ));

    return resultados;
  }

}