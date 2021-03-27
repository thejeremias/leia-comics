package br.edu.ifrn.comics.controller;

import javax.persistence.Access;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifrn.comics.dominio.Arquivo;
import br.edu.ifrn.comics.repository.ArquivoRepository;

/** A classe DownloadArquivosController providencia método para download de arquivos.*/
@Controller
public class DownloadArquivosController {

  @Autowired
  private ArquivoRepository arquivoRepository;

  /** O método downloadFile retorna o arquivo requerido para download*/
  @GetMapping("/download/{idArquivo}")
  public ResponseEntity < ? > downloadFile(@PathVariable Long idArquivo, @PathParam("salvar") String salvar) {

    Arquivo arquivoBD = arquivoRepository.findById(idArquivo).get();

    String texto = (salvar == null || salvar.equals("true")) ? "attachment; filename=\"" + arquivoBD.getNomeArquivo() + "\"" :
      "inline; filename=\"" + arquivoBD.getNomeArquivo() + "\"";
    return ResponseEntity.ok()
      .contentType(MediaType.parseMediaType(arquivoBD.getTipoArquivo()))
      .header(HttpHeaders.CONTENT_DISPOSITION, texto)
      .body(new ByteArrayResource(arquivoBD.getDados()));
  }
}