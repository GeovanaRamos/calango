package br.ucb.calango.gramatica;

import br.ucb.calango.api.publica.CalangoAPI;
import br.ucb.calango.util.ErrosUtil;
import br.ucb.calango.util.MensagensUtil;
import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

public class CalangoGrammarLexer extends Lexer {
   public static final int EOF = -1;
   public static final int T__101 = 101;
   public static final int T__102 = 102;
   public static final int T__103 = 103;
   public static final int T__104 = 104;
   public static final int T__105 = 105;
   public static final int T__106 = 106;
   public static final int T__107 = 107;
   public static final int T__108 = 108;
   public static final int T__109 = 109;
   public static final int T__110 = 110;
   public static final int T__111 = 111;
   public static final int T__112 = 112;
   public static final int T__113 = 113;
   public static final int T__114 = 114;
   public static final int T__115 = 115;
   public static final int T__116 = 116;
   public static final int T__117 = 117;
   public static final int T__118 = 118;
   public static final int T__119 = 119;
   public static final int T__120 = 120;
   public static final int T__121 = 121;
   public static final int T__122 = 122;
   public static final int T__123 = 123;
   public static final int T__124 = 124;
   public static final int T__125 = 125;
   public static final int T__126 = 126;
   public static final int T__127 = 127;
   public static final int T__128 = 128;
   public static final int T__129 = 129;
   public static final int T__130 = 130;
   public static final int T__131 = 131;
   public static final int T__132 = 132;
   public static final int T__133 = 133;
   public static final int T__134 = 134;
   public static final int T__135 = 135;
   public static final int T__136 = 136;
   public static final int T__137 = 137;
   public static final int T__138 = 138;
   public static final int T__139 = 139;
   public static final int T__140 = 140;
   public static final int ABS = 4;
   public static final int ADICAO = 5;
   public static final int ALEAT = 6;
   public static final int ALGORITMO = 7;
   public static final int ASCII_CHAR = 8;
   public static final int ATRIBUICAO = 9;
   public static final int BOOL_LITERAL = 10;
   public static final int CASO = 11;
   public static final int CASOS = 12;
   public static final int CHAMADA = 13;
   public static final int CHAR_ASCII = 14;
   public static final int CHAR_LITERAL = 15;
   public static final int CHAR_TEXTO = 16;
   public static final int COMENTARIO = 17;
   public static final int COMENTARIO_MULTILINHA = 18;
   public static final int COMPARA_TEXTO = 19;
   public static final int CONCATENACAO = 20;
   public static final int CONV_CHAR_TEXTO = 21;
   public static final int CONV_TEXTO_CHAR = 22;
   public static final int COPIA = 23;
   public static final int CORPO = 24;
   public static final int DECL_ATRIBUICAO = 25;
   public static final int DECL_VARIAVEL = 26;
   public static final int DIFERENTE = 27;
   public static final int DIGITO = 28;
   public static final int DIVISAO = 29;
   public static final int DIVISAO_INT = 30;
   public static final int E = 31;
   public static final int ENQUANTO = 32;
   public static final int ENQUANTO_STM = 33;
   public static final int ESCOLHA = 34;
   public static final int ESCREVA = 35;
   public static final int ESCREVAL = 36;
   public static final int EXP = 37;
   public static final int FACA = 38;
   public static final int FIM_FUNCAO = 39;
   public static final int FIM_PROCEDIMENTO = 40;
   public static final int FORMATA_REAL = 41;
   public static final int FORMATA_REAL_DECIMAL = 42;
   public static final int FORMATA_REAL_TOTAL = 43;
   public static final int FUNCAO = 44;
   public static final int IDENT = 45;
   public static final int IDENTIFICADOR = 46;
   public static final int IGUAL = 47;
   public static final int INTEIRO_LITERAL = 48;
   public static final int INTERROMPA = 49;
   public static final int LEIA = 50;
   public static final int LEIA_CARACTER = 51;
   public static final int LETRA = 52;
   public static final int LIMPA_TELA = 53;
   public static final int MAIOR = 54;
   public static final int MAIOR_IGUAL = 55;
   public static final int MAIUSC = 56;
   public static final int MAIUSC_CHAR = 57;
   public static final int MENOR = 58;
   public static final int MENOR_IGUAL = 59;
   public static final int MINUSC = 60;
   public static final int MINUSC_CHAR = 61;
   public static final int MOD = 62;
   public static final int MOD2 = 63;
   public static final int MULTIPLICACAO = 64;
   public static final int NEGACAO_LOGICA = 65;
   public static final int NEGACAO_LOGICA2 = 66;
   public static final int NEGACAO_MATEMATICA = 67;
   public static final int OU = 68;
   public static final int OUTROCASO = 69;
   public static final int PARA = 70;
   public static final int PARAMETRO = 71;
   public static final int PARAMETROS = 72;
   public static final int PARAMETROS_CHAMADA = 73;
   public static final int PARAM_MATRIZ = 74;
   public static final int PARAM_VETOR = 75;
   public static final int PI = 76;
   public static final int PRINCIPAL = 77;
   public static final int PROCEDIMENTO = 78;
   public static final int REAL_LITERAL = 79;
   public static final int REFERENCIA = 80;
   public static final int RETORNA = 81;
   public static final int RQUAD = 82;
   public static final int SE = 83;
   public static final int SENAO = 84;
   public static final int SENAO_SE = 85;
   public static final int SENAO_STMS = 86;
   public static final int SE_STMS = 87;
   public static final int STATEMENTS = 88;
   public static final int SUBTRACAO = 89;
   public static final int TAMANHO_TEXTO = 90;
   public static final int TERMO_VETOR = 91;
   public static final int TEXTO_LITERAL = 92;
   public static final int TIPO_CARACTER = 93;
   public static final int TIPO_IDENTIFICADOR = 94;
   public static final int TIPO_INTEIRO = 95;
   public static final int TIPO_LOGICO = 96;
   public static final int TIPO_REAL = 97;
   public static final int TIPO_TEXTO = 98;
   public static final int VETOR = 99;
   public static final int WHITESPACE = 100;
   private boolean errors;
   protected CalangoGrammarLexer.DFA10 dfa10;
   static final String DFA10_eotS = "\u0002\uffff\u0001&\u0001\uffff\u0001+\u0001.\u0001\uffff\u00012\u0001&\u00017\u0002&\u0001=\u0001?\u0001&\u0002\uffff\u0003&\u0001I\u0002&\u0001\uffff\u0001&\u0005\uffff\u0002&\u0004\uffff\u0001&\u0001U\u0002\uffff\u0002&\u0005\uffff\u0003&\u0001\uffff\u0003&\u0002\uffff\u0004&\u0004\uffff\u0004&\u0001m\u0002&\u0001r\u0001t\u0001\uffff\u0002&\u0001z\u0006&\u0001\u0082\u0001&\u0002\uffff\b&\u0001\u008d\b&\u0001\u0099\u0002&\u0001\u009c\u0001&\u0001\uffff\u0004&\u0001\uffff\u0001£\u0001\uffff\u0001¤\u0004&\u0001\uffff\u0002&\u0001«\u0003&\u0001¯\u0001\uffff\u0001&\u0001±\b&\u0001\uffff\u0001»\u0007&\u0001Ç\u0002&\u0001\uffff\u0002&\u0001\uffff\u0001&\u0001Í\u0003&\u0003\uffff\u0001&\u0001Ò\u0004&\u0001\uffff\u0003&\u0001\uffff\u0001&\u0001\uffff\u0004&\u0001ß\u0001&\u0001á\u0002&\u0001\uffff\u0001ä\u0005&\u0001ë\u0004&\u0001\uffff\u0005&\u0001\uffff\u0001õ\u0003&\u0001\uffff\u0001&\u0001û\u0001ü\t&\u0001\uffff\u0001&\u0001\uffff\u0002&\u0001\uffff\u0006&\u0001\uffff\u0001ď\u0004&\u0001Ĕ\u0003&\u0001\uffff\u0005&\u0002\uffff\n&\u0001ħ\u0001ĩ\u0003&\u0001ĭ\u0002&\u0001\uffff\u0001&\u0001ı\u0002&\u0001\uffff\u0005&\u0001Ĺ\u0001&\u0001Ļ\u0005&\u0001Ń\u0003&\u0001Ň\u0001\uffff\u0001ň\u0001\uffff\u0003&\u0001\uffff\u0003&\u0001\uffff\u0007&\u0001\uffff\u0001&\u0001\uffff\u0007&\u0001\uffff\u0003&\u0002\uffff\u0001š\u0006&\u0001Ũ\u0001Ū\u0001Ŭ\u0001ŭ\u0001&\u0001ů\u0002&\u0001Ų\u0001ų\u0007&\u0001\uffff\u0003&\u0001ž\u0001ſ\u0001&\u0001\uffff\u0001&\u0001\uffff\u0001&\u0002\uffff\u0001&\u0001\uffff\u0002&\u0002\uffff\u0001&\u0001ä\u0007&\u0001Ǝ\u0002\uffff\t&\u0001Ƙ\u0003&\u0001Ɯ\u0001\uffff\u0001Ɲ\u0002&\u0001Ơ\u0001ơ\u0001Ƣ\u0001&\u0001Ƥ\u0001ƥ\u0001\uffff\u0003&\u0002\uffff\u0002&\u0003\uffff\u0001ƫ\u0002\uffff\u0005&\u0001\uffff\u0001Ʊ\u0001Ʋ\u0001Ƴ\u0002&\u0003\uffff\u0002&\u0001Ƹ\u0001ƹ\u0002\uffff";
   static final String DFA10_eofS = "ƺ\uffff";
   static final String DFA10_minS = "\u0001\t\u0001\uffff\u0001a\u0001\uffff\u0001=\u0001*\u0001\uffff\u00010\u0001a\u0001=\u0001n\u0001e\u0002=\u0001a\u0002\uffff\u0001a\u0001u\u0001a\u0001]\u0001a\u0001e\u0001\uffff\u0001a\u0005\uffff\u0001b\u0001e\u0004\uffff\u0001e\u0001.\u0002\uffff\u0001r\u0001m\u0005\uffff\u0001q\u0001c\u0001p\u0001\uffff\u0001c\u0001m\u0001n\u0002\uffff\u0001t\u0001i\u0001m\u0001g\u0004\uffff\u0001d\u0001i\u0001n\u0001o\u00010\u0001r\u0001i\u00010\u0001[\u0001\uffff\u0001a\u0001i\u00010\u0001x\u0001m\u0001s\u0001e\u0001c\u0001e\u00010\u0001r\u0002\uffff\u0001o\u0001a\u0001p\u0001v\u0001i\u0001u\u0001a\u0001o\u00010\u0001a\u0001s\u0001E\u0001c\u0001e\u0001a\u0001p\u0001i\u00010\u0002u\u00010\u0001r\u0001\uffff\u0001a\u0001s\u0001c\u0001n\u0001\uffff\u0001]\u0001\uffff\u00010\u0001o\u0001l\u0001z\u0001a\u0001\uffff\u0001t\u0001a\u00010\u0001a\u0001o\u0001i\u00010\u0001\uffff\u0001d\u00010\u0001c\u0001a\u0001C\u0002a\u0001o\u0001l\u0001e\u0001\uffff\u00010\u0001o\u0001u\u0001a\u0001n\u0001e\u0001a\u0001i\u00010\u0001a\u0001c\u0001\uffff\u0002s\u0001\uffff\u0001o\u00010\u0001o\u0001e\u0001c\u0003\uffff\u0001r\u00010\u0001Q\u0002o\u0001n\u0001\uffff\u0001t\u0001r\u0001i\u0001\uffff\u0001a\u0001\uffff\u0001t\u0001r\u0001a\u0001e\u00010\u0001n\u00010\u0001h\u0001v\u0001\uffff\u00010\u0001n\u0001i\u0001r\u0001q\u0001c\u00010\u0001o\u0002r\u0001a\u0001\uffff\u0001T\u0001o\u0002c\u0001C\u0001\uffff\u00010\u0001d\u0001i\u0001n\u0001\uffff\u0001u\u00020\u0001h\u0001o\u0001i\u0001C\u0001d\u0001e\u0001a\u0001r\u0001x\u0001\uffff\u0001t\u0001\uffff\u0002a\u0001\uffff\u0002c\u0001n\u0001a\u0001u\u0001o\u0001\uffff\u00010\u0002o\u0001r\u0001e\u00010\u0002u\u0001a\u0001\uffff\u0001i\u0001p\u0002a\u0001e\u0002\uffff\u0001o\u0001r\u0001t\u0001a\u0001e\u0001r\u0001T\u0001a\u0001t\u0001o\u00020\u0001a\u0001e\u0001c\u00010\u0001a\u0001l\u0001\uffff\u0001m\u00010\u0001a\u0001l\u0001\uffff\u0002l\u0001s\u0001m\u0001a\u00010\u0001d\u00010\u0001T\u0001i\u0001m\u0001r\u0001i\u00010\u0001e\u0001c\u0001o\u00010\u0001\uffff\u00010\u0001\uffff\u0001o\u0001d\u0001i\u0001\uffff\u0001n\u0001h\u0001p\u0001\uffff\u0001c\u0001a\u0003o\u0001e\u0001l\u0001\uffff\u0001r\u0001\uffff\u0001e\u0002o\u0001a\u0001r\u0001s\u0001e\u0001\uffff\u0001x\u0001t\u0001C\u0002\uffff\u00010\u0001i\u0001p\u0001t\u0002a\u0001t\u00040\u0001n\u00010\u0001a\u0001x\u00020\u0001c\u0001o\u0001c\u0001x\u0001t\u0001T\u0001a\u0001\uffff\u0001m\u0001a\u0001o\u00020\u0001e\u0001\uffff\u0001a\u0001\uffff\u0001a\u0002\uffff\u0001t\u0001\uffff\u0001d\u0001t\u0002\uffff\u0001t\u00010\u0001i\u0001t\u0001o\u0001e\u0001r\u0001e\u0001l\u00010\u0002\uffff\u0003r\u0001o\u0001a\u0001o\u0001e\u0001i\u0001o\u00010\u0001x\u0001a\u0001n\u00010\u0001\uffff\u00010\u0002a\u00030\u0001r\u00020\u0001\uffff\u0001t\u0001c\u0001t\u0002\uffff\u0002c\u0003\uffff\u00010\u0002\uffff\u0001o\u0001t\u0001o\u0002t\u0001\uffff\u00030\u0002e\u0003\uffff\u0002r\u00020\u0002\uffff";
   static final String DFA10_maxS = "\u0001}\u0001\uffff\u0001o\u0001\uffff\u0001=\u0001/\u0001\uffff\u0001z\u0001u\u0001=\u0001n\u0001o\u0002=\u0001o\u0002\uffff\u0001a\u0001u\u0001r\u0001]\u0002e\u0001\uffff\u0001e\u0005\uffff\u0001t\u0001e\u0004\uffff\u0001e\u00019\u0002\uffff\u0001s\u0001p\u0005\uffff\u0001t\u0001c\u0001p\u0001\uffff\u0001l\u0001m\u0001n\u0002\uffff\u0001t\u0001i\u0001m\u0001g\u0004\uffff\u0001d\u0001i\u0001n\u0001o\u0001z\u0001s\u0001o\u0001z\u0001[\u0001\uffff\u0001t\u0001i\u0001z\u0001x\u0001m\u0001s\u0001g\u0001c\u0001e\u0001z\u0001r\u0002\uffff\u0001o\u0001a\u0001p\u0001v\u0001i\u0001u\u0001a\u0001r\u0001z\u0001a\u0001s\u0001S\u0001c\u0001e\u0001a\u0001p\u0001i\u0001z\u0002u\u0001z\u0001r\u0001\uffff\u0001a\u0001s\u0001c\u0001n\u0001\uffff\u0001]\u0001\uffff\u0001z\u0001o\u0001l\u0001z\u0001a\u0001\uffff\u0001t\u0001a\u0001z\u0001a\u0001o\u0001i\u0001z\u0001\uffff\u0001d\u0001z\u0001c\u0001a\u0001T\u0002a\u0001o\u0001l\u0001e\u0001\uffff\u0001z\u0001o\u0001u\u0001r\u0001s\u0001e\u0001a\u0001r\u0001z\u0001a\u0001c\u0001\uffff\u0002s\u0001\uffff\u0001o\u0001z\u0001o\u0001e\u0001c\u0003\uffff\u0001r\u0001z\u0001Q\u0002o\u0001n\u0001\uffff\u0001t\u0001r\u0001i\u0001\uffff\u0001a\u0001\uffff\u0001t\u0001r\u0001a\u0001e\u0001z\u0001n\u0001z\u0001h\u0001v\u0001\uffff\u0001z\u0001n\u0001o\u0001r\u0001q\u0001c\u0001z\u0001o\u0002r\u0001a\u0001\uffff\u0001T\u0001o\u0002c\u0001C\u0001\uffff\u0001z\u0001d\u0001i\u0001n\u0001\uffff\u0001u\u0002z\u0001h\u0001o\u0001i\u0001C\u0001d\u0001e\u0001a\u0001r\u0001x\u0001\uffff\u0001t\u0001\uffff\u0002a\u0001\uffff\u0002c\u0001n\u0001a\u0001u\u0001o\u0001\uffff\u0001z\u0002o\u0001r\u0001e\u0001z\u0002u\u0001a\u0001\uffff\u0001i\u0001p\u0002a\u0001e\u0002\uffff\u0001o\u0001r\u0001t\u0001a\u0001e\u0001r\u0001T\u0001a\u0001t\u0001o\u0002z\u0001a\u0001e\u0001c\u0001z\u0001a\u0001l\u0001\uffff\u0001m\u0001z\u0001a\u0001l\u0001\uffff\u0002l\u0001s\u0001m\u0001a\u0001z\u0001d\u0001z\u0001T\u0001i\u0001m\u0001r\u0001i\u0001z\u0001e\u0001c\u0001o\u0001z\u0001\uffff\u0001z\u0001\uffff\u0001o\u0001d\u0001i\u0001\uffff\u0001n\u0001h\u0001p\u0001\uffff\u0001c\u0001a\u0003o\u0001e\u0001l\u0001\uffff\u0001r\u0001\uffff\u0001e\u0002o\u0001a\u0001r\u0001s\u0001e\u0001\uffff\u0001x\u0001t\u0001C\u0002\uffff\u0001z\u0001i\u0001p\u0001t\u0002a\u0001t\u0004z\u0001n\u0001z\u0001a\u0001x\u0002z\u0001c\u0001o\u0001c\u0001x\u0001t\u0001T\u0001a\u0001\uffff\u0001m\u0001a\u0001o\u0002z\u0001e\u0001\uffff\u0001a\u0001\uffff\u0001a\u0002\uffff\u0001t\u0001\uffff\u0001d\u0001t\u0002\uffff\u0001t\u0001z\u0001i\u0001t\u0001o\u0001e\u0001r\u0001e\u0001l\u0001z\u0002\uffff\u0003r\u0001o\u0001a\u0001o\u0001e\u0001i\u0001o\u0001z\u0001x\u0001a\u0001n\u0001z\u0001\uffff\u0001z\u0002a\u0003z\u0001r\u0002z\u0001\uffff\u0001t\u0001c\u0001t\u0002\uffff\u0002c\u0003\uffff\u0001z\u0002\uffff\u0001o\u0001t\u0001o\u0002t\u0001\uffff\u0003z\u0002e\u0003\uffff\u0002r\u0002z\u0002\uffff";
   static final String DFA10_acceptS = "\u0001\uffff\u0001\u0001\u0001\uffff\u0001\u0003\u0002\uffff\u0001\u0006\b\uffff\u0001\u001a\u0001\u001b\u0006\uffff\u0001'\u0001\uffff\u0001-\u0001.\u0001/\u00010\u00014\u0002\uffff\u0001S\u0001T\u0001U\u0001V\u0002\uffff\u0001Z\u0001[\u0002\uffff\u0001\u0004\u0001\u001d\u0001\\\u0001]\u0001\u0005\u0003\uffff\u0001\u0007\u0003\uffff\u0001\u0010\u00011\u0004\uffff\u0001\u0016\u0001\u0015\u0001\u0018\u0001\u0017\t\uffff\u00012\u000b\uffff\u0001X\u0001Y\u0016\uffff\u0001\u001e\u0004\uffff\u0001M\u0001\uffff\u0001\"\u0005\uffff\u0001&\u0007\uffff\u0001@\n\uffff\u0001B\u000b\uffff\u0001\u0019\u0002\uffff\u0001\u001c\u0005\uffff\u0001!\u00013\u0001$\u0006\uffff\u00015\u0003\uffff\u00019\u0001\uffff\u0001\u0002\t\uffff\u0001\f\u000b\uffff\u0001\u0012\u0005\uffff\u0001 \u0004\uffff\u0001+\f\uffff\u0001?\u0001\uffff\u0001A\u0002\uffff\u0001W\u0006\uffff\u0001G\t\uffff\u0001L\u0005\uffff\u0001P\u0001,\u0012\uffff\u0001\u000f\u0004\uffff\u0001*\u0012\uffff\u0001\t\u0001\uffff\u0001\n\u0003\uffff\u0001E\u0003\uffff\u0001)\u0007\uffff\u0001%\u0001\uffff\u0001Q\u0007\uffff\u0001(\u0003\uffff\u0001\b\u0001\u000b\u0018\uffff\u0001\r\u0006\uffff\u0001\u0014\u0001\uffff\u0001H\u0001\uffff\u0001J\u0001\u001f\u0001\uffff\u0001N\u0002\uffff\u00016\u00017\n\uffff\u0001D\u0001\u0011\u000e\uffff\u0001C\t\uffff\u0001<\u0003\uffff\u0001F\u0001\u0013\u0002\uffff\u0001#\u0001O\u0001R\u0001\uffff\u0001:\u0001;\u0005\uffff\u00018\u0005\uffff\u0001=\u0001>\u0001\u000e\u0004\uffff\u0001I\u0001K";
   static final String DFA10_specialS = "ƺ\uffff}>";
   static final String[] DFA10_transitionS = new String[]{"\u0002'\u0001\uffff\u0002'\u0012\uffff\u0001'\u0001\u0004\u0001\"\u0002\uffff\u0001\u000f\u0001\uffff\u0001#\u0001\u0019\u0001\u001a\u0001\u0010\u0001\u0001\u0001\u0003\u0001\u0017\u0001\uffff\u0001\u0005\n%\u0001\u001b\u0001\u001c\u0001\r\u0001\t\u0001\f\u0002\uffff\u001a&\u0001\u0014\u0001\u0006\u0001\u001d\u0003\uffff\u0001\u001e\u0001&\u0001\u0002\u0001\u001f\u0001\u0007\u0001\b\u0002&\u0001\n\u0002&\u0001\u000b\u0001\u000e\u0001\u0011\u0001\u0012\u0001\u0013\u0001&\u0001\u0015\u0001\u0016\u0001\u0018\u0001&\u0001$\u0004&\u0001 \u0001\uffff\u0001!", "", "\u0001(\r\uffff\u0001)", "", "\u0001*", "\u0001-\u0004\uffff\u0001,", "", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\r&\u0001/\u0004&\u00010\u0004&\u00011\u0002&", "\u00013\u0007\uffff\u00014\u000b\uffff\u00015", "\u00016", "\u00018", "\u00019\u0003\uffff\u0001:\u0005\uffff\u0001;", "\u0001<", "\u0001>", "\u0001A\u0007\uffff\u0001B\u0005\uffff\u0001@", "", "", "\u0001C", "\u0001D", "\u0001E\u0007\uffff\u0001G\b\uffff\u0001F", "\u0001H", "\u0001K\u0003\uffff\u0001J", "\u0001L", "", "\u0001N\u0003\uffff\u0001M", "", "", "", "", "", "\u0001O\t\uffff\u0001P\u0006\uffff\u0001Q\u0001R", "\u0001S", "", "", "", "", "\u0001T", "\u0001V\u0001\uffff\n%", "", "", "\u0001X\u0001W", "\u0001Y\u0001Z\u0001\uffff\u0001[", "", "", "", "", "", "\u0001\\\u0002\uffff\u0001]", "\u0001^", "\u0001_", "", "\u0001`\b\uffff\u0001a", "\u0001b", "\u0001c", "", "", "\u0001d", "\u0001e", "\u0001f", "\u0001g", "", "", "", "", "\u0001h", "\u0001i", "\u0001j", "\u0001k", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u0013&\u0001l\u0006&", "\u0001n\u0001o", "\u0001q\u0005\uffff\u0001p", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001s", "", "\u0001w\u0004\uffff\u0001u\r\uffff\u0001v", "\u0001x", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\r&\u0001y\f&", "\u0001{", "\u0001|", "\u0001}", "\u0001~\u0001\uffff\u0001\u007f", "\u0001\u0080", "\u0001\u0081", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001\u0083", "", "", "\u0001\u0084", "\u0001\u0085", "\u0001\u0086", "\u0001\u0087", "\u0001\u0088", "\u0001\u0089", "\u0001\u008a", "\u0001\u008b\u0002\uffff\u0001\u008c", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001\u008e", "\u0001\u008f", "\u0001\u0092\u0001\u0090\t\uffff\u0001\u0091\u0002\uffff\u0001\u0093", "\u0001\u0094", "\u0001\u0095", "\u0001\u0096", "\u0001\u0097", "\u0001\u0098", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001\u009a", "\u0001\u009b", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001\u009d", "", "\u0001\u009e", "\u0001\u009f", "\u0001 ", "\u0001¡", "", "\u0001¢", "", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001¥", "\u0001¦", "\u0001§", "\u0001¨", "", "\u0001©", "\u0001ª", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001¬", "\u0001\u00ad", "\u0001®", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "", "\u0001°", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001²", "\u0001³", "\u0001´\u0010\uffff\u0001µ", "\u0001¶", "\u0001·", "\u0001¸", "\u0001¹", "\u0001º", "", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001¼", "\u0001½", "\u0001¿\u0010\uffff\u0001¾", "\u0001À\u0004\uffff\u0001Á", "\u0001Â", "\u0001Ã", "\u0001Å\b\uffff\u0001Ä", "\n&\u0007\uffff\u0002&\u0001Æ\u0017&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001È", "\u0001É", "", "\u0001Ê", "\u0001Ë", "", "\u0001Ì", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001Î", "\u0001Ï", "\u0001Ð", "", "", "", "\u0001Ñ", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001Ó", "\u0001Ô", "\u0001Õ", "\u0001Ö", "", "\u0001×", "\u0001Ø", "\u0001Ù", "", "\u0001Ú", "", "\u0001Û", "\u0001Ü", "\u0001Ý", "\u0001Þ", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001à", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001â", "\u0001ã", "", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001å", "\u0001ç\u0005\uffff\u0001æ", "\u0001è", "\u0001é", "\u0001ê", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001ì", "\u0001í", "\u0001î", "\u0001ï", "", "\u0001ð", "\u0001ñ", "\u0001ò", "\u0001ó", "\u0001ô", "", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001ö", "\u0001÷", "\u0001ø", "", "\u0001ù", "\n&\u0007\uffff\u0012&\u0001ú\u0007&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001ý", "\u0001þ", "\u0001ÿ", "\u0001Ā", "\u0001ā", "\u0001Ă", "\u0001ă", "\u0001Ą", "\u0001ą", "", "\u0001Ć", "", "\u0001ć", "\u0001Ĉ", "", "\u0001ĉ", "\u0001Ċ", "\u0001ċ", "\u0001Č", "\u0001č", "\u0001Ď", "", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001Đ", "\u0001đ", "\u0001Ē", "\u0001ē", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001ĕ", "\u0001Ė", "\u0001ė", "", "\u0001Ę", "\u0001ę", "\u0001Ě", "\u0001ě", "\u0001Ĝ", "", "", "\u0001ĝ", "\u0001Ğ", "\u0001ğ", "\u0001Ġ", "\u0001ġ", "\u0001Ģ", "\u0001ģ", "\u0001Ĥ", "\u0001ĥ", "\u0001Ħ", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u000b&\u0001Ĩ\u000e&", "\u0001Ī", "\u0001ī", "\u0001Ĭ", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001Į", "\u0001į", "", "\u0001İ", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001Ĳ", "\u0001ĳ", "", "\u0001Ĵ", "\u0001ĵ", "\u0001Ķ", "\u0001ķ", "\u0001ĸ", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001ĺ", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001ļ", "\u0001Ľ", "\u0001ľ", "\u0001Ŀ", "\u0001ŀ", "\n&\u0007\uffff\u0001Ł\u0012&\u0001ł\u0006&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001ń", "\u0001Ņ", "\u0001ņ", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "", "\u0001ŉ", "\u0001Ŋ", "\u0001ŋ", "", "\u0001Ō", "\u0001ō", "\u0001Ŏ", "", "\u0001ŏ", "\u0001Ő", "\u0001ő", "\u0001Œ", "\u0001œ", "\u0001Ŕ", "\u0001ŕ", "", "\u0001Ŗ", "", "\u0001ŗ", "\u0001Ř", "\u0001ř", "\u0001Ś", "\u0001ś", "\u0001Ŝ", "\u0001ŝ", "", "\u0001Ş", "\u0001ş", "\u0001Š", "", "", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001Ţ", "\u0001ţ", "\u0001Ť", "\u0001ť", "\u0001Ŧ", "\u0001ŧ", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\n&\u0007\uffff\u0002&\u0001ũ\u0017&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\n&\u0007\uffff\u0002&\u0001ū\u0017&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001Ů", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001Ű", "\u0001ű", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001Ŵ", "\u0001ŵ", "\u0001Ŷ", "\u0001ŷ", "\u0001Ÿ", "\u0001Ź", "\u0001ź", "", "\u0001Ż", "\u0001ż", "\u0001Ž", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001ƀ", "", "\u0001Ɓ", "", "\u0001Ƃ", "", "", "\u0001ƃ", "", "\u0001Ƅ", "\u0001ƅ", "", "", "\u0001Ɔ", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001Ƈ", "\u0001ƈ", "\u0001Ɖ", "\u0001Ɗ", "\u0001Ƌ", "\u0001ƌ", "\u0001ƍ", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "", "", "\u0001Ə", "\u0001Ɛ", "\u0001Ƒ", "\u0001ƒ", "\u0001Ɠ", "\u0001Ɣ", "\u0001ƕ", "\u0001Ɩ", "\u0001Ɨ", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001ƙ", "\u0001ƚ", "\u0001ƛ", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001ƞ", "\u0001Ɵ", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001ƣ", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "", "\u0001Ʀ", "\u0001Ƨ", "\u0001ƨ", "", "", "\u0001Ʃ", "\u0001ƪ", "", "", "", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "", "", "\u0001Ƭ", "\u0001ƭ", "\u0001Ʈ", "\u0001Ư", "\u0001ư", "", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\u0001ƴ", "\u0001Ƶ", "", "", "", "\u0001ƶ", "\u0001Ʒ", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "\n&\u0007\uffff\u001a&\u0004\uffff\u0001&\u0001\uffff\u001a&", "", ""};
   static final short[] DFA10_eot = DFA.unpackEncodedString("\u0002\uffff\u0001&\u0001\uffff\u0001+\u0001.\u0001\uffff\u00012\u0001&\u00017\u0002&\u0001=\u0001?\u0001&\u0002\uffff\u0003&\u0001I\u0002&\u0001\uffff\u0001&\u0005\uffff\u0002&\u0004\uffff\u0001&\u0001U\u0002\uffff\u0002&\u0005\uffff\u0003&\u0001\uffff\u0003&\u0002\uffff\u0004&\u0004\uffff\u0004&\u0001m\u0002&\u0001r\u0001t\u0001\uffff\u0002&\u0001z\u0006&\u0001\u0082\u0001&\u0002\uffff\b&\u0001\u008d\b&\u0001\u0099\u0002&\u0001\u009c\u0001&\u0001\uffff\u0004&\u0001\uffff\u0001£\u0001\uffff\u0001¤\u0004&\u0001\uffff\u0002&\u0001«\u0003&\u0001¯\u0001\uffff\u0001&\u0001±\b&\u0001\uffff\u0001»\u0007&\u0001Ç\u0002&\u0001\uffff\u0002&\u0001\uffff\u0001&\u0001Í\u0003&\u0003\uffff\u0001&\u0001Ò\u0004&\u0001\uffff\u0003&\u0001\uffff\u0001&\u0001\uffff\u0004&\u0001ß\u0001&\u0001á\u0002&\u0001\uffff\u0001ä\u0005&\u0001ë\u0004&\u0001\uffff\u0005&\u0001\uffff\u0001õ\u0003&\u0001\uffff\u0001&\u0001û\u0001ü\t&\u0001\uffff\u0001&\u0001\uffff\u0002&\u0001\uffff\u0006&\u0001\uffff\u0001ď\u0004&\u0001Ĕ\u0003&\u0001\uffff\u0005&\u0002\uffff\n&\u0001ħ\u0001ĩ\u0003&\u0001ĭ\u0002&\u0001\uffff\u0001&\u0001ı\u0002&\u0001\uffff\u0005&\u0001Ĺ\u0001&\u0001Ļ\u0005&\u0001Ń\u0003&\u0001Ň\u0001\uffff\u0001ň\u0001\uffff\u0003&\u0001\uffff\u0003&\u0001\uffff\u0007&\u0001\uffff\u0001&\u0001\uffff\u0007&\u0001\uffff\u0003&\u0002\uffff\u0001š\u0006&\u0001Ũ\u0001Ū\u0001Ŭ\u0001ŭ\u0001&\u0001ů\u0002&\u0001Ų\u0001ų\u0007&\u0001\uffff\u0003&\u0001ž\u0001ſ\u0001&\u0001\uffff\u0001&\u0001\uffff\u0001&\u0002\uffff\u0001&\u0001\uffff\u0002&\u0002\uffff\u0001&\u0001ä\u0007&\u0001Ǝ\u0002\uffff\t&\u0001Ƙ\u0003&\u0001Ɯ\u0001\uffff\u0001Ɲ\u0002&\u0001Ơ\u0001ơ\u0001Ƣ\u0001&\u0001Ƥ\u0001ƥ\u0001\uffff\u0003&\u0002\uffff\u0002&\u0003\uffff\u0001ƫ\u0002\uffff\u0005&\u0001\uffff\u0001Ʊ\u0001Ʋ\u0001Ƴ\u0002&\u0003\uffff\u0002&\u0001Ƹ\u0001ƹ\u0002\uffff");
   static final short[] DFA10_eof = DFA.unpackEncodedString("ƺ\uffff");
   static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars("\u0001\t\u0001\uffff\u0001a\u0001\uffff\u0001=\u0001*\u0001\uffff\u00010\u0001a\u0001=\u0001n\u0001e\u0002=\u0001a\u0002\uffff\u0001a\u0001u\u0001a\u0001]\u0001a\u0001e\u0001\uffff\u0001a\u0005\uffff\u0001b\u0001e\u0004\uffff\u0001e\u0001.\u0002\uffff\u0001r\u0001m\u0005\uffff\u0001q\u0001c\u0001p\u0001\uffff\u0001c\u0001m\u0001n\u0002\uffff\u0001t\u0001i\u0001m\u0001g\u0004\uffff\u0001d\u0001i\u0001n\u0001o\u00010\u0001r\u0001i\u00010\u0001[\u0001\uffff\u0001a\u0001i\u00010\u0001x\u0001m\u0001s\u0001e\u0001c\u0001e\u00010\u0001r\u0002\uffff\u0001o\u0001a\u0001p\u0001v\u0001i\u0001u\u0001a\u0001o\u00010\u0001a\u0001s\u0001E\u0001c\u0001e\u0001a\u0001p\u0001i\u00010\u0002u\u00010\u0001r\u0001\uffff\u0001a\u0001s\u0001c\u0001n\u0001\uffff\u0001]\u0001\uffff\u00010\u0001o\u0001l\u0001z\u0001a\u0001\uffff\u0001t\u0001a\u00010\u0001a\u0001o\u0001i\u00010\u0001\uffff\u0001d\u00010\u0001c\u0001a\u0001C\u0002a\u0001o\u0001l\u0001e\u0001\uffff\u00010\u0001o\u0001u\u0001a\u0001n\u0001e\u0001a\u0001i\u00010\u0001a\u0001c\u0001\uffff\u0002s\u0001\uffff\u0001o\u00010\u0001o\u0001e\u0001c\u0003\uffff\u0001r\u00010\u0001Q\u0002o\u0001n\u0001\uffff\u0001t\u0001r\u0001i\u0001\uffff\u0001a\u0001\uffff\u0001t\u0001r\u0001a\u0001e\u00010\u0001n\u00010\u0001h\u0001v\u0001\uffff\u00010\u0001n\u0001i\u0001r\u0001q\u0001c\u00010\u0001o\u0002r\u0001a\u0001\uffff\u0001T\u0001o\u0002c\u0001C\u0001\uffff\u00010\u0001d\u0001i\u0001n\u0001\uffff\u0001u\u00020\u0001h\u0001o\u0001i\u0001C\u0001d\u0001e\u0001a\u0001r\u0001x\u0001\uffff\u0001t\u0001\uffff\u0002a\u0001\uffff\u0002c\u0001n\u0001a\u0001u\u0001o\u0001\uffff\u00010\u0002o\u0001r\u0001e\u00010\u0002u\u0001a\u0001\uffff\u0001i\u0001p\u0002a\u0001e\u0002\uffff\u0001o\u0001r\u0001t\u0001a\u0001e\u0001r\u0001T\u0001a\u0001t\u0001o\u00020\u0001a\u0001e\u0001c\u00010\u0001a\u0001l\u0001\uffff\u0001m\u00010\u0001a\u0001l\u0001\uffff\u0002l\u0001s\u0001m\u0001a\u00010\u0001d\u00010\u0001T\u0001i\u0001m\u0001r\u0001i\u00010\u0001e\u0001c\u0001o\u00010\u0001\uffff\u00010\u0001\uffff\u0001o\u0001d\u0001i\u0001\uffff\u0001n\u0001h\u0001p\u0001\uffff\u0001c\u0001a\u0003o\u0001e\u0001l\u0001\uffff\u0001r\u0001\uffff\u0001e\u0002o\u0001a\u0001r\u0001s\u0001e\u0001\uffff\u0001x\u0001t\u0001C\u0002\uffff\u00010\u0001i\u0001p\u0001t\u0002a\u0001t\u00040\u0001n\u00010\u0001a\u0001x\u00020\u0001c\u0001o\u0001c\u0001x\u0001t\u0001T\u0001a\u0001\uffff\u0001m\u0001a\u0001o\u00020\u0001e\u0001\uffff\u0001a\u0001\uffff\u0001a\u0002\uffff\u0001t\u0001\uffff\u0001d\u0001t\u0002\uffff\u0001t\u00010\u0001i\u0001t\u0001o\u0001e\u0001r\u0001e\u0001l\u00010\u0002\uffff\u0003r\u0001o\u0001a\u0001o\u0001e\u0001i\u0001o\u00010\u0001x\u0001a\u0001n\u00010\u0001\uffff\u00010\u0002a\u00030\u0001r\u00020\u0001\uffff\u0001t\u0001c\u0001t\u0002\uffff\u0002c\u0003\uffff\u00010\u0002\uffff\u0001o\u0001t\u0001o\u0002t\u0001\uffff\u00030\u0002e\u0003\uffff\u0002r\u00020\u0002\uffff");
   static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars("\u0001}\u0001\uffff\u0001o\u0001\uffff\u0001=\u0001/\u0001\uffff\u0001z\u0001u\u0001=\u0001n\u0001o\u0002=\u0001o\u0002\uffff\u0001a\u0001u\u0001r\u0001]\u0002e\u0001\uffff\u0001e\u0005\uffff\u0001t\u0001e\u0004\uffff\u0001e\u00019\u0002\uffff\u0001s\u0001p\u0005\uffff\u0001t\u0001c\u0001p\u0001\uffff\u0001l\u0001m\u0001n\u0002\uffff\u0001t\u0001i\u0001m\u0001g\u0004\uffff\u0001d\u0001i\u0001n\u0001o\u0001z\u0001s\u0001o\u0001z\u0001[\u0001\uffff\u0001t\u0001i\u0001z\u0001x\u0001m\u0001s\u0001g\u0001c\u0001e\u0001z\u0001r\u0002\uffff\u0001o\u0001a\u0001p\u0001v\u0001i\u0001u\u0001a\u0001r\u0001z\u0001a\u0001s\u0001S\u0001c\u0001e\u0001a\u0001p\u0001i\u0001z\u0002u\u0001z\u0001r\u0001\uffff\u0001a\u0001s\u0001c\u0001n\u0001\uffff\u0001]\u0001\uffff\u0001z\u0001o\u0001l\u0001z\u0001a\u0001\uffff\u0001t\u0001a\u0001z\u0001a\u0001o\u0001i\u0001z\u0001\uffff\u0001d\u0001z\u0001c\u0001a\u0001T\u0002a\u0001o\u0001l\u0001e\u0001\uffff\u0001z\u0001o\u0001u\u0001r\u0001s\u0001e\u0001a\u0001r\u0001z\u0001a\u0001c\u0001\uffff\u0002s\u0001\uffff\u0001o\u0001z\u0001o\u0001e\u0001c\u0003\uffff\u0001r\u0001z\u0001Q\u0002o\u0001n\u0001\uffff\u0001t\u0001r\u0001i\u0001\uffff\u0001a\u0001\uffff\u0001t\u0001r\u0001a\u0001e\u0001z\u0001n\u0001z\u0001h\u0001v\u0001\uffff\u0001z\u0001n\u0001o\u0001r\u0001q\u0001c\u0001z\u0001o\u0002r\u0001a\u0001\uffff\u0001T\u0001o\u0002c\u0001C\u0001\uffff\u0001z\u0001d\u0001i\u0001n\u0001\uffff\u0001u\u0002z\u0001h\u0001o\u0001i\u0001C\u0001d\u0001e\u0001a\u0001r\u0001x\u0001\uffff\u0001t\u0001\uffff\u0002a\u0001\uffff\u0002c\u0001n\u0001a\u0001u\u0001o\u0001\uffff\u0001z\u0002o\u0001r\u0001e\u0001z\u0002u\u0001a\u0001\uffff\u0001i\u0001p\u0002a\u0001e\u0002\uffff\u0001o\u0001r\u0001t\u0001a\u0001e\u0001r\u0001T\u0001a\u0001t\u0001o\u0002z\u0001a\u0001e\u0001c\u0001z\u0001a\u0001l\u0001\uffff\u0001m\u0001z\u0001a\u0001l\u0001\uffff\u0002l\u0001s\u0001m\u0001a\u0001z\u0001d\u0001z\u0001T\u0001i\u0001m\u0001r\u0001i\u0001z\u0001e\u0001c\u0001o\u0001z\u0001\uffff\u0001z\u0001\uffff\u0001o\u0001d\u0001i\u0001\uffff\u0001n\u0001h\u0001p\u0001\uffff\u0001c\u0001a\u0003o\u0001e\u0001l\u0001\uffff\u0001r\u0001\uffff\u0001e\u0002o\u0001a\u0001r\u0001s\u0001e\u0001\uffff\u0001x\u0001t\u0001C\u0002\uffff\u0001z\u0001i\u0001p\u0001t\u0002a\u0001t\u0004z\u0001n\u0001z\u0001a\u0001x\u0002z\u0001c\u0001o\u0001c\u0001x\u0001t\u0001T\u0001a\u0001\uffff\u0001m\u0001a\u0001o\u0002z\u0001e\u0001\uffff\u0001a\u0001\uffff\u0001a\u0002\uffff\u0001t\u0001\uffff\u0001d\u0001t\u0002\uffff\u0001t\u0001z\u0001i\u0001t\u0001o\u0001e\u0001r\u0001e\u0001l\u0001z\u0002\uffff\u0003r\u0001o\u0001a\u0001o\u0001e\u0001i\u0001o\u0001z\u0001x\u0001a\u0001n\u0001z\u0001\uffff\u0001z\u0002a\u0003z\u0001r\u0002z\u0001\uffff\u0001t\u0001c\u0001t\u0002\uffff\u0002c\u0003\uffff\u0001z\u0002\uffff\u0001o\u0001t\u0001o\u0002t\u0001\uffff\u0003z\u0002e\u0003\uffff\u0002r\u0002z\u0002\uffff");
   static final short[] DFA10_accept = DFA.unpackEncodedString("\u0001\uffff\u0001\u0001\u0001\uffff\u0001\u0003\u0002\uffff\u0001\u0006\b\uffff\u0001\u001a\u0001\u001b\u0006\uffff\u0001'\u0001\uffff\u0001-\u0001.\u0001/\u00010\u00014\u0002\uffff\u0001S\u0001T\u0001U\u0001V\u0002\uffff\u0001Z\u0001[\u0002\uffff\u0001\u0004\u0001\u001d\u0001\\\u0001]\u0001\u0005\u0003\uffff\u0001\u0007\u0003\uffff\u0001\u0010\u00011\u0004\uffff\u0001\u0016\u0001\u0015\u0001\u0018\u0001\u0017\t\uffff\u00012\u000b\uffff\u0001X\u0001Y\u0016\uffff\u0001\u001e\u0004\uffff\u0001M\u0001\uffff\u0001\"\u0005\uffff\u0001&\u0007\uffff\u0001@\n\uffff\u0001B\u000b\uffff\u0001\u0019\u0002\uffff\u0001\u001c\u0005\uffff\u0001!\u00013\u0001$\u0006\uffff\u00015\u0003\uffff\u00019\u0001\uffff\u0001\u0002\t\uffff\u0001\f\u000b\uffff\u0001\u0012\u0005\uffff\u0001 \u0004\uffff\u0001+\f\uffff\u0001?\u0001\uffff\u0001A\u0002\uffff\u0001W\u0006\uffff\u0001G\t\uffff\u0001L\u0005\uffff\u0001P\u0001,\u0012\uffff\u0001\u000f\u0004\uffff\u0001*\u0012\uffff\u0001\t\u0001\uffff\u0001\n\u0003\uffff\u0001E\u0003\uffff\u0001)\u0007\uffff\u0001%\u0001\uffff\u0001Q\u0007\uffff\u0001(\u0003\uffff\u0001\b\u0001\u000b\u0018\uffff\u0001\r\u0006\uffff\u0001\u0014\u0001\uffff\u0001H\u0001\uffff\u0001J\u0001\u001f\u0001\uffff\u0001N\u0002\uffff\u00016\u00017\n\uffff\u0001D\u0001\u0011\u000e\uffff\u0001C\t\uffff\u0001<\u0003\uffff\u0001F\u0001\u0013\u0002\uffff\u0001#\u0001O\u0001R\u0001\uffff\u0001:\u0001;\u0005\uffff\u00018\u0005\uffff\u0001=\u0001>\u0001\u000e\u0004\uffff\u0001I\u0001K");
   static final short[] DFA10_special = DFA.unpackEncodedString("ƺ\uffff}>");
   static final short[][] DFA10_transition;

   static {
      int numStates = DFA10_transitionS.length;
      DFA10_transition = new short[numStates][];

      for(int i = 0; i < numStates; ++i) {
         DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
      }

   }

   public void emitErrorMessage(String msg) {
      this.errors = true;
      CalangoAPI.printErro(-1, msg);
   }

   public String getErrorMessage(RecognitionException e, String[] tokenNames) {
      return MensagensUtil.getMessage(e.getClass(), ErrosUtil.getMessageParams(e));
   }

   public boolean hasErrors() {
      return this.errors;
   }

   public Lexer[] getDelegates() {
      return new Lexer[0];
   }

   public CalangoGrammarLexer() {
      this.errors = false;
      this.dfa10 = new CalangoGrammarLexer.DFA10(this);
   }

   public CalangoGrammarLexer(CharStream input) {
      this(input, new RecognizerSharedState());
   }

   public CalangoGrammarLexer(CharStream input, RecognizerSharedState state) {
      super(input, state);
      this.errors = false;
      this.dfa10 = new CalangoGrammarLexer.DFA10(this);
   }

   public String getGrammarFileName() {
      return "/media/Dados/Desenvolvimento/Workspace/Java/workspaceCalango/calango/interpretador/br/ucb/calango/gramatica/CalangoGrammar.g";
   }

   public final void mADICAO() throws RecognitionException {
      int _type = 5;
      int _channel = 0;
      this.match(43);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mCASO() throws RecognitionException {
      int _type = 11;
      int _channel = 0;
      this.match("caso");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mCONCATENACAO() throws RecognitionException {
      int _type = 20;
      int _channel = 0;
      this.match(44);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mDIFERENTE() throws RecognitionException {
      int _type = 27;
      int _channel = 0;
      this.match("!=");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mDIVISAO() throws RecognitionException {
      int _type = 29;
      int _channel = 0;
      this.match(47);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mDIVISAO_INT() throws RecognitionException {
      int _type = 30;
      int _channel = 0;
      this.match(92);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mE() throws RecognitionException {
      int _type = 31;
      int _channel = 0;
      this.match(101);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mENQUANTO() throws RecognitionException {
      int _type = 32;
      int _channel = 0;
      this.match("enquanto");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mESCOLHA() throws RecognitionException {
      int _type = 34;
      int _channel = 0;
      this.match("escolha");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mESCREVA() throws RecognitionException {
      int _type = 35;
      int _channel = 0;
      this.match("escreva");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mESCREVAL() throws RecognitionException {
      int _type = 36;
      int _channel = 0;
      this.match("escreval");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mFACA() throws RecognitionException {
      int _type = 38;
      int _channel = 0;
      this.match("faca");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mFIM_FUNCAO() throws RecognitionException {
      int _type = 39;
      int _channel = 0;
      this.match("fimFuncao");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mFIM_PROCEDIMENTO() throws RecognitionException {
      int _type = 40;
      int _channel = 0;
      this.match("fimProcedimento");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mFUNCAO() throws RecognitionException {
      int _type = 44;
      int _channel = 0;
      this.match("funcao");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mIGUAL() throws RecognitionException {
      int _type = 47;
      int _channel = 0;
      this.match("==");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mINTERROMPA() throws RecognitionException {
      int _type = 49;
      int _channel = 0;
      this.match("interrompa");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mLEIA() throws RecognitionException {
      int _type = 50;
      int _channel = 0;
      this.match("leia");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mLEIA_CARACTER() throws RecognitionException {
      int _type = 51;
      int _channel = 0;
      this.match("leiaCaracter");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mLIMPA_TELA() throws RecognitionException {
      int _type = 53;
      int _channel = 0;
      this.match("limpaTela");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mMAIOR() throws RecognitionException {
      int _type = 54;
      int _channel = 0;
      this.match(62);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mMAIOR_IGUAL() throws RecognitionException {
      int _type = 55;
      int _channel = 0;
      this.match(">=");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mMENOR() throws RecognitionException {
      int _type = 58;
      int _channel = 0;
      this.match(60);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mMENOR_IGUAL() throws RecognitionException {
      int _type = 59;
      int _channel = 0;
      this.match("<=");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mMOD() throws RecognitionException {
      int _type = 62;
      int _channel = 0;
      this.match("mod");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mMOD2() throws RecognitionException {
      int _type = 63;
      int _channel = 0;
      this.match(37);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mMULTIPLICACAO() throws RecognitionException {
      int _type = 64;
      int _channel = 0;
      this.match(42);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mNEGACAO_LOGICA() throws RecognitionException {
      int _type = 65;
      int _channel = 0;
      this.match("nao");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mNEGACAO_LOGICA2() throws RecognitionException {
      int _type = 66;
      int _channel = 0;
      this.match(33);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mOU() throws RecognitionException {
      int _type = 68;
      int _channel = 0;
      this.match("ou");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mOUTROCASO() throws RecognitionException {
      int _type = 69;
      int _channel = 0;
      this.match("outroCaso");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mPARA() throws RecognitionException {
      int _type = 70;
      int _channel = 0;
      this.match("para");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mPARAM_MATRIZ() throws RecognitionException {
      int _type = 74;
      int _channel = 0;
      this.match("[][]");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mPARAM_VETOR() throws RecognitionException {
      int _type = 75;
      int _channel = 0;
      this.match("[]");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mPROCEDIMENTO() throws RecognitionException {
      int _type = 78;
      int _channel = 0;
      this.match("procedimento");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mREFERENCIA() throws RecognitionException {
      int _type = 80;
      int _channel = 0;
      this.match("ref");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mRETORNA() throws RecognitionException {
      int _type = 81;
      int _channel = 0;
      this.match("retorna");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mSE() throws RecognitionException {
      int _type = 83;
      int _channel = 0;
      this.match("se");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mSUBTRACAO() throws RecognitionException {
      int _type = 89;
      int _channel = 0;
      this.match(45);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mTIPO_CARACTER() throws RecognitionException {
      int _type = 93;
      int _channel = 0;
      this.match("caracter");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mTIPO_INTEIRO() throws RecognitionException {
      int _type = 95;
      int _channel = 0;
      this.match("inteiro");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mTIPO_LOGICO() throws RecognitionException {
      int _type = 96;
      int _channel = 0;
      this.match("logico");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mTIPO_REAL() throws RecognitionException {
      int _type = 97;
      int _channel = 0;
      this.match("real");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mTIPO_TEXTO() throws RecognitionException {
      int _type = 98;
      int _channel = 0;
      this.match("texto");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__101() throws RecognitionException {
      int _type = 101;
      int _channel = 0;
      this.match(40);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__102() throws RecognitionException {
      int _type = 102;
      int _channel = 0;
      this.match(41);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__103() throws RecognitionException {
      int _type = 103;
      int _channel = 0;
      this.match(58);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__104() throws RecognitionException {
      int _type = 104;
      int _channel = 0;
      this.match(59);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__105() throws RecognitionException {
      int _type = 105;
      int _channel = 0;
      this.match(61);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__106() throws RecognitionException {
      int _type = 106;
      int _channel = 0;
      this.match(91);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__107() throws RecognitionException {
      int _type = 107;
      int _channel = 0;
      this.match("[][");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__108() throws RecognitionException {
      int _type = 108;
      int _channel = 0;
      this.match(93);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__109() throws RecognitionException {
      int _type = 109;
      int _channel = 0;
      this.match("abs");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__110() throws RecognitionException {
      int _type = 110;
      int _channel = 0;
      this.match("aleatorio");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__111() throws RecognitionException {
      int _type = 111;
      int _channel = 0;
      this.match("algoritmo");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__112() throws RecognitionException {
      int _type = 112;
      int _channel = 0;
      this.match("asciiCaracter");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__113() throws RecognitionException {
      int _type = 113;
      int _channel = 0;
      this.match("ate");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__114() throws RecognitionException {
      int _type = 114;
      int _channel = 0;
      this.match("caracterAscii");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__115() throws RecognitionException {
      int _type = 115;
      int _channel = 0;
      this.match("caracterTexto");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__116() throws RecognitionException {
      int _type = 116;
      int _channel = 0;
      this.match("comparaTexto");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__117() throws RecognitionException {
      int _type = 117;
      int _channel = 0;
      this.match("convCaractTexto");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__118() throws RecognitionException {
      int _type = 118;
      int _channel = 0;
      this.match("convTextoCaract");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__119() throws RecognitionException {
      int _type = 119;
      int _channel = 0;
      this.match("copia");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__120() throws RecognitionException {
      int _type = 120;
      int _channel = 0;
      this.match("de");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__121() throws RecognitionException {
      int _type = 121;
      int _channel = 0;
      this.match("entao");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__122() throws RecognitionException {
      int _type = 122;
      int _channel = 0;
      this.match("exp");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__123() throws RecognitionException {
      int _type = 123;
      int _channel = 0;
      this.match("fimEnquanto");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__124() throws RecognitionException {
      int _type = 124;
      int _channel = 0;
      this.match("fimEscolha");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__125() throws RecognitionException {
      int _type = 125;
      int _channel = 0;
      this.match("fimPara");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__126() throws RecognitionException {
      int _type = 126;
      int _channel = 0;
      this.match("fimPrincipal");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__127() throws RecognitionException {
      int _type = 127;
      int _channel = 0;
      this.match("fimSe");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__128() throws RecognitionException {
      int _type = 128;
      int _channel = 0;
      this.match("maiusculo");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__129() throws RecognitionException {
      int _type = 129;
      int _channel = 0;
      this.match("maiusculoCaracter");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__130() throws RecognitionException {
      int _type = 130;
      int _channel = 0;
      this.match("minusculo");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__131() throws RecognitionException {
      int _type = 131;
      int _channel = 0;
      this.match("minusculoCaracter");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__132() throws RecognitionException {
      int _type = 132;
      int _channel = 0;
      this.match("passo");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__133() throws RecognitionException {
      int _type = 133;
      int _channel = 0;
      this.match("pi");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__134() throws RecognitionException {
      int _type = 134;
      int _channel = 0;
      this.match("principal");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__135() throws RecognitionException {
      int _type = 135;
      int _channel = 0;
      this.match("raizQuadrada");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__136() throws RecognitionException {
      int _type = 136;
      int _channel = 0;
      this.match("senao");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__137() throws RecognitionException {
      int _type = 137;
      int _channel = 0;
      this.match("senaoSe");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__138() throws RecognitionException {
      int _type = 138;
      int _channel = 0;
      this.match("tamanhoTexto");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__139() throws RecognitionException {
      int _type = 139;
      int _channel = 0;
      this.match(123);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mT__140() throws RecognitionException {
      int _type = 140;
      int _channel = 0;
      this.match(125);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mTEXTO_LITERAL() throws RecognitionException {
      int _type = 92;
      int _channel = 0;
      this.match(34);
      StringBuilder string = new StringBuilder();

      while(true) {
         int alt1 = 3;
         int LA1_0 = this.input.LA(1);
         if (LA1_0 == 34) {
            int LA1_1 = this.input.LA(2);
            if (LA1_1 == 34) {
               alt1 = 1;
            }
         } else if (LA1_0 >= 0 && LA1_0 <= 9 || LA1_0 >= 11 && LA1_0 <= 12 || LA1_0 >= 14 && LA1_0 <= 33 || LA1_0 >= 35 && LA1_0 <= 65535) {
            alt1 = 2;
         }

         switch(alt1) {
            case 1:
               this.match(34);
               this.match(34);
               string.appendCodePoint(34);
               break;
            case 2:
               int c = this.input.LA(1);
               if ((this.input.LA(1) < 0 || this.input.LA(1) > 9) && (this.input.LA(1) < 11 || this.input.LA(1) > 12) && (this.input.LA(1) < 14 || this.input.LA(1) > 33) && (this.input.LA(1) < 35 || this.input.LA(1) > 65535)) {
                  MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                  this.recover(mse);
                  throw mse;
               }

               this.input.consume();
               string.appendCodePoint(c);
               break;
            default:
               this.match(34);
               this.setText(string.toString());
               this.state.type = _type;
               this.state.channel = _channel;
               return;
         }
      }
   }

   public final void mCHAR_LITERAL() throws RecognitionException {
      int _type = 15;
      int _channel = 0;
      this.match(39);
      this.matchAny();
      this.match(39);
      this.setText(this.getText().substring(1, 2));
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mBOOL_LITERAL() throws RecognitionException {
      int _type = 10;
      int _channel = 0;
      int LA2_0 = this.input.LA(1);
      byte alt2;
      if (LA2_0 == 118) {
         alt2 = 1;
      } else {
         if (LA2_0 != 102) {
            NoViableAltException nvae = new NoViableAltException("", 2, 0, this.input);
            throw nvae;
         }

         alt2 = 2;
      }

      switch(alt2) {
         case 1:
            this.match("verdadeiro");
            break;
         case 2:
            this.match("falso");
      }

      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mLETRA() throws RecognitionException {
      if ((this.input.LA(1) < 65 || this.input.LA(1) > 90) && (this.input.LA(1) < 97 || this.input.LA(1) > 122)) {
         MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
         this.recover(mse);
         throw mse;
      } else {
         this.input.consume();
      }
   }

   public final void mDIGITO() throws RecognitionException {
      if (this.input.LA(1) >= 48 && this.input.LA(1) <= 57) {
         this.input.consume();
      } else {
         MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
         this.recover(mse);
         throw mse;
      }
   }

   public final void mINTEIRO_LITERAL() throws RecognitionException {
      int _type = 48;
      int _channel = 0;
      int cnt3 = 0;

      while(true) {
         int alt3 = 2;
         int LA3_0 = this.input.LA(1);
         if (LA3_0 >= 48 && LA3_0 <= 57) {
            alt3 = 1;
         }

         switch(alt3) {
            case 1:
               if (this.input.LA(1) < 48 || this.input.LA(1) > 57) {
                  MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                  this.recover(mse);
                  throw mse;
               }

               this.input.consume();
               ++cnt3;
               break;
            default:
               if (cnt3 >= 1) {
                  this.state.type = _type;
                  this.state.channel = _channel;
                  return;
               }

               EarlyExitException eee = new EarlyExitException(3, this.input);
               throw eee;
         }
      }
   }

   public final void mREAL_LITERAL() throws RecognitionException {
      int _type = 79;
      int _channel = 0;
      int cnt4 = 0;

      while(true) {
         int alt5 = 2;
         int LA5_0 = this.input.LA(1);
         if (LA5_0 >= 48 && LA5_0 <= 57) {
            alt5 = 1;
         }

         MismatchedSetException mse;
         switch(alt5) {
            case 1:
               if (this.input.LA(1) < 48 || this.input.LA(1) > 57) {
                  mse = new MismatchedSetException((BitSet)null, this.input);
                  this.recover(mse);
                  throw mse;
               }

               this.input.consume();
               ++cnt4;
               break;
            default:
               if (cnt4 < 1) {
                  EarlyExitException eee = new EarlyExitException(4, this.input);
                  throw eee;
               }

               this.match(46);

               while(true) {
                  alt5 = 2;
                  LA5_0 = this.input.LA(1);
                  if (LA5_0 >= 48 && LA5_0 <= 57) {
                     alt5 = 1;
                  }

                  switch(alt5) {
                     case 1:
                        if (this.input.LA(1) < 48 || this.input.LA(1) > 57) {
                           mse = new MismatchedSetException((BitSet)null, this.input);
                           this.recover(mse);
                           throw mse;
                        }

                        this.input.consume();
                        break;
                     default:
                        this.state.type = _type;
                        this.state.channel = _channel;
                        return;
                  }
               }
         }
      }
   }

   public final void mIDENTIFICADOR() throws RecognitionException {
      int _type = 46;
      int _channel = 0;
      this.mLETRA();

      while(true) {
         int alt6 = 2;
         int LA6_0 = this.input.LA(1);
         if (LA6_0 >= 48 && LA6_0 <= 57 || LA6_0 >= 65 && LA6_0 <= 90 || LA6_0 == 95 || LA6_0 >= 97 && LA6_0 <= 122) {
            alt6 = 1;
         }

         switch(alt6) {
            case 1:
               if ((this.input.LA(1) < 48 || this.input.LA(1) > 57) && (this.input.LA(1) < 65 || this.input.LA(1) > 90) && this.input.LA(1) != 95 && (this.input.LA(1) < 97 || this.input.LA(1) > 122)) {
                  MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                  this.recover(mse);
                  throw mse;
               }

               this.input.consume();
               break;
            default:
               this.state.type = _type;
               this.state.channel = _channel;
               return;
         }
      }
   }

   public final void mWHITESPACE() throws RecognitionException {
      int _type = 100;
      int cnt7 = 0;

      while(true) {
         int alt7 = 2;
         int LA7_0 = this.input.LA(1);
         if (LA7_0 >= 9 && LA7_0 <= 10 || LA7_0 >= 12 && LA7_0 <= 13 || LA7_0 == 32) {
            alt7 = 1;
         }

         switch(alt7) {
            case 1:
               if ((this.input.LA(1) < 9 || this.input.LA(1) > 10) && (this.input.LA(1) < 12 || this.input.LA(1) > 13) && this.input.LA(1) != 32) {
                  MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                  this.recover(mse);
                  throw mse;
               }

               this.input.consume();
               ++cnt7;
               break;
            default:
               if (cnt7 >= 1) {
                  int _channel = 99;
                  this.state.type = _type;
                  this.state.channel = _channel;
                  return;
               }

               EarlyExitException eee = new EarlyExitException(7, this.input);
               throw eee;
         }
      }
   }

   public final void mCOMENTARIO() throws RecognitionException {
      int _type = 17;
      this.match("//");

      while(true) {
         int alt8 = 2;
         int LA8_0 = this.input.LA(1);
         if (LA8_0 >= 0 && LA8_0 <= 9 || LA8_0 >= 11 && LA8_0 <= 12 || LA8_0 >= 14 && LA8_0 <= 65535) {
            alt8 = 1;
         }

         switch(alt8) {
            case 1:
               if ((this.input.LA(1) < 0 || this.input.LA(1) > 9) && (this.input.LA(1) < 11 || this.input.LA(1) > 12) && (this.input.LA(1) < 14 || this.input.LA(1) > 65535)) {
                  MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                  this.recover(mse);
                  throw mse;
               }

               this.input.consume();
               break;
            default:
               int _channel = 99;
               this.state.type = _type;
               this.state.channel = _channel;
               return;
         }
      }
   }

   public final void mCOMENTARIO_MULTILINHA() throws RecognitionException {
      int _type = 18;
      this.match("/*");

      while(true) {
         int alt9 = 2;
         int LA9_0 = this.input.LA(1);
         if (LA9_0 == 42) {
            int LA9_1 = this.input.LA(2);
            if (LA9_1 == 47) {
               alt9 = 2;
            } else if (LA9_1 >= 0 && LA9_1 <= 46 || LA9_1 >= 48 && LA9_1 <= 65535) {
               alt9 = 1;
            }
         } else if (LA9_0 >= 0 && LA9_0 <= 41 || LA9_0 >= 43 && LA9_0 <= 65535) {
            alt9 = 1;
         }

         switch(alt9) {
            case 1:
               this.matchAny();
               break;
            default:
               this.match("*/");
               int _channel = 99;
               this.state.type = _type;
               this.state.channel = _channel;
               return;
         }
      }
   }

   public void mTokens() throws RecognitionException {
      int alt10 = this.dfa10.predict(this.input);
      switch(alt10) {
         case 1:
            this.mADICAO();
            break;
         case 2:
            this.mCASO();
            break;
         case 3:
            this.mCONCATENACAO();
            break;
         case 4:
            this.mDIFERENTE();
            break;
         case 5:
            this.mDIVISAO();
            break;
         case 6:
            this.mDIVISAO_INT();
            break;
         case 7:
            this.mE();
            break;
         case 8:
            this.mENQUANTO();
            break;
         case 9:
            this.mESCOLHA();
            break;
         case 10:
            this.mESCREVA();
            break;
         case 11:
            this.mESCREVAL();
            break;
         case 12:
            this.mFACA();
            break;
         case 13:
            this.mFIM_FUNCAO();
            break;
         case 14:
            this.mFIM_PROCEDIMENTO();
            break;
         case 15:
            this.mFUNCAO();
            break;
         case 16:
            this.mIGUAL();
            break;
         case 17:
            this.mINTERROMPA();
            break;
         case 18:
            this.mLEIA();
            break;
         case 19:
            this.mLEIA_CARACTER();
            break;
         case 20:
            this.mLIMPA_TELA();
            break;
         case 21:
            this.mMAIOR();
            break;
         case 22:
            this.mMAIOR_IGUAL();
            break;
         case 23:
            this.mMENOR();
            break;
         case 24:
            this.mMENOR_IGUAL();
            break;
         case 25:
            this.mMOD();
            break;
         case 26:
            this.mMOD2();
            break;
         case 27:
            this.mMULTIPLICACAO();
            break;
         case 28:
            this.mNEGACAO_LOGICA();
            break;
         case 29:
            this.mNEGACAO_LOGICA2();
            break;
         case 30:
            this.mOU();
            break;
         case 31:
            this.mOUTROCASO();
            break;
         case 32:
            this.mPARA();
            break;
         case 33:
            this.mPARAM_MATRIZ();
            break;
         case 34:
            this.mPARAM_VETOR();
            break;
         case 35:
            this.mPROCEDIMENTO();
            break;
         case 36:
            this.mREFERENCIA();
            break;
         case 37:
            this.mRETORNA();
            break;
         case 38:
            this.mSE();
            break;
         case 39:
            this.mSUBTRACAO();
            break;
         case 40:
            this.mTIPO_CARACTER();
            break;
         case 41:
            this.mTIPO_INTEIRO();
            break;
         case 42:
            this.mTIPO_LOGICO();
            break;
         case 43:
            this.mTIPO_REAL();
            break;
         case 44:
            this.mTIPO_TEXTO();
            break;
         case 45:
            this.mT__101();
            break;
         case 46:
            this.mT__102();
            break;
         case 47:
            this.mT__103();
            break;
         case 48:
            this.mT__104();
            break;
         case 49:
            this.mT__105();
            break;
         case 50:
            this.mT__106();
            break;
         case 51:
            this.mT__107();
            break;
         case 52:
            this.mT__108();
            break;
         case 53:
            this.mT__109();
            break;
         case 54:
            this.mT__110();
            break;
         case 55:
            this.mT__111();
            break;
         case 56:
            this.mT__112();
            break;
         case 57:
            this.mT__113();
            break;
         case 58:
            this.mT__114();
            break;
         case 59:
            this.mT__115();
            break;
         case 60:
            this.mT__116();
            break;
         case 61:
            this.mT__117();
            break;
         case 62:
            this.mT__118();
            break;
         case 63:
            this.mT__119();
            break;
         case 64:
            this.mT__120();
            break;
         case 65:
            this.mT__121();
            break;
         case 66:
            this.mT__122();
            break;
         case 67:
            this.mT__123();
            break;
         case 68:
            this.mT__124();
            break;
         case 69:
            this.mT__125();
            break;
         case 70:
            this.mT__126();
            break;
         case 71:
            this.mT__127();
            break;
         case 72:
            this.mT__128();
            break;
         case 73:
            this.mT__129();
            break;
         case 74:
            this.mT__130();
            break;
         case 75:
            this.mT__131();
            break;
         case 76:
            this.mT__132();
            break;
         case 77:
            this.mT__133();
            break;
         case 78:
            this.mT__134();
            break;
         case 79:
            this.mT__135();
            break;
         case 80:
            this.mT__136();
            break;
         case 81:
            this.mT__137();
            break;
         case 82:
            this.mT__138();
            break;
         case 83:
            this.mT__139();
            break;
         case 84:
            this.mT__140();
            break;
         case 85:
            this.mTEXTO_LITERAL();
            break;
         case 86:
            this.mCHAR_LITERAL();
            break;
         case 87:
            this.mBOOL_LITERAL();
            break;
         case 88:
            this.mINTEIRO_LITERAL();
            break;
         case 89:
            this.mREAL_LITERAL();
            break;
         case 90:
            this.mIDENTIFICADOR();
            break;
         case 91:
            this.mWHITESPACE();
            break;
         case 92:
            this.mCOMENTARIO();
            break;
         case 93:
            this.mCOMENTARIO_MULTILINHA();
      }

   }

   class DFA10 extends DFA {
      public DFA10(BaseRecognizer recognizer) {
         this.recognizer = recognizer;
         this.decisionNumber = 10;
         this.eot = CalangoGrammarLexer.DFA10_eot;
         this.eof = CalangoGrammarLexer.DFA10_eof;
         this.min = CalangoGrammarLexer.DFA10_min;
         this.max = CalangoGrammarLexer.DFA10_max;
         this.accept = CalangoGrammarLexer.DFA10_accept;
         this.special = CalangoGrammarLexer.DFA10_special;
         this.transition = CalangoGrammarLexer.DFA10_transition;
      }

      public String getDescription() {
         return "1:1: Tokens : ( ADICAO | CASO | CONCATENACAO | DIFERENTE | DIVISAO | DIVISAO_INT | E | ENQUANTO | ESCOLHA | ESCREVA | ESCREVAL | FACA | FIM_FUNCAO | FIM_PROCEDIMENTO | FUNCAO | IGUAL | INTERROMPA | LEIA | LEIA_CARACTER | LIMPA_TELA | MAIOR | MAIOR_IGUAL | MENOR | MENOR_IGUAL | MOD | MOD2 | MULTIPLICACAO | NEGACAO_LOGICA | NEGACAO_LOGICA2 | OU | OUTROCASO | PARA | PARAM_MATRIZ | PARAM_VETOR | PROCEDIMENTO | REFERENCIA | RETORNA | SE | SUBTRACAO | TIPO_CARACTER | TIPO_INTEIRO | TIPO_LOGICO | TIPO_REAL | TIPO_TEXTO | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | T__116 | T__117 | T__118 | T__119 | T__120 | T__121 | T__122 | T__123 | T__124 | T__125 | T__126 | T__127 | T__128 | T__129 | T__130 | T__131 | T__132 | T__133 | T__134 | T__135 | T__136 | T__137 | T__138 | T__139 | T__140 | TEXTO_LITERAL | CHAR_LITERAL | BOOL_LITERAL | INTEIRO_LITERAL | REAL_LITERAL | IDENTIFICADOR | WHITESPACE | COMENTARIO | COMENTARIO_MULTILINHA );";
      }
   }
}

