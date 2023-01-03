package Shared.BST;



import Domain.Local;

import java.util.*;

public class BST<E extends Comparable<E>> implements BSTInterface<E> {


    public Node<E> getRoot() {
        return root;
    }

    /**
     * Nested static class for a binary search tree node.
     *
     * @param <E> the type parameter
     */
    public static class Node<E> {
        private E element;          // an element stored at this node
        private Node<E> left;       // a reference to the left child (if any)
        private Node<E> right;      // a reference to the right child (if any)

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param e          the element to be stored
         * @param leftChild  reference to a left child node
         * @param rightChild reference to a right child node
         */
        public Node(E e, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            left = leftChild;
            right = rightChild;
        }


        //accessor methods

        /**
         * Gets element.
         *
         * @return the element
         */
        public E getElement() {
            return element;
        }

        /**
         * Gets left.
         *
         * @return the left
         */
        public Node<E> getLeft() {
            return left;
        }

        /**
         * Gets right.
         *
         * @return the right
         */
        public Node<E> getRight() {
            return right;
        }

        /**
         * Sets element.
         *
         * @param e the e
         */

        // update methods
        public void setElement(E e) {
            element = e;
        }

        /**
         * Sets left.
         *
         * @param leftChild the left child
         */
        public void setLeft(Node<E> leftChild) {
            left = leftChild;
        }

        /**
         * Sets right.
         *
         * @param rightChild the right child
         */
        public void setRight(Node<E> rightChild) {
            right = rightChild;
        }
    }

    //----------- end of nested Node class -----------
    /**
     * The Root.
     */
    public Node<E> root;     // root of the tree


    /**
     * Instantiates a new BST.
     */
    /* Constructs an empty binary search tree. */
    public BST() {
        root = null;
    }

    /**
     * Root node.
     *
     * @return root Node of the tree (or null if tree is empty)
     */
    protected Node<E> root() {
        return root;
    }

    /**
     * Verifies if the tree is empty
     *
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Inserts an element in the tree.
     */
    public void insert(E element) {

        if (element == null) {
            //System.out.println("null");
            return;
        }
        if (this.isEmpty()) {
          //  System.out.println("empty");

            root = new Node<>(element, null, null);
            return;
        }
        //System.out.println("funcao");

        insert(element, root);
    }

   /* private Node<E> insert(E element, Node<E> node) {
        if (element.compareTo(node.getElement()) < 0) {
            if (node.getLeft() == null) {
                Node<E> newNode = new Node<>(element, null, null);
                node.setLeft(newNode);
                return newNode;
            }

            return insert(element, node.getLeft());
        }
        if (element.compareTo(node.getElement()) > 0) {
            if (node.getRight() == null) {
                Node<E> newNode = new Node<>(element, null, null);
                node.setRight(newNode);
                return newNode;
            }

            return insert(element, node.getRight());
        }
        return null;
    }
*/

