package com.djain.trie;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie_Node {
    char ch;
    boolean is_e_o_string;
    Map<Character, Trie_Node> nodes = new HashMap<Character, Trie_Node>();
    String actualWord;
    
    public Trie_Node(char ch) {
        this.ch = ch;
        is_e_o_string = false;
        nodes = new HashMap<Character, Trie_Node>();
    }

    public Trie_Node children(char ch) {
        return nodes.get(ch);
    }
    private Map<Character, Trie_Node> getAllchildren() {
        return nodes;
    }

    public boolean isChildExist(char c) {
        return children(c) != null;
    }

    public void insert(String arg) {
        if (arg == null || arg.trim().length() == 0) {
            return;
        }
        Trie_Node current = this;
        for (int i = 0; i < arg.length(); i++) {
            char c = arg.charAt(i);
            if (!current.isChildExist(c)) {
                Trie_Node node = new Trie_Node(c);
                current.nodes.put(c, node);
            }
            current = current.children(c);
        }
        current.actualWord=arg;
        current.is_e_o_string = true;
    }

    public boolean search(String arg) {
        Trie_Node current = getLastNode(arg);
        return current!=null?current.is_e_o_string:false;
    }

    private Trie_Node getLastNode(String arg) {
        Trie_Node current = this;
        for (int i = 0; i < arg.length(); i++) {
            char c = arg.charAt(i);
            if (!current.isChildExist(c)) {
                return null;
            }
            current = current.children(c);
        }
        return current;
    }

    public List<String> searchWithPrefix(String arg) {
        List<String> retval=new ArrayList<String>();
        Trie_Node current = getLastNode(arg);
        if(current!=null){
            if(current.is_e_o_string){
                retval.add(arg);
            }
            current.getAllChildrens(retval);
            
        }
        return retval;
    }
    private List<String> getAllChildrens(List<String> retval){
        Trie_Node current=this;
        Map<Character, Trie_Node> nodes=current.getAllchildren();
        Collection< Trie_Node> sets=nodes.values();
        for (Trie_Node trieNode : sets) {
            if(trieNode.is_e_o_string){
                retval.add(trieNode.actualWord);
            }
            trieNode.getAllChildrens(retval);
        }
        return retval;
    }

}
