package br.ucb.calango.simbolos;

import br.ucb.calango.exceptions.erros.ImpressaoDeMatrizException;
import br.ucb.calango.exceptions.erros.ImpressaoDeVetorException;
import br.ucb.calango.exceptions.erros.NegacaoLogicaInvalidaException;
import br.ucb.calango.exceptions.erros.NegacaoNumericaInvalidaException;
import br.ucb.calango.exceptions.erros.OperacaoInvalidaException;
import br.ucb.calango.exceptions.erros.TiposIncompativeisException;
import br.ucb.calango.exceptions.erros.VariavelNaoInicializadaException;
import br.ucb.calango.exceptions.erros.VariavelTravadaException;
import br.ucb.calango.tipos.Operacoes;
import br.ucb.calango.util.AcoesUtil;

public class Simbolo {
   Object valor;
   private String identificador;
   private Class<?> tipo;
   private boolean travada = false;

   public Simbolo(String identificador, Class<?> tipo, Object value) {
      this.identificador = identificador;
      this.tipo = tipo;
      this.setValor(value);
   }

   public String getIdentificador() {
      return this.identificador;
   }

   public Class<?> getTipo() {
      return this.tipo;
   }

   public Object getValor() {
      if (this.valor == null) {
         throw new VariavelNaoInicializadaException(this.getIdentificador());
      } else {
         return this.valor;
      }
   }

   public <T> T getValor(Class<T> c) {
      return (T) AcoesUtil.convertValue(c, this.getValor());
   }

   public Object getValorUnchecked() {
      return this.valor;
   }

   public final void setValor(Simbolo valor) throws TiposIncompativeisException {
      this.setValor(valor.getValor());
   }

   public final void setValor(Object valor) throws TiposIncompativeisException {
      if (this.isTravada()) {
         throw new VariavelTravadaException(this.getIdentificador());
      } else {
         this.valor = AcoesUtil.convertValue(this.getTipo(), valor);
      }
   }

   public final boolean isArray() {
      return this.getTipo().isArray();
   }

   public final boolean isMatrix() {
      return !this.isArray() ? false : this.getTipo().getComponentType().isArray();
   }

   public boolean isTravada() {
      return this.travada;
   }

   public void travar() {
      this.travada = true;
   }

   public void destravar() {
      this.travada = false;
   }

   public String toString() {
      if (this.getValor() == null) {
         return "";
      } else if (this.getTipo().equals(Boolean.class)) {
         return (Boolean)this.valor ? "verdadeiro" : "falso";
      } else if (this.getTipo().isArray()) {
         throw new ImpressaoDeMatrizException();
      } else if (this.getTipo().isArray()) {
         throw new ImpressaoDeVetorException();
      } else {
         return this.getValor().toString();
      }
   }

   public final SimboloLiteral add(Simbolo simbolo) {
      Class<?> tipo = null;
      Object valor = null;

      try {
         if (!AcoesUtil.oneSymbolIsOfType(this, simbolo, String.class) && !AcoesUtil.oneSymbolIsOfType(this, simbolo, Character.class)) {
            if (AcoesUtil.oneSymbolIsOfType(this, simbolo, Double.class)) {
               tipo = Double.class;
               valor = new Double(this.getValor().toString()) + new Double(simbolo.getValor().toString());
            } else {
               if (!AcoesUtil.oneSymbolIsOfType(this, simbolo, Integer.class)) {
                  throw new OperacaoInvalidaException(Operacoes.SOMA.getDescricao(), this.toString(), simbolo.toString());
               }

               tipo = Integer.class;
               valor = new Integer(this.getValor().toString()) + new Integer(simbolo.getValor().toString());
            }
         } else {
            tipo = String.class;
            valor = this.toString() + simbolo.toString();
         }
      } catch (Exception var5) {
         throw new OperacaoInvalidaException(Operacoes.SOMA.getDescricao(), this.toString(), simbolo.toString());
      }

      return new SimboloLiteral(tipo, valor);
   }