    private Node<E> insert(E element, Node<E> node){

        if(element.compareTo(node.getElement())>0){
           // System.out.println("Right");
            if (node.getRight()==null){
                node.setRight(new Node<E>(element,null,null));
             //   System.out.println("ok1");
                return null;
            }
            else{
                insert(element,node.getRight());
            }
        }
        else if(element.compareTo(node.getElement())<0){
            //System.out.println("Left");
            if (node.getLeft()==null){
                node.setLeft(new Node<E>(element,null,null));
              //  System.out.println("ok2");
                return null;
            }
            else{
                insert(element,node.getLeft());
            }
        }

        return null;
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * Removes an element from the tree maintaining its consistency as a Binary Search Tree.
     */
    public void remove(E element) {
        root = remove(element, root());
    }

    private Node<E> remove(E element, Node<E> node) {

        if (node == null) {
            return null;    //throw new IllegalArgumentException("Element does not exist");
        }
        if (element.compareTo(node.getElement()) == 0) {
            // node is the Node to be removed
            if (node.getLeft() == null && node.getRight() == null) { //node is a leaf (has no childs)
                return null;
            }
            if (node.getLeft() == null) {   //has only right child
                return node.getRight();
            }
            if (node.getRight() == null) {  //has only left child
                return node.getLeft();
            }
            E min = smallestElement(node.getRight());
            node.setElement(min);
            node.setRight(remove(min, node.getRight()));
        } else if (element.compareTo(node.getElement()) < 0)
            node.setLeft(remove(element, node.getLeft()));
        else
            node.setRight(remove(element, node.getRight()));

        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    public int size() {
        if (this.isEmpty()) {
            return 0;
        }

        return size(root);
    }

    private int size(Node<E> node) {
        int size = 1;
        if (node.getLeft() != null) {
            size += size(node.getLeft());
        }
        if (node.getRight() != null) {
            size += size(node.getRight());
        }
        return size;
    }

    /**
     * Returns the height of the tree
     *
     * @return height
     */
    public int height() {
        if (this.isEmpty()) return -1;

        return (height(root) - 1);
    }

    /**
     * Returns the height of the subtree rooted at Node node.
     *
     * @param node A valid Node within the tree
     * @return height
     */


    protected int height(Node<E> node) {
        int heightLeft = 1;
        int heightRight = 1;
        if (node.getLeft() != null) {
            heightLeft += height(node.getLeft());
        }
        if (node.getRight() != null) {
            heightRight += height(node.getRight());
        }
        return Math.max(heightRight, heightLeft);
    }

    /**
     * Returns the smallest element within the tree.
     *
     * @return the smallest element within the tree
     */
    public E smallestElement() {
        if (isEmpty()) return null;

        return smallestElement(root);
    }




    /**
     * Smallest element e.
     *
     * @param node the node
     * @return the e
     */
    protected E smallestElement(Node<E> node) {
        if (node.getLeft() != null) {
            return smallestElement(node.getLeft());
        }
        return node.getElement();
    }


    public int largestElement(Node<Integer> node){
        //Check whether tree is empty
        if(root == null) {
            System.out.println("Tree is empty");
            return 0;
        }
        else{
            int leftMax, rightMax;
            //Max will store temp's data
            int max = (int) node.element;

            //It will find largest element in left subtree
            if(node.left != null){
                leftMax = largestElement(node.left);
                //Compare max with leftMax and store greater value into max
                max = Math.max(max, leftMax);
            }

            //It will find largest element in right subtree
            if(node.right != null){
                rightMax = largestElement(node.right);
                //Compare max with rightMax and store greater value into max
                max = Math.max(max, rightMax);
            }
            return max;
        }
    }
    /**
     * Returns the Node containing a specific Element, or null otherwise.
     *
     * @param element the element to find
     * @param node    the node
     * @return the Node that contains the Element, or null otherwise <p> This method despite not being essential is very useful. It is written here in order to be used by this class and its subclasses avoiding recoding. So its access level is protected
     */
    public Node<E> find(E element, Node<E> node) {


        if (node == null) return null;
        if (element.compareTo(node.getElement()) == 0){
            return node;}
        if (element.compareTo(node.getElement()) < 0) return find(element, node.getLeft());
        return find(element, node.getRight());
    }

    /**
     * Return a list with all node between minimum and maximum.
     *
     * @param node    the node
     * @param minimum the minimum
     * @param maximum the maximum
     * @return the list with all node between minimum and maximum
     */
    public List<E> find(Node<E> node, E minimum, E maximum) {
        if (node == null) return null;

        LinkedList<Node<E>> queue = new LinkedList<>();
        LinkedList<E> list = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node<E> nodeRemove = queue.remove();

            if (nodeRemove.getElement().compareTo(minimum) >= 0 && nodeRemove.getElement().compareTo(maximum) <= 0) {
                list.add(nodeRemove.getElement());
            }
            if (nodeRemove.left != null && nodeRemove.getElement().compareTo(minimum) >= 0) {
                queue.add(nodeRemove.left);
            }
            if (nodeRemove.right != null && nodeRemove.getElement().compareTo(maximum) <= 0) {
                queue.add(nodeRemove.right);
            }
        }

        return list;

    }

    /**
     * Find equals occurrences in all tree.
     *
     * @param node the root
     * @return the tree map: key:E   Value_amount of occurrences
     */
    public TreeMap<String, Integer> findOccurrences(Node<E> node) {
        if (node == null) return null;

        TreeMap<String, Integer> mapAux = new TreeMap<>();

        LinkedList<Node<E>> queue = new LinkedList<>();

        queue.add(node);

        while (!queue.isEmpty()) {
            Node<E> nodeRemove = queue.remove();

            String[] data = nodeRemove.getElement().toString().split(" ");

            List<String> list = new ArrayList<>(Arrays.asList(data));

            while (!list.isEmpty()) {

                StringBuilder s = new StringBuilder();

                for (String aux : list) {
                    s.append(aux).append(" ");
                }

                if (mapAux.isEmpty() || !mapAux.containsKey(s.toString().trim())) {
                    mapAux.put(s.toString().trim(), 0);
                }
                int amount = mapAux.get(s.toString().trim()) + 1;
                mapAux.put(s.toString().trim(), amount);

                list.remove(list.size() - 1);
            }

            if (nodeRemove.left != null) {
                queue.add(nodeRemove.left);
            }
            if (nodeRemove.right != null) {
                queue.add(nodeRemove.right);
            }
        }

        return mapAux;

    }

    /**
     * Find maximum distance and the two nodes.
     *
     * @return the map: key:list with the nodes    Value: distance
     */
    public Map<List<E>, Integer> findMaximumDistance() {
        List<E> listAux = this.getLeafs();
        Map<List<E>, Integer> map = new HashMap<>();

        if(listAux.size()==1){
            List<E> list = new ArrayList<>();
            list.add(root.getElement());
            list.add(listAux.get(0));
            map = new HashMap<>();
            map.put(list, this.height());
            return map;
        }

        int count = 1;
        int maxDistance = 0;

        for (E c : listAux) {
            for (int i = count; i < listAux.size(); i++) {
                int distance = this.findDistance(this.root, c, listAux.get(i));
                if (distance > maxDistance) {
                    maxDistance = distance;
                    List<E> list = new ArrayList<>();
                    list.add(c);
                    list.add(listAux.get(i));
                    map = new HashMap<>();
                    map.put(list, maxDistance);
                }
            }
            count++;
        }

        return map;
    }
    public Local findByDestinatario(String entity) {
        Iterator<Local> list = (Iterator<Local>) this.inOrder().iterator();
        Local aux = null;
        while (list.hasNext()) {
            aux = list.next();
            if (aux.getDestinatário().equals(entity)) {
                return aux;
            }
        }
        return null;
    }

    /**
     * Find distance between two nodes.
     *
     * @param root the root
     * @param a    the nodeA
     * @param b    the nodeB
     * @return the distance
     */
    public int findDistance(Node<E> root, E a, E b) {

        Node<E> lca = LCA(root, a, b);

        int d1 = findLevel(lca, a, 0);
        int d2 = findLevel(lca, b, 0);

        return d1 + d2;
    }

    /**
     * Node in common closer to the root.
     *
     * @param root the root
     * @param n1   the node1
     * @param n2   the node2
     * @return the Node in common closer to the root
     */
    public Node<E> LCA(Node<E> root, E n1, E n2) {

        if (root == null) {
            return root;
        }
        if (root.getElement().equals(n1) || root.getElement().equals(n2)) {
            return root;
        }

        Node<E> left = LCA(root.getLeft(), n1, n2);
        Node<E> right = LCA(root.getRight(), n1, n2);

        if (left != null && right != null) {
            return root;
        }
        if (left == null && right == null) {
            return null;
        }
        if (left != null) {
            return LCA(root.getLeft(), n1, n2);
        }

        return LCA(root.getRight(), n1, n2);
    }

    /**
     * Find node's level.
     *
     * @param root  the root
     * @param a     the node
     * @param level the level
     * @return the level
     */
    public int findLevel(Node<E> root, E a, int level) {

        if (root == null) {
            return -1;
        }
        if (root.getElement().equals(a)) {
            return level;
        }
        int left = findLevel(root.getLeft(), a, level + 1);
        if (left == -1) {
            return findLevel(root.getRight(), a, level + 1);
        }

        return left;
    }

    /**
     * Check if a tree is complete.
     *
     * @return true if is complete and false if not
     */
    public boolean isComplete() {
        Map<Integer, List<E>> levelMap = this.nodesByLevel();

        int size = levelMap.keySet().size();
        boolean flag = true;

        for (Integer level : levelMap.keySet()) {
            if (level == (size - 1)) {
                break;
            }
            if (level != 0) {
                if (levelMap.get(level).size() != (levelMap.get(level - 1).size() * 2)) {
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }

    public List<E> getLeafs(){
        if (root == null) return null;

        LinkedList<Node<E>> queue = new LinkedList<>();
        LinkedList<E> list = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<E> nodeRemove = queue.remove();

            if (nodeRemove.left != null) {
                queue.add(nodeRemove.left);
            }
            if (nodeRemove.right != null) {
                queue.add(nodeRemove.right);
            }
            if(nodeRemove.left == null && nodeRemove.right == null){
                list.add(nodeRemove.getElement());
            }
        }

        return list;
    }

    /**
     * Returns an iterable collection of elements of the tree, reported in in-order.
     *
     * @return iterable collection of the tree's elements reported in in-order
     */
    public Iterable<E> inOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null)
            inOrderSubtree(root, snapshot);   // fill the snapshot recursively
        return snapshot;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given
     * snapshot using an in-order traversal
     *
     * @param node     Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void inOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null)
            return;
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        inOrderSubtree(node.getRight(), snapshot);
    }

