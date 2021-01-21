package br.ucb.calango.simbolos;

import br.ucb.calango.arvore.CalangoTree;
import java.util.ArrayList;
import java.util.List;

public class SimboloMetodo extends Simbolo {
   private int lineStart;
   private int lineEnd;
   private CalangoTree statements;
   private List<SimboloVariavel> parametros;

   public SimboloMetodo(String identificador, Class<?> tipoRetorno, List<SimboloVariavel> parametros, CalangoTree statements, int lineStart, int lineEnd) {
      super(identificador, tipoRetorno, (Object)null);
      this.statements = statements;
      this.parametros = parametros;
      this.lineStart = lineStart;
      this.lineEnd = lineEnd;
   }

   public SimboloMetodo(String identificador, List<SimboloVariavel> parametros, CalangoTree statements, int lineStart, int lineEnd) {
      super(identificador, (Class)null, (Object)null);
      this.statements = statements;
      this.parametros = parametros;
      this.lineStart = lineStart;
      this.lineEnd = lineEnd;
   }

   public SimboloMetodo(String identificador, CalangoTree statements, int lineStart, int lineEnd) {
      this(identificador, (List)(new ArrayList()), statements, lineStart, lineEnd);
   }

   public SimboloMetodo(String identificador, Class<?> tipoRetorno, CalangoTree statements, int lineStart, int lineEnd) {
      this(identificador, tipoRetorno, new ArrayList(), statements, lineStart, lineEnd);
   }

   public boolean isProcedimento() {
      return this.getTipo() == null;
   }

   public boolean isFuncao() {
      return !this.isProcedimento();
   }

   public List<SimboloVariavel> getParametros() {
      return this.parametros;
   }

   public CalangoTree getStatements() {
      return this.statements;
   }

   public int getLineStart() {
      return this.lineStart;
   }

   public int getLineEnd() {
      return this.lineEnd;
   }
}
