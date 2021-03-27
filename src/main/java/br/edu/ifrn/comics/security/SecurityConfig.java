package br.edu.ifrn.comics.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.edu.ifrn.comics.dominio.Usuario;
import br.edu.ifrn.comics.service.UsuarioService;

/** A classe SecurityConfig providencia métodos para a configuração da autenticação do login.*/
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UsuarioService service;

  /** O método configure salva faz a configuração do login e dá permissões.*/
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .antMatchers("/css/**", "/imagens/**", "/js/**", "/usuario/cadastro").permitAll()
      .antMatchers("/publico/**", "/usuario/cadastro",
        "/usuario/salvar", "/usuario/autocompleteGeneros",
        "/usuario/addGeneros", "/usuario/removerGenero/**", "/", "/usuario/sobreNos").permitAll()
      .antMatchers("/usuario/adm**", "/usuario/buscar**",
        "/usuario/editar**", "/usuario/remover**", "/usuario/edicao**").hasAuthority(Usuario.ADMIN)
      .anyRequest().authenticated()
      .and()

      .formLogin()
      .loginPage("/login")
      .defaultSuccessUrl("/", true)
      .failureUrl("/login-error")
      .permitAll()
      .and()
      .logout()
      .logoutSuccessUrl("/")
      .and()
      .rememberMe();
  }

  /** O método confiure faz a criptografia da senha do usuário.*/
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
  }

}