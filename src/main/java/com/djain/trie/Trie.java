package com.djain.trie;

import java.util.List;

public class Trie {
    Trie_Node root;
    
    /*
     * initializing Trie
     */
    public Trie() {
        root = new Trie_Node(' ');
    }
    
    /*
     * inserting string into Trie
     */

    public void insert(String arg) {
        if (arg == null || arg.trim().length() == 0) {
            return;
        }
        root.insert(arg);
    }

    /*
     * searching for a string in Trie
     * @return true if entry is found else false
     */
    
    public boolean search(String arg) {
        if (arg == null || arg.trim().length() == 0) {
            return false;
        }
        return root.search(arg);
    }
    
    /*
     * @return , list of Strings that are in trie and starts with prefix arg
     */
    
    public List<String> searchWithPrefix(String arg) {
        List<String> list=root.searchWithPrefix(arg);
        return list;
        
    }
}

