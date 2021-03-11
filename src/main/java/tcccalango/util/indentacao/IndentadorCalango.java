package tcccalango.util.indentacao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IndentadorCalango {
   private static final String TOKEN_REGEXP = "([ \\t]*(principal|para.+faca|se.+entao|caso.+|senao|senaoSe.+entao|outroCaso|escolha.+\\)|(funcao|procedimento).+\\)|faca|enquanto.+\\)[ \\t.]*(;|faca)|fimSe|fimPara|fimFuncao|fimProcedimento|fimEnquanto|fimEscolha|fimPrincipal|//.*|(logico|texto|caracter|inteiro|real)[ \\t.]+.+(=.+)?[ \\t.]*;|interrompa[ \\t.]*;|.+=.+;|retorna.*;|(.+=)?.+\\(.*\\)[ \\t]*;|algoritmo.+;)|\\*/)[ \\t]*(//.*[ \\t]*)?(\\n\\s*)*";
   private static final Pattern TOKEN_PATTERN = Pattern.compile("([ \\t]*(principal|para.+faca|se.+entao|caso.+|senao|senaoSe.+entao|outroCaso|escolha.+\\)|(funcao|procedimento).+\\)|faca|enquanto.+\\)[ \\t.]*(;|faca)|fimSe|fimPara|fimFuncao|fimProcedimento|fimEnquanto|fimEscolha|fimPrincipal|//.*|(logico|texto|caracter|inteiro|real)[ \\t.]+.+(=.+)?[ \\t.]*;|interrompa[ \\t.]*;|.+=.+;|retorna.*;|(.+=)?.+\\(.*\\)[ \\t]*;|algoritmo.+;)|\\*/)[ \\t]*(//.*[ \\t]*)?(\\n\\s*)*");
   private String identacao;

   public String identa(String codigo) {
      Matcher m = TOKEN_PATTERN.matcher(codigo);
      List<String> groups = new ArrayList();
      int lastIndex = 0;
      int lastSize = 0;

      while(m.find()) {
         if (m.start() > lastIndex + lastSize) {
            groups.add(codigo.substring(lastIndex + lastSize, m.start()));
         }

         String group = m.group();
         lastIndex = m.start();
         lastSize = group.length();
         groups.add(group.replaceAll("\\n[ \\t]*", "\n").replaceAll("^[ \\t]+|[ \\t]+$", ""));
      }

      StringBuilder indent = new StringBuilder();

      while(!groups.isEmpty()) {
         this.token(indent, "", groups);
      }

      return indent.toString();
   }

   private void token(StringBuilder codigo, String indent, List<String> tokens) {
      String line = (String)tokens.remove(0);
      String token = line.trim();
      if (token.matches("principal([^A-Za-z_0-9].*)?")) {
         this.principal(line, codigo, indent, tokens);
      } else if (token.matches("para([^A-Za-z_0-9].*)?")) {
         this.para(line, codigo, indent, tokens);
      } else if (token.matches("senao([^A-Za-z_0-9].*)?")) {
         this.senao(line, codigo, indent, tokens);
      } else if (token.matches("se([^A-Za-z_0-9].*)?")) {
         this.se(line, codigo, indent, tokens);
      } else if (token.matches("escolha([^A-Za-z_0-9].*)?")) {
         this.escolha(line, codigo, indent, tokens);
      } else if (token.matches("(funcao|procedimento)([^A-Za-z_0-9].*)?")) {
         this.funcaoProcedimento(line, codigo, indent, tokens);
      } else if (token.matches("faca([^A-Za-z_0-9].*)?")) {
         this.faca(line, codigo, indent, tokens);
      } else if (token.matches("enquanto([^A-Za-z_0-9].*)?")) {
         this.enquanto(line, codigo, indent, tokens);
      } else if (token.matches("/\\*.*")) {
         this.comentarioBloco(line, codigo, indent, tokens);
      } else if (token.matches("//.*")) {
         this.comentarioLinha(line, codigo, indent, tokens);
      } else {
         this.generic(line, codigo, indent, tokens);
      }

   }

   private void escolha(String line, StringBuilder codigo, String indent, List<String> tokens) {
      this.generic(line, codigo, indent, tokens);

      while(!tokens.isEmpty()) {
         String child = ((String)tokens.get(0)).trim();
         if (child.matches("(caso([^A-Za-z_0-9].*)?|outroCaso)")) {
            this.generic((String)tokens.remove(0), codigo, indent, tokens);
         } else {
            if (child.equals("fimEscolha")) {
               this.generic((String)tokens.remove(0), codigo, indent, tokens);
               return;
            }

            this.token(codigo, indent + this.identacao, tokens);
         }
      }

   }

   private void comentarioLinha(String line, StringBuilder codigo, String indent, List<String> tokens) {
      this.generic(line, codigo, indent, tokens);
   }

   private void generic(String line, StringBuilder codigo, String indent, List<String> tokens) {
      codigo.append(indent).append(line);
      if (!line.endsWith("\n")) {
         codigo.append("\n");
      }

   }

   private void senao(String line, StringBuilder codigo, String indent, List<String> tokens) {
      this.se(line, codigo, indent, tokens);
   }

   private void se(String line, StringBuilder codigo, String indent, List<String> tokens) {
      this.generic(line, codigo, indent, tokens);

      while(!tokens.isEmpty()) {
         String child = ((String)tokens.get(0)).trim();
         if (child.matches("(senao|senaoSe)([^A-Za-z_0-9].*)?")) {
            this.token(codigo, indent, tokens);
            return;
         }

         if (child.matches("fimSe([^A-Za-z_0-9].*)?")) {
            this.generic((String)tokens.remove(0), codigo, indent, tokens);
            return;
         }

         this.token(codigo, indent + this.identacao, tokens);
      }

   }

   private void principal(String line, StringBuilder codigo, String indent, List<String> tokens) {
      this.blocoSimples(line, "fimPrincipal([^A-Za-z_0-9].*)?", codigo, indent, tokens);
   }

   private void para(String line, StringBuilder codigo, String indent, List<String> tokens) {
      this.blocoSimples(line, "fimPara([^A-Za-z_0-9].*)?", codigo, indent, tokens);
   }

   private void funcaoProcedimento(String line, StringBuilder codigo, String indent, List<String> tokens) {
      this.blocoSimples(line, "fim(Funcao|Procedimento)([^A-Za-z_0-9].*)?", codigo, indent, tokens);
   }

   private void faca(String line, StringBuilder codigo, String indent, List<String> tokens) {
      this.blocoSimples(line, "enquanto.+\\)[ \\t.]*;.*", codigo, indent, tokens);
   }

   private void enquanto(String line, StringBuilder codigo, String indent, List<String> tokens) {
      this.blocoSimples(line, "fimEnquanto([^A-Za-z_0-9].*)?", codigo, indent, tokens);
   }

   private void comentarioBloco(String line, StringBuilder codigo, String indent, List<String> tokens) {
      this.generic(line, codigo, indent, tokens);

      String child;
      do {
         if (tokens.isEmpty()) {
            return;
         }

         String childLine = (String)tokens.remove(0);
         this.generic(childLine, codigo, indent, tokens);
         child = line.trim();
      } while(!child.matches(".*\\*/"));

   }

   private void blocoSimples(String line, String endRegexp, StringBuilder codigo, String indent, List<String> tokens) {
      this.generic(line, codigo, indent, tokens);

      while(!tokens.isEmpty()) {
         String child = ((String)tokens.get(0)).trim();
         if (child.matches(endRegexp)) {
            this.generic((String)tokens.remove(0), codigo, indent, tokens);
            return;
         }

         this.token(codigo, indent + this.identacao, tokens);
      }

   }

   public IndentadorCalango(String identacao) {
      this.identacao = identacao;
   }

   public IndentadorCalango() {
      this("\t");
   }

   public String getIdentacao() {
      return this.identacao;
   }

   public void setIdentacao(String identacao) {
      this.identacao = identacao;
   }
}
