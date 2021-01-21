package br.ucb.calango.util;

import br.ucb.calango.api.publica.TipoDado;
import br.ucb.calango.arvore.CalangoTree;
import br.ucb.calango.exceptions.erros.TiposIncompativeisException;
import br.ucb.calango.interpretador.Interpretador;
import br.ucb.calango.simbolos.Simbolo;
import br.ucb.calango.simbolos.SimboloVariavel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AcoesUtil {
   private static Map<String, Class<?>> existingTypes = new HashMap();

   static {
      String tipoInteiro = "inteiro";
      String tipoReal = "real";
      String tipoTexto = "texto";
      String tipoCaracter = "caracter";
      String tipoLogico = "logico";
      existingTypes.put(tipoInteiro, Integer.class);
      existingTypes.put(tipoReal, Double.class);
      existingTypes.put(tipoTexto, String.class);
      existingTypes.put(tipoCaracter, Character.class);
      existingTypes.put(tipoLogico, Boolean.class);
      existingTypes.put(tipoInteiro + "[]", Integer[].class);
      existingTypes.put(tipoReal + "[]", Double[].class);
      existingTypes.put(tipoTexto + "[]", String[].class);
      existingTypes.put(tipoCaracter + "[]", Character[].class);
      existingTypes.put(tipoLogico + "[]", Boolean[].class);
      existingTypes.put(tipoInteiro + "[][]", Integer[][].class);
      existingTypes.put(tipoReal + "[][]", Double[][].class);
      existingTypes.put(tipoTexto + "[][]", String[][].class);
      existingTypes.put(tipoCaracter + "[][]", Character[][].class);
      existingTypes.put(tipoLogico + "[][]", Boolean[][].class);
   }

   public static boolean oneSymbolIsOfType(Simbolo result1, Simbolo result2, Class<?> c) {
      return result1.getTipo().equals(c) || result2.getTipo().equals(c);
   }

   public static Object convertValue(Class<?> targetType, Object valor) {
      Object valorConvertido = null;
      if (valor != null && !targetType.equals(valor.getClass())) {
         if (targetType.isArray() && !valor.getClass().isArray() || !targetType.isArray() && valor.getClass().isArray()) {
            throw new TiposIncompativeisException(targetType, valor.getClass());
         }

         Class<?> tipoSimbolo = targetType.isArray() ? targetType.getComponentType() : targetType;
         Class<?> tipoValor = valor.getClass().isArray() ? valor.getClass().getComponentType() : valor.getClass();
         if (tipoSimbolo.equals(String.class)) {
            if (!tipoValor.equals(String.class)) {
               throw new TiposIncompativeisException(targetType, valor.getClass());
            }

            valorConvertido = valor.toString();
         } else if (tipoSimbolo.equals(Integer.class)) {
            if (!tipoValor.equals(Double.class)) {
               throw new TiposIncompativeisException(targetType, valor.getClass());
            }

            valorConvertido = ((Double)valor).intValue();
         } else {
            if (!tipoSimbolo.equals(Double.class)) {
               throw new TiposIncompativeisException(targetType, valor.getClass());
            }

            if (!tipoValor.equals(Integer.class)) {
               throw new TiposIncompativeisException(targetType, valor.getClass());
            }

            valorConvertido = ((Integer)valor).doubleValue();
         }
      } else {
         valorConvertido = valor;
      }

      return valorConvertido;
   }

   public static Object getTypedValue(String object, Class<?> c) {
      try {
         if (c.equals(String.class)) {
            return object.toString();
         } else if (c.equals(Character.class)) {
            return new Character(object.toString().charAt(0));
         } else if (c.equals(Integer.class)) {
            return new Double(object.toString());
         } else {
            return c.equals(Double.class) ? new Double(object.toString()) : null;
         }
      } catch (NumberFormatException var3) {
         throw new TiposIncompativeisException(c, object.getClass());
      }
   }

   public static Class<?> getTypeFromString(String type) {
      return (Class)existingTypes.get(type);
   }

   public static List<SimboloVariavel> getParameters(CalangoTree tree) {
      List<SimboloVariavel> simbolos = new ArrayList();
      Iterator var3 = tree.getChildren().iterator();

      while(var3.hasNext()) {
         CalangoTree parameter = (CalangoTree)var3.next();
         simbolos.add((SimboloVariavel)Interpretador.exec(parameter));
      }

      return simbolos;
   }

   public static String getTypeName(Class<?> c) {
      if (c.equals(String.class)) {
         return "texto";
      } else if (c.equals(Character.class)) {
         return "caracter";
      } else if (c.equals(Integer.class)) {
         return "inteiro";
      } else if (c.equals(Double.class)) {
         return "real";
      } else if (c.equals(Boolean.class)) {
         return "logico";
      } else if (c.equals(String[].class)) {
         return "vetor de texto";
      } else if (c.equals(Character[].class)) {
         return "vetor de caracter";
      } else if (c.equals(Integer[].class)) {
         return "vetor de inteiro";
      } else if (c.equals(Double[].class)) {
         return "vetor de real";
      } else if (c.equals(Boolean[].class)) {
         return "vetor de logico";
      } else if (c.equals(String[][].class)) {
         return "matriz de texto";
      } else if (c.equals(Character[][].class)) {
         return "matriz de caracter";
      } else if (c.equals(Integer[][].class)) {
         return "matriz de inteiro";
      } else if (c.equals(Double[][].class)) {
         return "matriz de real";
      } else {
         return c.equals(Boolean[][].class) ? "matriz de logico" : "";
      }
   }

   public static TipoDado getTypeEnum(Class<?> c) {
      if (c.equals(String.class)) {
         return TipoDado.TEXTO;
      } else if (c.equals(Character.class)) {
         return TipoDado.CARACTER;
      } else if (c.equals(Integer.class)) {
         return TipoDado.INTEIRO;
      } else if (c.equals(Double.class)) {
         return TipoDado.REAL;
      } else if (c.equals(Boolean.class)) {
         return TipoDado.LOGICO;
      } else if (c.equals(String[].class)) {
         return TipoDado.VETOR_TEXTO;
      } else if (c.equals(Character[].class)) {
         return TipoDado.VETOR_CARACTER;
      } else if (c.equals(Integer[].class)) {
         return TipoDado.VETOR_INTEIRO;
      } else if (c.equals(Double[].class)) {
         return TipoDado.VETOR_REAL;
      } else if (c.equals(Boolean[].class)) {
         return TipoDado.VETOR_LOGICO;
      } else if (c.equals(String[][].class)) {
         return TipoDado.MATRIZ_TEXTO;
      } else if (c.equals(Character[][].class)) {
         return TipoDado.MATRIZ_CARACTER;
      } else if (c.equals(Integer[][].class)) {
         return TipoDado.MATRIZ_INTEIRO;
      } else if (c.equals(Double[][].class)) {
         return TipoDado.MATRIZ_REAL;
      } else {
         return c.equals(Boolean[][].class) ? TipoDado.MATRIZ_LOGICO : null;
      }
   }
}
