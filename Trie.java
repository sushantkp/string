import java.util.UUID;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;


// implements prefix free trie
public class Trie {
    private static final char SENTINEL = 0;
    private Node root = new InnerNode(); 
    
    public void insert(String s) {
        checknull(s);
        Node t = root;
        int j = 0;
        while (j < s.length() && t.child(s.charAt(j)) != null) {
            t = t.child(s.charAt(j)); 
            j++;
        }   
        while (j < s.length()) {
            Node u = new InnerNode();
            t.addChild(s.charAt(j), u);
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
        while (j < s.length() && t.child(s.charAt(j)) != null) {
            t = t.child(s.charAt(j));
            j++;
        }
        if (j == s.length()) {
            return t.child(SENTINEL) != null;   
        }
        return false;
    }


    void checknull(String s) {
        if (s == null) {
            throw new NullPointerException("String input null");
        }
    }

    private interface Node {
        Node child(char c);
        void addChild(char c, Node t);
    }

    private class InnerNode implements Node {
        final Map<Character, Node> childMap = new TreeMap<Character, Node>(); // check sync needed
        public Node child(char c) {
            return childMap.get(c);
        } 
        public void addChild(char c, Node t) {
            childMap.put(c, t);
        } 

    }

    private class Sentinel implements Node {
        final List<UUID> docids = new LinkedList();        
        void adddoc(UUID docid) {
            docids.add(docid);
        }
        public Node child(char c) {
            throw new UnsupportedOperationException();
        }
        public void addChild(char c, Node t) {
            throw new UnsupportedOperationException();
        }
    }


    public static void main(String [] args) {
        String[] doc = new String [] {"pot", "potato", "pottery", "tempo", "tattoo"};
        Trie t = new Trie();

        for (String s : doc) {
            t.insert(s);
        }

        System.out.println(t.lookup("tempo"));
        System.out.println(t.lookup("trie"));
        System.out.println(t.lookup(""));
        try {
            System.out.println(t.lookup(null));
            assert false;
        }
        catch (NullPointerException e) {

        }
    } 
}
