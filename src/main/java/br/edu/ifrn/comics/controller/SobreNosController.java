package br.edu.ifrn.comics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** A classe SobreNosController providencia o método para entrar na página sobreNos*/
@Controller
@RequestMapping("/usuario")
public class SobreNosController {

  /** O método entrarSobreNos() retorna a página sobreNos.*/
  @GetMapping("/sobreNos")
  public String entrarSobre() {
    return "sobreNos";
  }
}