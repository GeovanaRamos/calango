package br.ucb.calango.gramatica;

import br.ucb.calango.api.publica.CalangoAPI;
import br.ucb.calango.escopo.Escopo;
import br.ucb.calango.simbolos.Simbolo;
import br.ucb.calango.simbolos.SimboloVariavel;
import br.ucb.calango.util.AcoesUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Algoritmo {
   private static boolean debugging = false;
   private Escopo escopoBase = new Escopo("_base");
   private static Algoritmo algoritmo = null;
   private Stack<Escopo> pilhaDeEscopo = new Stack();

   public static void define(Simbolo s) {
      getCurrentInstance().getEscopoAtual().put(s);
      if (s instanceof SimboloVariavel) {
         CalangoAPI.definedVariable(AcoesUtil.getTypeEnum(s.getTipo()), s.getIdentificador(), s.getValorUnchecked(), ((SimboloVariavel)s).getReferencia());
      }

   }

   public static void define(List<SimboloVariavel> s) {
      Iterator var2 = s.iterator();

      while(var2.hasNext()) {
         SimboloVariavel simbolo = (SimboloVariavel)var2.next();
         define((Simbolo)simbolo);
      }

   }

   public static Simbolo reference(String identificador) {
      Simbolo s = getCurrentInstance().getEscopoAtual().reference(identificador);
      if (s == null) {
         s = getCurrentInstance().getEscopoBase().reference(identificador);
      }

      return s;
   }

   public static void setValue(SimboloVariavel var, Object valor) {
      var.setValor(valor);
      CalangoAPI.settedValor(var.getIdentificador(), var.getValor());
   }

   public static void setValueNoEscopoPai(SimboloVariavel var, Object valor) {
      Escopo escopo = (Escopo)getCurrentInstance().pilhaDeEscopo.pop();
      setValue(var, valor);
      getCurrentInstance().pilhaDeEscopo.push(escopo);
   }

   public static Escopo pushEscopo(Escopo escopo) {
      CalangoAPI.pushedEscopo(escopo.getNome());
      return (Escopo)getCurrentInstance().pilhaDeEscopo.push(escopo);
   }

   public static Escopo popEscopo() {
      CalangoAPI.popedEscopo();
      return (Escopo)getCurrentInstance().pilhaDeEscopo.pop();
   }

   public static void setDebugging(boolean isDebugging) {
      debugging = isDebugging;
   }

   public static boolean isDebugging() {
      return debugging;
   }

   private Algoritmo() {
      this.pilhaDeEscopo.push(this.escopoBase);
   }

   private Escopo getEscopoBase() {
      return this.escopoBase;
   }

   private Escopo getEscopoAtual() {
      return (Escopo)this.pilhaDeEscopo.peek();
   }

   private static Algoritmo getCurrentInstance() {
      if (algoritmo == null) {
         algoritmo = new Algoritmo();
      }

      return algoritmo;
   }

   public static void destroy() {
      algoritmo = null;
   }
}