   public final SimboloLiteral sub(Simbolo simbolo) {
      Class<?> tipo = null;
      Object valor = null;

      try {
         if (AcoesUtil.oneSymbolIsOfType(this, simbolo, Double.class)) {
            tipo = Double.class;
            valor = new Double(this.getValor().toString()) - new Double(simbolo.getValor().toString());
         } else {
            if (!AcoesUtil.oneSymbolIsOfType(this, simbolo, Integer.class)) {
               throw new OperacaoInvalidaException(Operacoes.SUBTRACAO.getDescricao(), this.toString(), simbolo.toString());
            }

            tipo = Integer.class;
            valor = new Integer(this.getValor().toString()) - new Integer(simbolo.getValor().toString());
         }
      } catch (Exception var5) {
         throw new OperacaoInvalidaException(Operacoes.SUBTRACAO.getDescricao(), this.toString(), simbolo.toString());
      }

      return new SimboloLiteral(tipo, valor);
   }

   public final SimboloLiteral mult(Simbolo simbolo) {
      Class<?> tipo = null;
      Object valor = null;

      try {
         if (AcoesUtil.oneSymbolIsOfType(this, simbolo, Double.class)) {
            tipo = Double.class;
            valor = new Double(this.getValor().toString()) * new Double(simbolo.getValor().toString());
         } else {
            if (!AcoesUtil.oneSymbolIsOfType(this, simbolo, Integer.class)) {
               throw new OperacaoInvalidaException(Operacoes.MULTIPLICACAO.getDescricao(), this.toString(), simbolo.toString());
            }

            tipo = Integer.class;
            valor = new Integer(this.getValor().toString()) * new Integer(simbolo.getValor().toString());
         }
      } catch (Exception var5) {
         throw new OperacaoInvalidaException(Operacoes.MULTIPLICACAO.getDescricao(), this.toString(), simbolo.toString());
      }

      return new SimboloLiteral(tipo, valor);
   }

   public final SimboloLiteral div(Simbolo simbolo) {
      Class<?> tipo = null;
      Double valor = null;

      try {
         tipo = Double.class;
         valor = new Double(this.getValor().toString()) / new Double(simbolo.getValor().toString());
      } catch (Exception var5) {
         throw new OperacaoInvalidaException(Operacoes.DIVISAO.getDescricao(), this.toString(), simbolo.toString());
      }

      return new SimboloLiteral(tipo, valor);
   }

   public Simbolo divInt(Simbolo simbolo) {
      return new SimboloLiteral(Integer.class, this.div(simbolo).getValor());
   }

   public final SimboloLiteral mod(Simbolo simbolo) {
      Class<?> tipo = null;
      Object valor = null;

      try {
         if (AcoesUtil.oneSymbolIsOfType(this, simbolo, Double.class)) {
            tipo = Double.class;
            valor = new Double(this.getValor().toString()) % new Double(simbolo.getValor().toString());
         } else {
            if (!AcoesUtil.oneSymbolIsOfType(this, simbolo, Integer.class)) {
               throw new OperacaoInvalidaException(Operacoes.MOD.getDescricao(), this.toString(), simbolo.toString());
            }

            tipo = Integer.class;
            valor = new Integer(this.getValor().toString()) % new Integer(simbolo.getValor().toString());
         }
      } catch (Exception var5) {
         throw new OperacaoInvalidaException(Operacoes.MOD.getDescricao(), this.toString(), simbolo.toString());
      }

      return new SimboloLiteral(tipo, valor);
   }

   public SimboloLiteral igual(Simbolo s) {
      return s.getValor().equals(this.getValor()) ? new SimboloLiteral(Boolean.class, true) : new SimboloLiteral(Boolean.class, false);
   }

   public SimboloLiteral nao() {
      if (!this.getTipo().equals(Boolean.class)) {
         throw new NegacaoLogicaInvalidaException(this.getTipo());
      } else {
         return new SimboloLiteral(Boolean.class, !(Boolean)this.getValor());
      }
   }

   public SimboloLiteral neg() {
      if (this.getTipo().equals(Double.class)) {
         return new SimboloLiteral(Double.class, (Double)this.getValor() * -1.0D);
      } else if (this.getTipo().equals(Integer.class)) {
         return new SimboloLiteral(Integer.class, (Integer)this.getValor() * -1);
      } else {
         throw new NegacaoNumericaInvalidaException(this.getTipo());
      }
   }

   public boolean isCompativel(Simbolo outro) {
      if (this.isArray() & outro.isArray() && !this.getTipo().equals(outro.getTipo())) {
         return false;
      } else if (this.isMatrix() & outro.isMatrix() && !this.getTipo().equals(outro.getTipo())) {
         return false;
      } else if (this.isArray() != outro.isArray()) {
         return false;
      } else {
         return this.isMatrix() == outro.isMatrix();
      }
   }
}
