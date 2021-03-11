package tcccalango.util.componentes.passoapasso;

import br.ucb.calango.api.publica.TipoDado;

public class VariavelFactory {
   private static Variavel create(TipoDado tipo) {
      if (tipo == null) {
         tipo = TipoDado.MATRIZ_INTEIRO;
      }

      Object t;
      switch(tipo) {
      case LOGICO:
         t = new BooleanVariavel();
         break;
      case REAL:
         t = new DoubleVariavel();
         break;
      case VETOR_CARACTER:
      case VETOR_INTEIRO:
      case VETOR_LOGICO:
      case VETOR_REAL:
      case VETOR_TEXTO:
         t = new ArrayVariavel();
         break;
      case MATRIZ_CARACTER:
      case MATRIZ_INTEIRO:
      case MATRIZ_LOGICO:
      case MATRIZ_REAL:
      case MATRIZ_TEXTO:
         t = new MatrizVariavel();
         break;
      default:
         t = new DefaultVariavel();
      }

      return (Variavel)t;
   }

   public static Variavel create(Parent parent, String nome, TipoDado tipo, Object valor, Variavel referencia) {
      Variavel t = create(tipo);
      t.prepare(parent, nome, tipo, valor, referencia);
      return t;
   }
}
