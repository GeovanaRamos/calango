/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcccalango.view;

import tcccalango.util.settings.CalangoSettings;

/**
 *
 * @author JessicaLuanne
 */
public enum EnumCamposCoresEditaveis {
    COMENTARIO(1, "Comentário"),
    CONSTANTE_TEXTO(2, "Constante texto"),
    CONSTANTE_NUMERICA(3, "Constante numérica"),
    FUNDO_EDITOR(4, "Fundo do Editor"),
    PALAVRA_CHAVE(5, "Palabra Chave"),
    TIPO_DADO(6, "Tipo de Dado"),
    TEXTO_GERAL(7, "Texto em Geral");
    
    private String descricao;
    private int id;

    private EnumCamposCoresEditaveis(int id,String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnumCamposCoresEditaveis getEnumByDescricao(String desc){
        for (EnumCamposCoresEditaveis campo : values()) {
            if(campo.getDescricao().equalsIgnoreCase(desc)){
                return campo;
            }
        }
        return null;
    }
    
    public int getIdEnumByDescricao(String desc){
        for (EnumCamposCoresEditaveis campo : values()) {
            if(campo.getDescricao().equalsIgnoreCase(desc)){
                return campo.getId();
            }
        }
        return 0;
    }
    
}
