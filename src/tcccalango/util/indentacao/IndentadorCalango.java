/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcccalango.util.indentacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Gabriel
 */
public class IndentadorCalango {
    
    private static final String TOKEN_REGEXP = "(\\s*(principal|para.+faca|se.+entao|senao|(funcao|procedimento)[^)]+\\)|faca|enquanto[^)]+\\)(;|\\s*faca)|fimSe|fimPara|fimFuncao|fimProcedimento|fimEnquanto|fimPrincipal|//.+|(logico|texto|caracter|inteiro|real)\\s+.+(=.+)?;|interrompa;|.+=.+;|retorna.*;|(.+=)?.+\\(.*\\);|algoritmo.+;)|\\*/)(\\n\\s*)*";
    private static final Pattern TOKEN_PATTERN = Pattern.compile(TOKEN_REGEXP);
    private static final Map<String, String> blocos = new HashMap<String, String>();
    
    static {
        blocos.put("principal", "fimPrincipal");
        blocos.put("para", "fimPara");
        blocos.put("se", "senao");
        blocos.put("senao", "fimSe");
        blocos.put("funcao", "fimFuncao");
        blocos.put("enquantofaca", "fimEnquanto");
        blocos.put("faca", "enquanto");
        blocos.put("procedimento", "fimProcedimento");
        blocos.put("/*", "*/");
    }
    
    private String identacao;

    public IndentadorCalango(String identacao) {
        this.identacao = identacao;
    }

    public IndentadorCalango() {
        this("\t");
    }

    public String getIdentacao() {
        return identacao;
    }

    public void setIdentacao(String identacao) {
        this.identacao = identacao;
    }
    
    public String identa(String codigo){
        StringBuffer sb = retiraIdentacao(codigo);

        System.out.println(sb);

        List<String> linhas = new ArrayList<String>(Arrays.asList(sb.toString().split("\n")));

        StringBuilder codigoIdentado = new StringBuilder();
        
        identa(null, null, linhas, null, codigoIdentado);
        
        return codigoIdentado.toString().replace("\n"+identacao, "\n");
    }

    private void identa(String aberturaBloco, String chave, List<String> linhas, String tab, StringBuilder codigoIdentado){
        if (aberturaBloco==null || chave==null){
            aberturaBloco = linhas.remove(0);
            String[] chaves = aberturaBloco.split("((\\s+|\\([^\\n]*\\)|;)+)");

            chave = chaves.toString().replaceAll("[\\[\\]\\,]", "");
            if (!blocos.containsKey(chave) && blocos.containsValue(chave) && chaves.length!=0){
                chave = chaves[0];
            }
        }

        codigoIdentado.append((tab==null?"":tab)).append(aberturaBloco).append("\n");

        do{
            String current = linhas.remove(0);
            String[] currentChaves = current.split("(\\s+|\\([^\\n]*\\)|;)+");

            String currentChave = Arrays.asList(currentChaves).toString().replaceAll("[\\[\\]\\,\\s]", "").trim();
            if (!blocos.containsKey(currentChave) && !blocos.containsValue(currentChave) && currentChaves.length!=0){
                currentChave = currentChaves[0].trim();
            }
            
            if (blocos.values().contains(currentChave)){
                if (currentChave.equals(blocos.get(chave))){
                    if (blocos.containsKey(blocos.get(chave))){
                        identa(current, currentChave, linhas, (tab==null?"":tab), codigoIdentado);
                    }else{
                        codigoIdentado.append((tab==null?"":tab)).append(current).append("\n");
                    }
                    return;
                }else if (!chave.equals("/*")){
                    if (currentChave.equals(blocos.get(blocos.get(chave)))){
                        codigoIdentado.append((tab==null?"":tab)).append(current).append("\n");
                        return;
                    }else{
                        //System.err.println(codigoIdentado);
                        //throw new RuntimeException("Código Inválido");
                    }
                }
            }

            if (chave.equals("/*")){
                codigoIdentado.append((tab==null?"":tab)).append(current).append("\n");
            }else if (blocos.containsKey(currentChave)){
                identa(current, currentChave, linhas, identacao+(tab==null?"":tab), codigoIdentado);
            }else{
                codigoIdentado.append((tab==null?"":tab)).append(identacao).append(current).append("\n");
            }
        }while(!linhas.isEmpty());
    }

    private StringBuffer retiraIdentacao(String algoritmo){
        Matcher matcher = TOKEN_PATTERN.matcher(algoritmo);

        StringBuffer sb = new StringBuffer();

        while(matcher.find()){

            String group = matcher.group().replaceAll("[ \\t]*\\n[ \\t]*", "\n").replaceAll("^[ \\t]+|[ \\t]+$", "");
            
            if (group.matches("(fimPrincipal|fimProcedimento|fimFuncao)\\n*")){
                if (!group.endsWith("\n")){
                    group = group + "\n";
                }
                if (!group.endsWith("\n\n")){
                    group = group + "\n";
                }
            }else{
                if (!group.endsWith("\n")){
                    group = group + "\n";
                }
            }
            matcher.appendReplacement(sb, group);
            

        }

        return sb;
    }
}
