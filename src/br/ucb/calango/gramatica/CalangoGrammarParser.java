package br.ucb.calango.gramatica;

import br.ucb.calango.api.publica.CalangoAPI;
import br.ucb.calango.exceptions.parser.ComandoForaDoContextoException;
import br.ucb.calango.util.ErrosUtil;
import br.ucb.calango.util.MensagensUtil;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.MissingTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.RewriteEarlyExitException;
import org.antlr.runtime.tree.RewriteRuleSubtreeStream;
import org.antlr.runtime.tree.RewriteRuleTokenStream;
import org.antlr.runtime.tree.TreeAdaptor;

public class CalangoGrammarParser extends Parser {
   public static final String[] tokenNames = new String[]{"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ABS", "ADICAO", "ALEAT", "ALGORITMO", "ASCII_CHAR", "ATRIBUICAO", "BOOL_LITERAL", "CASO", "CASOS", "CHAMADA", "CHAR_ASCII", "CHAR_LITERAL", "CHAR_TEXTO", "COMENTARIO", "COMENTARIO_MULTILINHA", "COMPARA_TEXTO", "CONCATENACAO", "CONV_CHAR_TEXTO", "CONV_TEXTO_CHAR", "COPIA", "CORPO", "DECL_ATRIBUICAO", "DECL_VARIAVEL", "DIFERENTE", "DIGITO", "DIVISAO", "DIVISAO_INT", "E", "ENQUANTO", "ENQUANTO_STM", "ESCOLHA", "ESCREVA", "ESCREVAL", "EXP", "FACA", "FIM_FUNCAO", "FIM_PROCEDIMENTO", "FORMATA_REAL", "FORMATA_REAL_DECIMAL", "FORMATA_REAL_TOTAL", "FUNCAO", "IDENT", "IDENTIFICADOR", "IGUAL", "INTEIRO_LITERAL", "INTERROMPA", "LEIA", "LEIA_CARACTER", "LETRA", "LIMPA_TELA", "MAIOR", "MAIOR_IGUAL", "MAIUSC", "MAIUSC_CHAR", "MENOR", "MENOR_IGUAL", "MINUSC", "MINUSC_CHAR", "MOD", "MOD2", "MULTIPLICACAO", "NEGACAO_LOGICA", "NEGACAO_LOGICA2", "NEGACAO_MATEMATICA", "OU", "OUTROCASO", "PARA", "PARAMETRO", "PARAMETROS", "PARAMETROS_CHAMADA", "PARAM_MATRIZ", "PARAM_VETOR", "PI", "PRINCIPAL", "PROCEDIMENTO", "REAL_LITERAL", "REFERENCIA", "RETORNA", "RQUAD", "SE", "SENAO", "SENAO_SE", "SENAO_STMS", "SE_STMS", "STATEMENTS", "SUBTRACAO", "TAMANHO_TEXTO", "TERMO_VETOR", "TEXTO_LITERAL", "TIPO_CARACTER", "TIPO_IDENTIFICADOR", "TIPO_INTEIRO", "TIPO_LOGICO", "TIPO_REAL", "TIPO_TEXTO", "VETOR", "WHITESPACE", "'('", "')'", "':'", "';'", "'='", "'['", "'[]['", "']'", "'abs'", "'aleatorio'", "'algoritmo'", "'asciiCaracter'", "'ate'", "'caracterAscii'", "'caracterTexto'", "'comparaTexto'", "'convCaractTexto'", "'convTextoCaract'", "'copia'", "'de'", "'entao'", "'exp'", "'fimEnquanto'", "'fimEscolha'", "'fimPara'", "'fimPrincipal'", "'fimSe'", "'maiusculo'", "'maiusculoCaracter'", "'minusculo'", "'minusculoCaracter'", "'passo'", "'pi'", "'principal'", "'raizQuadrada'", "'senao'", "'senaoSe'", "'tamanhoTexto'", "'{'", "'}'"};
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
   protected TreeAdaptor adaptor;
   private boolean errors;
   public static int previousLineNumber = 0;
   public static final BitSet FOLLOW_111_in_algoritmo696 = new BitSet(new long[]{70368744177664L});
   public static final BitSet FOLLOW_IDENTIFICADOR_in_algoritmo698 = new BitSet(new long[]{0L, 1099511627776L});
   public static final BitSet FOLLOW_104_in_algoritmo700 = new BitSet(new long[]{0L, 0L, 64L});
   public static final BitSet FOLLOW_principal_in_algoritmo709 = new BitSet(new long[]{17592186044416L, 16384L});
   public static final BitSet FOLLOW_funcao_ou_procedimento_in_algoritmo718 = new BitSet(new long[]{17592186044416L, 16384L});
   public static final BitSet FOLLOW_EOF_in_algoritmo727 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_funcao_in_funcao_ou_procedimento772 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_procedimento_in_funcao_ou_procedimento776 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_FUNCAO_in_funcao801 = new BitSet(new long[]{0L, 32749125632L});
   public static final BitSet FOLLOW_tipo_dado_in_funcao803 = new BitSet(new long[]{70368744177664L});
   public static final BitSet FOLLOW_IDENTIFICADOR_in_funcao805 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_funcao807 = new BitSet(new long[]{0L, 307627098112L});
   public static final BitSet FOLLOW_decl_parametros_in_funcao809 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_funcao812 = new BitSet(new long[]{13019166860640256L, 32749781056L});
   public static final BitSet FOLLOW_corpo_in_funcao820 = new BitSet(new long[]{549755813888L});
   public static final BitSet FOLLOW_FIM_FUNCAO_in_funcao828 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_PROCEDIMENTO_in_procedimento876 = new BitSet(new long[]{70368744177664L});
   public static final BitSet FOLLOW_IDENTIFICADOR_in_procedimento878 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_procedimento880 = new BitSet(new long[]{0L, 307627098112L});
   public static final BitSet FOLLOW_decl_parametros_in_procedimento882 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_procedimento885 = new BitSet(new long[]{13019716616454144L, 32749781056L});
   public static final BitSet FOLLOW_corpo_in_procedimento893 = new BitSet(new long[]{1099511627776L});
   public static final BitSet FOLLOW_FIM_PROCEDIMENTO_in_procedimento902 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_134_in_principal948 = new BitSet(new long[]{13018617104826368L, 4611686051177168960L});
   public static final BitSet FOLLOW_corpo_in_principal956 = new BitSet(new long[]{0L, 4611686018427387904L});
   public static final BitSet FOLLOW_126_in_principal965 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_decl_variaveis_in_corpo1006 = new BitSet(new long[]{13018617104826370L, 32749781056L});
   public static final BitSet FOLLOW_mult_statements_in_corpo1015 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_statements_in_mult_statements1059 = new BitSet(new long[]{13018617104826370L, 655424L});
   public static final BitSet FOLLOW_facaStm_in_statements1086 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_enquantoStm_in_statements1111 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_paraStm_in_statements1132 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_seStm_in_statements1157 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_escolhaStm_in_statements1184 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_statementsSemiColon_in_statements1206 = new BitSet(new long[]{0L, 1099511627776L});
   public static final BitSet FOLLOW_104_in_statements1208 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_SE_in_seStm1227 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_seStm1229 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_seStm1231 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_seStm1233 = new BitSet(new long[]{0L, 144115188075855872L});
   public static final BitSet FOLLOW_121_in_seStm1235 = new BitSet(new long[]{13018617104826368L, -9223372036854120384L, 768L});
   public static final BitSet FOLLOW_mult_statements_in_seStm1243 = new BitSet(new long[]{0L, Long.MIN_VALUE, 768L});
   public static final BitSet FOLLOW_senaoStm_in_seStm1252 = new BitSet(new long[]{0L, Long.MIN_VALUE});
   public static final BitSet FOLLOW_127_in_seStm1261 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_137_in_senaoStm1343 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_senaoStm1345 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_senaoStm1347 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_senaoStm1349 = new BitSet(new long[]{0L, 144115188075855872L});
   public static final BitSet FOLLOW_121_in_senaoStm1351 = new BitSet(new long[]{13018617104826370L, 655424L, 768L});
   public static final BitSet FOLLOW_mult_statements_in_senaoStm1359 = new BitSet(new long[]{2L, 0L, 768L});
   public static final BitSet FOLLOW_senaoStm_in_senaoStm1362 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_136_in_senaoStm1403 = new BitSet(new long[]{13018617104826370L, 655424L});
   public static final BitSet FOLLOW_mult_statements_in_senaoStm1412 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_ESCOLHA_in_escolhaStm1454 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_escolhaStm1456 = new BitSet(new long[]{70368744177664L});
   public static final BitSet FOLLOW_IDENTIFICADOR_in_escolhaStm1458 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_escolhaStm1460 = new BitSet(new long[]{2048L});
   public static final BitSet FOLLOW_caso_in_escolhaStm1468 = new BitSet(new long[]{2048L, 1152921504606847008L});
   public static final BitSet FOLLOW_outrocaso_in_escolhaStm1477 = new BitSet(new long[]{0L, 1152921504606846976L});
   public static final BitSet FOLLOW_124_in_escolhaStm1486 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_CASO_in_caso1540 = new BitSet(new long[]{281474976743424L, 33554432L});
   public static final BitSet FOLLOW_caso_negativo_in_caso1543 = new BitSet(new long[]{13018617104826370L, 655424L});
   public static final BitSet FOLLOW_INTEIRO_LITERAL_in_caso1547 = new BitSet(new long[]{13018617104826370L, 655424L});
   public static final BitSet FOLLOW_CHAR_LITERAL_in_caso1551 = new BitSet(new long[]{13018617104826370L, 655424L});
   public static final BitSet FOLLOW_mult_statements_in_caso1560 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_SUBTRACAO_in_caso_negativo1613 = new BitSet(new long[]{281474976710656L});
   public static final BitSet FOLLOW_INTEIRO_LITERAL_in_caso_negativo1615 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_OUTROCASO_in_outrocaso1655 = new BitSet(new long[]{13018617104826370L, 655424L});
   public static final BitSet FOLLOW_mult_statements_in_outrocaso1663 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_PARA_in_paraStm1714 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_paraStm1716 = new BitSet(new long[]{70368744177664L});
   public static final BitSet FOLLOW_IDENTIFICADOR_in_paraStm1718 = new BitSet(new long[]{0L, 72057594037927936L});
   public static final BitSet FOLLOW_120_in_paraStm1720 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_paraStm1722 = new BitSet(new long[]{0L, 562949953421312L});
   public static final BitSet FOLLOW_113_in_paraStm1724 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_paraStm1726 = new BitSet(new long[]{0L, 0L, 16L});
   public static final BitSet FOLLOW_132_in_paraStm1728 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_paraStm1730 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_paraStm1732 = new BitSet(new long[]{274877906944L});
   public static final BitSet FOLLOW_FACA_in_paraStm1734 = new BitSet(new long[]{13018617104826368L, 2305843009214349376L});
   public static final BitSet FOLLOW_mult_statements_in_paraStm1742 = new BitSet(new long[]{0L, 2305843009213693952L});
   public static final BitSet FOLLOW_125_in_paraStm1751 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_ENQUANTO_in_enquantoStm1803 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_enquantoStm1805 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_enquantoStm1807 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_enquantoStm1809 = new BitSet(new long[]{274877906944L});
   public static final BitSet FOLLOW_FACA_in_enquantoStm1811 = new BitSet(new long[]{13018617104826368L, 576460752304078912L});
   public static final BitSet FOLLOW_mult_statements_in_enquantoStm1819 = new BitSet(new long[]{0L, 576460752303423488L});
   public static final BitSet FOLLOW_123_in_enquantoStm1828 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_FACA_in_facaStm1875 = new BitSet(new long[]{13018617104826368L, 655424L});
   public static final BitSet FOLLOW_corpo_facaStm_in_facaStm1877 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_ENQUANTO_in_corpo_facaStm1927 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_corpo_facaStm1929 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_corpo_facaStm1931 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_corpo_facaStm1933 = new BitSet(new long[]{0L, 1099511627776L});
   public static final BitSet FOLLOW_104_in_corpo_facaStm1935 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_statements_in_corpo_facaStm1969 = new BitSet(new long[]{13018617104826368L, 655424L});
   public static final BitSet FOLLOW_corpo_facaStm_in_corpo_facaStm1971 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_atribuicaoStm_in_statementsSemiColon1988 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_limpatela_in_statementsSemiColon1996 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_chamadaRotina_in_statementsSemiColon2008 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_lerStatement_in_statementsSemiColon2016 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_lerCaracterStatement_in_statementsSemiColon2025 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_escreverStatement_in_statementsSemiColon2033 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_interrompa_in_statementsSemiColon2042 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_retornaStm_in_statementsSemiColon2058 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_IDENTIFICADOR_in_atribuicaoStm2087 = new BitSet(new long[]{0L, 6597069766656L});
   public static final BitSet FOLLOW_106_in_atribuicaoStm2090 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_atribuicaoStm2094 = new BitSet(new long[]{0L, 17592186044416L});
   public static final BitSet FOLLOW_108_in_atribuicaoStm2096 = new BitSet(new long[]{0L, 6597069766656L});
   public static final BitSet FOLLOW_106_in_atribuicaoStm2099 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_atribuicaoStm2103 = new BitSet(new long[]{0L, 17592186044416L});
   public static final BitSet FOLLOW_108_in_atribuicaoStm2105 = new BitSet(new long[]{0L, 2199023255552L});
   public static final BitSet FOLLOW_105_in_atribuicaoStm2111 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_atribuicaoStm2115 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_LIMPA_TELA_in_limpatela2162 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_limpatela2164 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_limpatela2166 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_IDENTIFICADOR_in_chamadaRotina2200 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_chamadaRotina2202 = new BitSet(new long[]{351843720922144L, 359549510994657286L, 1199L});
   public static final BitSet FOLLOW_parametros_chamada_in_chamadaRotina2204 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_chamadaRotina2207 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_LEIA_in_lerStatement2242 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_lerStatement2244 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_lerStatement2246 = new BitSet(new long[]{1048576L, 274877906944L});
   public static final BitSet FOLLOW_CONCATENACAO_in_lerStatement2249 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_lerStatement2251 = new BitSet(new long[]{1048576L, 274877906944L});
   public static final BitSet FOLLOW_102_in_lerStatement2255 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_LEIA_CARACTER_in_lerCaracterStatement2294 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_lerCaracterStatement2296 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_lerCaracterStatement2298 = new BitSet(new long[]{1048576L, 274877906944L});
   public static final BitSet FOLLOW_CONCATENACAO_in_lerCaracterStatement2301 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_lerCaracterStatement2303 = new BitSet(new long[]{1048576L, 274877906944L});
   public static final BitSet FOLLOW_102_in_lerCaracterStatement2307 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_ESCREVA_in_escreverStatement2345 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_escreverStatement2347 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressaoEscreva_in_escreverStatement2349 = new BitSet(new long[]{1048576L, 274877906944L});
   public static final BitSet FOLLOW_CONCATENACAO_in_escreverStatement2352 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressaoEscreva_in_escreverStatement2354 = new BitSet(new long[]{1048576L, 274877906944L});
   public static final BitSet FOLLOW_102_in_escreverStatement2358 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_ESCREVAL_in_escreverStatement2384 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_escreverStatement2386 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressaoEscreva_in_escreverStatement2388 = new BitSet(new long[]{1048576L, 274877906944L});
   public static final BitSet FOLLOW_CONCATENACAO_in_escreverStatement2391 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressaoEscreva_in_escreverStatement2393 = new BitSet(new long[]{1048576L, 274877906944L});
   public static final BitSet FOLLOW_102_in_escreverStatement2397 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_expressao_in_expressaoEscreva2447 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_expressao_in_expressaoEscreva2465 = new BitSet(new long[]{0L, 549755813888L});
   public static final BitSet FOLLOW_103_in_expressaoEscreva2467 = new BitSet(new long[]{281474976710656L});
   public static final BitSet FOLLOW_INTEIRO_LITERAL_in_expressaoEscreva2471 = new BitSet(new long[]{2L, 549755813888L});
   public static final BitSet FOLLOW_103_in_expressaoEscreva2474 = new BitSet(new long[]{281474976710656L});
   public static final BitSet FOLLOW_INTEIRO_LITERAL_in_expressaoEscreva2478 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_expressao_in_expressaoEscreva2517 = new BitSet(new long[]{0L, 549755813888L});
   public static final BitSet FOLLOW_103_in_expressaoEscreva2519 = new BitSet(new long[]{0L, 549755813888L});
   public static final BitSet FOLLOW_103_in_expressaoEscreva2521 = new BitSet(new long[]{281474976710656L});
   public static final BitSet FOLLOW_INTEIRO_LITERAL_in_expressaoEscreva2525 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_INTERROMPA_in_interrompa2572 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_RETORNA_in_retornaStm2589 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_retornaStm2591 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_IDENTIFICADOR_in_termo2629 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_IDENTIFICADOR_in_termo2639 = new BitSet(new long[]{0L, 4398046511104L});
   public static final BitSet FOLLOW_106_in_termo2641 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_termo2643 = new BitSet(new long[]{0L, 17592186044416L});
   public static final BitSet FOLLOW_108_in_termo2645 = new BitSet(new long[]{2L, 4398046511104L});
   public static final BitSet FOLLOW_106_in_termo2648 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_termo2650 = new BitSet(new long[]{0L, 17592186044416L});
   public static final BitSet FOLLOW_108_in_termo2652 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_101_in_termo2677 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_termo2680 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_termo2682 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_chamadaRotina_in_termo2693 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_TEXTO_LITERAL_in_termo2703 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_CHAR_LITERAL_in_termo2713 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_BOOL_LITERAL_in_termo2723 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_INTEIRO_LITERAL_in_termo2733 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_REAL_LITERAL_in_termo2743 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_funcoesEmbutidas_in_termo2753 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_139_in_formataReal2774 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_formataReal2776 = new BitSet(new long[]{0L, 0L, 4096L});
   public static final BitSet FOLLOW_140_in_formataReal2778 = new BitSet(new long[]{0L, 549755813888L});
   public static final BitSet FOLLOW_103_in_formataReal2780 = new BitSet(new long[]{281474976710656L, 549755813888L});
   public static final BitSet FOLLOW_INTEIRO_LITERAL_in_formataReal2784 = new BitSet(new long[]{0L, 549755813888L});
   public static final BitSet FOLLOW_103_in_formataReal2787 = new BitSet(new long[]{281474976710658L});
   public static final BitSet FOLLOW_INTEIRO_LITERAL_in_formataReal2791 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_116_in_funcoesEmbutidas2850 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_funcoesEmbutidas2852 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas2854 = new BitSet(new long[]{1048576L});
   public static final BitSet FOLLOW_CONCATENACAO_in_funcoesEmbutidas2856 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas2858 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_funcoesEmbutidas2860 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_138_in_funcoesEmbutidas2880 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_funcoesEmbutidas2882 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas2884 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_funcoesEmbutidas2886 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_109_in_funcoesEmbutidas2904 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_funcoesEmbutidas2906 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas2908 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_funcoesEmbutidas2910 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_122_in_funcoesEmbutidas2928 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_funcoesEmbutidas2930 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas2932 = new BitSet(new long[]{1048576L});
   public static final BitSet FOLLOW_CONCATENACAO_in_funcoesEmbutidas2934 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas2936 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_funcoesEmbutidas2938 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_133_in_funcoesEmbutidas2958 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_funcoesEmbutidas2960 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_funcoesEmbutidas2962 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_135_in_funcoesEmbutidas2978 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_funcoesEmbutidas2980 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas2982 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_funcoesEmbutidas2984 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_110_in_funcoesEmbutidas3002 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_funcoesEmbutidas3004 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas3006 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_funcoesEmbutidas3008 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_128_in_funcoesEmbutidas3026 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_funcoesEmbutidas3028 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas3030 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_funcoesEmbutidas3032 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_130_in_funcoesEmbutidas3051 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_funcoesEmbutidas3053 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas3055 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_funcoesEmbutidas3057 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_119_in_funcoesEmbutidas3075 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_funcoesEmbutidas3077 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas3079 = new BitSet(new long[]{1048576L});
   public static final BitSet FOLLOW_CONCATENACAO_in_funcoesEmbutidas3081 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas3083 = new BitSet(new long[]{1048576L});
   public static final BitSet FOLLOW_CONCATENACAO_in_funcoesEmbutidas3085 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas3087 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_funcoesEmbutidas3089 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_129_in_funcoesEmbutidas3111 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_funcoesEmbutidas3113 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas3115 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_funcoesEmbutidas3117 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_131_in_funcoesEmbutidas3135 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_funcoesEmbutidas3137 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas3139 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_funcoesEmbutidas3141 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_112_in_funcoesEmbutidas3159 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_funcoesEmbutidas3161 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas3163 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_funcoesEmbutidas3165 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_114_in_funcoesEmbutidas3183 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_funcoesEmbutidas3185 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas3187 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_funcoesEmbutidas3189 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_115_in_funcoesEmbutidas3207 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_funcoesEmbutidas3209 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas3211 = new BitSet(new long[]{1048576L});
   public static final BitSet FOLLOW_CONCATENACAO_in_funcoesEmbutidas3213 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas3215 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_funcoesEmbutidas3217 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_118_in_funcoesEmbutidas3237 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_funcoesEmbutidas3239 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas3241 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_funcoesEmbutidas3243 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_117_in_funcoesEmbutidas3261 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_funcoesEmbutidas3263 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_funcoesEmbutidas3265 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_funcoesEmbutidas3267 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_NEGACAO_LOGICA_in_negacao3295 = new BitSet(new long[]{351843720922112L, 359549236083195910L, 1199L});
   public static final BitSet FOLLOW_NEGACAO_LOGICA2_in_negacao3300 = new BitSet(new long[]{351843720922112L, 359549236083195910L, 1199L});
   public static final BitSet FOLLOW_termo_in_negacao3305 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_ADICAO_in_unario3329 = new BitSet(new long[]{351843720922112L, 359549236083195910L, 1199L});
   public static final BitSet FOLLOW_negacao_matematica_in_unario3334 = new BitSet(new long[]{351843720922112L, 359549236083195910L, 1199L});
   public static final BitSet FOLLOW_negacao_in_unario3339 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_SUBTRACAO_in_negacao_matematica3360 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_unario_in_mult3386 = new BitSet(new long[]{-4611686016816775166L, 1L});
   public static final BitSet FOLLOW_MULTIPLICACAO_in_mult3390 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_DIVISAO_in_mult3395 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_DIVISAO_INT_in_mult3400 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_MOD_in_mult3405 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_MOD2_in_mult3410 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_unario_in_mult3414 = new BitSet(new long[]{-4611686016816775166L, 1L});
   public static final BitSet FOLLOW_mult_in_soma3439 = new BitSet(new long[]{34L, 33554432L});
   public static final BitSet FOLLOW_ADICAO_in_soma3443 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_SUBTRACAO_in_soma3448 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_mult_in_soma3452 = new BitSet(new long[]{34L, 33554432L});
   public static final BitSet FOLLOW_soma_in_comparacao3473 = new BitSet(new long[]{918875061606154242L});
   public static final BitSet FOLLOW_IGUAL_in_comparacao3477 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_DIFERENTE_in_comparacao3482 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_MENOR_in_comparacao3487 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_MENOR_IGUAL_in_comparacao3492 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_MAIOR_IGUAL_in_comparacao3497 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_MAIOR_in_comparacao3502 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_soma_in_comparacao3506 = new BitSet(new long[]{918875061606154242L});
   public static final BitSet FOLLOW_comparacao_in_expressao3534 = new BitSet(new long[]{2147483650L, 16L});
   public static final BitSet FOLLOW_E_in_expressao3538 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_OU_in_expressao3543 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_comparacao_in_expressao3547 = new BitSet(new long[]{2147483650L, 16L});
   public static final BitSet FOLLOW_decl_variavel_in_decl_variaveis3620 = new BitSet(new long[]{0L, 1099511627776L});
   public static final BitSet FOLLOW_104_in_decl_variaveis3623 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_tipo_dado_in_decl_variavel3646 = new BitSet(new long[]{70368744177664L});
   public static final BitSet FOLLOW_identificadores_in_decl_variavel3648 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_IDENTIFICADOR_in_identificadores3689 = new BitSet(new long[]{1048578L, 4398046511104L});
   public static final BitSet FOLLOW_106_in_identificadores3693 = new BitSet(new long[]{281474976710656L});
   public static final BitSet FOLLOW_INTEIRO_LITERAL_in_identificadores3697 = new BitSet(new long[]{0L, 17592186044416L});
   public static final BitSet FOLLOW_108_in_identificadores3699 = new BitSet(new long[]{1048578L});
   public static final BitSet FOLLOW_106_in_identificadores3705 = new BitSet(new long[]{281474976710656L});
   public static final BitSet FOLLOW_INTEIRO_LITERAL_in_identificadores3709 = new BitSet(new long[]{0L, 17592186044416L});
   public static final BitSet FOLLOW_108_in_identificadores3711 = new BitSet(new long[]{0L, 4398046511104L});
   public static final BitSet FOLLOW_106_in_identificadores3713 = new BitSet(new long[]{281474976710656L});
   public static final BitSet FOLLOW_INTEIRO_LITERAL_in_identificadores3717 = new BitSet(new long[]{0L, 17592186044416L});
   public static final BitSet FOLLOW_108_in_identificadores3719 = new BitSet(new long[]{1048578L});
   public static final BitSet FOLLOW_CONCATENACAO_in_identificadores3725 = new BitSet(new long[]{70368744177664L});
   public static final BitSet FOLLOW_identificadores_in_identificadores3727 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_tipo_dado_in_tipo_identificador3776 = new BitSet(new long[]{70368744177664L});
   public static final BitSet FOLLOW_IDENTIFICADOR_in_tipo_identificador3778 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_decl_parametro_in_decl_parametros3821 = new BitSet(new long[]{1048578L});
   public static final BitSet FOLLOW_CONCATENACAO_in_decl_parametros3824 = new BitSet(new long[]{0L, 32749191168L});
   public static final BitSet FOLLOW_decl_parametro_in_decl_parametros3826 = new BitSet(new long[]{1048578L});
   public static final BitSet FOLLOW_tipo_identificador_in_decl_parametro3867 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_REFERENCIA_in_decl_parametro3883 = new BitSet(new long[]{0L, 32749125632L});
   public static final BitSet FOLLOW_tipo_identificador_in_decl_parametro3885 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_tipo_identificador_in_decl_parametro3903 = new BitSet(new long[]{0L, 8796093022208L});
   public static final BitSet FOLLOW_107_in_decl_parametro3905 = new BitSet(new long[]{281474976710656L});
   public static final BitSet FOLLOW_INTEIRO_LITERAL_in_decl_parametro3907 = new BitSet(new long[]{0L, 17592186044416L});
   public static final BitSet FOLLOW_108_in_decl_parametro3909 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_tipo_identificador_in_decl_parametro3929 = new BitSet(new long[]{0L, 2048L});
   public static final BitSet FOLLOW_PARAM_VETOR_in_decl_parametro3931 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_expressao_in_parametros_chamada3965 = new BitSet(new long[]{1048578L});
   public static final BitSet FOLLOW_CONCATENACAO_in_parametros_chamada3968 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_parametros_chamada3970 = new BitSet(new long[]{1048578L});
   public static final BitSet FOLLOW_137_in_synpred1_CalangoGrammar1312 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_synpred1_CalangoGrammar1314 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_synpred1_CalangoGrammar1316 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_synpred1_CalangoGrammar1318 = new BitSet(new long[]{0L, 144115188075855872L});
   public static final BitSet FOLLOW_121_in_synpred1_CalangoGrammar1320 = new BitSet(new long[]{13018617104826370L, 655424L, 768L});
   public static final BitSet FOLLOW_mult_statements_in_synpred1_CalangoGrammar1328 = new BitSet(new long[]{2L, 0L, 768L});
   public static final BitSet FOLLOW_senaoStm_in_synpred1_CalangoGrammar1331 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_ENQUANTO_in_synpred2_CalangoGrammar1914 = new BitSet(new long[]{0L, 137438953472L});
   public static final BitSet FOLLOW_101_in_synpred2_CalangoGrammar1916 = new BitSet(new long[]{351843720922144L, 359549236116750342L, 1199L});
   public static final BitSet FOLLOW_expressao_in_synpred2_CalangoGrammar1918 = new BitSet(new long[]{0L, 274877906944L});
   public static final BitSet FOLLOW_102_in_synpred2_CalangoGrammar1920 = new BitSet(new long[]{0L, 1099511627776L});
   public static final BitSet FOLLOW_104_in_synpred2_CalangoGrammar1922 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_expressao_in_synpred3_CalangoGrammar2437 = new BitSet(new long[]{1048576L, 274877906944L});
   public static final BitSet FOLLOW_set_in_synpred3_CalangoGrammar2439 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_expressao_in_synpred4_CalangoGrammar2457 = new BitSet(new long[]{0L, 549755813888L});
   public static final BitSet FOLLOW_103_in_synpred4_CalangoGrammar2459 = new BitSet(new long[]{281474976710656L});
   public static final BitSet FOLLOW_INTEIRO_LITERAL_in_synpred4_CalangoGrammar2461 = new BitSet(new long[]{2L});

