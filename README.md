<p align="center">
    <img src="https://raw.githubusercontent.com/GeovanaRamos/calango/master/src/main/resources/tcccalango/view/resources/calango-icone-provisorio.png" alt="Parsifal logo" height="128">
</p>

<h3 align="center">Calango</h3>

<p align="center">
  O Calango é um software educacional que interpreta pseudocódigos escritos com base na Língua Portuguesa e 
  procura que se aproxima das características de organização, 
  sintaxe e semântica existentes na maioria das atuais linguagens de programação, especialmente da Linguagem C.
  Dessa forma, com o Calango é possível elaborar um algoritmo em português estruturado, utilizando o pseudocódigo do Calango, 
  para solucionar um problema computacional. 
</p>

<p align="center">
O software Calango foi desenvolvido como Trabalho de Conclusão de Curso (TCC) de dois estudantes graduandos 
em Ciência da Computaçãoo (2012) na Universidade Católica de Brasília (UCB). Atualmente, o Calango e mantido
por alunos e docentes da Universidade de Brasília (UnB), o que culminou no desenvolvimento do
<a href="https://github.com/GeovanaRamos/calango-online-judge">Calango Online Judge</a>.
</p>

## Arquitetura e Tecnologias

Para o funcionamento completo da solução, são necessários os seguintes serviços:

**Serviço** | **Linguagem** | **Framework** | **Repositório**
---|---|---------------|---
Calango IDE | Java | Swing   | Aqui
Calango Interpretador | Java | *Puro*        | [GitHub](https://github.com/GeovanaRamos/calango-interpreter)


## Rodando Localmente

Por se tratar de uma aplicação Java que faz uso do framework Swing para a criação de interfaces, é recomendado
que se utilize uma IDE compatível que facilite o desenvolvimento e que também seja compatível com 
o gerenciador _Maven_, como o _Eclipse_ ou o _IntelliJ_.

Caso deseje apenas testar o Calango, [clique aqui](https://github.com/GeovanaRamos/calango/packages/934042) 
e escolha o último arquivo que termine com _.jar_ na lista de _Assets_
para baixar o JAR mais recente do Calango. Você também pode utilizar 
[este JAR](https://github.com/GeovanaRamos/calango/blob/master/out/artifacts/calango_jar/calango.jar) se desejar. Então, clique com o botão
direito no arquivo baixado, e escolha executar como uma aplicação Java.

Em alguns SOs, também é possível executar com:
```
java -jar calango.jar
```
Lembre-se de modificar o nome do JAR colocando o nome exato que aparece no download que você fez.