    /**
     * Returns an iterable collection of elements of the tree, reported in pre-order.
     *
     * @return iterable collection of the tree's elements reported in pre-order
     */
    public Iterable<E> preOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null)
            preOrderSubtree(root, snapshot);   // fill the snapshot recursively
        return snapshot;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given
     * snapshot using an pre-order traversal
     *
     * @param node     Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void preOrderSubtree(Node<E> node, List<E> snapshot) {
        snapshot.add(node.getElement());
        if (node.getLeft() != null) {
            preOrderSubtree(node.getLeft(), snapshot);
        }
        if (node.getRight() != null) {
            preOrderSubtree(node.getRight(), snapshot);
        }
    }

    /**
     * Returns an iterable collection of elements of the tree, reported in post-order.
     *
     * @return iterable collection of the tree's elements reported in post-order
     */
    public Iterable<E> posOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null)
            posOrderSubtree(root, snapshot);   // fill the snapshot recursively
        return snapshot;
    }

    /**
     * Adds positions of the subtree rooted at Node node to the given
     * snapshot using an post-order traversal
     *
     * @param node     Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void posOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node.getLeft() != null) {
            posOrderSubtree(node.getLeft(), snapshot);
        }
        if (node.getRight() != null) {
            posOrderSubtree(node.getRight(), snapshot);
        }
        snapshot.add(node.getElement());
    }

    /**
     * Returns a map with a list of nodes by each tree level.
     *
     * @return a map with a list of nodes by each tree level
     */
    public Map<Integer, List<E>> nodesByLevel() {
        Map<Integer, List<E>> levelMap = new TreeMap<>();
        if (isEmpty()) return null;
        processBstByLevel(root, levelMap, 0);
        return levelMap;
    }

    private void processBstByLevel(Node<E> node, Map<Integer, List<E>> result, int level) {
        if (!result.containsKey(level)) {
            result.put(level, new LinkedList<>());
        }
        result.get(level).add(node.getElement());
        if (node.getLeft() != null) {
            processBstByLevel(node.getLeft(), result, level + 1);
        }
        if (node.getRight() != null) {
            processBstByLevel(node.getRight(), result, level + 1);
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, 0, sb);
        return sb.toString();
    }

    private void toStringRec(Node<E> root, int level, StringBuilder sb) {
        if (root == null) {
            return;
        }
        toStringRec(root.getRight(), level + 1, sb);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                sb.append("|\t");
            }
            sb.append("|-------").append(root.getElement().toString()).append("\n");
        } else {
            sb.append(root.getElement().toString()).append("\n");
        }
        toStringRec(root.getLeft(), level + 1, sb);
    }

}