   public Parser[] getDelegates() {
      return new Parser[0];
   }

   public CalangoGrammarParser(TokenStream input) {
      this(input, new RecognizerSharedState());
   }

   public CalangoGrammarParser(TokenStream input, RecognizerSharedState state) {
      super(input, state);
      this.adaptor = new CommonTreeAdaptor();
      this.errors = false;
   }

   public void setTreeAdaptor(TreeAdaptor adaptor) {
      this.adaptor = adaptor;
   }

   public TreeAdaptor getTreeAdaptor() {
      return this.adaptor;
   }

   public String[] getTokenNames() {
      return tokenNames;
   }

   public String getGrammarFileName() {
      return "/media/Dados/Desenvolvimento/Workspace/Java/workspaceCalango/calango/interpretador/br/ucb/calango/gramatica/CalangoGrammar.g";
   }

   public void emitErrorMessage(String msg) {
      this.errors = true;
      CalangoAPI.printErro(-1, msg);
   }

   public String getErrorMessage(RecognitionException e, String[] tokenNames) {
      if (e instanceof MissingTokenException && ((MissingTokenException)e).expecting == -1) {
         e = new ComandoForaDoContextoException();
      }

      return MensagensUtil.getMessage(e.getClass(), ErrosUtil.getMessageParams((RecognitionException)e));
   }

   public boolean hasErrors() {
      return this.errors;
   }

   private CommonToken createToken(String text, int type, int line) {
      CommonToken t = new CommonToken(type, text);
      t.setLine(line);
      return t;
   }

