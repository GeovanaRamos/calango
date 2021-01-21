package br.ucb.calango.arvore;

import java.util.List;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

public class CalangoTree extends CommonTree {
   public CalangoTree(Token payload) {
      super(payload);
   }

   public CalangoTree getCalangoChild(int i) {
      return (CalangoTree)this.getChild(i);
   }

   public Tree getChild(int i) {
      return super.getChild(i);
   }

   public CalangoTree getParent() {
      return (CalangoTree)super.getParent();
   }

   public CalangoTree getAncestor(int ttype) {
      return (CalangoTree)super.getAncestor(ttype);
   }

   public CalangoTree getFirstChildWithType(int arg0) {
      return (CalangoTree)super.getFirstChildWithType(arg0);
   }

   public boolean parentHasChild(int childType) {
      return ((CalangoTree)super.getParent()).getFirstChildWithType(childType) != null;
   }

   public boolean isType(int i) {
      return this.getType() == i;
   }

   public List<CalangoTree> getChildren() {
      return (List<CalangoTree>) super.getChildren();
   }

   public void printTree() {
      System.out.println(this.toStringTree());
   }
}
