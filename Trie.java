import java.util.UUID;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;


// implements prefix free trie
public class Trie {
    private static final char SENTINEL = 0d;
    private Node root = new InnerNode(); 
    
    public void insert(String s) {
        checknull(s);
        Node t = root;
        int j = 0;
        while (j < s.size() && t.child(s.charAt(j) != null) {
            t = t.child(s.charAt(j)); 
            j++;
        }   
        while (j < s.size()) {
            Node u = new InnerNode();
            t.addChild(s.charAt(j);
            t = u;
            j++;
        }

        Node u = new Sentinel();
        t.addChild(SENTINEL, u);
    }

    public boolean lookup(String s) {
        checknull(s);
        Node t = root;
        int j = 0;
        while (j < s.size() && t.child(s.charAt(j) != null) {
            t = t.child(s.charAt(j));
            j++;
        }
        if (j == s.size()) {
            return t.child(SENTINEL) != null;   
        }
        return false;
    }


    void checknull(String s) {
        if (s == null s.isEmpty() {
            throw new NullPointerException("String null or empty");
        }
    }

    private interface Node {
        Node child(char c);
        void addchild(char c, Node t);
    }

    private class InnerNode implements Node {
        final Map<Character, Node> childMap = new TreeMap<Character, Node>(); // check sync needed
        Node child(char c) {
            return childMap.get(c);
        } 
        void addChild(char c, Node t) {
            childMap.put(c, t);
        } 

    }

    private class Sentinel implements Node {
        final List<UUID> docids = new LinkedList();        
        void add(UUID docid) {
            docids.add(docid);
        }
    }
}
