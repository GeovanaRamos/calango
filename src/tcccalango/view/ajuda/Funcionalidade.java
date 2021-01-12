/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcccalango.view.ajuda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Giovanna
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Funcionalidade implements Comparable<Funcionalidade> {
    @XmlElement
    private String nome;
    @XmlElement
    private Integer index;
    @XmlElement(nillable=true)
    private String parent;
    @XmlElement
    private String descricao;
    
    @XmlTransient
    private List<Funcionalidade> childs = new ArrayList<Funcionalidade>();

    public Funcionalidade() {
    }

    public Funcionalidade(String nome, Integer index, String parent, String descricao) {
        this.nome = nome;
        this.index = index;
        this.parent = parent;
        this.descricao = descricao;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<Funcionalidade> getChilds() {
        return childs;
    }

    public void setChilds(List<Funcionalidade> childs) {
        this.childs = childs;
    }
    
    public void addChild(Funcionalidade funcionalidade){
        this.childs.add(funcionalidade);
        Collections.sort(childs);
    }

    @Override
    public int compareTo(Funcionalidade o) {
        return getIndex().compareTo(o.getIndex());
    }

    @Override
    public String toString() {
        return getNome();
    }
    
    
}
