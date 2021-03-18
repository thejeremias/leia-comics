# Leia Comics!
"Leia Comics!" é uma plataforma on-line em que você ler, publicar e baixar histórias em quadrinhos da Marvel, DC Comics, dentre outras editoras.

![image](https://user-images.githubusercontent.com/53026536/111553394-0d533100-8763-11eb-977a-311c765ac579.png)

A aplicação foi criada utilizando a linguagem de programação java junto ao framework Spring com integração ao thymeleaf. No front-end foi utilizado Html, Css, Javacript, Jquery e o framework de estilo Bootstrap.
Para a implementação do projeto foi utilizado técnicas de crud com acesso a banco de dados, hibernate, modularização, template, download e upload de arquivos, autenticação (login e logout) e autorização.

## O projeto tem as seguintes funcionalidades
* Cadastro de usuários e HQs
* Busca de usuários e HQs (acesso de administrador)
* Edição de usuários e HQs (acesso de administrador)
* Remoção de usuários e HQs (acesso de administrador)
* Login
* Logout
* Download de arquivos
* Upload de arquivos
* Exibição de HQs

## Para rodar a aplicação
1. Baixe o projeto via git clone com a seguinte URL: https://github.com/thejeremias/Comics
2. Baixe o JRE e JDK do java em: https://www.oracle.com/br/downloads/#category-java
3. Baixe a ide do spring em: https://spring.io/tools
4. Baixe um servidor Mysql de sua preferência e o inicie, que o banco de dados utilizado no projeto será criado automaticamente ao iniciar o projeto
5. Importe o projeto para dentro do Spring 
6. Configure o projeto como MAVEN
7. Atualize as dependências via Maven Update Project
8. Inicie um servidor local do projeto dentro do Spring 
9. Abra o seguinte link em seu navegador de preferência: http://localhost:8080/