   public final CalangoGrammarParser.algoritmo_return algoritmo() throws RecognitionException {
      CalangoGrammarParser.algoritmo_return retval = new CalangoGrammarParser.algoritmo_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token string_literal1 = null;
      Token IDENTIFICADOR2 = null;
      Token char_literal3 = null;
      Token EOF6 = null;
      CalangoGrammarParser.principal_return principal4 = null;
      CalangoGrammarParser.funcao_ou_procedimento_return funcao_ou_procedimento5 = null;
      CommonTree string_literal1_tree = null;
      CommonTree IDENTIFICADOR2_tree = null;
      CommonTree char_literal3_tree = null;
      CommonTree EOF6_tree = null;
      RewriteRuleTokenStream stream_111 = new RewriteRuleTokenStream(this.adaptor, "token 111");
      RewriteRuleTokenStream stream_EOF = new RewriteRuleTokenStream(this.adaptor, "token EOF");
      RewriteRuleTokenStream stream_104 = new RewriteRuleTokenStream(this.adaptor, "token 104");
      RewriteRuleTokenStream stream_IDENTIFICADOR = new RewriteRuleTokenStream(this.adaptor, "token IDENTIFICADOR");
      RewriteRuleSubtreeStream stream_principal = new RewriteRuleSubtreeStream(this.adaptor, "rule principal");
      RewriteRuleSubtreeStream stream_funcao_ou_procedimento = new RewriteRuleSubtreeStream(this.adaptor, "rule funcao_ou_procedimento");

      try {
         string_literal1 = (Token)this.match(this.input, 111, FOLLOW_111_in_algoritmo696);
         if (this.state.failed) {
            return retval;
         } else {
            if (this.state.backtracking == 0) {
               stream_111.add(string_literal1);
            }

            IDENTIFICADOR2 = (Token)this.match(this.input, 46, FOLLOW_IDENTIFICADOR_in_algoritmo698);
            if (this.state.failed) {
               return retval;
            } else {
               if (this.state.backtracking == 0) {
                  stream_IDENTIFICADOR.add(IDENTIFICADOR2);
               }

               char_literal3 = (Token)this.match(this.input, 104, FOLLOW_104_in_algoritmo700);
               if (this.state.failed) {
                  return retval;
               } else {
                  if (this.state.backtracking == 0) {
                     stream_104.add(char_literal3);
                  }

                  this.pushFollow(FOLLOW_principal_in_algoritmo709);
                  principal4 = this.principal();
                  --this.state._fsp;
                  if (this.state.failed) {
                     return retval;
                  } else {
                     if (this.state.backtracking == 0) {
                        stream_principal.add(principal4.getTree());
                     }

                     while(true) {
                        int alt1 = 2;
                        int LA1_0 = this.input.LA(1);
                        if (LA1_0 == 44 || LA1_0 == 78) {
                           alt1 = 1;
                        }

                        switch(alt1) {
                        case 1:
                           this.pushFollow(FOLLOW_funcao_ou_procedimento_in_algoritmo718);
                           funcao_ou_procedimento5 = this.funcao_ou_procedimento();
                           --this.state._fsp;
                           if (this.state.failed) {
                              return retval;
                           }

                           if (this.state.backtracking == 0) {
                              stream_funcao_ou_procedimento.add(funcao_ou_procedimento5.getTree());
                           }
                           break;
                        default:
                           EOF6 = (Token)this.match(this.input, -1, FOLLOW_EOF_in_algoritmo727);
                           if (this.state.failed) {
                              return retval;
                           }

                           if (this.state.backtracking == 0) {
                              stream_EOF.add(EOF6);
                           }

                           if (this.state.backtracking == 0) {
                              retval.tree = root_0;
                              new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                              root_0 = (CommonTree)this.adaptor.nil();
                              CommonTree root_1 = (CommonTree)this.adaptor.nil();
                              root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(7, (String)"ALGORITMO")), root_1);

                              while(stream_funcao_ou_procedimento.hasNext()) {
                                 this.adaptor.addChild(root_1, stream_funcao_ou_procedimento.nextTree());
                              }

                              stream_funcao_ou_procedimento.reset();
                              this.adaptor.addChild(root_1, stream_principal.nextTree());
                              this.adaptor.addChild(root_1, stream_EOF.nextNode());
                              this.adaptor.addChild(root_0, root_1);
                              retval.tree = root_0;
                           }

                           retval.stop = this.input.LT(-1);
                           if (this.state.backtracking == 0) {
                              retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                              this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                           }

                           return retval;
                        }
                     }
                  }
               }
            }
         }
      } catch (RecognitionException var21) {
         this.reportError(var21);
         this.recover(this.input, var21);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var21);
         return retval;
      }
   }

   public final CalangoGrammarParser.funcao_ou_procedimento_return funcao_ou_procedimento() throws RecognitionException {
      CalangoGrammarParser.funcao_ou_procedimento_return retval = new CalangoGrammarParser.funcao_ou_procedimento_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CalangoGrammarParser.funcao_return funcao7 = null;
      CalangoGrammarParser.procedimento_return procedimento8 = null;

      try {
         root_0 = (CommonTree)this.adaptor.nil();
         int LA2_0 = this.input.LA(1);
         byte alt2;
         if (LA2_0 == 44) {
            alt2 = 1;
         } else {
            if (LA2_0 != 78) {
               if (this.state.backtracking > 0) {
                  this.state.failed = true;
                  return retval;
               }

               NoViableAltException nvae = new NoViableAltException("", 2, 0, this.input);
               throw nvae;
            }

            alt2 = 2;
         }

         switch(alt2) {
         case 1:
            this.pushFollow(FOLLOW_funcao_in_funcao_ou_procedimento772);
            funcao7 = this.funcao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, funcao7.getTree());
            }
            break;
         case 2:
            this.pushFollow(FOLLOW_procedimento_in_funcao_ou_procedimento776);
            procedimento8 = this.procedimento();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, procedimento8.getTree());
            }
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var8) {
         this.reportError(var8);
         this.recover(this.input, var8);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var8);
      }

      return retval;
   }

   public final CalangoGrammarParser.funcao_return funcao() throws RecognitionException {
      CalangoGrammarParser.funcao_return retval = new CalangoGrammarParser.funcao_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token FUNCAO9 = null;
      Token IDENTIFICADOR11 = null;
      Token char_literal12 = null;
      Token char_literal14 = null;
      Token FIM_FUNCAO16 = null;
      CalangoGrammarParser.tipo_dado_return tipo_dado10 = null;
      CalangoGrammarParser.decl_parametros_return decl_parametros13 = null;
      CalangoGrammarParser.corpo_return corpo15 = null;
      CommonTree FUNCAO9_tree = null;
      CommonTree IDENTIFICADOR11_tree = null;
      CommonTree char_literal12_tree = null;
      CommonTree char_literal14_tree = null;
      CommonTree FIM_FUNCAO16_tree = null;
      RewriteRuleTokenStream stream_FIM_FUNCAO = new RewriteRuleTokenStream(this.adaptor, "token FIM_FUNCAO");
      RewriteRuleTokenStream stream_FUNCAO = new RewriteRuleTokenStream(this.adaptor, "token FUNCAO");
      RewriteRuleTokenStream stream_102 = new RewriteRuleTokenStream(this.adaptor, "token 102");
      RewriteRuleTokenStream stream_101 = new RewriteRuleTokenStream(this.adaptor, "token 101");
      RewriteRuleTokenStream stream_IDENTIFICADOR = new RewriteRuleTokenStream(this.adaptor, "token IDENTIFICADOR");
      RewriteRuleSubtreeStream stream_corpo = new RewriteRuleSubtreeStream(this.adaptor, "rule corpo");
      RewriteRuleSubtreeStream stream_tipo_dado = new RewriteRuleSubtreeStream(this.adaptor, "rule tipo_dado");
      RewriteRuleSubtreeStream stream_decl_parametros = new RewriteRuleSubtreeStream(this.adaptor, "rule decl_parametros");

      try {
         FUNCAO9 = (Token)this.match(this.input, 44, FOLLOW_FUNCAO_in_funcao801);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_FUNCAO.add(FUNCAO9);
         }

         this.pushFollow(FOLLOW_tipo_dado_in_funcao803);
         tipo_dado10 = this.tipo_dado();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_tipo_dado.add(tipo_dado10.getTree());
         }

         IDENTIFICADOR11 = (Token)this.match(this.input, 46, FOLLOW_IDENTIFICADOR_in_funcao805);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_IDENTIFICADOR.add(IDENTIFICADOR11);
         }

         char_literal12 = (Token)this.match(this.input, 101, FOLLOW_101_in_funcao807);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_101.add(char_literal12);
         }

         int alt3 = 2;
         int LA3_0 = this.input.LA(1);
         if (LA3_0 == 80 || LA3_0 == 93 || LA3_0 >= 95 && LA3_0 <= 98) {
            alt3 = 1;
         }

         switch(alt3) {
         case 1:
            this.pushFollow(FOLLOW_decl_parametros_in_funcao809);
            decl_parametros13 = this.decl_parametros();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_decl_parametros.add(decl_parametros13.getTree());
            }
         default:
            char_literal14 = (Token)this.match(this.input, 102, FOLLOW_102_in_funcao812);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal14);
            }

            this.pushFollow(FOLLOW_corpo_in_funcao820);
            corpo15 = this.corpo();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_corpo.add(corpo15.getTree());
            }

            FIM_FUNCAO16 = (Token)this.match(this.input, 39, FOLLOW_FIM_FUNCAO_in_funcao828);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_FIM_FUNCAO.add(FIM_FUNCAO16);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               CommonTree root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)stream_FUNCAO.nextNode(), root_1);
               this.adaptor.addChild(root_1, stream_tipo_dado.nextTree());
               this.adaptor.addChild(root_1, stream_IDENTIFICADOR.nextNode());
               if (stream_decl_parametros.hasNext()) {
                  this.adaptor.addChild(root_1, stream_decl_parametros.nextTree());
               }

               stream_decl_parametros.reset();
               this.adaptor.addChild(root_1, stream_corpo.nextTree());
               this.adaptor.addChild(root_1, stream_FIM_FUNCAO.nextNode());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }

            retval.stop = this.input.LT(-1);
            if (this.state.backtracking == 0) {
               retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
               this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
         }
      } catch (RecognitionException var28) {
         this.reportError(var28);
         this.recover(this.input, var28);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var28);
      }

      return retval;
   }

   public final CalangoGrammarParser.procedimento_return procedimento() throws RecognitionException {
      CalangoGrammarParser.procedimento_return retval = new CalangoGrammarParser.procedimento_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token PROCEDIMENTO17 = null;
      Token IDENTIFICADOR18 = null;
      Token char_literal19 = null;
      Token char_literal21 = null;
      Token FIM_PROCEDIMENTO23 = null;
      CalangoGrammarParser.decl_parametros_return decl_parametros20 = null;
      CalangoGrammarParser.corpo_return corpo22 = null;
      CommonTree PROCEDIMENTO17_tree = null;
      CommonTree IDENTIFICADOR18_tree = null;
      CommonTree char_literal19_tree = null;
      CommonTree char_literal21_tree = null;
      CommonTree FIM_PROCEDIMENTO23_tree = null;
      RewriteRuleTokenStream stream_PROCEDIMENTO = new RewriteRuleTokenStream(this.adaptor, "token PROCEDIMENTO");
      RewriteRuleTokenStream stream_FIM_PROCEDIMENTO = new RewriteRuleTokenStream(this.adaptor, "token FIM_PROCEDIMENTO");
      RewriteRuleTokenStream stream_102 = new RewriteRuleTokenStream(this.adaptor, "token 102");
      RewriteRuleTokenStream stream_101 = new RewriteRuleTokenStream(this.adaptor, "token 101");
      RewriteRuleTokenStream stream_IDENTIFICADOR = new RewriteRuleTokenStream(this.adaptor, "token IDENTIFICADOR");
      RewriteRuleSubtreeStream stream_corpo = new RewriteRuleSubtreeStream(this.adaptor, "rule corpo");
      RewriteRuleSubtreeStream stream_decl_parametros = new RewriteRuleSubtreeStream(this.adaptor, "rule decl_parametros");

      try {
         PROCEDIMENTO17 = (Token)this.match(this.input, 78, FOLLOW_PROCEDIMENTO_in_procedimento876);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_PROCEDIMENTO.add(PROCEDIMENTO17);
         }

         IDENTIFICADOR18 = (Token)this.match(this.input, 46, FOLLOW_IDENTIFICADOR_in_procedimento878);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_IDENTIFICADOR.add(IDENTIFICADOR18);
         }

         char_literal19 = (Token)this.match(this.input, 101, FOLLOW_101_in_procedimento880);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_101.add(char_literal19);
         }

         int alt4 = 2;
         int LA4_0 = this.input.LA(1);
         if (LA4_0 == 80 || LA4_0 == 93 || LA4_0 >= 95 && LA4_0 <= 98) {
            alt4 = 1;
         }

         switch(alt4) {
         case 1:
            this.pushFollow(FOLLOW_decl_parametros_in_procedimento882);
            decl_parametros20 = this.decl_parametros();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_decl_parametros.add(decl_parametros20.getTree());
            }
         default:
            char_literal21 = (Token)this.match(this.input, 102, FOLLOW_102_in_procedimento885);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal21);
            }

            this.pushFollow(FOLLOW_corpo_in_procedimento893);
            corpo22 = this.corpo();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_corpo.add(corpo22.getTree());
            }

            FIM_PROCEDIMENTO23 = (Token)this.match(this.input, 40, FOLLOW_FIM_PROCEDIMENTO_in_procedimento902);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_FIM_PROCEDIMENTO.add(FIM_PROCEDIMENTO23);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               CommonTree root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)stream_PROCEDIMENTO.nextNode(), root_1);
               this.adaptor.addChild(root_1, stream_IDENTIFICADOR.nextNode());
               if (stream_decl_parametros.hasNext()) {
                  this.adaptor.addChild(root_1, stream_decl_parametros.nextTree());
               }

               stream_decl_parametros.reset();
               this.adaptor.addChild(root_1, stream_corpo.nextTree());
               this.adaptor.addChild(root_1, stream_FIM_PROCEDIMENTO.nextNode());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }

            retval.stop = this.input.LT(-1);
            if (this.state.backtracking == 0) {
               retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
               this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
         }
      } catch (RecognitionException var26) {
         this.reportError(var26);
         this.recover(this.input, var26);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var26);
      }

      return retval;
   }

   public final CalangoGrammarParser.principal_return principal() throws RecognitionException {
      CalangoGrammarParser.principal_return retval = new CalangoGrammarParser.principal_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token string_literal24 = null;
      Token string_literal26 = null;
      CalangoGrammarParser.corpo_return corpo25 = null;
      CommonTree string_literal24_tree = null;
      CommonTree string_literal26_tree = null;
      RewriteRuleTokenStream stream_134 = new RewriteRuleTokenStream(this.adaptor, "token 134");
      RewriteRuleTokenStream stream_126 = new RewriteRuleTokenStream(this.adaptor, "token 126");
      RewriteRuleSubtreeStream stream_corpo = new RewriteRuleSubtreeStream(this.adaptor, "rule corpo");

      try {
         string_literal24 = (Token)this.match(this.input, 134, FOLLOW_134_in_principal948);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_134.add(string_literal24);
         }

         this.pushFollow(FOLLOW_corpo_in_principal956);
         corpo25 = this.corpo();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_corpo.add(corpo25.getTree());
         }

         string_literal26 = (Token)this.match(this.input, 126, FOLLOW_126_in_principal965);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_126.add(string_literal26);
         }

         if (this.state.backtracking == 0) {
            retval.tree = root_0;
            new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
            root_0 = (CommonTree)this.adaptor.nil();
            CommonTree root_1 = (CommonTree)this.adaptor.nil();
            root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(77, (String)"PRINCIPAL")), root_1);
            this.adaptor.addChild(root_1, stream_corpo.nextTree());
            this.adaptor.addChild(root_0, root_1);
            retval.tree = root_0;
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var13) {
         this.reportError(var13);
         this.recover(this.input, var13);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var13);
      }

      return retval;
   }

   public final CalangoGrammarParser.corpo_return corpo() throws RecognitionException {
      CalangoGrammarParser.corpo_return retval = new CalangoGrammarParser.corpo_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CalangoGrammarParser.decl_variaveis_return decl_variaveis27 = null;
      CalangoGrammarParser.mult_statements_return mult_statements28 = null;
      RewriteRuleSubtreeStream stream_mult_statements = new RewriteRuleSubtreeStream(this.adaptor, "rule mult_statements");
      RewriteRuleSubtreeStream stream_decl_variaveis = new RewriteRuleSubtreeStream(this.adaptor, "rule decl_variaveis");

      try {
         while(true) {
            int alt6 = 2;
            int LA6_0 = this.input.LA(1);
            if (LA6_0 == 93 || LA6_0 >= 95 && LA6_0 <= 98) {
               alt6 = 1;
            }

            switch(alt6) {
            case 1:
               this.pushFollow(FOLLOW_decl_variaveis_in_corpo1006);
               decl_variaveis27 = this.decl_variaveis();
               --this.state._fsp;
               if (this.state.failed) {
                  return retval;
               }

               if (this.state.backtracking == 0) {
                  stream_decl_variaveis.add(decl_variaveis27.getTree());
               }
               break;
            default:
               alt6 = 2;
               LA6_0 = this.input.LA(1);
               if (LA6_0 == 32 || LA6_0 >= 34 && LA6_0 <= 36 || LA6_0 == 38 || LA6_0 == 46 || LA6_0 >= 49 && LA6_0 <= 51 || LA6_0 == 53 || LA6_0 == 70 || LA6_0 == 81 || LA6_0 == 83) {
                  alt6 = 1;
               }

               switch(alt6) {
               case 1:
                  this.pushFollow(FOLLOW_mult_statements_in_corpo1015);
                  mult_statements28 = this.mult_statements();
                  --this.state._fsp;
                  if (this.state.failed) {
                     return retval;
                  }

                  if (this.state.backtracking == 0) {
                     stream_mult_statements.add(mult_statements28.getTree());
                  }
               }

               if (this.state.backtracking == 0) {
                  retval.tree = root_0;
                  new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                  root_0 = (CommonTree)this.adaptor.nil();
                  CommonTree root_1 = (CommonTree)this.adaptor.nil();
                  root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(24, (String)"CORPO")), root_1);

                  while(stream_decl_variaveis.hasNext()) {
                     this.adaptor.addChild(root_1, stream_decl_variaveis.nextTree());
                  }

                  stream_decl_variaveis.reset();
                  if (stream_mult_statements.hasNext()) {
                     this.adaptor.addChild(root_1, stream_mult_statements.nextTree());
                  }

                  stream_mult_statements.reset();
                  this.adaptor.addChild(root_0, root_1);
                  retval.tree = root_0;
               }

               retval.stop = this.input.LT(-1);
               if (this.state.backtracking == 0) {
                  retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                  this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
               }

               return retval;
            }
         }
      } catch (RecognitionException var11) {
         this.reportError(var11);
         this.recover(this.input, var11);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var11);
         return retval;
      }
   }

   public final CalangoGrammarParser.mult_statements_return mult_statements() throws RecognitionException {
      CalangoGrammarParser.mult_statements_return retval = new CalangoGrammarParser.mult_statements_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CalangoGrammarParser.statements_return statements29 = null;
      RewriteRuleSubtreeStream stream_statements = new RewriteRuleSubtreeStream(this.adaptor, "rule statements");

      try {
         int cnt7 = 0;

         while(true) {
            int alt7 = 2;
            int LA7_0 = this.input.LA(1);
            if (LA7_0 == 32 || LA7_0 >= 34 && LA7_0 <= 36 || LA7_0 == 38 || LA7_0 == 46 || LA7_0 >= 49 && LA7_0 <= 51 || LA7_0 == 53 || LA7_0 == 70 || LA7_0 == 81 || LA7_0 == 83) {
               alt7 = 1;
            }

            switch(alt7) {
            case 1:
               this.pushFollow(FOLLOW_statements_in_mult_statements1059);
               statements29 = this.statements();
               --this.state._fsp;
               if (this.state.failed) {
                  return retval;
               }

               if (this.state.backtracking == 0) {
                  stream_statements.add(statements29.getTree());
               }

               ++cnt7;
               break;
            default:
               if (cnt7 < 1) {
                  if (this.state.backtracking > 0) {
                     this.state.failed = true;
                     return retval;
                  }

                  EarlyExitException eee = new EarlyExitException(7, this.input);
                  throw eee;
               }

               if (this.state.backtracking == 0) {
                  retval.tree = root_0;
                  new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                  root_0 = (CommonTree)this.adaptor.nil();
                  CommonTree root_1 = (CommonTree)this.adaptor.nil();
                  root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(88, (String)"STATEMENTS")), root_1);
                  if (!stream_statements.hasNext()) {
                     throw new RewriteEarlyExitException();
                  }

                  while(stream_statements.hasNext()) {
                     this.adaptor.addChild(root_1, stream_statements.nextTree());
                  }

                  stream_statements.reset();
                  this.adaptor.addChild(root_0, root_1);
                  retval.tree = root_0;
               }

               retval.stop = this.input.LT(-1);
               if (this.state.backtracking == 0) {
                  retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                  this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
               }

               return retval;
            }
         }
      } catch (RecognitionException var9) {
         this.reportError(var9);
         this.recover(this.input, var9);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var9);
         return retval;
      }
   }

   public final CalangoGrammarParser.statements_return statements() throws RecognitionException {
      CalangoGrammarParser.statements_return retval = new CalangoGrammarParser.statements_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token char_literal36 = null;
      CalangoGrammarParser.facaStm_return facaStm30 = null;
      CalangoGrammarParser.enquantoStm_return enquantoStm31 = null;
      CalangoGrammarParser.paraStm_return paraStm32 = null;
      CalangoGrammarParser.seStm_return seStm33 = null;
      CalangoGrammarParser.escolhaStm_return escolhaStm34 = null;
      CalangoGrammarParser.statementsSemiColon_return statementsSemiColon35 = null;
      Object var10 = null;

      try {
         byte alt8;
         switch(this.input.LA(1)) {
         case 32:
            alt8 = 2;
            break;
         case 34:
            alt8 = 5;
            break;
         case 35:
         case 36:
         case 46:
         case 49:
         case 50:
         case 51:
         case 53:
         case 81:
            alt8 = 6;
            break;
         case 38:
            alt8 = 1;
            break;
         case 70:
            alt8 = 3;
            break;
         case 83:
            alt8 = 4;
            break;
         default:
            if (this.state.backtracking > 0) {
               this.state.failed = true;
               return retval;
            }

            NoViableAltException nvae = new NoViableAltException("", 8, 0, this.input);
            throw nvae;
         }

         switch(alt8) {
         case 1:
            root_0 = (CommonTree)this.adaptor.nil();
            this.pushFollow(FOLLOW_facaStm_in_statements1086);
            facaStm30 = this.facaStm();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, facaStm30.getTree());
            }
            break;
         case 2:
            root_0 = (CommonTree)this.adaptor.nil();
            this.pushFollow(FOLLOW_enquantoStm_in_statements1111);
            enquantoStm31 = this.enquantoStm();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, enquantoStm31.getTree());
            }
            break;
         case 3:
            root_0 = (CommonTree)this.adaptor.nil();
            this.pushFollow(FOLLOW_paraStm_in_statements1132);
            paraStm32 = this.paraStm();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, paraStm32.getTree());
            }
            break;
         case 4:
            root_0 = (CommonTree)this.adaptor.nil();
            this.pushFollow(FOLLOW_seStm_in_statements1157);
            seStm33 = this.seStm();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, seStm33.getTree());
            }
            break;
         case 5:
            root_0 = (CommonTree)this.adaptor.nil();
            this.pushFollow(FOLLOW_escolhaStm_in_statements1184);
            escolhaStm34 = this.escolhaStm();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, escolhaStm34.getTree());
            }
            break;
         case 6:
            root_0 = (CommonTree)this.adaptor.nil();
            this.pushFollow(FOLLOW_statementsSemiColon_in_statements1206);
            statementsSemiColon35 = this.statementsSemiColon();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, statementsSemiColon35.getTree());
            }

            char_literal36 = (Token)this.match(this.input, 104, FOLLOW_104_in_statements1208);
            if (this.state.failed) {
               return retval;
            }
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var13) {
         this.reportError(var13);
         this.recover(this.input, var13);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var13);
      }

      return retval;
   }

   public final CalangoGrammarParser.seStm_return seStm() throws RecognitionException {
      CalangoGrammarParser.seStm_return retval = new CalangoGrammarParser.seStm_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token string_literal37 = null;
      Token char_literal38 = null;
      Token char_literal40 = null;
      Token string_literal41 = null;
      Token string_literal44 = null;
      CalangoGrammarParser.expressao_return expressao39 = null;
      CalangoGrammarParser.mult_statements_return mult_statements42 = null;
      CalangoGrammarParser.senaoStm_return senaoStm43 = null;
      CommonTree string_literal37_tree = null;
      CommonTree char_literal38_tree = null;
      CommonTree char_literal40_tree = null;
      CommonTree string_literal41_tree = null;
      CommonTree string_literal44_tree = null;
      RewriteRuleTokenStream stream_127 = new RewriteRuleTokenStream(this.adaptor, "token 127");
      RewriteRuleTokenStream stream_SE = new RewriteRuleTokenStream(this.adaptor, "token SE");
      RewriteRuleTokenStream stream_121 = new RewriteRuleTokenStream(this.adaptor, "token 121");
      RewriteRuleTokenStream stream_102 = new RewriteRuleTokenStream(this.adaptor, "token 102");
      RewriteRuleTokenStream stream_101 = new RewriteRuleTokenStream(this.adaptor, "token 101");
      RewriteRuleSubtreeStream stream_mult_statements = new RewriteRuleSubtreeStream(this.adaptor, "rule mult_statements");
      RewriteRuleSubtreeStream stream_senaoStm = new RewriteRuleSubtreeStream(this.adaptor, "rule senaoStm");
      RewriteRuleSubtreeStream stream_expressao = new RewriteRuleSubtreeStream(this.adaptor, "rule expressao");

      try {
         string_literal37 = (Token)this.match(this.input, 83, FOLLOW_SE_in_seStm1227);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_SE.add(string_literal37);
         }

         char_literal38 = (Token)this.match(this.input, 101, FOLLOW_101_in_seStm1229);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_101.add(char_literal38);
         }

         this.pushFollow(FOLLOW_expressao_in_seStm1231);
         expressao39 = this.expressao();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_expressao.add(expressao39.getTree());
         }

         char_literal40 = (Token)this.match(this.input, 102, FOLLOW_102_in_seStm1233);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_102.add(char_literal40);
         }

         string_literal41 = (Token)this.match(this.input, 121, FOLLOW_121_in_seStm1235);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_121.add(string_literal41);
         }

         int alt9 = 2;
         int LA9_0 = this.input.LA(1);
         if (LA9_0 == 32 || LA9_0 >= 34 && LA9_0 <= 36 || LA9_0 == 38 || LA9_0 == 46 || LA9_0 >= 49 && LA9_0 <= 51 || LA9_0 == 53 || LA9_0 == 70 || LA9_0 == 81 || LA9_0 == 83) {
            alt9 = 1;
         }

         switch(alt9) {
         case 1:
            this.pushFollow(FOLLOW_mult_statements_in_seStm1243);
            mult_statements42 = this.mult_statements();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_mult_statements.add(mult_statements42.getTree());
            }
         default:
            int alt10 = 2;
            int LA10_0 = this.input.LA(1);
            if (LA10_0 >= 136 && LA10_0 <= 137) {
               alt10 = 1;
            }

            switch(alt10) {
            case 1:
               this.pushFollow(FOLLOW_senaoStm_in_seStm1252);
               senaoStm43 = this.senaoStm();
               --this.state._fsp;
               if (this.state.failed) {
                  return retval;
               }

               if (this.state.backtracking == 0) {
                  stream_senaoStm.add(senaoStm43.getTree());
               }
            default:
               string_literal44 = (Token)this.match(this.input, 127, FOLLOW_127_in_seStm1261);
               if (this.state.failed) {
                  return retval;
               }

               if (this.state.backtracking == 0) {
                  stream_127.add(string_literal44);
               }

               if (this.state.backtracking == 0) {
                  retval.tree = root_0;
                  new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                  root_0 = (CommonTree)this.adaptor.nil();
                  CommonTree root_1 = (CommonTree)this.adaptor.nil();
                  root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(83, (String)"SE")), root_1);
                  this.adaptor.addChild(root_1, stream_expressao.nextTree());
                  if (stream_mult_statements.hasNext()) {
                     this.adaptor.addChild(root_1, stream_mult_statements.nextTree());
                  }

                  stream_mult_statements.reset();
                  if (stream_senaoStm.hasNext()) {
                     this.adaptor.addChild(root_1, stream_senaoStm.nextTree());
                  }

                  stream_senaoStm.reset();
                  this.adaptor.addChild(root_0, root_1);
                  retval.tree = root_0;
               }

               retval.stop = this.input.LT(-1);
               if (this.state.backtracking == 0) {
                  retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                  this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
               }
            }
         }
      } catch (RecognitionException var30) {
         this.reportError(var30);
         this.recover(this.input, var30);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var30);
      }

      return retval;
   }

   public final CalangoGrammarParser.senaoStm_return senaoStm() throws RecognitionException {
      CalangoGrammarParser.senaoStm_return retval = new CalangoGrammarParser.senaoStm_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token string_literal45 = null;
      Token char_literal46 = null;
      Token char_literal48 = null;
      Token string_literal49 = null;
      Token string_literal52 = null;
      CalangoGrammarParser.expressao_return expressao47 = null;
      CalangoGrammarParser.mult_statements_return mult_statements50 = null;
      CalangoGrammarParser.senaoStm_return senaoStm51 = null;
      CalangoGrammarParser.mult_statements_return mult_statements53 = null;
      CommonTree string_literal45_tree = null;
      CommonTree char_literal46_tree = null;
      CommonTree char_literal48_tree = null;
      CommonTree string_literal49_tree = null;
      CommonTree string_literal52_tree = null;
      RewriteRuleTokenStream stream_121 = new RewriteRuleTokenStream(this.adaptor, "token 121");
      RewriteRuleTokenStream stream_136 = new RewriteRuleTokenStream(this.adaptor, "token 136");
      RewriteRuleTokenStream stream_137 = new RewriteRuleTokenStream(this.adaptor, "token 137");
      RewriteRuleTokenStream stream_102 = new RewriteRuleTokenStream(this.adaptor, "token 102");
      RewriteRuleTokenStream stream_101 = new RewriteRuleTokenStream(this.adaptor, "token 101");
      RewriteRuleSubtreeStream stream_mult_statements = new RewriteRuleSubtreeStream(this.adaptor, "rule mult_statements");
      RewriteRuleSubtreeStream stream_senaoStm = new RewriteRuleSubtreeStream(this.adaptor, "rule senaoStm");
      RewriteRuleSubtreeStream stream_expressao = new RewriteRuleSubtreeStream(this.adaptor, "rule expressao");

      try {
         int LA14_0 = this.input.LA(1);
         byte alt14;
         if (LA14_0 == 137 && this.synpred1_CalangoGrammar()) {
            alt14 = 1;
         } else {
            if (LA14_0 != 136) {
               if (this.state.backtracking > 0) {
                  this.state.failed = true;
                  return retval;
               }

               NoViableAltException nvae = new NoViableAltException("", 14, 0, this.input);
               throw nvae;
            }

            alt14 = 2;
         }

         int LA11_0;
         byte alt11;
         label222:
         switch(alt14) {
         case 1:
            string_literal45 = (Token)this.match(this.input, 137, FOLLOW_137_in_senaoStm1343);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_137.add(string_literal45);
            }

            char_literal46 = (Token)this.match(this.input, 101, FOLLOW_101_in_senaoStm1345);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal46);
            }

            this.pushFollow(FOLLOW_expressao_in_senaoStm1347);
            expressao47 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao47.getTree());
            }

            char_literal48 = (Token)this.match(this.input, 102, FOLLOW_102_in_senaoStm1349);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal48);
            }

            string_literal49 = (Token)this.match(this.input, 121, FOLLOW_121_in_senaoStm1351);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_121.add(string_literal49);
            }

            alt11 = 2;
            LA11_0 = this.input.LA(1);
            if (LA11_0 == 32 || LA11_0 >= 34 && LA11_0 <= 36 || LA11_0 == 38 || LA11_0 == 46 || LA11_0 >= 49 && LA11_0 <= 51 || LA11_0 == 53 || LA11_0 == 70 || LA11_0 == 81 || LA11_0 == 83) {
               alt11 = 1;
            }

            switch(alt11) {
            case 1:
               this.pushFollow(FOLLOW_mult_statements_in_senaoStm1359);
               mult_statements50 = this.mult_statements();
               --this.state._fsp;
               if (this.state.failed) {
                  return retval;
               }

               if (this.state.backtracking == 0) {
                  stream_mult_statements.add(mult_statements50.getTree());
               }
            default:
               int alt12 = 2;
               int LA12_0 = this.input.LA(1);
               if (LA12_0 >= 136 && LA12_0 <= 137) {
                  alt12 = 1;
               }

               switch(alt12) {
               case 1:
                  this.pushFollow(FOLLOW_senaoStm_in_senaoStm1362);
                  senaoStm51 = this.senaoStm();
                  --this.state._fsp;
                  if (this.state.failed) {
                     return retval;
                  }

                  if (this.state.backtracking == 0) {
                     stream_senaoStm.add(senaoStm51.getTree());
                  }
               default:
                  if (this.state.backtracking == 0) {
                     retval.tree = root_0;
                     new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                     root_0 = (CommonTree)this.adaptor.nil();
                     CommonTree root_1 = (CommonTree)this.adaptor.nil();
                     root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(85, (String)"SENAO_SE")), root_1);
                     this.adaptor.addChild(root_1, stream_expressao.nextTree());
                     if (stream_mult_statements.hasNext()) {
                        this.adaptor.addChild(root_1, stream_mult_statements.nextTree());
                     }

                     stream_mult_statements.reset();
                     this.adaptor.addChild(root_0, root_1);
                     if (stream_senaoStm.hasNext()) {
                        this.adaptor.addChild(root_0, stream_senaoStm.nextTree());
                     }

                     stream_senaoStm.reset();
                     retval.tree = root_0;
                  }
                  break label222;
               }
            }
         case 2:
            string_literal52 = (Token)this.match(this.input, 136, FOLLOW_136_in_senaoStm1403);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_136.add(string_literal52);
            }

            alt11 = 2;
            LA11_0 = this.input.LA(1);
            if (LA11_0 == 32 || LA11_0 >= 34 && LA11_0 <= 36 || LA11_0 == 38 || LA11_0 == 46 || LA11_0 >= 49 && LA11_0 <= 51 || LA11_0 == 53 || LA11_0 == 70 || LA11_0 == 81 || LA11_0 == 83) {
               alt11 = 1;
            }

            switch(alt11) {
            case 1:
               this.pushFollow(FOLLOW_mult_statements_in_senaoStm1412);
               mult_statements53 = this.mult_statements();
               --this.state._fsp;
               if (this.state.failed) {
                  return retval;
               }

               if (this.state.backtracking == 0) {
                  stream_mult_statements.add(mult_statements53.getTree());
               }
            default:
               if (this.state.backtracking == 0) {
                  retval.tree = root_0;
                  new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                  root_0 = (CommonTree)this.adaptor.nil();
                  if (stream_mult_statements.hasNext()) {
                     CommonTree root_1 = (CommonTree)this.adaptor.nil();
                     root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(84, (String)"SENAO")), root_1);
                     this.adaptor.addChild(root_1, stream_mult_statements.nextTree());
                     this.adaptor.addChild(root_0, root_1);
                  }

                  stream_mult_statements.reset();
                  retval.tree = root_0;
               }
            }
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var33) {
         this.reportError(var33);
         this.recover(this.input, var33);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var33);
      }

      return retval;
   }

   public final CalangoGrammarParser.escolhaStm_return escolhaStm() throws RecognitionException {
      CalangoGrammarParser.escolhaStm_return retval = new CalangoGrammarParser.escolhaStm_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token string_literal54 = null;
      Token char_literal55 = null;
      Token IDENTIFICADOR56 = null;
      Token char_literal57 = null;
      Token string_literal60 = null;
      CalangoGrammarParser.caso_return caso58 = null;
      CalangoGrammarParser.outrocaso_return outrocaso59 = null;
      CommonTree string_literal54_tree = null;
      CommonTree char_literal55_tree = null;
      CommonTree IDENTIFICADOR56_tree = null;
      CommonTree char_literal57_tree = null;
      CommonTree string_literal60_tree = null;
      RewriteRuleTokenStream stream_124 = new RewriteRuleTokenStream(this.adaptor, "token 124");
      RewriteRuleTokenStream stream_ESCOLHA = new RewriteRuleTokenStream(this.adaptor, "token ESCOLHA");
      RewriteRuleTokenStream stream_102 = new RewriteRuleTokenStream(this.adaptor, "token 102");
      RewriteRuleTokenStream stream_101 = new RewriteRuleTokenStream(this.adaptor, "token 101");
      RewriteRuleTokenStream stream_IDENTIFICADOR = new RewriteRuleTokenStream(this.adaptor, "token IDENTIFICADOR");
      RewriteRuleSubtreeStream stream_caso = new RewriteRuleSubtreeStream(this.adaptor, "rule caso");
      RewriteRuleSubtreeStream stream_outrocaso = new RewriteRuleSubtreeStream(this.adaptor, "rule outrocaso");

      try {
         string_literal54 = (Token)this.match(this.input, 34, FOLLOW_ESCOLHA_in_escolhaStm1454);
         if (this.state.failed) {
            return retval;
         } else {
            if (this.state.backtracking == 0) {
               stream_ESCOLHA.add(string_literal54);
            }

            char_literal55 = (Token)this.match(this.input, 101, FOLLOW_101_in_escolhaStm1456);
            if (this.state.failed) {
               return retval;
            } else {
               if (this.state.backtracking == 0) {
                  stream_101.add(char_literal55);
               }

               IDENTIFICADOR56 = (Token)this.match(this.input, 46, FOLLOW_IDENTIFICADOR_in_escolhaStm1458);
               if (this.state.failed) {
                  return retval;
               } else {
                  if (this.state.backtracking == 0) {
                     stream_IDENTIFICADOR.add(IDENTIFICADOR56);
                  }

                  char_literal57 = (Token)this.match(this.input, 102, FOLLOW_102_in_escolhaStm1460);
                  if (this.state.failed) {
                     return retval;
                  } else {
                     if (this.state.backtracking == 0) {
                        stream_102.add(char_literal57);
                     }

                     int cnt15 = 0;

                     while(true) {
                        int alt16 = 2;
                        int LA16_0 = this.input.LA(1);
                        if (LA16_0 == 11) {
                           alt16 = 1;
                        }

                        switch(alt16) {
                        case 1:
                           this.pushFollow(FOLLOW_caso_in_escolhaStm1468);
                           caso58 = this.caso();
                           --this.state._fsp;
                           if (this.state.failed) {
                              return retval;
                           }

                           if (this.state.backtracking == 0) {
                              stream_caso.add(caso58.getTree());
                           }

                           ++cnt15;
                           break;
                        default:
                           if (cnt15 < 1) {
                              if (this.state.backtracking > 0) {
                                 this.state.failed = true;
                                 return retval;
                              }

                              EarlyExitException eee = new EarlyExitException(15, this.input);
                              throw eee;
                           }

                           alt16 = 2;
                           LA16_0 = this.input.LA(1);
                           if (LA16_0 == 69) {
                              alt16 = 1;
                           }

                           switch(alt16) {
                           case 1:
                              this.pushFollow(FOLLOW_outrocaso_in_escolhaStm1477);
                              outrocaso59 = this.outrocaso();
                              --this.state._fsp;
                              if (this.state.failed) {
                                 return retval;
                              }

                              if (this.state.backtracking == 0) {
                                 stream_outrocaso.add(outrocaso59.getTree());
                              }
                           }

                           string_literal60 = (Token)this.match(this.input, 124, FOLLOW_124_in_escolhaStm1486);
                           if (this.state.failed) {
                              return retval;
                           }

                           if (this.state.backtracking == 0) {
                              stream_124.add(string_literal60);
                           }

                           if (this.state.backtracking == 0) {
                              retval.tree = root_0;
                              new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                              root_0 = (CommonTree)this.adaptor.nil();
                              CommonTree root_1 = (CommonTree)this.adaptor.nil();
                              root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(34, (String)"ESCOLHA")), root_1);
                              this.adaptor.addChild(root_1, stream_IDENTIFICADOR.nextNode());
                              CommonTree root_2 = (CommonTree)this.adaptor.nil();
                              root_2 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(12, (String)"CASOS")), root_2);
                              if (!stream_caso.hasNext()) {
                                 throw new RewriteEarlyExitException();
                              }

                              while(stream_caso.hasNext()) {
                                 this.adaptor.addChild(root_2, stream_caso.nextTree());
                              }

                              stream_caso.reset();
                              this.adaptor.addChild(root_1, root_2);
                              if (stream_outrocaso.hasNext()) {
                                 this.adaptor.addChild(root_1, stream_outrocaso.nextTree());
                              }

                              stream_outrocaso.reset();
                              this.adaptor.addChild(root_0, root_1);
                              retval.tree = root_0;
                           }

                           retval.stop = this.input.LT(-1);
                           if (this.state.backtracking == 0) {
                              retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                              this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                           }

                           return retval;
                        }
                     }
                  }
               }
            }
         }
      } catch (RecognitionException var28) {
         this.reportError(var28);
         this.recover(this.input, var28);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var28);
         return retval;
      }
   }

   public final CalangoGrammarParser.caso_return caso() throws RecognitionException {
      CalangoGrammarParser.caso_return retval = new CalangoGrammarParser.caso_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token string_literal61 = null;
      Token INTEIRO_LITERAL63 = null;
      Token CHAR_LITERAL64 = null;
      CalangoGrammarParser.caso_negativo_return caso_negativo62 = null;
      CalangoGrammarParser.mult_statements_return mult_statements65 = null;
      CommonTree string_literal61_tree = null;
      CommonTree INTEIRO_LITERAL63_tree = null;
      CommonTree CHAR_LITERAL64_tree = null;
      RewriteRuleTokenStream stream_INTEIRO_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token INTEIRO_LITERAL");
      RewriteRuleTokenStream stream_CASO = new RewriteRuleTokenStream(this.adaptor, "token CASO");
      RewriteRuleTokenStream stream_CHAR_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token CHAR_LITERAL");
      RewriteRuleSubtreeStream stream_mult_statements = new RewriteRuleSubtreeStream(this.adaptor, "rule mult_statements");
      RewriteRuleSubtreeStream stream_caso_negativo = new RewriteRuleSubtreeStream(this.adaptor, "rule caso_negativo");

      try {
         string_literal61 = (Token)this.match(this.input, 11, FOLLOW_CASO_in_caso1540);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_CASO.add(string_literal61);
         }

         byte alt17;
         switch(this.input.LA(1)) {
         case 15:
            alt17 = 3;
            break;
         case 48:
            alt17 = 2;
            break;
         case 89:
            alt17 = 1;
            break;
         default:
            if (this.state.backtracking > 0) {
               this.state.failed = true;
               return retval;
            }

            NoViableAltException nvae = new NoViableAltException("", 17, 0, this.input);
            throw nvae;
         }

         switch(alt17) {
         case 1:
            this.pushFollow(FOLLOW_caso_negativo_in_caso1543);
            caso_negativo62 = this.caso_negativo();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_caso_negativo.add(caso_negativo62.getTree());
            }
            break;
         case 2:
            INTEIRO_LITERAL63 = (Token)this.match(this.input, 48, FOLLOW_INTEIRO_LITERAL_in_caso1547);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_INTEIRO_LITERAL.add(INTEIRO_LITERAL63);
            }
            break;
         case 3:
            CHAR_LITERAL64 = (Token)this.match(this.input, 15, FOLLOW_CHAR_LITERAL_in_caso1551);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_CHAR_LITERAL.add(CHAR_LITERAL64);
            }
         }

         int alt18 = 2;
         int LA18_0 = this.input.LA(1);
         if (LA18_0 == 32 || LA18_0 >= 34 && LA18_0 <= 36 || LA18_0 == 38 || LA18_0 == 46 || LA18_0 >= 49 && LA18_0 <= 51 || LA18_0 == 53 || LA18_0 == 70 || LA18_0 == 81 || LA18_0 == 83) {
            alt18 = 1;
         }

         switch(alt18) {
         case 1:
            this.pushFollow(FOLLOW_mult_statements_in_caso1560);
            mult_statements65 = this.mult_statements();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_mult_statements.add(mult_statements65.getTree());
            }
         default:
            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               CommonTree root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(11, (String)"CASO")), root_1);
               if (stream_caso_negativo.hasNext()) {
                  this.adaptor.addChild(root_1, stream_caso_negativo.nextTree());
               }

               stream_caso_negativo.reset();
               if (stream_INTEIRO_LITERAL.hasNext()) {
                  this.adaptor.addChild(root_1, stream_INTEIRO_LITERAL.nextNode());
               }

               stream_INTEIRO_LITERAL.reset();
               if (stream_CHAR_LITERAL.hasNext()) {
                  this.adaptor.addChild(root_1, stream_CHAR_LITERAL.nextNode());
               }

               stream_CHAR_LITERAL.reset();
               if (stream_mult_statements.hasNext()) {
                  this.adaptor.addChild(root_1, stream_mult_statements.nextTree());
               }

               stream_mult_statements.reset();
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }

            retval.stop = this.input.LT(-1);
            if (this.state.backtracking == 0) {
               retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
               this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
         }
      } catch (RecognitionException var21) {
         this.reportError(var21);
         this.recover(this.input, var21);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var21);
      }

      return retval;
   }

   public final CalangoGrammarParser.caso_negativo_return caso_negativo() throws RecognitionException {
      CalangoGrammarParser.caso_negativo_return retval = new CalangoGrammarParser.caso_negativo_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token char_literal66 = null;
      Token INTEIRO_LITERAL67 = null;
      CommonTree char_literal66_tree = null;
      CommonTree INTEIRO_LITERAL67_tree = null;
      RewriteRuleTokenStream stream_INTEIRO_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token INTEIRO_LITERAL");
      RewriteRuleTokenStream stream_SUBTRACAO = new RewriteRuleTokenStream(this.adaptor, "token SUBTRACAO");

      try {
         char_literal66 = (Token)this.match(this.input, 89, FOLLOW_SUBTRACAO_in_caso_negativo1613);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_SUBTRACAO.add(char_literal66);
         }

         INTEIRO_LITERAL67 = (Token)this.match(this.input, 48, FOLLOW_INTEIRO_LITERAL_in_caso_negativo1615);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_INTEIRO_LITERAL.add(INTEIRO_LITERAL67);
         }

         if (this.state.backtracking == 0) {
            retval.tree = root_0;
            new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
            root_0 = (CommonTree)this.adaptor.nil();
            CommonTree root_1 = (CommonTree)this.adaptor.nil();
            root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(67, (String)"NEGACAO_MATEMATICA")), root_1);
            this.adaptor.addChild(root_1, stream_INTEIRO_LITERAL.nextNode());
            this.adaptor.addChild(root_0, root_1);
            retval.tree = root_0;
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var11) {
         this.reportError(var11);
         this.recover(this.input, var11);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var11);
      }

      return retval;
   }

   public final CalangoGrammarParser.outrocaso_return outrocaso() throws RecognitionException {
      CalangoGrammarParser.outrocaso_return retval = new CalangoGrammarParser.outrocaso_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token string_literal68 = null;
      CalangoGrammarParser.mult_statements_return mult_statements69 = null;
      CommonTree string_literal68_tree = null;
      RewriteRuleTokenStream stream_OUTROCASO = new RewriteRuleTokenStream(this.adaptor, "token OUTROCASO");
      RewriteRuleSubtreeStream stream_mult_statements = new RewriteRuleSubtreeStream(this.adaptor, "rule mult_statements");

      try {
         string_literal68 = (Token)this.match(this.input, 69, FOLLOW_OUTROCASO_in_outrocaso1655);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_OUTROCASO.add(string_literal68);
         }

         int alt19 = 2;
         int LA19_0 = this.input.LA(1);
         if (LA19_0 == 32 || LA19_0 >= 34 && LA19_0 <= 36 || LA19_0 == 38 || LA19_0 == 46 || LA19_0 >= 49 && LA19_0 <= 51 || LA19_0 == 53 || LA19_0 == 70 || LA19_0 == 81 || LA19_0 == 83) {
            alt19 = 1;
         }

         switch(alt19) {
         case 1:
            this.pushFollow(FOLLOW_mult_statements_in_outrocaso1663);
            mult_statements69 = this.mult_statements();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_mult_statements.add(mult_statements69.getTree());
            }
         default:
            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               CommonTree root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(69, (String)"OUTROCASO")), root_1);
               if (stream_mult_statements.hasNext()) {
                  this.adaptor.addChild(root_1, stream_mult_statements.nextTree());
               }

               stream_mult_statements.reset();
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }

            retval.stop = this.input.LT(-1);
            if (this.state.backtracking == 0) {
               retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
               this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
         }
      } catch (RecognitionException var12) {
         this.reportError(var12);
         this.recover(this.input, var12);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var12);
      }

      return retval;
   }

   public final CalangoGrammarParser.paraStm_return paraStm() throws RecognitionException {
      CalangoGrammarParser.paraStm_return retval = new CalangoGrammarParser.paraStm_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token string_literal70 = null;
      Token char_literal71 = null;
      Token IDENTIFICADOR72 = null;
      Token string_literal73 = null;
      Token string_literal75 = null;
      Token string_literal77 = null;
      Token char_literal79 = null;
      Token string_literal80 = null;
      Token string_literal82 = null;
      CalangoGrammarParser.expressao_return expressao74 = null;
      CalangoGrammarParser.expressao_return expressao76 = null;
      CalangoGrammarParser.expressao_return expressao78 = null;
      CalangoGrammarParser.mult_statements_return mult_statements81 = null;
      CommonTree string_literal70_tree = null;
      CommonTree char_literal71_tree = null;
      CommonTree IDENTIFICADOR72_tree = null;
      CommonTree string_literal73_tree = null;
      CommonTree string_literal75_tree = null;
      CommonTree string_literal77_tree = null;
      CommonTree char_literal79_tree = null;
      CommonTree string_literal80_tree = null;
      CommonTree string_literal82_tree = null;
      RewriteRuleTokenStream stream_125 = new RewriteRuleTokenStream(this.adaptor, "token 125");
      RewriteRuleTokenStream stream_132 = new RewriteRuleTokenStream(this.adaptor, "token 132");
      RewriteRuleTokenStream stream_113 = new RewriteRuleTokenStream(this.adaptor, "token 113");
      RewriteRuleTokenStream stream_FACA = new RewriteRuleTokenStream(this.adaptor, "token FACA");
      RewriteRuleTokenStream stream_120 = new RewriteRuleTokenStream(this.adaptor, "token 120");
      RewriteRuleTokenStream stream_102 = new RewriteRuleTokenStream(this.adaptor, "token 102");
      RewriteRuleTokenStream stream_101 = new RewriteRuleTokenStream(this.adaptor, "token 101");
      RewriteRuleTokenStream stream_IDENTIFICADOR = new RewriteRuleTokenStream(this.adaptor, "token IDENTIFICADOR");
      RewriteRuleTokenStream stream_PARA = new RewriteRuleTokenStream(this.adaptor, "token PARA");
      RewriteRuleSubtreeStream stream_mult_statements = new RewriteRuleSubtreeStream(this.adaptor, "rule mult_statements");
      RewriteRuleSubtreeStream stream_expressao = new RewriteRuleSubtreeStream(this.adaptor, "rule expressao");

      try {
         string_literal70 = (Token)this.match(this.input, 70, FOLLOW_PARA_in_paraStm1714);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_PARA.add(string_literal70);
         }

         char_literal71 = (Token)this.match(this.input, 101, FOLLOW_101_in_paraStm1716);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_101.add(char_literal71);
         }

         IDENTIFICADOR72 = (Token)this.match(this.input, 46, FOLLOW_IDENTIFICADOR_in_paraStm1718);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_IDENTIFICADOR.add(IDENTIFICADOR72);
         }

         string_literal73 = (Token)this.match(this.input, 120, FOLLOW_120_in_paraStm1720);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_120.add(string_literal73);
         }

         this.pushFollow(FOLLOW_expressao_in_paraStm1722);
         expressao74 = this.expressao();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_expressao.add(expressao74.getTree());
         }

         string_literal75 = (Token)this.match(this.input, 113, FOLLOW_113_in_paraStm1724);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_113.add(string_literal75);
         }

         this.pushFollow(FOLLOW_expressao_in_paraStm1726);
         expressao76 = this.expressao();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_expressao.add(expressao76.getTree());
         }

         string_literal77 = (Token)this.match(this.input, 132, FOLLOW_132_in_paraStm1728);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_132.add(string_literal77);
         }

         this.pushFollow(FOLLOW_expressao_in_paraStm1730);
         expressao78 = this.expressao();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_expressao.add(expressao78.getTree());
         }

         char_literal79 = (Token)this.match(this.input, 102, FOLLOW_102_in_paraStm1732);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_102.add(char_literal79);
         }

         string_literal80 = (Token)this.match(this.input, 38, FOLLOW_FACA_in_paraStm1734);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_FACA.add(string_literal80);
         }

         int alt20 = 2;
         int LA20_0 = this.input.LA(1);
         if (LA20_0 == 32 || LA20_0 >= 34 && LA20_0 <= 36 || LA20_0 == 38 || LA20_0 == 46 || LA20_0 >= 49 && LA20_0 <= 51 || LA20_0 == 53 || LA20_0 == 70 || LA20_0 == 81 || LA20_0 == 83) {
            alt20 = 1;
         }

         switch(alt20) {
         case 1:
            this.pushFollow(FOLLOW_mult_statements_in_paraStm1742);
            mult_statements81 = this.mult_statements();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_mult_statements.add(mult_statements81.getTree());
            }
         default:
            string_literal82 = (Token)this.match(this.input, 125, FOLLOW_125_in_paraStm1751);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_125.add(string_literal82);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               CommonTree root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(70, (String)"PARA")), root_1);
               this.adaptor.addChild(root_1, stream_IDENTIFICADOR.nextNode());
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               if (stream_mult_statements.hasNext()) {
                  this.adaptor.addChild(root_1, stream_mult_statements.nextTree());
               }

               stream_mult_statements.reset();
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }

            retval.stop = this.input.LT(-1);
            if (this.state.backtracking == 0) {
               retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
               this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
         }
      } catch (RecognitionException var40) {
         this.reportError(var40);
         this.recover(this.input, var40);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var40);
      }

      return retval;
   }

   public final CalangoGrammarParser.enquantoStm_return enquantoStm() throws RecognitionException {
      CalangoGrammarParser.enquantoStm_return retval = new CalangoGrammarParser.enquantoStm_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token string_literal83 = null;
      Token char_literal84 = null;
      Token char_literal86 = null;
      Token string_literal87 = null;
      Token string_literal89 = null;
      CalangoGrammarParser.expressao_return expressao85 = null;
      CalangoGrammarParser.mult_statements_return mult_statements88 = null;
      CommonTree string_literal83_tree = null;
      CommonTree char_literal84_tree = null;
      CommonTree char_literal86_tree = null;
      CommonTree string_literal87_tree = null;
      CommonTree string_literal89_tree = null;
      RewriteRuleTokenStream stream_ENQUANTO = new RewriteRuleTokenStream(this.adaptor, "token ENQUANTO");
      RewriteRuleTokenStream stream_123 = new RewriteRuleTokenStream(this.adaptor, "token 123");
      RewriteRuleTokenStream stream_FACA = new RewriteRuleTokenStream(this.adaptor, "token FACA");
      RewriteRuleTokenStream stream_102 = new RewriteRuleTokenStream(this.adaptor, "token 102");
      RewriteRuleTokenStream stream_101 = new RewriteRuleTokenStream(this.adaptor, "token 101");
      RewriteRuleSubtreeStream stream_mult_statements = new RewriteRuleSubtreeStream(this.adaptor, "rule mult_statements");
      RewriteRuleSubtreeStream stream_expressao = new RewriteRuleSubtreeStream(this.adaptor, "rule expressao");

      try {
         string_literal83 = (Token)this.match(this.input, 32, FOLLOW_ENQUANTO_in_enquantoStm1803);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_ENQUANTO.add(string_literal83);
         }

         char_literal84 = (Token)this.match(this.input, 101, FOLLOW_101_in_enquantoStm1805);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_101.add(char_literal84);
         }

         this.pushFollow(FOLLOW_expressao_in_enquantoStm1807);
         expressao85 = this.expressao();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_expressao.add(expressao85.getTree());
         }

         char_literal86 = (Token)this.match(this.input, 102, FOLLOW_102_in_enquantoStm1809);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_102.add(char_literal86);
         }

         string_literal87 = (Token)this.match(this.input, 38, FOLLOW_FACA_in_enquantoStm1811);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_FACA.add(string_literal87);
         }

         int alt21 = 2;
         int LA21_0 = this.input.LA(1);
         if (LA21_0 == 32 || LA21_0 >= 34 && LA21_0 <= 36 || LA21_0 == 38 || LA21_0 == 46 || LA21_0 >= 49 && LA21_0 <= 51 || LA21_0 == 53 || LA21_0 == 70 || LA21_0 == 81 || LA21_0 == 83) {
            alt21 = 1;
         }

         switch(alt21) {
         case 1:
            this.pushFollow(FOLLOW_mult_statements_in_enquantoStm1819);
            mult_statements88 = this.mult_statements();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_mult_statements.add(mult_statements88.getTree());
            }
         default:
            string_literal89 = (Token)this.match(this.input, 123, FOLLOW_123_in_enquantoStm1828);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_123.add(string_literal89);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               CommonTree root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(33, (String)"ENQUANTO_STM")), root_1);
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               if (stream_mult_statements.hasNext()) {
                  this.adaptor.addChild(root_1, stream_mult_statements.nextTree());
               }

               stream_mult_statements.reset();
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }

            retval.stop = this.input.LT(-1);
            if (this.state.backtracking == 0) {
               retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
               this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
         }
      } catch (RecognitionException var26) {
         this.reportError(var26);
         this.recover(this.input, var26);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var26);
      }

      return retval;
   }

   public final CalangoGrammarParser.facaStm_return facaStm() throws RecognitionException {
      CalangoGrammarParser.facaStm_return retval = new CalangoGrammarParser.facaStm_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token FACA90 = null;
      CalangoGrammarParser.corpo_facaStm_return corpo_facaStm91 = null;
      CommonTree FACA90_tree = null;
      RewriteRuleTokenStream stream_FACA = new RewriteRuleTokenStream(this.adaptor, "token FACA");
      RewriteRuleSubtreeStream stream_corpo_facaStm = new RewriteRuleSubtreeStream(this.adaptor, "rule corpo_facaStm");

      try {
         FACA90 = (Token)this.match(this.input, 38, FOLLOW_FACA_in_facaStm1875);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_FACA.add(FACA90);
         }

         this.pushFollow(FOLLOW_corpo_facaStm_in_facaStm1877);
         corpo_facaStm91 = this.corpo_facaStm();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_corpo_facaStm.add(corpo_facaStm91.getTree());
         }

         if (this.state.backtracking == 0) {
            retval.tree = root_0;
            new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
            root_0 = (CommonTree)this.adaptor.nil();
            CommonTree root_1 = (CommonTree)this.adaptor.nil();
            root_1 = (CommonTree)this.adaptor.becomeRoot((Object)stream_FACA.nextNode(), root_1);
            this.adaptor.addChild(root_1, stream_corpo_facaStm.nextTree());
            this.adaptor.addChild(root_0, root_1);
            retval.tree = root_0;
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var10) {
         this.reportError(var10);
         this.recover(this.input, var10);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var10);
      }

      return retval;
   }

   public final CalangoGrammarParser.corpo_facaStm_return corpo_facaStm() throws RecognitionException {
      CalangoGrammarParser.corpo_facaStm_return retval = new CalangoGrammarParser.corpo_facaStm_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token string_literal92 = null;
      Token char_literal93 = null;
      Token char_literal95 = null;
      Token char_literal96 = null;
      CalangoGrammarParser.expressao_return expressao94 = null;
      CalangoGrammarParser.statements_return statements97 = null;
      CalangoGrammarParser.corpo_facaStm_return corpo_facaStm98 = null;
      CommonTree string_literal92_tree = null;
      CommonTree char_literal93_tree = null;
      CommonTree char_literal95_tree = null;
      CommonTree char_literal96_tree = null;
      RewriteRuleTokenStream stream_ENQUANTO = new RewriteRuleTokenStream(this.adaptor, "token ENQUANTO");
      RewriteRuleTokenStream stream_104 = new RewriteRuleTokenStream(this.adaptor, "token 104");
      RewriteRuleTokenStream stream_102 = new RewriteRuleTokenStream(this.adaptor, "token 102");
      RewriteRuleTokenStream stream_101 = new RewriteRuleTokenStream(this.adaptor, "token 101");
      RewriteRuleSubtreeStream stream_expressao = new RewriteRuleSubtreeStream(this.adaptor, "rule expressao");

      try {
         int LA22_0 = this.input.LA(1);
         byte alt22;
         if (LA22_0 == 32) {
            int LA22_1 = this.input.LA(2);
            if (this.synpred2_CalangoGrammar()) {
               alt22 = 1;
            } else {
               alt22 = 2;
            }
         } else {
            if ((LA22_0 < 34 || LA22_0 > 36) && LA22_0 != 38 && LA22_0 != 46 && (LA22_0 < 49 || LA22_0 > 51) && LA22_0 != 53 && LA22_0 != 70 && LA22_0 != 81 && LA22_0 != 83) {
               if (this.state.backtracking > 0) {
                  this.state.failed = true;
                  return retval;
               }

               NoViableAltException nvae = new NoViableAltException("", 22, 0, this.input);
               throw nvae;
            }

            alt22 = 2;
         }

         switch(alt22) {
         case 1:
            string_literal92 = (Token)this.match(this.input, 32, FOLLOW_ENQUANTO_in_corpo_facaStm1927);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_ENQUANTO.add(string_literal92);
            }

            char_literal93 = (Token)this.match(this.input, 101, FOLLOW_101_in_corpo_facaStm1929);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal93);
            }

            this.pushFollow(FOLLOW_expressao_in_corpo_facaStm1931);
            expressao94 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao94.getTree());
            }

            char_literal95 = (Token)this.match(this.input, 102, FOLLOW_102_in_corpo_facaStm1933);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal95);
            }

            char_literal96 = (Token)this.match(this.input, 104, FOLLOW_104_in_corpo_facaStm1935);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_104.add(char_literal96);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               CommonTree root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(32, (String)"ENQUANTO")), root_1);
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 2:
            root_0 = (CommonTree)this.adaptor.nil();
            this.pushFollow(FOLLOW_statements_in_corpo_facaStm1969);
            statements97 = this.statements();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, statements97.getTree());
            }

            this.pushFollow(FOLLOW_corpo_facaStm_in_corpo_facaStm1971);
            corpo_facaStm98 = this.corpo_facaStm();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, corpo_facaStm98.getTree());
            }
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var23) {
         this.reportError(var23);
         this.recover(this.input, var23);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var23);
      }

      return retval;
   }

   public final CalangoGrammarParser.statementsSemiColon_return statementsSemiColon() throws RecognitionException {
      CalangoGrammarParser.statementsSemiColon_return retval = new CalangoGrammarParser.statementsSemiColon_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CalangoGrammarParser.atribuicaoStm_return atribuicaoStm99 = null;
      CalangoGrammarParser.limpatela_return limpatela100 = null;
      CalangoGrammarParser.chamadaRotina_return chamadaRotina101 = null;
      CalangoGrammarParser.lerStatement_return lerStatement102 = null;
      CalangoGrammarParser.lerCaracterStatement_return lerCaracterStatement103 = null;
      CalangoGrammarParser.escreverStatement_return escreverStatement104 = null;
      CalangoGrammarParser.interrompa_return interrompa105 = null;
      CalangoGrammarParser.retornaStm_return retornaStm106 = null;

      try {
         byte alt23;
         switch(this.input.LA(1)) {
         case 35:
         case 36:
            alt23 = 6;
            break;
         case 46:
            int LA23_1 = this.input.LA(2);
            if (LA23_1 == 101) {
               alt23 = 3;
               break;
            } else {
               if (LA23_1 >= 105 && LA23_1 <= 106) {
                  alt23 = 1;
                  break;
               }

               if (this.state.backtracking > 0) {
                  this.state.failed = true;
                  return retval;
               }

               NoViableAltException nvae = new NoViableAltException("", 23, 1, this.input);
               throw nvae;
            }
         case 49:
            alt23 = 7;
            break;
         case 50:
            alt23 = 4;
            break;
         case 51:
            alt23 = 5;
            break;
         case 53:
            alt23 = 2;
            break;
         case 81:
            alt23 = 8;
            break;
         default:
            if (this.state.backtracking > 0) {
               this.state.failed = true;
               return retval;
            }

            NoViableAltException nvae = new NoViableAltException("", 23, 0, this.input);
            throw nvae;
         }

         switch(alt23) {
         case 1:
            root_0 = (CommonTree)this.adaptor.nil();
            this.pushFollow(FOLLOW_atribuicaoStm_in_statementsSemiColon1988);
            atribuicaoStm99 = this.atribuicaoStm();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, atribuicaoStm99.getTree());
            }
            break;
         case 2:
            root_0 = (CommonTree)this.adaptor.nil();
            this.pushFollow(FOLLOW_limpatela_in_statementsSemiColon1996);
            limpatela100 = this.limpatela();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, limpatela100.getTree());
            }
            break;
         case 3:
            root_0 = (CommonTree)this.adaptor.nil();
            this.pushFollow(FOLLOW_chamadaRotina_in_statementsSemiColon2008);
            chamadaRotina101 = this.chamadaRotina();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, chamadaRotina101.getTree());
            }
            break;
         case 4:
            root_0 = (CommonTree)this.adaptor.nil();
            this.pushFollow(FOLLOW_lerStatement_in_statementsSemiColon2016);
            lerStatement102 = this.lerStatement();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, lerStatement102.getTree());
            }
            break;
         case 5:
            root_0 = (CommonTree)this.adaptor.nil();
            this.pushFollow(FOLLOW_lerCaracterStatement_in_statementsSemiColon2025);
            lerCaracterStatement103 = this.lerCaracterStatement();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, lerCaracterStatement103.getTree());
            }
            break;
         case 6:
            root_0 = (CommonTree)this.adaptor.nil();
            this.pushFollow(FOLLOW_escreverStatement_in_statementsSemiColon2033);
            escreverStatement104 = this.escreverStatement();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, escreverStatement104.getTree());
            }
            break;
         case 7:
            root_0 = (CommonTree)this.adaptor.nil();
            this.pushFollow(FOLLOW_interrompa_in_statementsSemiColon2042);
            interrompa105 = this.interrompa();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, interrompa105.getTree());
            }
            break;
         case 8:
            root_0 = (CommonTree)this.adaptor.nil();
            this.pushFollow(FOLLOW_retornaStm_in_statementsSemiColon2058);
            retornaStm106 = this.retornaStm();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, retornaStm106.getTree());
            }
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var14) {
         this.reportError(var14);
         this.recover(this.input, var14);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var14);
      }

      return retval;
   }

   public final CalangoGrammarParser.atribuicaoStm_return atribuicaoStm() throws RecognitionException {
      CalangoGrammarParser.atribuicaoStm_return retval = new CalangoGrammarParser.atribuicaoStm_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token IDENTIFICADOR107 = null;
      Token char_literal108 = null;
      Token char_literal109 = null;
      Token char_literal110 = null;
      Token char_literal111 = null;
      Token char_literal112 = null;
      CalangoGrammarParser.expressao_return identVetor = null;
      CalangoGrammarParser.expressao_return identMatriz = null;
      CalangoGrammarParser.expressao_return valorAtribuido = null;
      CommonTree IDENTIFICADOR107_tree = null;
      CommonTree char_literal108_tree = null;
      CommonTree char_literal109_tree = null;
      CommonTree char_literal110_tree = null;
      CommonTree char_literal111_tree = null;
      CommonTree char_literal112_tree = null;
      RewriteRuleTokenStream stream_108 = new RewriteRuleTokenStream(this.adaptor, "token 108");
      RewriteRuleTokenStream stream_106 = new RewriteRuleTokenStream(this.adaptor, "token 106");
      RewriteRuleTokenStream stream_105 = new RewriteRuleTokenStream(this.adaptor, "token 105");
      RewriteRuleTokenStream stream_IDENTIFICADOR = new RewriteRuleTokenStream(this.adaptor, "token IDENTIFICADOR");
      RewriteRuleSubtreeStream stream_expressao = new RewriteRuleSubtreeStream(this.adaptor, "rule expressao");

      try {
         IDENTIFICADOR107 = (Token)this.match(this.input, 46, FOLLOW_IDENTIFICADOR_in_atribuicaoStm2087);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_IDENTIFICADOR.add(IDENTIFICADOR107);
         }

         int alt25 = 2;
         int LA25_0 = this.input.LA(1);
         if (LA25_0 == 106) {
            alt25 = 1;
         }

         switch(alt25) {
         case 1:
            char_literal108 = (Token)this.match(this.input, 106, FOLLOW_106_in_atribuicaoStm2090);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_106.add(char_literal108);
            }

            this.pushFollow(FOLLOW_expressao_in_atribuicaoStm2094);
            identVetor = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(identVetor.getTree());
            }

            char_literal109 = (Token)this.match(this.input, 108, FOLLOW_108_in_atribuicaoStm2096);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_108.add(char_literal109);
            }

            int alt24 = 2;
            int LA24_0 = this.input.LA(1);
            if (LA24_0 == 106) {
               alt24 = 1;
            }

            switch(alt24) {
            case 1:
               char_literal110 = (Token)this.match(this.input, 106, FOLLOW_106_in_atribuicaoStm2099);
               if (this.state.failed) {
                  return retval;
               }

               if (this.state.backtracking == 0) {
                  stream_106.add(char_literal110);
               }

               this.pushFollow(FOLLOW_expressao_in_atribuicaoStm2103);
               identMatriz = this.expressao();
               --this.state._fsp;
               if (this.state.failed) {
                  return retval;
               }

               if (this.state.backtracking == 0) {
                  stream_expressao.add(identMatriz.getTree());
               }

               char_literal111 = (Token)this.match(this.input, 108, FOLLOW_108_in_atribuicaoStm2105);
               if (this.state.failed) {
                  return retval;
               }

               if (this.state.backtracking == 0) {
                  stream_108.add(char_literal111);
               }
            }
         default:
            char_literal112 = (Token)this.match(this.input, 105, FOLLOW_105_in_atribuicaoStm2111);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_105.add(char_literal112);
            }

            this.pushFollow(FOLLOW_expressao_in_atribuicaoStm2115);
            valorAtribuido = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(valorAtribuido.getTree());
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               RewriteRuleSubtreeStream stream_identVetor = new RewriteRuleSubtreeStream(this.adaptor, "rule identVetor", identVetor != null ? identVetor.tree : null);
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               RewriteRuleSubtreeStream stream_identMatriz = new RewriteRuleSubtreeStream(this.adaptor, "rule identMatriz", identMatriz != null ? identMatriz.tree : null);
               RewriteRuleSubtreeStream stream_valorAtribuido = new RewriteRuleSubtreeStream(this.adaptor, "rule valorAtribuido", valorAtribuido != null ? valorAtribuido.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               CommonTree root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(9, (String)"ATRIBUICAO")), root_1);
               this.adaptor.addChild(root_1, stream_IDENTIFICADOR.nextNode());
               this.adaptor.addChild(root_1, stream_valorAtribuido.nextTree());
               if (stream_identVetor.hasNext()) {
                  this.adaptor.addChild(root_1, stream_identVetor.nextTree());
               }

               stream_identVetor.reset();
               if (stream_identMatriz.hasNext()) {
                  this.adaptor.addChild(root_1, stream_identMatriz.nextTree());
               }

               stream_identMatriz.reset();
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }

            retval.stop = this.input.LT(-1);
            if (this.state.backtracking == 0) {
               retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
               this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
         }
      } catch (RecognitionException var30) {
         this.reportError(var30);
         this.recover(this.input, var30);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var30);
      }

      return retval;
   }

   public final CalangoGrammarParser.limpatela_return limpatela() throws RecognitionException {
      CalangoGrammarParser.limpatela_return retval = new CalangoGrammarParser.limpatela_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token LIMPA_TELA113 = null;
      Token char_literal114 = null;
      Token char_literal115 = null;
      CommonTree LIMPA_TELA113_tree = null;
      CommonTree char_literal114_tree = null;
      CommonTree char_literal115_tree = null;
      RewriteRuleTokenStream stream_102 = new RewriteRuleTokenStream(this.adaptor, "token 102");
      RewriteRuleTokenStream stream_LIMPA_TELA = new RewriteRuleTokenStream(this.adaptor, "token LIMPA_TELA");
      RewriteRuleTokenStream stream_101 = new RewriteRuleTokenStream(this.adaptor, "token 101");

      try {
         LIMPA_TELA113 = (Token)this.match(this.input, 53, FOLLOW_LIMPA_TELA_in_limpatela2162);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_LIMPA_TELA.add(LIMPA_TELA113);
         }

         char_literal114 = (Token)this.match(this.input, 101, FOLLOW_101_in_limpatela2164);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_101.add(char_literal114);
         }

         char_literal115 = (Token)this.match(this.input, 102, FOLLOW_102_in_limpatela2166);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_102.add(char_literal115);
         }

         if (this.state.backtracking == 0) {
            retval.tree = root_0;
            new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
            root_0 = (CommonTree)this.adaptor.nil();
            CommonTree root_1 = (CommonTree)this.adaptor.nil();
            root_1 = (CommonTree)this.adaptor.becomeRoot((Object)stream_LIMPA_TELA.nextNode(), root_1);
            this.adaptor.addChild(root_0, root_1);
            retval.tree = root_0;
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var14) {
         this.reportError(var14);
         this.recover(this.input, var14);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var14);
      }

      return retval;
   }

   public final CalangoGrammarParser.chamadaRotina_return chamadaRotina() throws RecognitionException {
      CalangoGrammarParser.chamadaRotina_return retval = new CalangoGrammarParser.chamadaRotina_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token IDENTIFICADOR116 = null;
      Token char_literal117 = null;
      Token char_literal119 = null;
      CalangoGrammarParser.parametros_chamada_return parametros_chamada118 = null;
      CommonTree IDENTIFICADOR116_tree = null;
      CommonTree char_literal117_tree = null;
      CommonTree char_literal119_tree = null;
      RewriteRuleTokenStream stream_102 = new RewriteRuleTokenStream(this.adaptor, "token 102");
      RewriteRuleTokenStream stream_101 = new RewriteRuleTokenStream(this.adaptor, "token 101");
      RewriteRuleTokenStream stream_IDENTIFICADOR = new RewriteRuleTokenStream(this.adaptor, "token IDENTIFICADOR");
      RewriteRuleSubtreeStream stream_parametros_chamada = new RewriteRuleSubtreeStream(this.adaptor, "rule parametros_chamada");

      try {
         IDENTIFICADOR116 = (Token)this.match(this.input, 46, FOLLOW_IDENTIFICADOR_in_chamadaRotina2200);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_IDENTIFICADOR.add(IDENTIFICADOR116);
         }

         char_literal117 = (Token)this.match(this.input, 101, FOLLOW_101_in_chamadaRotina2202);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_101.add(char_literal117);
         }

         int alt26 = 2;
         int LA26_0 = this.input.LA(1);
         if (LA26_0 == 5 || LA26_0 == 10 || LA26_0 == 15 || LA26_0 == 46 || LA26_0 == 48 || LA26_0 >= 65 && LA26_0 <= 66 || LA26_0 == 79 || LA26_0 == 89 || LA26_0 == 92 || LA26_0 == 101 || LA26_0 >= 109 && LA26_0 <= 110 || LA26_0 == 112 || LA26_0 >= 114 && LA26_0 <= 119 || LA26_0 == 122 || LA26_0 >= 128 && LA26_0 <= 131 || LA26_0 == 133 || LA26_0 == 135 || LA26_0 == 138) {
            alt26 = 1;
         }

         switch(alt26) {
         case 1:
            this.pushFollow(FOLLOW_parametros_chamada_in_chamadaRotina2204);
            parametros_chamada118 = this.parametros_chamada();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_parametros_chamada.add(parametros_chamada118.getTree());
            }
         default:
            char_literal119 = (Token)this.match(this.input, 102, FOLLOW_102_in_chamadaRotina2207);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal119);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               CommonTree root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(13, (String)"CHAMADA")), root_1);
               this.adaptor.addChild(root_1, stream_IDENTIFICADOR.nextNode());
               if (stream_parametros_chamada.hasNext()) {
                  this.adaptor.addChild(root_1, stream_parametros_chamada.nextTree());
               }

               stream_parametros_chamada.reset();
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }

            retval.stop = this.input.LT(-1);
            if (this.state.backtracking == 0) {
               retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
               this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
         }
      } catch (RecognitionException var18) {
         this.reportError(var18);
         this.recover(this.input, var18);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var18);
      }

      return retval;
   }

   public final CalangoGrammarParser.lerStatement_return lerStatement() throws RecognitionException {
      CalangoGrammarParser.lerStatement_return retval = new CalangoGrammarParser.lerStatement_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token LEIA120 = null;
      Token char_literal121 = null;
      Token char_literal123 = null;
      Token char_literal125 = null;
      CalangoGrammarParser.expressao_return expressao122 = null;
      CalangoGrammarParser.expressao_return expressao124 = null;
      CommonTree LEIA120_tree = null;
      CommonTree char_literal121_tree = null;
      CommonTree char_literal123_tree = null;
      CommonTree char_literal125_tree = null;
      RewriteRuleTokenStream stream_CONCATENACAO = new RewriteRuleTokenStream(this.adaptor, "token CONCATENACAO");
      RewriteRuleTokenStream stream_LEIA = new RewriteRuleTokenStream(this.adaptor, "token LEIA");
      RewriteRuleTokenStream stream_102 = new RewriteRuleTokenStream(this.adaptor, "token 102");
      RewriteRuleTokenStream stream_101 = new RewriteRuleTokenStream(this.adaptor, "token 101");
      RewriteRuleSubtreeStream stream_expressao = new RewriteRuleSubtreeStream(this.adaptor, "rule expressao");

      try {
         LEIA120 = (Token)this.match(this.input, 50, FOLLOW_LEIA_in_lerStatement2242);
         if (this.state.failed) {
            return retval;
         } else {
            if (this.state.backtracking == 0) {
               stream_LEIA.add(LEIA120);
            }

            char_literal121 = (Token)this.match(this.input, 101, FOLLOW_101_in_lerStatement2244);
            if (this.state.failed) {
               return retval;
            } else {
               if (this.state.backtracking == 0) {
                  stream_101.add(char_literal121);
               }

               this.pushFollow(FOLLOW_expressao_in_lerStatement2246);
               expressao122 = this.expressao();
               --this.state._fsp;
               if (this.state.failed) {
                  return retval;
               } else {
                  if (this.state.backtracking == 0) {
                     stream_expressao.add(expressao122.getTree());
                  }

                  while(true) {
                     int alt27 = 2;
                     int LA27_0 = this.input.LA(1);
                     if (LA27_0 == 20) {
                        alt27 = 1;
                     }

                     switch(alt27) {
                     case 1:
                        char_literal123 = (Token)this.match(this.input, 20, FOLLOW_CONCATENACAO_in_lerStatement2249);
                        if (this.state.failed) {
                           return retval;
                        }

                        if (this.state.backtracking == 0) {
                           stream_CONCATENACAO.add(char_literal123);
                        }

                        this.pushFollow(FOLLOW_expressao_in_lerStatement2251);
                        expressao124 = this.expressao();
                        --this.state._fsp;
                        if (this.state.failed) {
                           return retval;
                        }

                        if (this.state.backtracking == 0) {
                           stream_expressao.add(expressao124.getTree());
                        }
                        break;
                     default:
                        char_literal125 = (Token)this.match(this.input, 102, FOLLOW_102_in_lerStatement2255);
                        if (this.state.failed) {
                           return retval;
                        }

                        if (this.state.backtracking == 0) {
                           stream_102.add(char_literal125);
                        }

                        if (this.state.backtracking == 0) {
                           retval.tree = root_0;
                           new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                           root_0 = (CommonTree)this.adaptor.nil();
                           CommonTree root_1 = (CommonTree)this.adaptor.nil();
                           root_1 = (CommonTree)this.adaptor.becomeRoot((Object)stream_LEIA.nextNode(), root_1);
                           this.adaptor.addChild(root_1, stream_expressao.nextTree());

                           while(stream_expressao.hasNext()) {
                              this.adaptor.addChild(root_1, stream_expressao.nextTree());
                           }

                           stream_expressao.reset();
                           this.adaptor.addChild(root_0, root_1);
                           retval.tree = root_0;
                        }

                        retval.stop = this.input.LT(-1);
                        if (this.state.backtracking == 0) {
                           retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                           this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                        }

                        return retval;
                     }
                  }
               }
            }
         }
      } catch (RecognitionException var20) {
         this.reportError(var20);
         this.recover(this.input, var20);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var20);
         return retval;
      }
   }

   public final CalangoGrammarParser.lerCaracterStatement_return lerCaracterStatement() throws RecognitionException {
      CalangoGrammarParser.lerCaracterStatement_return retval = new CalangoGrammarParser.lerCaracterStatement_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token LEIA_CARACTER126 = null;
      Token char_literal127 = null;
      Token char_literal129 = null;
      Token char_literal131 = null;
      CalangoGrammarParser.expressao_return expressao128 = null;
      CalangoGrammarParser.expressao_return expressao130 = null;
      CommonTree LEIA_CARACTER126_tree = null;
      CommonTree char_literal127_tree = null;
      CommonTree char_literal129_tree = null;
      CommonTree char_literal131_tree = null;
      RewriteRuleTokenStream stream_CONCATENACAO = new RewriteRuleTokenStream(this.adaptor, "token CONCATENACAO");
      RewriteRuleTokenStream stream_LEIA_CARACTER = new RewriteRuleTokenStream(this.adaptor, "token LEIA_CARACTER");
      RewriteRuleTokenStream stream_102 = new RewriteRuleTokenStream(this.adaptor, "token 102");
      RewriteRuleTokenStream stream_101 = new RewriteRuleTokenStream(this.adaptor, "token 101");
      RewriteRuleSubtreeStream stream_expressao = new RewriteRuleSubtreeStream(this.adaptor, "rule expressao");

      try {
         LEIA_CARACTER126 = (Token)this.match(this.input, 51, FOLLOW_LEIA_CARACTER_in_lerCaracterStatement2294);
         if (this.state.failed) {
            return retval;
         } else {
            if (this.state.backtracking == 0) {
               stream_LEIA_CARACTER.add(LEIA_CARACTER126);
            }

            char_literal127 = (Token)this.match(this.input, 101, FOLLOW_101_in_lerCaracterStatement2296);
            if (this.state.failed) {
               return retval;
            } else {
               if (this.state.backtracking == 0) {
                  stream_101.add(char_literal127);
               }

               this.pushFollow(FOLLOW_expressao_in_lerCaracterStatement2298);
               expressao128 = this.expressao();
               --this.state._fsp;
               if (this.state.failed) {
                  return retval;
               } else {
                  if (this.state.backtracking == 0) {
                     stream_expressao.add(expressao128.getTree());
                  }

                  while(true) {
                     int alt28 = 2;
                     int LA28_0 = this.input.LA(1);
                     if (LA28_0 == 20) {
                        alt28 = 1;
                     }

                     switch(alt28) {
                     case 1:
                        char_literal129 = (Token)this.match(this.input, 20, FOLLOW_CONCATENACAO_in_lerCaracterStatement2301);
                        if (this.state.failed) {
                           return retval;
                        }

                        if (this.state.backtracking == 0) {
                           stream_CONCATENACAO.add(char_literal129);
                        }

                        this.pushFollow(FOLLOW_expressao_in_lerCaracterStatement2303);
                        expressao130 = this.expressao();
                        --this.state._fsp;
                        if (this.state.failed) {
                           return retval;
                        }

                        if (this.state.backtracking == 0) {
                           stream_expressao.add(expressao130.getTree());
                        }
                        break;
                     default:
                        char_literal131 = (Token)this.match(this.input, 102, FOLLOW_102_in_lerCaracterStatement2307);
                        if (this.state.failed) {
                           return retval;
                        }

                        if (this.state.backtracking == 0) {
                           stream_102.add(char_literal131);
                        }

                        if (this.state.backtracking == 0) {
                           retval.tree = root_0;
                           new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                           root_0 = (CommonTree)this.adaptor.nil();
                           CommonTree root_1 = (CommonTree)this.adaptor.nil();
                           root_1 = (CommonTree)this.adaptor.becomeRoot((Object)stream_LEIA_CARACTER.nextNode(), root_1);
                           this.adaptor.addChild(root_1, stream_expressao.nextTree());

                           while(stream_expressao.hasNext()) {
                              this.adaptor.addChild(root_1, stream_expressao.nextTree());
                           }

                           stream_expressao.reset();
                           this.adaptor.addChild(root_0, root_1);
                           retval.tree = root_0;
                        }

                        retval.stop = this.input.LT(-1);
                        if (this.state.backtracking == 0) {
                           retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                           this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                        }

                        return retval;
                     }
                  }
               }
            }
         }
      } catch (RecognitionException var20) {
         this.reportError(var20);
         this.recover(this.input, var20);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var20);
         return retval;
      }
   }

   public final CalangoGrammarParser.escreverStatement_return escreverStatement() throws RecognitionException {
      CalangoGrammarParser.escreverStatement_return retval = new CalangoGrammarParser.escreverStatement_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token string_literal132 = null;
      Token char_literal133 = null;
      Token char_literal135 = null;
      Token char_literal137 = null;
      Token string_literal138 = null;
      Token char_literal139 = null;
      Token char_literal141 = null;
      Token char_literal143 = null;
      CalangoGrammarParser.expressaoEscreva_return expressaoEscreva134 = null;
      CalangoGrammarParser.expressaoEscreva_return expressaoEscreva136 = null;
      CalangoGrammarParser.expressaoEscreva_return expressaoEscreva140 = null;
      CalangoGrammarParser.expressaoEscreva_return expressaoEscreva142 = null;
      CommonTree string_literal132_tree = null;
      CommonTree char_literal133_tree = null;
      CommonTree char_literal135_tree = null;
      CommonTree char_literal137_tree = null;
      CommonTree string_literal138_tree = null;
      CommonTree char_literal139_tree = null;
      CommonTree char_literal141_tree = null;
      CommonTree char_literal143_tree = null;
      RewriteRuleTokenStream stream_ESCREVAL = new RewriteRuleTokenStream(this.adaptor, "token ESCREVAL");
      RewriteRuleTokenStream stream_CONCATENACAO = new RewriteRuleTokenStream(this.adaptor, "token CONCATENACAO");
      RewriteRuleTokenStream stream_102 = new RewriteRuleTokenStream(this.adaptor, "token 102");
      RewriteRuleTokenStream stream_ESCREVA = new RewriteRuleTokenStream(this.adaptor, "token ESCREVA");
      RewriteRuleTokenStream stream_101 = new RewriteRuleTokenStream(this.adaptor, "token 101");
      RewriteRuleSubtreeStream stream_expressaoEscreva = new RewriteRuleSubtreeStream(this.adaptor, "rule expressaoEscreva");

      try {
         int LA31_0 = this.input.LA(1);
         byte alt31;
         if (LA31_0 == 35) {
            alt31 = 1;
         } else {
            if (LA31_0 != 36) {
               if (this.state.backtracking > 0) {
                  this.state.failed = true;
                  return retval;
               }

               NoViableAltException nvae = new NoViableAltException("", 31, 0, this.input);
               throw nvae;
            }

            alt31 = 2;
         }

         byte alt30;
         int LA30_0;
         CommonTree root_1;
         label205:
         switch(alt31) {
         case 1:
            string_literal132 = (Token)this.match(this.input, 35, FOLLOW_ESCREVA_in_escreverStatement2345);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_ESCREVA.add(string_literal132);
            }

            char_literal133 = (Token)this.match(this.input, 101, FOLLOW_101_in_escreverStatement2347);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal133);
            }

            this.pushFollow(FOLLOW_expressaoEscreva_in_escreverStatement2349);
            expressaoEscreva134 = this.expressaoEscreva();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressaoEscreva.add(expressaoEscreva134.getTree());
            }

            while(true) {
               alt30 = 2;
               LA30_0 = this.input.LA(1);
               if (LA30_0 == 20) {
                  alt30 = 1;
               }

               switch(alt30) {
               case 1:
                  char_literal135 = (Token)this.match(this.input, 20, FOLLOW_CONCATENACAO_in_escreverStatement2352);
                  if (this.state.failed) {
                     return retval;
                  }

                  if (this.state.backtracking == 0) {
                     stream_CONCATENACAO.add(char_literal135);
                  }

                  this.pushFollow(FOLLOW_expressaoEscreva_in_escreverStatement2354);
                  expressaoEscreva136 = this.expressaoEscreva();
                  --this.state._fsp;
                  if (this.state.failed) {
                     return retval;
                  }

                  if (this.state.backtracking == 0) {
                     stream_expressaoEscreva.add(expressaoEscreva136.getTree());
                  }
                  break;
               default:
                  char_literal137 = (Token)this.match(this.input, 102, FOLLOW_102_in_escreverStatement2358);
                  if (this.state.failed) {
                     return retval;
                  }

                  if (this.state.backtracking == 0) {
                     stream_102.add(char_literal137);
                  }

                  if (this.state.backtracking == 0) {
                     retval.tree = root_0;
                     new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                     root_0 = (CommonTree)this.adaptor.nil();
                     root_1 = (CommonTree)this.adaptor.nil();
                     root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(35, (String)"ESCREVA")), root_1);
                     this.adaptor.addChild(root_1, stream_expressaoEscreva.nextTree());

                     while(stream_expressaoEscreva.hasNext()) {
                        this.adaptor.addChild(root_1, stream_expressaoEscreva.nextTree());
                     }

                     stream_expressaoEscreva.reset();
                     this.adaptor.addChild(root_0, root_1);
                     retval.tree = root_0;
                  }
                  break label205;
               }
            }
         case 2:
            string_literal138 = (Token)this.match(this.input, 36, FOLLOW_ESCREVAL_in_escreverStatement2384);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_ESCREVAL.add(string_literal138);
            }

            char_literal139 = (Token)this.match(this.input, 101, FOLLOW_101_in_escreverStatement2386);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal139);
            }

            this.pushFollow(FOLLOW_expressaoEscreva_in_escreverStatement2388);
            expressaoEscreva140 = this.expressaoEscreva();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressaoEscreva.add(expressaoEscreva140.getTree());
            }

            label184:
            while(true) {
               alt30 = 2;
               LA30_0 = this.input.LA(1);
               if (LA30_0 == 20) {
                  alt30 = 1;
               }

               switch(alt30) {
               case 1:
                  char_literal141 = (Token)this.match(this.input, 20, FOLLOW_CONCATENACAO_in_escreverStatement2391);
                  if (this.state.failed) {
                     return retval;
                  }

                  if (this.state.backtracking == 0) {
                     stream_CONCATENACAO.add(char_literal141);
                  }

                  this.pushFollow(FOLLOW_expressaoEscreva_in_escreverStatement2393);
                  expressaoEscreva142 = this.expressaoEscreva();
                  --this.state._fsp;
                  if (this.state.failed) {
                     return retval;
                  }

                  if (this.state.backtracking == 0) {
                     stream_expressaoEscreva.add(expressaoEscreva142.getTree());
                  }
                  break;
               default:
                  char_literal143 = (Token)this.match(this.input, 102, FOLLOW_102_in_escreverStatement2397);
                  if (this.state.failed) {
                     return retval;
                  }

                  if (this.state.backtracking == 0) {
                     stream_102.add(char_literal143);
                  }

                  if (this.state.backtracking == 0) {
                     retval.tree = root_0;
                     new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                     root_0 = (CommonTree)this.adaptor.nil();
                     root_1 = (CommonTree)this.adaptor.nil();
                     root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(36, (String)"ESCREVAL")), root_1);
                     this.adaptor.addChild(root_1, stream_expressaoEscreva.nextTree());

                     while(stream_expressaoEscreva.hasNext()) {
                        this.adaptor.addChild(root_1, stream_expressaoEscreva.nextTree());
                     }

                     stream_expressaoEscreva.reset();
                     this.adaptor.addChild(root_0, root_1);
                     retval.tree = root_0;
                  }
                  break label184;
               }
            }
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var33) {
         this.reportError(var33);
         this.recover(this.input, var33);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var33);
      }

      return retval;
   }

   public final CalangoGrammarParser.expressaoEscreva_return expressaoEscreva() throws RecognitionException {
      CalangoGrammarParser.expressaoEscreva_return retval = new CalangoGrammarParser.expressaoEscreva_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token total = null;
      Token decimal = null;
      Token char_literal146 = null;
      Token char_literal147 = null;
      Token char_literal149 = null;
      Token char_literal150 = null;
      CalangoGrammarParser.expressao_return expressao144 = null;
      CalangoGrammarParser.expressao_return expressao145 = null;
      CalangoGrammarParser.expressao_return expressao148 = null;
      CommonTree total_tree = null;
      CommonTree decimal_tree = null;
      CommonTree char_literal146_tree = null;
      CommonTree char_literal147_tree = null;
      CommonTree char_literal149_tree = null;
      CommonTree char_literal150_tree = null;
      RewriteRuleTokenStream stream_INTEIRO_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token INTEIRO_LITERAL");
      RewriteRuleTokenStream stream_103 = new RewriteRuleTokenStream(this.adaptor, "token 103");
      RewriteRuleSubtreeStream stream_expressao = new RewriteRuleSubtreeStream(this.adaptor, "rule expressao");

      try {
         int LA33_13;
         byte alt33;
         switch(this.input.LA(1)) {
         case 5:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 10:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 15:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 46:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 48:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 65:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 66:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 79:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 89:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 92:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 101:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 109:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 110:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 112:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 114:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 115:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 116:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 117:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 118:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 119:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 122:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 128:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 129:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 130:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 131:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 133:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 135:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         case 138:
            LA33_13 = this.input.LA(2);
            if (this.synpred3_CalangoGrammar()) {
               alt33 = 1;
            } else if (this.synpred4_CalangoGrammar()) {
               alt33 = 2;
            } else {
               alt33 = 3;
            }
            break;
         default:
            if (this.state.backtracking > 0) {
               this.state.failed = true;
               return retval;
            }

            NoViableAltException nvae = new NoViableAltException("", 33, 0, this.input);
            throw nvae;
         }

         label288:
         switch(alt33) {
         case 1:
            root_0 = (CommonTree)this.adaptor.nil();
            this.pushFollow(FOLLOW_expressao_in_expressaoEscreva2447);
            expressao144 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, expressao144.getTree());
            }
            break;
         case 2:
            this.pushFollow(FOLLOW_expressao_in_expressaoEscreva2465);
            expressao145 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao145.getTree());
            }

            char_literal146 = (Token)this.match(this.input, 103, FOLLOW_103_in_expressaoEscreva2467);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_103.add(char_literal146);
            }

            total = (Token)this.match(this.input, 48, FOLLOW_INTEIRO_LITERAL_in_expressaoEscreva2471);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_INTEIRO_LITERAL.add(total);
            }

            int alt32 = 2;
            int LA32_0 = this.input.LA(1);
            if (LA32_0 == 103) {
               alt32 = 1;
            }

            switch(alt32) {
            case 1:
               char_literal147 = (Token)this.match(this.input, 103, FOLLOW_103_in_expressaoEscreva2474);
               if (this.state.failed) {
                  return retval;
               }

               if (this.state.backtracking == 0) {
                  stream_103.add(char_literal147);
               }

               decimal = (Token)this.match(this.input, 48, FOLLOW_INTEIRO_LITERAL_in_expressaoEscreva2478);
               if (this.state.failed) {
                  return retval;
               }

               if (this.state.backtracking == 0) {
                  stream_INTEIRO_LITERAL.add(decimal);
               }
            default:
               if (this.state.backtracking == 0) {
                  retval.tree = root_0;
                  RewriteRuleTokenStream stream_total = new RewriteRuleTokenStream(this.adaptor, "token total", total);
                  RewriteRuleTokenStream stream_decimal = new RewriteRuleTokenStream(this.adaptor, "token decimal", decimal);
                  new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                  root_0 = (CommonTree)this.adaptor.nil();
                  CommonTree root_1 = (CommonTree)this.adaptor.nil();
                  root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(41, (String)"FORMATA_REAL")), root_1);
                  this.adaptor.addChild(root_1, stream_expressao.nextTree());
                  CommonTree root_2 = (CommonTree)this.adaptor.nil();
                  root_2 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(43, (String)"FORMATA_REAL_TOTAL")), root_2);
                  this.adaptor.addChild(root_2, stream_total.nextNode());
                  this.adaptor.addChild(root_1, root_2);
                  root_2 = (CommonTree)this.adaptor.nil();
                  root_2 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(42, (String)"FORMATA_REAL_DECIMAL")), root_2);
                  if (stream_decimal.hasNext()) {
                     this.adaptor.addChild(root_2, stream_decimal.nextNode());
                  }

                  stream_decimal.reset();
                  this.adaptor.addChild(root_1, root_2);
                  this.adaptor.addChild(root_0, root_1);
                  retval.tree = root_0;
               }
               break label288;
            }
         case 3:
            this.pushFollow(FOLLOW_expressao_in_expressaoEscreva2517);
            expressao148 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao148.getTree());
            }

            char_literal149 = (Token)this.match(this.input, 103, FOLLOW_103_in_expressaoEscreva2519);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_103.add(char_literal149);
            }

            char_literal150 = (Token)this.match(this.input, 103, FOLLOW_103_in_expressaoEscreva2521);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_103.add(char_literal150);
            }

            decimal = (Token)this.match(this.input, 48, FOLLOW_INTEIRO_LITERAL_in_expressaoEscreva2525);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_INTEIRO_LITERAL.add(decimal);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               RewriteRuleTokenStream stream_decimal = new RewriteRuleTokenStream(this.adaptor, "token decimal", decimal);
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               CommonTree root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(41, (String)"FORMATA_REAL")), root_1);
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               CommonTree root_2 = (CommonTree)this.adaptor.nil();
               root_2 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(43, (String)"FORMATA_REAL_TOTAL")), root_2);
               this.adaptor.addChild(root_1, root_2);
               root_2 = (CommonTree)this.adaptor.nil();
               root_2 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(42, (String)"FORMATA_REAL_DECIMAL")), root_2);
               if (stream_decimal.hasNext()) {
                  this.adaptor.addChild(root_2, stream_decimal.nextNode());
               }

               stream_decimal.reset();
               this.adaptor.addChild(root_1, root_2);
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var29) {
         this.reportError(var29);
         this.recover(this.input, var29);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var29);
      }

      return retval;
   }

   public final CalangoGrammarParser.interrompa_return interrompa() throws RecognitionException {
      CalangoGrammarParser.interrompa_return retval = new CalangoGrammarParser.interrompa_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token INTERROMPA151 = null;
      CommonTree INTERROMPA151_tree = null;

      try {
         root_0 = (CommonTree)this.adaptor.nil();
         INTERROMPA151 = (Token)this.match(this.input, 49, FOLLOW_INTERROMPA_in_interrompa2572);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            INTERROMPA151_tree = (CommonTree)this.adaptor.create(INTERROMPA151);
            this.adaptor.addChild(root_0, INTERROMPA151_tree);
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var6) {
         this.reportError(var6);
         this.recover(this.input, var6);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var6);
      }

      return retval;
   }

   public final CalangoGrammarParser.retornaStm_return retornaStm() throws RecognitionException {
      CalangoGrammarParser.retornaStm_return retval = new CalangoGrammarParser.retornaStm_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token string_literal152 = null;
      CalangoGrammarParser.expressao_return expressao153 = null;
      CommonTree string_literal152_tree = null;
      RewriteRuleTokenStream stream_RETORNA = new RewriteRuleTokenStream(this.adaptor, "token RETORNA");
      RewriteRuleSubtreeStream stream_expressao = new RewriteRuleSubtreeStream(this.adaptor, "rule expressao");

      try {
         string_literal152 = (Token)this.match(this.input, 81, FOLLOW_RETORNA_in_retornaStm2589);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_RETORNA.add(string_literal152);
         }

         this.pushFollow(FOLLOW_expressao_in_retornaStm2591);
         expressao153 = this.expressao();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_expressao.add(expressao153.getTree());
         }

         if (this.state.backtracking == 0) {
            retval.tree = root_0;
            new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
            root_0 = (CommonTree)this.adaptor.nil();
            CommonTree root_1 = (CommonTree)this.adaptor.nil();
            root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(81, (String)"RETORNA")), root_1);
            this.adaptor.addChild(root_1, stream_expressao.nextTree());
            this.adaptor.addChild(root_0, root_1);
            retval.tree = root_0;
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var10) {
         this.reportError(var10);
         this.recover(this.input, var10);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var10);
      }

      return retval;
   }

   public final CalangoGrammarParser.termo_return termo() throws RecognitionException {
      CalangoGrammarParser.termo_return retval = new CalangoGrammarParser.termo_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token IDENTIFICADOR154 = null;
      Token IDENTIFICADOR155 = null;
      Token char_literal156 = null;
      Token char_literal158 = null;
      Token char_literal159 = null;
      Token char_literal161 = null;
      Token char_literal162 = null;
      Token char_literal164 = null;
      Token TEXTO_LITERAL166 = null;
      Token CHAR_LITERAL167 = null;
      Token BOOL_LITERAL168 = null;
      Token INTEIRO_LITERAL169 = null;
      Token REAL_LITERAL170 = null;
      CalangoGrammarParser.expressao_return expressao157 = null;
      CalangoGrammarParser.expressao_return expressao160 = null;
      CalangoGrammarParser.expressao_return expressao163 = null;
      CalangoGrammarParser.chamadaRotina_return chamadaRotina165 = null;
      CalangoGrammarParser.funcoesEmbutidas_return funcoesEmbutidas171 = null;
      CommonTree IDENTIFICADOR154_tree = null;
      CommonTree IDENTIFICADOR155_tree = null;
      CommonTree char_literal156_tree = null;
      CommonTree char_literal158_tree = null;
      CommonTree char_literal159_tree = null;
      CommonTree char_literal161_tree = null;
      CommonTree char_literal162_tree = null;
      CommonTree char_literal164_tree = null;
      CommonTree TEXTO_LITERAL166_tree = null;
      CommonTree CHAR_LITERAL167_tree = null;
      CommonTree BOOL_LITERAL168_tree = null;
      CommonTree INTEIRO_LITERAL169_tree = null;
      CommonTree REAL_LITERAL170_tree = null;
      RewriteRuleTokenStream stream_108 = new RewriteRuleTokenStream(this.adaptor, "token 108");
      RewriteRuleTokenStream stream_106 = new RewriteRuleTokenStream(this.adaptor, "token 106");
      RewriteRuleTokenStream stream_IDENTIFICADOR = new RewriteRuleTokenStream(this.adaptor, "token IDENTIFICADOR");
      RewriteRuleSubtreeStream stream_expressao = new RewriteRuleSubtreeStream(this.adaptor, "rule expressao");

      try {
         byte alt35;
         NoViableAltException nvae;
         label225:
         switch(this.input.LA(1)) {
         case 10:
            alt35 = 7;
            break;
         case 15:
            alt35 = 6;
            break;
         case 46:
            switch(this.input.LA(2)) {
            case 5:
            case 20:
            case 27:
            case 29:
            case 30:
            case 31:
            case 47:
            case 54:
            case 55:
            case 58:
            case 59:
            case 62:
            case 63:
            case 64:
            case 68:
            case 89:
            case 102:
            case 103:
            case 104:
            case 108:
            case 113:
            case 132:
            case 140:
               alt35 = 1;
               break label225;
            case 101:
               alt35 = 4;
               break label225;
            case 106:
               alt35 = 2;
               break label225;
            default:
               if (this.state.backtracking > 0) {
                  this.state.failed = true;
                  return retval;
               }

               nvae = new NoViableAltException("", 35, 1, this.input);
               throw nvae;
            }
         case 48:
            alt35 = 8;
            break;
         case 79:
            alt35 = 9;
            break;
         case 92:
            alt35 = 5;
            break;
         case 101:
            alt35 = 3;
            break;
         case 109:
         case 110:
         case 112:
         case 114:
         case 115:
         case 116:
         case 117:
         case 118:
         case 119:
         case 122:
         case 128:
         case 129:
         case 130:
         case 131:
         case 133:
         case 135:
         case 138:
            alt35 = 10;
            break;
         default:
            if (this.state.backtracking > 0) {
               this.state.failed = true;
               return retval;
            }

            nvae = new NoViableAltException("", 35, 0, this.input);
            throw nvae;
         }

         label218:
         switch(alt35) {
         case 1:
            root_0 = (CommonTree)this.adaptor.nil();
            IDENTIFICADOR154 = (Token)this.match(this.input, 46, FOLLOW_IDENTIFICADOR_in_termo2629);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               IDENTIFICADOR154_tree = (CommonTree)this.adaptor.create(IDENTIFICADOR154);
               this.adaptor.addChild(root_0, IDENTIFICADOR154_tree);
            }
            break;
         case 2:
            IDENTIFICADOR155 = (Token)this.match(this.input, 46, FOLLOW_IDENTIFICADOR_in_termo2639);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_IDENTIFICADOR.add(IDENTIFICADOR155);
            }

            char_literal156 = (Token)this.match(this.input, 106, FOLLOW_106_in_termo2641);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_106.add(char_literal156);
            }

            this.pushFollow(FOLLOW_expressao_in_termo2643);
            expressao157 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao157.getTree());
            }

            char_literal158 = (Token)this.match(this.input, 108, FOLLOW_108_in_termo2645);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_108.add(char_literal158);
            }

            int alt34 = 2;
            int LA34_0 = this.input.LA(1);
            if (LA34_0 == 106) {
               alt34 = 1;
            }

            switch(alt34) {
            case 1:
               char_literal159 = (Token)this.match(this.input, 106, FOLLOW_106_in_termo2648);
               if (this.state.failed) {
                  return retval;
               }

               if (this.state.backtracking == 0) {
                  stream_106.add(char_literal159);
               }

               this.pushFollow(FOLLOW_expressao_in_termo2650);
               expressao160 = this.expressao();
               --this.state._fsp;
               if (this.state.failed) {
                  return retval;
               }

               if (this.state.backtracking == 0) {
                  stream_expressao.add(expressao160.getTree());
               }

               char_literal161 = (Token)this.match(this.input, 108, FOLLOW_108_in_termo2652);
               if (this.state.failed) {
                  return retval;
               }

               if (this.state.backtracking == 0) {
                  stream_108.add(char_literal161);
               }
            default:
               if (this.state.backtracking == 0) {
                  retval.tree = root_0;
                  new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                  root_0 = (CommonTree)this.adaptor.nil();
                  CommonTree root_1 = (CommonTree)this.adaptor.nil();
                  root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(91, (String)"TERMO_VETOR")), root_1);
                  this.adaptor.addChild(root_1, stream_IDENTIFICADOR.nextNode());
                  this.adaptor.addChild(root_1, stream_expressao.nextTree());
                  if (stream_expressao.hasNext()) {
                     this.adaptor.addChild(root_1, stream_expressao.nextTree());
                  }

                  stream_expressao.reset();
                  this.adaptor.addChild(root_0, root_1);
                  retval.tree = root_0;
               }
               break label218;
            }
         case 3:
            root_0 = (CommonTree)this.adaptor.nil();
            char_literal162 = (Token)this.match(this.input, 101, FOLLOW_101_in_termo2677);
            if (this.state.failed) {
               return retval;
            }

            this.pushFollow(FOLLOW_expressao_in_termo2680);
            expressao163 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, expressao163.getTree());
            }

            char_literal164 = (Token)this.match(this.input, 102, FOLLOW_102_in_termo2682);
            if (this.state.failed) {
               return retval;
            }
            break;
         case 4:
            root_0 = (CommonTree)this.adaptor.nil();
            this.pushFollow(FOLLOW_chamadaRotina_in_termo2693);
            chamadaRotina165 = this.chamadaRotina();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, chamadaRotina165.getTree());
            }
            break;
         case 5:
            root_0 = (CommonTree)this.adaptor.nil();
            TEXTO_LITERAL166 = (Token)this.match(this.input, 92, FOLLOW_TEXTO_LITERAL_in_termo2703);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               TEXTO_LITERAL166_tree = (CommonTree)this.adaptor.create(TEXTO_LITERAL166);
               this.adaptor.addChild(root_0, TEXTO_LITERAL166_tree);
            }
            break;
         case 6:
            root_0 = (CommonTree)this.adaptor.nil();
            CHAR_LITERAL167 = (Token)this.match(this.input, 15, FOLLOW_CHAR_LITERAL_in_termo2713);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               CHAR_LITERAL167_tree = (CommonTree)this.adaptor.create(CHAR_LITERAL167);
               this.adaptor.addChild(root_0, CHAR_LITERAL167_tree);
            }
            break;
         case 7:
            root_0 = (CommonTree)this.adaptor.nil();
            BOOL_LITERAL168 = (Token)this.match(this.input, 10, FOLLOW_BOOL_LITERAL_in_termo2723);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               BOOL_LITERAL168_tree = (CommonTree)this.adaptor.create(BOOL_LITERAL168);
               this.adaptor.addChild(root_0, BOOL_LITERAL168_tree);
            }
            break;
         case 8:
            root_0 = (CommonTree)this.adaptor.nil();
            INTEIRO_LITERAL169 = (Token)this.match(this.input, 48, FOLLOW_INTEIRO_LITERAL_in_termo2733);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               INTEIRO_LITERAL169_tree = (CommonTree)this.adaptor.create(INTEIRO_LITERAL169);
               this.adaptor.addChild(root_0, INTEIRO_LITERAL169_tree);
            }
            break;
         case 9:
            root_0 = (CommonTree)this.adaptor.nil();
            REAL_LITERAL170 = (Token)this.match(this.input, 79, FOLLOW_REAL_LITERAL_in_termo2743);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               REAL_LITERAL170_tree = (CommonTree)this.adaptor.create(REAL_LITERAL170);
               this.adaptor.addChild(root_0, REAL_LITERAL170_tree);
            }
            break;
         case 10:
            root_0 = (CommonTree)this.adaptor.nil();
            this.pushFollow(FOLLOW_funcoesEmbutidas_in_termo2753);
            funcoesEmbutidas171 = this.funcoesEmbutidas();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, funcoesEmbutidas171.getTree());
            }
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var43) {
         this.reportError(var43);
         this.recover(this.input, var43);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var43);
      }

      return retval;
   }

   public final CalangoGrammarParser.formataReal_return formataReal() throws RecognitionException {
      CalangoGrammarParser.formataReal_return retval = new CalangoGrammarParser.formataReal_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token total = null;
      Token decimal = null;
      Token char_literal172 = null;
      Token char_literal174 = null;
      Token char_literal175 = null;
      Token char_literal176 = null;
      CalangoGrammarParser.expressao_return expressao173 = null;
      CommonTree total_tree = null;
      CommonTree decimal_tree = null;
      CommonTree char_literal172_tree = null;
      CommonTree char_literal174_tree = null;
      CommonTree char_literal175_tree = null;
      CommonTree char_literal176_tree = null;
      RewriteRuleTokenStream stream_INTEIRO_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token INTEIRO_LITERAL");
      RewriteRuleTokenStream stream_139 = new RewriteRuleTokenStream(this.adaptor, "token 139");
      RewriteRuleTokenStream stream_103 = new RewriteRuleTokenStream(this.adaptor, "token 103");
      RewriteRuleTokenStream stream_140 = new RewriteRuleTokenStream(this.adaptor, "token 140");
      RewriteRuleSubtreeStream stream_expressao = new RewriteRuleSubtreeStream(this.adaptor, "rule expressao");

      try {
         char_literal172 = (Token)this.match(this.input, 139, FOLLOW_139_in_formataReal2774);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_139.add(char_literal172);
         }

         this.pushFollow(FOLLOW_expressao_in_formataReal2776);
         expressao173 = this.expressao();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_expressao.add(expressao173.getTree());
         }

         char_literal174 = (Token)this.match(this.input, 140, FOLLOW_140_in_formataReal2778);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_140.add(char_literal174);
         }

         char_literal175 = (Token)this.match(this.input, 103, FOLLOW_103_in_formataReal2780);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_103.add(char_literal175);
         }

         int alt36 = 2;
         int LA36_0 = this.input.LA(1);
         if (LA36_0 == 48) {
            alt36 = 1;
         }

         switch(alt36) {
         case 1:
            total = (Token)this.match(this.input, 48, FOLLOW_INTEIRO_LITERAL_in_formataReal2784);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_INTEIRO_LITERAL.add(total);
            }
         default:
            char_literal176 = (Token)this.match(this.input, 103, FOLLOW_103_in_formataReal2787);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_103.add(char_literal176);
            }

            int alt37 = 2;
            int LA37_0 = this.input.LA(1);
            if (LA37_0 == 48) {
               alt37 = 1;
            }

            switch(alt37) {
            case 1:
               decimal = (Token)this.match(this.input, 48, FOLLOW_INTEIRO_LITERAL_in_formataReal2791);
               if (this.state.failed) {
                  return retval;
               }

               if (this.state.backtracking == 0) {
                  stream_INTEIRO_LITERAL.add(decimal);
               }
            default:
               if (this.state.backtracking == 0) {
                  retval.tree = root_0;
                  RewriteRuleTokenStream stream_total = new RewriteRuleTokenStream(this.adaptor, "token total", total);
                  RewriteRuleTokenStream stream_decimal = new RewriteRuleTokenStream(this.adaptor, "token decimal", decimal);
                  new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                  root_0 = (CommonTree)this.adaptor.nil();
                  CommonTree root_1 = (CommonTree)this.adaptor.nil();
                  root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(41, (String)"FORMATA_REAL")), root_1);
                  this.adaptor.addChild(root_1, stream_expressao.nextTree());
                  CommonTree root_2 = (CommonTree)this.adaptor.nil();
                  root_2 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(43, (String)"FORMATA_REAL_TOTAL")), root_2);
                  if (stream_total.hasNext()) {
                     this.adaptor.addChild(root_2, stream_total.nextNode());
                  }

                  stream_total.reset();
                  this.adaptor.addChild(root_1, root_2);
                  root_2 = (CommonTree)this.adaptor.nil();
                  root_2 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(42, (String)"FORMATA_REAL_DECIMAL")), root_2);
                  if (stream_decimal.hasNext()) {
                     this.adaptor.addChild(root_2, stream_decimal.nextNode());
                  }

                  stream_decimal.reset();
                  this.adaptor.addChild(root_1, root_2);
                  this.adaptor.addChild(root_0, root_1);
                  retval.tree = root_0;
               }

               retval.stop = this.input.LT(-1);
               if (this.state.backtracking == 0) {
                  retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                  this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
               }
            }
         }
      } catch (RecognitionException var30) {
         this.reportError(var30);
         this.recover(this.input, var30);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var30);
      }

      return retval;
   }

   public final CalangoGrammarParser.funcoesEmbutidas_return funcoesEmbutidas() throws RecognitionException {
      CalangoGrammarParser.funcoesEmbutidas_return retval = new CalangoGrammarParser.funcoesEmbutidas_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token string_literal177 = null;
      Token char_literal178 = null;
      Token char_literal180 = null;
      Token char_literal182 = null;
      Token string_literal183 = null;
      Token char_literal184 = null;
      Token char_literal186 = null;
      Token string_literal187 = null;
      Token char_literal188 = null;
      Token char_literal190 = null;
      Token string_literal191 = null;
      Token char_literal192 = null;
      Token char_literal194 = null;
      Token char_literal196 = null;
      Token string_literal197 = null;
      Token char_literal198 = null;
      Token char_literal199 = null;
      Token string_literal200 = null;
      Token char_literal201 = null;
      Token char_literal203 = null;
      Token string_literal204 = null;
      Token char_literal205 = null;
      Token char_literal207 = null;
      Token string_literal208 = null;
      Token char_literal209 = null;
      Token char_literal211 = null;
      Token string_literal212 = null;
      Token char_literal213 = null;
      Token char_literal215 = null;
      Token string_literal216 = null;
      Token char_literal217 = null;
      Token char_literal219 = null;
      Token char_literal221 = null;
      Token char_literal223 = null;
      Token string_literal224 = null;
      Token char_literal225 = null;
      Token char_literal227 = null;
      Token string_literal228 = null;
      Token char_literal229 = null;
      Token char_literal231 = null;
      Token string_literal232 = null;
      Token char_literal233 = null;
      Token char_literal235 = null;
      Token string_literal236 = null;
      Token char_literal237 = null;
      Token char_literal239 = null;
      Token string_literal240 = null;
      Token char_literal241 = null;
      Token char_literal243 = null;
      Token char_literal245 = null;
      Token string_literal246 = null;
      Token char_literal247 = null;
      Token char_literal249 = null;
      Token string_literal250 = null;
      Token char_literal251 = null;
      Token char_literal253 = null;
      CalangoGrammarParser.expressao_return expressao179 = null;
      CalangoGrammarParser.expressao_return expressao181 = null;
      CalangoGrammarParser.expressao_return expressao185 = null;
      CalangoGrammarParser.expressao_return expressao189 = null;
      CalangoGrammarParser.expressao_return expressao193 = null;
      CalangoGrammarParser.expressao_return expressao195 = null;
      CalangoGrammarParser.expressao_return expressao202 = null;
      CalangoGrammarParser.expressao_return expressao206 = null;
      CalangoGrammarParser.expressao_return expressao210 = null;
      CalangoGrammarParser.expressao_return expressao214 = null;
      CalangoGrammarParser.expressao_return expressao218 = null;
      CalangoGrammarParser.expressao_return expressao220 = null;
      CalangoGrammarParser.expressao_return expressao222 = null;
      CalangoGrammarParser.expressao_return expressao226 = null;
      CalangoGrammarParser.expressao_return expressao230 = null;
      CalangoGrammarParser.expressao_return expressao234 = null;
      CalangoGrammarParser.expressao_return expressao238 = null;
      CalangoGrammarParser.expressao_return expressao242 = null;
      CalangoGrammarParser.expressao_return expressao244 = null;
      CalangoGrammarParser.expressao_return expressao248 = null;
      CalangoGrammarParser.expressao_return expressao252 = null;
      CommonTree string_literal177_tree = null;
      CommonTree char_literal178_tree = null;
      CommonTree char_literal180_tree = null;
      CommonTree char_literal182_tree = null;
      CommonTree string_literal183_tree = null;
      CommonTree char_literal184_tree = null;
      CommonTree char_literal186_tree = null;
      CommonTree string_literal187_tree = null;
      CommonTree char_literal188_tree = null;
      CommonTree char_literal190_tree = null;
      CommonTree string_literal191_tree = null;
      CommonTree char_literal192_tree = null;
      CommonTree char_literal194_tree = null;
      CommonTree char_literal196_tree = null;
      CommonTree string_literal197_tree = null;
      CommonTree char_literal198_tree = null;
      CommonTree char_literal199_tree = null;
      CommonTree string_literal200_tree = null;
      CommonTree char_literal201_tree = null;
      CommonTree char_literal203_tree = null;
      CommonTree string_literal204_tree = null;
      CommonTree char_literal205_tree = null;
      CommonTree char_literal207_tree = null;
      CommonTree string_literal208_tree = null;
      CommonTree char_literal209_tree = null;
      CommonTree char_literal211_tree = null;
      CommonTree string_literal212_tree = null;
      CommonTree char_literal213_tree = null;
      CommonTree char_literal215_tree = null;
      CommonTree string_literal216_tree = null;
      CommonTree char_literal217_tree = null;
      CommonTree char_literal219_tree = null;
      CommonTree char_literal221_tree = null;
      CommonTree char_literal223_tree = null;
      CommonTree string_literal224_tree = null;
      CommonTree char_literal225_tree = null;
      CommonTree char_literal227_tree = null;
      CommonTree string_literal228_tree = null;
      CommonTree char_literal229_tree = null;
      CommonTree char_literal231_tree = null;
      CommonTree string_literal232_tree = null;
      CommonTree char_literal233_tree = null;
      CommonTree char_literal235_tree = null;
      CommonTree string_literal236_tree = null;
      CommonTree char_literal237_tree = null;
      CommonTree char_literal239_tree = null;
      CommonTree string_literal240_tree = null;
      CommonTree char_literal241_tree = null;
      CommonTree char_literal243_tree = null;
      CommonTree char_literal245_tree = null;
      CommonTree string_literal246_tree = null;
      CommonTree char_literal247_tree = null;
      CommonTree char_literal249_tree = null;
      CommonTree string_literal250_tree = null;
      CommonTree char_literal251_tree = null;
      CommonTree char_literal253_tree = null;
      RewriteRuleTokenStream stream_116 = new RewriteRuleTokenStream(this.adaptor, "token 116");
      RewriteRuleTokenStream stream_117 = new RewriteRuleTokenStream(this.adaptor, "token 117");
      RewriteRuleTokenStream stream_135 = new RewriteRuleTokenStream(this.adaptor, "token 135");
      RewriteRuleTokenStream stream_114 = new RewriteRuleTokenStream(this.adaptor, "token 114");
      RewriteRuleTokenStream stream_115 = new RewriteRuleTokenStream(this.adaptor, "token 115");
      RewriteRuleTokenStream stream_133 = new RewriteRuleTokenStream(this.adaptor, "token 133");
      RewriteRuleTokenStream stream_128 = new RewriteRuleTokenStream(this.adaptor, "token 128");
      RewriteRuleTokenStream stream_112 = new RewriteRuleTokenStream(this.adaptor, "token 112");
      RewriteRuleTokenStream stream_138 = new RewriteRuleTokenStream(this.adaptor, "token 138");
      RewriteRuleTokenStream stream_122 = new RewriteRuleTokenStream(this.adaptor, "token 122");
      RewriteRuleTokenStream stream_110 = new RewriteRuleTokenStream(this.adaptor, "token 110");
      RewriteRuleTokenStream stream_CONCATENACAO = new RewriteRuleTokenStream(this.adaptor, "token CONCATENACAO");
      RewriteRuleTokenStream stream_129 = new RewriteRuleTokenStream(this.adaptor, "token 129");
      RewriteRuleTokenStream stream_118 = new RewriteRuleTokenStream(this.adaptor, "token 118");
      RewriteRuleTokenStream stream_119 = new RewriteRuleTokenStream(this.adaptor, "token 119");
      RewriteRuleTokenStream stream_109 = new RewriteRuleTokenStream(this.adaptor, "token 109");
      RewriteRuleTokenStream stream_131 = new RewriteRuleTokenStream(this.adaptor, "token 131");
      RewriteRuleTokenStream stream_102 = new RewriteRuleTokenStream(this.adaptor, "token 102");
      RewriteRuleTokenStream stream_101 = new RewriteRuleTokenStream(this.adaptor, "token 101");
      RewriteRuleTokenStream stream_130 = new RewriteRuleTokenStream(this.adaptor, "token 130");
      RewriteRuleSubtreeStream stream_expressao = new RewriteRuleSubtreeStream(this.adaptor, "rule expressao");

      try {
         byte alt38;
         switch(this.input.LA(1)) {
         case 109:
            alt38 = 3;
            break;
         case 110:
            alt38 = 7;
            break;
         case 111:
         case 113:
         case 120:
         case 121:
         case 123:
         case 124:
         case 125:
         case 126:
         case 127:
         case 132:
         case 134:
         case 136:
         case 137:
         default:
            if (this.state.backtracking > 0) {
               this.state.failed = true;
               return retval;
            }

            NoViableAltException nvae = new NoViableAltException("", 38, 0, this.input);
            throw nvae;
         case 112:
            alt38 = 13;
            break;
         case 114:
            alt38 = 14;
            break;
         case 115:
            alt38 = 15;
            break;
         case 116:
            alt38 = 1;
            break;
         case 117:
            alt38 = 17;
            break;
         case 118:
            alt38 = 16;
            break;
         case 119:
            alt38 = 10;
            break;
         case 122:
            alt38 = 4;
            break;
         case 128:
            alt38 = 8;
            break;
         case 129:
            alt38 = 11;
            break;
         case 130:
            alt38 = 9;
            break;
         case 131:
            alt38 = 12;
            break;
         case 133:
            alt38 = 5;
            break;
         case 135:
            alt38 = 6;
            break;
         case 138:
            alt38 = 2;
         }

         CommonTree root_1;
         switch(alt38) {
         case 1:
            string_literal177 = (Token)this.match(this.input, 116, FOLLOW_116_in_funcoesEmbutidas2850);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_116.add(string_literal177);
            }

            char_literal178 = (Token)this.match(this.input, 101, FOLLOW_101_in_funcoesEmbutidas2852);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal178);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas2854);
            expressao179 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao179.getTree());
            }

            char_literal180 = (Token)this.match(this.input, 20, FOLLOW_CONCATENACAO_in_funcoesEmbutidas2856);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_CONCATENACAO.add(char_literal180);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas2858);
            expressao181 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao181.getTree());
            }

            char_literal182 = (Token)this.match(this.input, 102, FOLLOW_102_in_funcoesEmbutidas2860);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal182);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(19, (String)"COMPARA_TEXTO")), root_1);
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 2:
            string_literal183 = (Token)this.match(this.input, 138, FOLLOW_138_in_funcoesEmbutidas2880);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_138.add(string_literal183);
            }

            char_literal184 = (Token)this.match(this.input, 101, FOLLOW_101_in_funcoesEmbutidas2882);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal184);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas2884);
            expressao185 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao185.getTree());
            }

            char_literal186 = (Token)this.match(this.input, 102, FOLLOW_102_in_funcoesEmbutidas2886);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal186);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(90, (String)"TAMANHO_TEXTO")), root_1);
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 3:
            string_literal187 = (Token)this.match(this.input, 109, FOLLOW_109_in_funcoesEmbutidas2904);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_109.add(string_literal187);
            }

            char_literal188 = (Token)this.match(this.input, 101, FOLLOW_101_in_funcoesEmbutidas2906);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal188);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas2908);
            expressao189 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao189.getTree());
            }

            char_literal190 = (Token)this.match(this.input, 102, FOLLOW_102_in_funcoesEmbutidas2910);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal190);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(4, (String)"ABS")), root_1);
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 4:
            string_literal191 = (Token)this.match(this.input, 122, FOLLOW_122_in_funcoesEmbutidas2928);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_122.add(string_literal191);
            }

            char_literal192 = (Token)this.match(this.input, 101, FOLLOW_101_in_funcoesEmbutidas2930);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal192);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas2932);
            expressao193 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao193.getTree());
            }

            char_literal194 = (Token)this.match(this.input, 20, FOLLOW_CONCATENACAO_in_funcoesEmbutidas2934);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_CONCATENACAO.add(char_literal194);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas2936);
            expressao195 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao195.getTree());
            }

            char_literal196 = (Token)this.match(this.input, 102, FOLLOW_102_in_funcoesEmbutidas2938);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal196);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(37, (String)"EXP")), root_1);
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 5:
            string_literal197 = (Token)this.match(this.input, 133, FOLLOW_133_in_funcoesEmbutidas2958);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_133.add(string_literal197);
            }

            char_literal198 = (Token)this.match(this.input, 101, FOLLOW_101_in_funcoesEmbutidas2960);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal198);
            }

            char_literal199 = (Token)this.match(this.input, 102, FOLLOW_102_in_funcoesEmbutidas2962);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal199);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(76, (String)"PI")), root_1);
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 6:
            string_literal200 = (Token)this.match(this.input, 135, FOLLOW_135_in_funcoesEmbutidas2978);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_135.add(string_literal200);
            }

            char_literal201 = (Token)this.match(this.input, 101, FOLLOW_101_in_funcoesEmbutidas2980);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal201);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas2982);
            expressao202 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao202.getTree());
            }

            char_literal203 = (Token)this.match(this.input, 102, FOLLOW_102_in_funcoesEmbutidas2984);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal203);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(82, (String)"RQUAD")), root_1);
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 7:
            string_literal204 = (Token)this.match(this.input, 110, FOLLOW_110_in_funcoesEmbutidas3002);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_110.add(string_literal204);
            }

            char_literal205 = (Token)this.match(this.input, 101, FOLLOW_101_in_funcoesEmbutidas3004);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal205);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas3006);
            expressao206 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao206.getTree());
            }

            char_literal207 = (Token)this.match(this.input, 102, FOLLOW_102_in_funcoesEmbutidas3008);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal207);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(6, (String)"ALEAT")), root_1);
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 8:
            string_literal208 = (Token)this.match(this.input, 128, FOLLOW_128_in_funcoesEmbutidas3026);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_128.add(string_literal208);
            }

            char_literal209 = (Token)this.match(this.input, 101, FOLLOW_101_in_funcoesEmbutidas3028);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal209);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas3030);
            expressao210 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao210.getTree());
            }

            char_literal211 = (Token)this.match(this.input, 102, FOLLOW_102_in_funcoesEmbutidas3032);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal211);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(56, (String)"MAIUSC")), root_1);
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 9:
            string_literal212 = (Token)this.match(this.input, 130, FOLLOW_130_in_funcoesEmbutidas3051);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_130.add(string_literal212);
            }

            char_literal213 = (Token)this.match(this.input, 101, FOLLOW_101_in_funcoesEmbutidas3053);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal213);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas3055);
            expressao214 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao214.getTree());
            }

            char_literal215 = (Token)this.match(this.input, 102, FOLLOW_102_in_funcoesEmbutidas3057);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal215);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(60, (String)"MINUSC")), root_1);
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 10:
            string_literal216 = (Token)this.match(this.input, 119, FOLLOW_119_in_funcoesEmbutidas3075);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_119.add(string_literal216);
            }

            char_literal217 = (Token)this.match(this.input, 101, FOLLOW_101_in_funcoesEmbutidas3077);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal217);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas3079);
            expressao218 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao218.getTree());
            }

            char_literal219 = (Token)this.match(this.input, 20, FOLLOW_CONCATENACAO_in_funcoesEmbutidas3081);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_CONCATENACAO.add(char_literal219);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas3083);
            expressao220 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao220.getTree());
            }

            char_literal221 = (Token)this.match(this.input, 20, FOLLOW_CONCATENACAO_in_funcoesEmbutidas3085);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_CONCATENACAO.add(char_literal221);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas3087);
            expressao222 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao222.getTree());
            }

            char_literal223 = (Token)this.match(this.input, 102, FOLLOW_102_in_funcoesEmbutidas3089);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal223);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(23, (String)"COPIA")), root_1);
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 11:
            string_literal224 = (Token)this.match(this.input, 129, FOLLOW_129_in_funcoesEmbutidas3111);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_129.add(string_literal224);
            }

            char_literal225 = (Token)this.match(this.input, 101, FOLLOW_101_in_funcoesEmbutidas3113);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal225);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas3115);
            expressao226 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao226.getTree());
            }

            char_literal227 = (Token)this.match(this.input, 102, FOLLOW_102_in_funcoesEmbutidas3117);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal227);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(57, (String)"MAIUSC_CHAR")), root_1);
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 12:
            string_literal228 = (Token)this.match(this.input, 131, FOLLOW_131_in_funcoesEmbutidas3135);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_131.add(string_literal228);
            }

            char_literal229 = (Token)this.match(this.input, 101, FOLLOW_101_in_funcoesEmbutidas3137);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal229);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas3139);
            expressao230 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao230.getTree());
            }

            char_literal231 = (Token)this.match(this.input, 102, FOLLOW_102_in_funcoesEmbutidas3141);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal231);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(61, (String)"MINUSC_CHAR")), root_1);
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 13:
            string_literal232 = (Token)this.match(this.input, 112, FOLLOW_112_in_funcoesEmbutidas3159);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_112.add(string_literal232);
            }

            char_literal233 = (Token)this.match(this.input, 101, FOLLOW_101_in_funcoesEmbutidas3161);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal233);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas3163);
            expressao234 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao234.getTree());
            }

            char_literal235 = (Token)this.match(this.input, 102, FOLLOW_102_in_funcoesEmbutidas3165);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal235);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(8, (String)"ASCII_CHAR")), root_1);
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 14:
            string_literal236 = (Token)this.match(this.input, 114, FOLLOW_114_in_funcoesEmbutidas3183);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_114.add(string_literal236);
            }

            char_literal237 = (Token)this.match(this.input, 101, FOLLOW_101_in_funcoesEmbutidas3185);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal237);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas3187);
            expressao238 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao238.getTree());
            }

            char_literal239 = (Token)this.match(this.input, 102, FOLLOW_102_in_funcoesEmbutidas3189);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal239);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(14, (String)"CHAR_ASCII")), root_1);
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 15:
            string_literal240 = (Token)this.match(this.input, 115, FOLLOW_115_in_funcoesEmbutidas3207);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_115.add(string_literal240);
            }

            char_literal241 = (Token)this.match(this.input, 101, FOLLOW_101_in_funcoesEmbutidas3209);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal241);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas3211);
            expressao242 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao242.getTree());
            }

            char_literal243 = (Token)this.match(this.input, 20, FOLLOW_CONCATENACAO_in_funcoesEmbutidas3213);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_CONCATENACAO.add(char_literal243);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas3215);
            expressao244 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao244.getTree());
            }

            char_literal245 = (Token)this.match(this.input, 102, FOLLOW_102_in_funcoesEmbutidas3217);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal245);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(16, (String)"CHAR_TEXTO")), root_1);
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 16:
            string_literal246 = (Token)this.match(this.input, 118, FOLLOW_118_in_funcoesEmbutidas3237);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_118.add(string_literal246);
            }

            char_literal247 = (Token)this.match(this.input, 101, FOLLOW_101_in_funcoesEmbutidas3239);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal247);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas3241);
            expressao248 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao248.getTree());
            }

            char_literal249 = (Token)this.match(this.input, 102, FOLLOW_102_in_funcoesEmbutidas3243);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal249);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(22, (String)"CONV_TEXTO_CHAR")), root_1);
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 17:
            string_literal250 = (Token)this.match(this.input, 117, FOLLOW_117_in_funcoesEmbutidas3261);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_117.add(string_literal250);
            }

            char_literal251 = (Token)this.match(this.input, 101, FOLLOW_101_in_funcoesEmbutidas3263);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_101.add(char_literal251);
            }

            this.pushFollow(FOLLOW_expressao_in_funcoesEmbutidas3265);
            expressao252 = this.expressao();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao252.getTree());
            }

            char_literal253 = (Token)this.match(this.input, 102, FOLLOW_102_in_funcoesEmbutidas3267);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_102.add(char_literal253);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(21, (String)"CONV_CHAR_TEXTO")), root_1);
               this.adaptor.addChild(root_1, stream_expressao.nextTree());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var160) {
         this.reportError(var160);
         this.recover(this.input, var160);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var160);
      }

      return retval;
   }

   public final CalangoGrammarParser.negacao_return negacao() throws RecognitionException {
      CalangoGrammarParser.negacao_return retval = new CalangoGrammarParser.negacao_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token NEGACAO_LOGICA254 = null;
      Token NEGACAO_LOGICA2255 = null;
      CalangoGrammarParser.termo_return termo256 = null;
      CommonTree NEGACAO_LOGICA254_tree = null;
      CommonTree NEGACAO_LOGICA2255_tree = null;

      try {
         root_0 = (CommonTree)this.adaptor.nil();

         while(true) {
            int alt39 = 3;
            int LA39_0 = this.input.LA(1);
            if (LA39_0 == 65) {
               alt39 = 1;
            } else if (LA39_0 == 66) {
               alt39 = 2;
            }

            switch(alt39) {
            case 1:
               NEGACAO_LOGICA254 = (Token)this.match(this.input, 65, FOLLOW_NEGACAO_LOGICA_in_negacao3295);
               if (this.state.failed) {
                  return retval;
               }

               if (this.state.backtracking == 0) {
                  NEGACAO_LOGICA254_tree = (CommonTree)this.adaptor.create(NEGACAO_LOGICA254);
                  root_0 = (CommonTree)this.adaptor.becomeRoot((Object)NEGACAO_LOGICA254_tree, root_0);
               }
               break;
            case 2:
               NEGACAO_LOGICA2255 = (Token)this.match(this.input, 66, FOLLOW_NEGACAO_LOGICA2_in_negacao3300);
               if (this.state.failed) {
                  return retval;
               }

               if (this.state.backtracking == 0) {
                  NEGACAO_LOGICA2255_tree = (CommonTree)this.adaptor.create(NEGACAO_LOGICA2255);
                  root_0 = (CommonTree)this.adaptor.becomeRoot((Object)NEGACAO_LOGICA2255_tree, root_0);
               }
               break;
            default:
               this.pushFollow(FOLLOW_termo_in_negacao3305);
               termo256 = this.termo();
               --this.state._fsp;
               if (this.state.failed) {
                  return retval;
               }

               if (this.state.backtracking == 0) {
                  this.adaptor.addChild(root_0, termo256.getTree());
               }

               retval.stop = this.input.LT(-1);
               if (this.state.backtracking == 0) {
                  retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                  this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
               }

               return retval;
            }
         }
      } catch (RecognitionException var10) {
         this.reportError(var10);
         this.recover(this.input, var10);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var10);
         return retval;
      }
   }

   public final CalangoGrammarParser.unario_return unario() throws RecognitionException {
      CalangoGrammarParser.unario_return retval = new CalangoGrammarParser.unario_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token char_literal257 = null;
      CalangoGrammarParser.negacao_matematica_return negacao_matematica258 = null;
      CalangoGrammarParser.negacao_return negacao259 = null;
      Object var6 = null;

      try {
         root_0 = (CommonTree)this.adaptor.nil();
         int alt40 = 3;
         int LA40_0 = this.input.LA(1);
         if (LA40_0 == 5) {
            alt40 = 1;
         } else if (LA40_0 == 89) {
            alt40 = 2;
         }

         switch(alt40) {
         case 1:
            char_literal257 = (Token)this.match(this.input, 5, FOLLOW_ADICAO_in_unario3329);
            if (this.state.failed) {
               return retval;
            }
            break;
         case 2:
            this.pushFollow(FOLLOW_negacao_matematica_in_unario3334);
            negacao_matematica258 = this.negacao_matematica();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               root_0 = (CommonTree)this.adaptor.becomeRoot((Object)negacao_matematica258.getTree(), root_0);
            }
         }

         this.pushFollow(FOLLOW_negacao_in_unario3339);
         negacao259 = this.negacao();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            this.adaptor.addChild(root_0, negacao259.getTree());
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var9) {
         this.reportError(var9);
         this.recover(this.input, var9);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var9);
      }

      return retval;
   }

   public final CalangoGrammarParser.negacao_matematica_return negacao_matematica() throws RecognitionException {
      CalangoGrammarParser.negacao_matematica_return retval = new CalangoGrammarParser.negacao_matematica_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token char_literal260 = null;
      CommonTree char_literal260_tree = null;
      RewriteRuleTokenStream stream_SUBTRACAO = new RewriteRuleTokenStream(this.adaptor, "token SUBTRACAO");

      try {
         char_literal260 = (Token)this.match(this.input, 89, FOLLOW_SUBTRACAO_in_negacao_matematica3360);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_SUBTRACAO.add(char_literal260);
         }

         if (this.state.backtracking == 0) {
            retval.tree = root_0;
            new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
            root_0 = (CommonTree)this.adaptor.nil();
            this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(67, (String)"NEGACAO_MATEMATICA"));
            retval.tree = root_0;
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var7) {
         this.reportError(var7);
         this.recover(this.input, var7);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var7);
      }

      return retval;
   }

   public final CalangoGrammarParser.mult_return mult() throws RecognitionException {
      CalangoGrammarParser.mult_return retval = new CalangoGrammarParser.mult_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token MULTIPLICACAO262 = null;
      Token DIVISAO263 = null;
      Token DIVISAO_INT264 = null;
      Token MOD265 = null;
      Token MOD2266 = null;
      CalangoGrammarParser.unario_return unario261 = null;
      CalangoGrammarParser.unario_return unario267 = null;
      CommonTree MULTIPLICACAO262_tree = null;
      CommonTree DIVISAO263_tree = null;
      CommonTree DIVISAO_INT264_tree = null;
      CommonTree MOD265_tree = null;
      CommonTree MOD2266_tree = null;

      try {
         root_0 = (CommonTree)this.adaptor.nil();
         this.pushFollow(FOLLOW_unario_in_mult3386);
         unario261 = this.unario();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         } else {
            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, unario261.getTree());
            }

            while(true) {
               int alt42 = 2;
               int LA42_0 = this.input.LA(1);
               if (LA42_0 >= 29 && LA42_0 <= 30 || LA42_0 >= 62 && LA42_0 <= 64) {
                  alt42 = 1;
               }

               switch(alt42) {
               case 1:
                  byte alt41;
                  switch(this.input.LA(1)) {
                  case 29:
                     alt41 = 2;
                     break;
                  case 30:
                     alt41 = 3;
                     break;
                  case 62:
                     alt41 = 4;
                     break;
                  case 63:
                     alt41 = 5;
                     break;
                  case 64:
                     alt41 = 1;
                     break;
                  default:
                     if (this.state.backtracking > 0) {
                        this.state.failed = true;
                        return retval;
                     }

                     NoViableAltException nvae = new NoViableAltException("", 41, 0, this.input);
                     throw nvae;
                  }

                  switch(alt41) {
                  case 1:
                     MULTIPLICACAO262 = (Token)this.match(this.input, 64, FOLLOW_MULTIPLICACAO_in_mult3390);
                     if (this.state.failed) {
                        return retval;
                     }

                     if (this.state.backtracking == 0) {
                        MULTIPLICACAO262_tree = (CommonTree)this.adaptor.create(MULTIPLICACAO262);
                        root_0 = (CommonTree)this.adaptor.becomeRoot((Object)MULTIPLICACAO262_tree, root_0);
                     }
                     break;
                  case 2:
                     DIVISAO263 = (Token)this.match(this.input, 29, FOLLOW_DIVISAO_in_mult3395);
                     if (this.state.failed) {
                        return retval;
                     }

                     if (this.state.backtracking == 0) {
                        DIVISAO263_tree = (CommonTree)this.adaptor.create(DIVISAO263);
                        root_0 = (CommonTree)this.adaptor.becomeRoot((Object)DIVISAO263_tree, root_0);
                     }
                     break;
                  case 3:
                     DIVISAO_INT264 = (Token)this.match(this.input, 30, FOLLOW_DIVISAO_INT_in_mult3400);
                     if (this.state.failed) {
                        return retval;
                     }

                     if (this.state.backtracking == 0) {
                        DIVISAO_INT264_tree = (CommonTree)this.adaptor.create(DIVISAO_INT264);
                        root_0 = (CommonTree)this.adaptor.becomeRoot((Object)DIVISAO_INT264_tree, root_0);
                     }
                     break;
                  case 4:
                     MOD265 = (Token)this.match(this.input, 62, FOLLOW_MOD_in_mult3405);
                     if (this.state.failed) {
                        return retval;
                     }

                     if (this.state.backtracking == 0) {
                        MOD265_tree = (CommonTree)this.adaptor.create(MOD265);
                        root_0 = (CommonTree)this.adaptor.becomeRoot((Object)MOD265_tree, root_0);
                     }
                     break;
                  case 5:
                     MOD2266 = (Token)this.match(this.input, 63, FOLLOW_MOD2_in_mult3410);
                     if (this.state.failed) {
                        return retval;
                     }

                     if (this.state.backtracking == 0) {
                        MOD2266_tree = (CommonTree)this.adaptor.create(MOD2266);
                        root_0 = (CommonTree)this.adaptor.becomeRoot((Object)MOD2266_tree, root_0);
                     }
                  }

                  this.pushFollow(FOLLOW_unario_in_mult3414);
                  unario267 = this.unario();
                  --this.state._fsp;
                  if (this.state.failed) {
                     return retval;
                  }

                  if (this.state.backtracking == 0) {
                     this.adaptor.addChild(root_0, unario267.getTree());
                  }
                  break;
               default:
                  retval.stop = this.input.LT(-1);
                  if (this.state.backtracking == 0) {
                     retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                     this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                  }

                  return retval;
               }
            }
         }
      } catch (RecognitionException var19) {
         this.reportError(var19);
         this.recover(this.input, var19);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var19);
         return retval;
      }
   }

   public final CalangoGrammarParser.soma_return soma() throws RecognitionException {
      CalangoGrammarParser.soma_return retval = new CalangoGrammarParser.soma_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token ADICAO269 = null;
      Token SUBTRACAO270 = null;
      CalangoGrammarParser.mult_return mult268 = null;
      CalangoGrammarParser.mult_return mult271 = null;
      CommonTree ADICAO269_tree = null;
      CommonTree SUBTRACAO270_tree = null;

      try {
         root_0 = (CommonTree)this.adaptor.nil();
         this.pushFollow(FOLLOW_mult_in_soma3439);
         mult268 = this.mult();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         } else {
            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, mult268.getTree());
            }

            while(true) {
               int alt44 = 2;
               int LA44_0 = this.input.LA(1);
               if (LA44_0 == 5 || LA44_0 == 89) {
                  alt44 = 1;
               }

               switch(alt44) {
               case 1:
                  int LA43_0 = this.input.LA(1);
                  byte alt43;
                  if (LA43_0 == 5) {
                     alt43 = 1;
                  } else {
                     if (LA43_0 != 89) {
                        if (this.state.backtracking > 0) {
                           this.state.failed = true;
                           return retval;
                        }

                        NoViableAltException nvae = new NoViableAltException("", 43, 0, this.input);
                        throw nvae;
                     }

                     alt43 = 2;
                  }

                  switch(alt43) {
                  case 1:
                     ADICAO269 = (Token)this.match(this.input, 5, FOLLOW_ADICAO_in_soma3443);
                     if (this.state.failed) {
                        return retval;
                     }

                     if (this.state.backtracking == 0) {
                        ADICAO269_tree = (CommonTree)this.adaptor.create(ADICAO269);
                        root_0 = (CommonTree)this.adaptor.becomeRoot((Object)ADICAO269_tree, root_0);
                     }
                     break;
                  case 2:
                     SUBTRACAO270 = (Token)this.match(this.input, 89, FOLLOW_SUBTRACAO_in_soma3448);
                     if (this.state.failed) {
                        return retval;
                     }

                     if (this.state.backtracking == 0) {
                        SUBTRACAO270_tree = (CommonTree)this.adaptor.create(SUBTRACAO270);
                        root_0 = (CommonTree)this.adaptor.becomeRoot((Object)SUBTRACAO270_tree, root_0);
                     }
                  }

                  this.pushFollow(FOLLOW_mult_in_soma3452);
                  mult271 = this.mult();
                  --this.state._fsp;
                  if (this.state.failed) {
                     return retval;
                  }

                  if (this.state.backtracking == 0) {
                     this.adaptor.addChild(root_0, mult271.getTree());
                  }
                  break;
               default:
                  retval.stop = this.input.LT(-1);
                  if (this.state.backtracking == 0) {
                     retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                     this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                  }

                  return retval;
               }
            }
         }
      } catch (RecognitionException var14) {
         this.reportError(var14);
         this.recover(this.input, var14);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var14);
         return retval;
      }
   }

   public final CalangoGrammarParser.comparacao_return comparacao() throws RecognitionException {
      CalangoGrammarParser.comparacao_return retval = new CalangoGrammarParser.comparacao_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token IGUAL273 = null;
      Token DIFERENTE274 = null;
      Token MENOR275 = null;
      Token MENOR_IGUAL276 = null;
      Token MAIOR_IGUAL277 = null;
      Token MAIOR278 = null;
      CalangoGrammarParser.soma_return soma272 = null;
      CalangoGrammarParser.soma_return soma279 = null;
      CommonTree IGUAL273_tree = null;
      CommonTree DIFERENTE274_tree = null;
      CommonTree MENOR275_tree = null;
      CommonTree MENOR_IGUAL276_tree = null;
      CommonTree MAIOR_IGUAL277_tree = null;
      CommonTree MAIOR278_tree = null;

      try {
         root_0 = (CommonTree)this.adaptor.nil();
         this.pushFollow(FOLLOW_soma_in_comparacao3473);
         soma272 = this.soma();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         } else {
            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, soma272.getTree());
            }

            while(true) {
               int alt46 = 2;
               int LA46_0 = this.input.LA(1);
               if (LA46_0 == 27 || LA46_0 == 47 || LA46_0 >= 54 && LA46_0 <= 55 || LA46_0 >= 58 && LA46_0 <= 59) {
                  alt46 = 1;
               }

               switch(alt46) {
               case 1:
                  byte alt45;
                  switch(this.input.LA(1)) {
                  case 27:
                     alt45 = 2;
                     break;
                  case 47:
                     alt45 = 1;
                     break;
                  case 54:
                     alt45 = 6;
                     break;
                  case 55:
                     alt45 = 5;
                     break;
                  case 58:
                     alt45 = 3;
                     break;
                  case 59:
                     alt45 = 4;
                     break;
                  default:
                     if (this.state.backtracking > 0) {
                        this.state.failed = true;
                        return retval;
                     }

                     NoViableAltException nvae = new NoViableAltException("", 45, 0, this.input);
                     throw nvae;
                  }

                  switch(alt45) {
                  case 1:
                     IGUAL273 = (Token)this.match(this.input, 47, FOLLOW_IGUAL_in_comparacao3477);
                     if (this.state.failed) {
                        return retval;
                     }

                     if (this.state.backtracking == 0) {
                        IGUAL273_tree = (CommonTree)this.adaptor.create(IGUAL273);
                        root_0 = (CommonTree)this.adaptor.becomeRoot((Object)IGUAL273_tree, root_0);
                     }
                     break;
                  case 2:
                     DIFERENTE274 = (Token)this.match(this.input, 27, FOLLOW_DIFERENTE_in_comparacao3482);
                     if (this.state.failed) {
                        return retval;
                     }

                     if (this.state.backtracking == 0) {
                        DIFERENTE274_tree = (CommonTree)this.adaptor.create(DIFERENTE274);
                        root_0 = (CommonTree)this.adaptor.becomeRoot((Object)DIFERENTE274_tree, root_0);
                     }
                     break;
                  case 3:
                     MENOR275 = (Token)this.match(this.input, 58, FOLLOW_MENOR_in_comparacao3487);
                     if (this.state.failed) {
                        return retval;
                     }

                     if (this.state.backtracking == 0) {
                        MENOR275_tree = (CommonTree)this.adaptor.create(MENOR275);
                        root_0 = (CommonTree)this.adaptor.becomeRoot((Object)MENOR275_tree, root_0);
                     }
                     break;
                  case 4:
                     MENOR_IGUAL276 = (Token)this.match(this.input, 59, FOLLOW_MENOR_IGUAL_in_comparacao3492);
                     if (this.state.failed) {
                        return retval;
                     }

                     if (this.state.backtracking == 0) {
                        MENOR_IGUAL276_tree = (CommonTree)this.adaptor.create(MENOR_IGUAL276);
                        root_0 = (CommonTree)this.adaptor.becomeRoot((Object)MENOR_IGUAL276_tree, root_0);
                     }
                     break;
                  case 5:
                     MAIOR_IGUAL277 = (Token)this.match(this.input, 55, FOLLOW_MAIOR_IGUAL_in_comparacao3497);
                     if (this.state.failed) {
                        return retval;
                     }

                     if (this.state.backtracking == 0) {
                        MAIOR_IGUAL277_tree = (CommonTree)this.adaptor.create(MAIOR_IGUAL277);
                        root_0 = (CommonTree)this.adaptor.becomeRoot((Object)MAIOR_IGUAL277_tree, root_0);
                     }
                     break;
                  case 6:
                     MAIOR278 = (Token)this.match(this.input, 54, FOLLOW_MAIOR_in_comparacao3502);
                     if (this.state.failed) {
                        return retval;
                     }

                     if (this.state.backtracking == 0) {
                        MAIOR278_tree = (CommonTree)this.adaptor.create(MAIOR278);
                        root_0 = (CommonTree)this.adaptor.becomeRoot((Object)MAIOR278_tree, root_0);
                     }
                  }

                  this.pushFollow(FOLLOW_soma_in_comparacao3506);
                  soma279 = this.soma();
                  --this.state._fsp;
                  if (this.state.failed) {
                     return retval;
                  }

                  if (this.state.backtracking == 0) {
                     this.adaptor.addChild(root_0, soma279.getTree());
                  }
                  break;
               default:
                  retval.stop = this.input.LT(-1);
                  if (this.state.backtracking == 0) {
                     retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                     this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                  }

                  return retval;
               }
            }
         }
      } catch (RecognitionException var21) {
         this.reportError(var21);
         this.recover(this.input, var21);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var21);
         return retval;
      }
   }

   public final CalangoGrammarParser.expressao_return expressao() throws RecognitionException {
      CalangoGrammarParser.expressao_return retval = new CalangoGrammarParser.expressao_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token E281 = null;
      Token OU282 = null;
      CalangoGrammarParser.comparacao_return comparacao280 = null;
      CalangoGrammarParser.comparacao_return comparacao283 = null;
      CommonTree E281_tree = null;
      CommonTree OU282_tree = null;

      try {
         root_0 = (CommonTree)this.adaptor.nil();
         this.pushFollow(FOLLOW_comparacao_in_expressao3534);
         comparacao280 = this.comparacao();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         } else {
            if (this.state.backtracking == 0) {
               this.adaptor.addChild(root_0, comparacao280.getTree());
            }

            while(true) {
               int alt48 = 2;
               int LA48_0 = this.input.LA(1);
               if (LA48_0 == 31 || LA48_0 == 68) {
                  alt48 = 1;
               }

               switch(alt48) {
               case 1:
                  int LA47_0 = this.input.LA(1);
                  byte alt47;
                  if (LA47_0 == 31) {
                     alt47 = 1;
                  } else {
                     if (LA47_0 != 68) {
                        if (this.state.backtracking > 0) {
                           this.state.failed = true;
                           return retval;
                        }

                        NoViableAltException nvae = new NoViableAltException("", 47, 0, this.input);
                        throw nvae;
                     }

                     alt47 = 2;
                  }

                  switch(alt47) {
                  case 1:
                     E281 = (Token)this.match(this.input, 31, FOLLOW_E_in_expressao3538);
                     if (this.state.failed) {
                        return retval;
                     }

                     if (this.state.backtracking == 0) {
                        E281_tree = (CommonTree)this.adaptor.create(E281);
                        root_0 = (CommonTree)this.adaptor.becomeRoot((Object)E281_tree, root_0);
                     }
                     break;
                  case 2:
                     OU282 = (Token)this.match(this.input, 68, FOLLOW_OU_in_expressao3543);
                     if (this.state.failed) {
                        return retval;
                     }

                     if (this.state.backtracking == 0) {
                        OU282_tree = (CommonTree)this.adaptor.create(OU282);
                        root_0 = (CommonTree)this.adaptor.becomeRoot((Object)OU282_tree, root_0);
                     }
                  }

                  this.pushFollow(FOLLOW_comparacao_in_expressao3547);
                  comparacao283 = this.comparacao();
                  --this.state._fsp;
                  if (this.state.failed) {
                     return retval;
                  }

                  if (this.state.backtracking == 0) {
                     this.adaptor.addChild(root_0, comparacao283.getTree());
                  }
                  break;
               default:
                  retval.stop = this.input.LT(-1);
                  if (this.state.backtracking == 0) {
                     retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                     this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                  }

                  return retval;
               }
            }
         }
      } catch (RecognitionException var14) {
         this.reportError(var14);
         this.recover(this.input, var14);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var14);
         return retval;
      }
   }

   public final CalangoGrammarParser.tipo_dado_return tipo_dado() throws RecognitionException {
      CalangoGrammarParser.tipo_dado_return retval = new CalangoGrammarParser.tipo_dado_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token set284 = null;
      Object var4 = null;

      try {
         root_0 = (CommonTree)this.adaptor.nil();
         set284 = this.input.LT(1);
         if (this.input.LA(1) != 93 && (this.input.LA(1) < 95 || this.input.LA(1) > 98)) {
            if (this.state.backtracking > 0) {
               this.state.failed = true;
               return retval;
            }

            MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
            throw mse;
         }

         this.input.consume();
         if (this.state.backtracking == 0) {
            this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(set284));
         }

         this.state.errorRecovery = false;
         this.state.failed = false;
         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var6) {
         this.reportError(var6);
         this.recover(this.input, var6);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var6);
      }

      return retval;
   }

   public final CalangoGrammarParser.decl_variaveis_return decl_variaveis() throws RecognitionException {
      CalangoGrammarParser.decl_variaveis_return retval = new CalangoGrammarParser.decl_variaveis_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token char_literal286 = null;
      CalangoGrammarParser.decl_variavel_return decl_variavel285 = null;
      Object var5 = null;

      try {
         root_0 = (CommonTree)this.adaptor.nil();
         this.pushFollow(FOLLOW_decl_variavel_in_decl_variaveis3620);
         decl_variavel285 = this.decl_variavel();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            root_0 = (CommonTree)this.adaptor.becomeRoot((Object)decl_variavel285.getTree(), root_0);
         }

         char_literal286 = (Token)this.match(this.input, 104, FOLLOW_104_in_decl_variaveis3623);
         if (this.state.failed) {
            return retval;
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var7) {
         this.reportError(var7);
         this.recover(this.input, var7);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var7);
      }

      return retval;
   }

   public final CalangoGrammarParser.decl_variavel_return decl_variavel() throws RecognitionException {
      CalangoGrammarParser.decl_variavel_return retval = new CalangoGrammarParser.decl_variavel_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CalangoGrammarParser.tipo_dado_return tipoDado = null;
      CalangoGrammarParser.identificadores_return identificadores287 = null;
      RewriteRuleSubtreeStream stream_identificadores = new RewriteRuleSubtreeStream(this.adaptor, "rule identificadores");
      RewriteRuleSubtreeStream stream_tipo_dado = new RewriteRuleSubtreeStream(this.adaptor, "rule tipo_dado");

      try {
         this.pushFollow(FOLLOW_tipo_dado_in_decl_variavel3646);
         tipoDado = this.tipo_dado();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_tipo_dado.add(tipoDado.getTree());
         }

         this.pushFollow(FOLLOW_identificadores_in_decl_variavel3648);
         identificadores287 = this.identificadores();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_identificadores.add(identificadores287.getTree());
         }

         if (this.state.backtracking == 0) {
            retval.tree = root_0;
            new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
            root_0 = (CommonTree)this.adaptor.nil();
            CommonTree root_1 = (CommonTree)this.adaptor.nil();
            root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(26, (String)"DECL_VARIAVEL")), root_1);
            this.adaptor.addChild(root_1, stream_tipo_dado.nextTree());
            this.adaptor.addChild(root_1, stream_identificadores.nextTree());
            this.adaptor.addChild(root_0, root_1);
            retval.tree = root_0;
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var9) {
         this.reportError(var9);
         this.recover(this.input, var9);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var9);
      }

      return retval;
   }

   public final CalangoGrammarParser.identificadores_return identificadores() throws RecognitionException {
      CalangoGrammarParser.identificadores_return retval = new CalangoGrammarParser.identificadores_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token vetor = null;
      Token matriz = null;
      Token IDENTIFICADOR288 = null;
      Token char_literal289 = null;
      Token char_literal290 = null;
      Token char_literal291 = null;
      Token char_literal292 = null;
      Token char_literal293 = null;
      Token char_literal294 = null;
      Token char_literal295 = null;
      CalangoGrammarParser.identificadores_return identificadores296 = null;
      CommonTree vetor_tree = null;
      CommonTree matriz_tree = null;
      CommonTree IDENTIFICADOR288_tree = null;
      CommonTree char_literal289_tree = null;
      CommonTree char_literal290_tree = null;
      CommonTree char_literal291_tree = null;
      CommonTree char_literal292_tree = null;
      CommonTree char_literal293_tree = null;
      CommonTree char_literal294_tree = null;
      CommonTree char_literal295_tree = null;
      RewriteRuleTokenStream stream_INTEIRO_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token INTEIRO_LITERAL");
      RewriteRuleTokenStream stream_108 = new RewriteRuleTokenStream(this.adaptor, "token 108");
      RewriteRuleTokenStream stream_CONCATENACAO = new RewriteRuleTokenStream(this.adaptor, "token CONCATENACAO");
      RewriteRuleTokenStream stream_106 = new RewriteRuleTokenStream(this.adaptor, "token 106");
      RewriteRuleTokenStream stream_IDENTIFICADOR = new RewriteRuleTokenStream(this.adaptor, "token IDENTIFICADOR");
      RewriteRuleSubtreeStream stream_identificadores = new RewriteRuleSubtreeStream(this.adaptor, "rule identificadores");

      try {
         IDENTIFICADOR288 = (Token)this.match(this.input, 46, FOLLOW_IDENTIFICADOR_in_identificadores3689);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_IDENTIFICADOR.add(IDENTIFICADOR288);
         }

         int alt49 = 3;
         int LA49_0 = this.input.LA(1);
         int LA50_0;
         if (LA49_0 == 106) {
            int LA49_1 = this.input.LA(2);
            if (LA49_1 == 48) {
               LA50_0 = this.input.LA(3);
               if (LA50_0 == 108) {
                  int LA49_4 = this.input.LA(4);
                  if (LA49_4 == 106) {
                     alt49 = 2;
                  } else if (LA49_4 == 20 || LA49_4 == 104) {
                     alt49 = 1;
                  }
               }
            }
         }

         switch(alt49) {
         case 1:
            char_literal289 = (Token)this.match(this.input, 106, FOLLOW_106_in_identificadores3693);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_106.add(char_literal289);
            }

            vetor = (Token)this.match(this.input, 48, FOLLOW_INTEIRO_LITERAL_in_identificadores3697);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_INTEIRO_LITERAL.add(vetor);
            }

            char_literal290 = (Token)this.match(this.input, 108, FOLLOW_108_in_identificadores3699);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_108.add(char_literal290);
            }
            break;
         case 2:
            char_literal291 = (Token)this.match(this.input, 106, FOLLOW_106_in_identificadores3705);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_106.add(char_literal291);
            }

            vetor = (Token)this.match(this.input, 48, FOLLOW_INTEIRO_LITERAL_in_identificadores3709);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_INTEIRO_LITERAL.add(vetor);
            }

            char_literal292 = (Token)this.match(this.input, 108, FOLLOW_108_in_identificadores3711);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_108.add(char_literal292);
            }

            char_literal293 = (Token)this.match(this.input, 106, FOLLOW_106_in_identificadores3713);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_106.add(char_literal293);
            }

            matriz = (Token)this.match(this.input, 48, FOLLOW_INTEIRO_LITERAL_in_identificadores3717);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_INTEIRO_LITERAL.add(matriz);
            }

            char_literal294 = (Token)this.match(this.input, 108, FOLLOW_108_in_identificadores3719);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_108.add(char_literal294);
            }
         }

         int alt50 = 2;
         LA50_0 = this.input.LA(1);
         if (LA50_0 == 20) {
            alt50 = 1;
         }

         switch(alt50) {
         case 1:
            char_literal295 = (Token)this.match(this.input, 20, FOLLOW_CONCATENACAO_in_identificadores3725);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_CONCATENACAO.add(char_literal295);
            }

            this.pushFollow(FOLLOW_identificadores_in_identificadores3727);
            identificadores296 = this.identificadores();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_identificadores.add(identificadores296.getTree());
            }
         default:
            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               RewriteRuleTokenStream stream_vetor = new RewriteRuleTokenStream(this.adaptor, "token vetor", vetor);
               RewriteRuleTokenStream stream_matriz = new RewriteRuleTokenStream(this.adaptor, "token matriz", matriz);
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               CommonTree root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(45, (String)"IDENT")), root_1);
               this.adaptor.addChild(root_1, stream_IDENTIFICADOR.nextNode());
               if (stream_vetor.hasNext()) {
                  this.adaptor.addChild(root_1, stream_vetor.nextNode());
               }

               stream_vetor.reset();
               if (stream_matriz.hasNext()) {
                  this.adaptor.addChild(root_1, stream_matriz.nextNode());
               }

               stream_matriz.reset();
               this.adaptor.addChild(root_0, root_1);
               if (stream_identificadores.hasNext()) {
                  this.adaptor.addChild(root_0, stream_identificadores.nextTree());
               }

               stream_identificadores.reset();
               retval.tree = root_0;
            }

            retval.stop = this.input.LT(-1);
            if (this.state.backtracking == 0) {
               retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
               this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
         }
      } catch (RecognitionException var38) {
         this.reportError(var38);
         this.recover(this.input, var38);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var38);
      }

      return retval;
   }

   public final CalangoGrammarParser.tipo_identificador_return tipo_identificador() throws RecognitionException {
      CalangoGrammarParser.tipo_identificador_return retval = new CalangoGrammarParser.tipo_identificador_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token IDENTIFICADOR298 = null;
      CalangoGrammarParser.tipo_dado_return tipo_dado297 = null;
      CommonTree IDENTIFICADOR298_tree = null;
      RewriteRuleTokenStream stream_IDENTIFICADOR = new RewriteRuleTokenStream(this.adaptor, "token IDENTIFICADOR");
      RewriteRuleSubtreeStream stream_tipo_dado = new RewriteRuleSubtreeStream(this.adaptor, "rule tipo_dado");

      try {
         this.pushFollow(FOLLOW_tipo_dado_in_tipo_identificador3776);
         tipo_dado297 = this.tipo_dado();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_tipo_dado.add(tipo_dado297.getTree());
         }

         IDENTIFICADOR298 = (Token)this.match(this.input, 46, FOLLOW_IDENTIFICADOR_in_tipo_identificador3778);
         if (this.state.failed) {
            return retval;
         }

         if (this.state.backtracking == 0) {
            stream_IDENTIFICADOR.add(IDENTIFICADOR298);
         }

         if (this.state.backtracking == 0) {
            retval.tree = root_0;
            new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
            root_0 = (CommonTree)this.adaptor.nil();
            CommonTree root_1 = (CommonTree)this.adaptor.nil();
            root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(94, (String)"TIPO_IDENTIFICADOR")), root_1);
            this.adaptor.addChild(root_1, stream_tipo_dado.nextTree());
            this.adaptor.addChild(root_1, stream_IDENTIFICADOR.nextNode());
            this.adaptor.addChild(root_0, root_1);
            retval.tree = root_0;
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var10) {
         this.reportError(var10);
         this.recover(this.input, var10);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var10);
      }

      return retval;
   }

   public final CalangoGrammarParser.decl_parametros_return decl_parametros() throws RecognitionException {
      CalangoGrammarParser.decl_parametros_return retval = new CalangoGrammarParser.decl_parametros_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token char_literal300 = null;
      CalangoGrammarParser.decl_parametro_return decl_parametro299 = null;
      CalangoGrammarParser.decl_parametro_return decl_parametro301 = null;
      CommonTree char_literal300_tree = null;
      RewriteRuleTokenStream stream_CONCATENACAO = new RewriteRuleTokenStream(this.adaptor, "token CONCATENACAO");
      RewriteRuleSubtreeStream stream_decl_parametro = new RewriteRuleSubtreeStream(this.adaptor, "rule decl_parametro");

      try {
         this.pushFollow(FOLLOW_decl_parametro_in_decl_parametros3821);
         decl_parametro299 = this.decl_parametro();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         } else {
            if (this.state.backtracking == 0) {
               stream_decl_parametro.add(decl_parametro299.getTree());
            }

            while(true) {
               int alt51 = 2;
               int LA51_0 = this.input.LA(1);
               if (LA51_0 == 20) {
                  alt51 = 1;
               }

               switch(alt51) {
               case 1:
                  char_literal300 = (Token)this.match(this.input, 20, FOLLOW_CONCATENACAO_in_decl_parametros3824);
                  if (this.state.failed) {
                     return retval;
                  }

                  if (this.state.backtracking == 0) {
                     stream_CONCATENACAO.add(char_literal300);
                  }

                  this.pushFollow(FOLLOW_decl_parametro_in_decl_parametros3826);
                  decl_parametro301 = this.decl_parametro();
                  --this.state._fsp;
                  if (this.state.failed) {
                     return retval;
                  }

                  if (this.state.backtracking == 0) {
                     stream_decl_parametro.add(decl_parametro301.getTree());
                  }
                  break;
               default:
                  if (this.state.backtracking == 0) {
                     retval.tree = root_0;
                     new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                     root_0 = (CommonTree)this.adaptor.nil();
                     CommonTree root_1 = (CommonTree)this.adaptor.nil();
                     root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(72, (String)"PARAMETROS")), root_1);

                     while(stream_decl_parametro.hasNext()) {
                        this.adaptor.addChild(root_1, stream_decl_parametro.nextTree());
                     }

                     stream_decl_parametro.reset();
                     this.adaptor.addChild(root_0, root_1);
                     retval.tree = root_0;
                  }

                  retval.stop = this.input.LT(-1);
                  if (this.state.backtracking == 0) {
                     retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                     this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                  }

                  return retval;
               }
            }
         }
      } catch (RecognitionException var11) {
         this.reportError(var11);
         this.recover(this.input, var11);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var11);
         return retval;
      }
   }

   public final CalangoGrammarParser.decl_parametro_return decl_parametro() throws RecognitionException {
      CalangoGrammarParser.decl_parametro_return retval = new CalangoGrammarParser.decl_parametro_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token REFERENCIA303 = null;
      Token string_literal306 = null;
      Token INTEIRO_LITERAL307 = null;
      Token char_literal308 = null;
      Token string_literal310 = null;
      CalangoGrammarParser.tipo_identificador_return tipo_identificador302 = null;
      CalangoGrammarParser.tipo_identificador_return tipo_identificador304 = null;
      CalangoGrammarParser.tipo_identificador_return tipo_identificador305 = null;
      CalangoGrammarParser.tipo_identificador_return tipo_identificador309 = null;
      CommonTree REFERENCIA303_tree = null;
      CommonTree string_literal306_tree = null;
      CommonTree INTEIRO_LITERAL307_tree = null;
      CommonTree char_literal308_tree = null;
      CommonTree string_literal310_tree = null;
      RewriteRuleTokenStream stream_INTEIRO_LITERAL = new RewriteRuleTokenStream(this.adaptor, "token INTEIRO_LITERAL");
      RewriteRuleTokenStream stream_PARAM_VETOR = new RewriteRuleTokenStream(this.adaptor, "token PARAM_VETOR");
      RewriteRuleTokenStream stream_REFERENCIA = new RewriteRuleTokenStream(this.adaptor, "token REFERENCIA");
      RewriteRuleTokenStream stream_108 = new RewriteRuleTokenStream(this.adaptor, "token 108");
      RewriteRuleTokenStream stream_107 = new RewriteRuleTokenStream(this.adaptor, "token 107");
      RewriteRuleSubtreeStream stream_tipo_identificador = new RewriteRuleSubtreeStream(this.adaptor, "rule tipo_identificador");

      try {
         int LA52_0 = this.input.LA(1);
         byte alt52;
         if (LA52_0 != 93 && (LA52_0 < 95 || LA52_0 > 98)) {
            if (LA52_0 != 80) {
               if (this.state.backtracking > 0) {
                  this.state.failed = true;
                  return retval;
               }

               NoViableAltException nvae = new NoViableAltException("", 52, 0, this.input);
               throw nvae;
            }

            alt52 = 2;
         } else {
            int LA52_1 = this.input.LA(2);
            NoViableAltException nvae;
            if (LA52_1 != 46) {
               if (this.state.backtracking > 0) {
                  this.state.failed = true;
                  return retval;
               }

               nvae = new NoViableAltException("", 52, 1, this.input);
               throw nvae;
            }

            switch(this.input.LA(3)) {
            case 20:
            case 102:
               alt52 = 1;
               break;
            case 75:
               alt52 = 4;
               break;
            case 107:
               alt52 = 3;
               break;
            default:
               if (this.state.backtracking > 0) {
                  this.state.failed = true;
                  return retval;
               }

               nvae = new NoViableAltException("", 52, 3, this.input);
               throw nvae;
            }
         }

         CommonTree root_1;
         switch(alt52) {
         case 1:
            this.pushFollow(FOLLOW_tipo_identificador_in_decl_parametro3867);
            tipo_identificador302 = this.tipo_identificador();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_tipo_identificador.add(tipo_identificador302.getTree());
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(71, (String)"PARAMETRO")), root_1);
               this.adaptor.addChild(root_1, stream_tipo_identificador.nextTree());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 2:
            REFERENCIA303 = (Token)this.match(this.input, 80, FOLLOW_REFERENCIA_in_decl_parametro3883);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_REFERENCIA.add(REFERENCIA303);
            }

            this.pushFollow(FOLLOW_tipo_identificador_in_decl_parametro3885);
            tipo_identificador304 = this.tipo_identificador();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_tipo_identificador.add(tipo_identificador304.getTree());
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(71, (String)"PARAMETRO")), root_1);
               this.adaptor.addChild(root_1, stream_tipo_identificador.nextTree());
               this.adaptor.addChild(root_1, stream_REFERENCIA.nextNode());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 3:
            this.pushFollow(FOLLOW_tipo_identificador_in_decl_parametro3903);
            tipo_identificador305 = this.tipo_identificador();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_tipo_identificador.add(tipo_identificador305.getTree());
            }

            string_literal306 = (Token)this.match(this.input, 107, FOLLOW_107_in_decl_parametro3905);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_107.add(string_literal306);
            }

            INTEIRO_LITERAL307 = (Token)this.match(this.input, 48, FOLLOW_INTEIRO_LITERAL_in_decl_parametro3907);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_INTEIRO_LITERAL.add(INTEIRO_LITERAL307);
            }

            char_literal308 = (Token)this.match(this.input, 108, FOLLOW_108_in_decl_parametro3909);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_108.add(char_literal308);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(71, (String)"PARAMETRO")), root_1);
               this.adaptor.addChild(root_1, stream_tipo_identificador.nextTree());
               this.adaptor.addChild(root_1, (CommonTree)this.adaptor.create(74, (String)"PARAM_MATRIZ"));
               this.adaptor.addChild(root_1, stream_INTEIRO_LITERAL.nextNode());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
            break;
         case 4:
            this.pushFollow(FOLLOW_tipo_identificador_in_decl_parametro3929);
            tipo_identificador309 = this.tipo_identificador();
            --this.state._fsp;
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_tipo_identificador.add(tipo_identificador309.getTree());
            }

            string_literal310 = (Token)this.match(this.input, 75, FOLLOW_PARAM_VETOR_in_decl_parametro3931);
            if (this.state.failed) {
               return retval;
            }

            if (this.state.backtracking == 0) {
               stream_PARAM_VETOR.add(string_literal310);
            }

            if (this.state.backtracking == 0) {
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(71, (String)"PARAMETRO")), root_1);
               this.adaptor.addChild(root_1, stream_tipo_identificador.nextTree());
               this.adaptor.addChild(root_1, (CommonTree)this.adaptor.create(75, (String)"PARAM_VETOR"));
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
            }
         }

         retval.stop = this.input.LT(-1);
         if (this.state.backtracking == 0) {
            retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         }
      } catch (RecognitionException var27) {
         this.reportError(var27);
         this.recover(this.input, var27);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var27);
      }

      return retval;
   }

   public final CalangoGrammarParser.parametros_chamada_return parametros_chamada() throws RecognitionException {
      CalangoGrammarParser.parametros_chamada_return retval = new CalangoGrammarParser.parametros_chamada_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      Token char_literal312 = null;
      CalangoGrammarParser.expressao_return expressao311 = null;
      CalangoGrammarParser.expressao_return expressao313 = null;
      CommonTree char_literal312_tree = null;
      RewriteRuleTokenStream stream_CONCATENACAO = new RewriteRuleTokenStream(this.adaptor, "token CONCATENACAO");
      RewriteRuleSubtreeStream stream_expressao = new RewriteRuleSubtreeStream(this.adaptor, "rule expressao");

      try {
         this.pushFollow(FOLLOW_expressao_in_parametros_chamada3965);
         expressao311 = this.expressao();
         --this.state._fsp;
         if (this.state.failed) {
            return retval;
         } else {
            if (this.state.backtracking == 0) {
               stream_expressao.add(expressao311.getTree());
            }

            while(true) {
               int alt53 = 2;
               int LA53_0 = this.input.LA(1);
               if (LA53_0 == 20) {
                  alt53 = 1;
               }

               switch(alt53) {
               case 1:
                  char_literal312 = (Token)this.match(this.input, 20, FOLLOW_CONCATENACAO_in_parametros_chamada3968);
                  if (this.state.failed) {
                     return retval;
                  }

                  if (this.state.backtracking == 0) {
                     stream_CONCATENACAO.add(char_literal312);
                  }

                  this.pushFollow(FOLLOW_expressao_in_parametros_chamada3970);
                  expressao313 = this.expressao();
                  --this.state._fsp;
                  if (this.state.failed) {
                     return retval;
                  }

                  if (this.state.backtracking == 0) {
                     stream_expressao.add(expressao313.getTree());
                  }
                  break;
               default:
                  if (this.state.backtracking == 0) {
                     retval.tree = root_0;
                     new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                     root_0 = (CommonTree)this.adaptor.nil();
                     CommonTree root_1 = (CommonTree)this.adaptor.nil();
                     root_1 = (CommonTree)this.adaptor.becomeRoot((Object)((CommonTree)this.adaptor.create(73, (String)"PARAMETROS_CHAMADA")), root_1);

                     while(stream_expressao.hasNext()) {
                        this.adaptor.addChild(root_1, stream_expressao.nextTree());
                     }

                     stream_expressao.reset();
                     this.adaptor.addChild(root_0, root_1);
                     retval.tree = root_0;
                  }

                  retval.stop = this.input.LT(-1);
                  if (this.state.backtracking == 0) {
                     retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                     this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                  }

                  return retval;
               }
            }
         }
      } catch (RecognitionException var11) {
         this.reportError(var11);
         this.recover(this.input, var11);
         retval.tree = (CommonTree)this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1), var11);
         return retval;
      }
   }

   public final void synpred1_CalangoGrammar_fragment() throws RecognitionException {
      this.match(this.input, 137, FOLLOW_137_in_synpred1_CalangoGrammar1312);
      if (!this.state.failed) {
         this.match(this.input, 101, FOLLOW_101_in_synpred1_CalangoGrammar1314);
         if (!this.state.failed) {
            this.pushFollow(FOLLOW_expressao_in_synpred1_CalangoGrammar1316);
            this.expressao();
            --this.state._fsp;
            if (!this.state.failed) {
               this.match(this.input, 102, FOLLOW_102_in_synpred1_CalangoGrammar1318);
               if (!this.state.failed) {
                  this.match(this.input, 121, FOLLOW_121_in_synpred1_CalangoGrammar1320);
                  if (!this.state.failed) {
                     int alt54 = 2;
                     int LA54_0 = this.input.LA(1);
                     if (LA54_0 == 32 || LA54_0 >= 34 && LA54_0 <= 36 || LA54_0 == 38 || LA54_0 == 46 || LA54_0 >= 49 && LA54_0 <= 51 || LA54_0 == 53 || LA54_0 == 70 || LA54_0 == 81 || LA54_0 == 83) {
                        alt54 = 1;
                     }

                     switch(alt54) {
                     case 1:
                        this.pushFollow(FOLLOW_mult_statements_in_synpred1_CalangoGrammar1328);
                        this.mult_statements();
                        --this.state._fsp;
                        if (this.state.failed) {
                           return;
                        }
                     default:
                        int alt55 = 2;
                        int LA55_0 = this.input.LA(1);
                        if (LA55_0 >= 136 && LA55_0 <= 137) {
                           alt55 = 1;
                        }

                        switch(alt55) {
                        case 1:
                           this.pushFollow(FOLLOW_senaoStm_in_synpred1_CalangoGrammar1331);
                           this.senaoStm();
                           --this.state._fsp;
                           if (this.state.failed) {
                              return;
                           }
                        default:
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public final void synpred2_CalangoGrammar_fragment() throws RecognitionException {
      this.match(this.input, 32, FOLLOW_ENQUANTO_in_synpred2_CalangoGrammar1914);
      if (!this.state.failed) {
         this.match(this.input, 101, FOLLOW_101_in_synpred2_CalangoGrammar1916);
         if (!this.state.failed) {
            this.pushFollow(FOLLOW_expressao_in_synpred2_CalangoGrammar1918);
            this.expressao();
            --this.state._fsp;
            if (!this.state.failed) {
               this.match(this.input, 102, FOLLOW_102_in_synpred2_CalangoGrammar1920);
               if (!this.state.failed) {
                  this.match(this.input, 104, FOLLOW_104_in_synpred2_CalangoGrammar1922);
                  if (!this.state.failed) {
                     ;
                  }
               }
            }
         }
      }
   }

   public final void synpred3_CalangoGrammar_fragment() throws RecognitionException {
      this.pushFollow(FOLLOW_expressao_in_synpred3_CalangoGrammar2437);
      this.expressao();
      --this.state._fsp;
      if (!this.state.failed) {
         if (this.input.LA(1) != 20 && this.input.LA(1) != 102) {
            if (this.state.backtracking > 0) {
               this.state.failed = true;
            } else {
               MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
               throw mse;
            }
         } else {
            this.input.consume();
            this.state.errorRecovery = false;
            this.state.failed = false;
         }
      }
   }

   public final void synpred4_CalangoGrammar_fragment() throws RecognitionException {
      this.pushFollow(FOLLOW_expressao_in_synpred4_CalangoGrammar2457);
      this.expressao();
      --this.state._fsp;
      if (!this.state.failed) {
         this.match(this.input, 103, FOLLOW_103_in_synpred4_CalangoGrammar2459);
         if (!this.state.failed) {
            this.match(this.input, 48, FOLLOW_INTEIRO_LITERAL_in_synpred4_CalangoGrammar2461);
            if (!this.state.failed) {
               ;
            }
         }
      }
   }

   public final boolean synpred4_CalangoGrammar() {
      ++this.state.backtracking;
      int start = this.input.mark();

      try {
         this.synpred4_CalangoGrammar_fragment();
      } catch (RecognitionException var3) {
         System.err.println("impossible: " + var3);
      }

      boolean success = !this.state.failed;
      this.input.rewind(start);
      --this.state.backtracking;
      this.state.failed = false;
      return success;
   }

   public final boolean synpred1_CalangoGrammar() {
      ++this.state.backtracking;
      int start = this.input.mark();

      try {
         this.synpred1_CalangoGrammar_fragment();
      } catch (RecognitionException var3) {
         System.err.println("impossible: " + var3);
      }

      boolean success = !this.state.failed;
      this.input.rewind(start);
      --this.state.backtracking;
      this.state.failed = false;
      return success;
   }

   public final boolean synpred3_CalangoGrammar() {
      ++this.state.backtracking;
      int start = this.input.mark();

      try {
         this.synpred3_CalangoGrammar_fragment();
      } catch (RecognitionException var3) {
         System.err.println("impossible: " + var3);
      }

      boolean success = !this.state.failed;
      this.input.rewind(start);
      --this.state.backtracking;
      this.state.failed = false;
      return success;
   }

   public final boolean synpred2_CalangoGrammar() {
      ++this.state.backtracking;
      int start = this.input.mark();

      try {
         this.synpred2_CalangoGrammar_fragment();
      } catch (RecognitionException var3) {
         System.err.println("impossible: " + var3);
      }

      boolean success = !this.state.failed;
      this.input.rewind(start);
      --this.state.backtracking;
      this.state.failed = false;
      return success;
   }

   public static class algoritmo_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class atribuicaoStm_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class caso_negativo_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class caso_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class chamadaRotina_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class comparacao_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class corpo_facaStm_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class corpo_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class decl_parametro_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class decl_parametros_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class decl_variaveis_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class decl_variavel_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class enquantoStm_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class escolhaStm_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class escreverStatement_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class expressaoEscreva_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class expressao_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class facaStm_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class formataReal_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class funcao_ou_procedimento_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class funcao_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class funcoesEmbutidas_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class identificadores_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class interrompa_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class lerCaracterStatement_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class lerStatement_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class limpatela_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class mult_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class mult_statements_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class negacao_matematica_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class negacao_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class outrocaso_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class paraStm_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class parametros_chamada_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class principal_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class procedimento_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class retornaStm_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class seStm_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class senaoStm_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class soma_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class statementsSemiColon_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class statements_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class termo_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class tipo_dado_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class tipo_identificador_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class unario_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }
}
