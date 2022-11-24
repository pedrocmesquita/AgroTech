package Shared.BST;

import java.util.List;
import java.util.Map;

public interface BSTInterface<E> {

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    boolean isEmpty();

    /**
     * Insert.
     *
     * @param element the element
     */
    void insert(E element);

    /**
     * Remove.
     *
     * @param element the element
     */
    void remove(E element);

    /**
     * Size.
     *
     * @return the size
     */
    int size();

    /**
     * Height.
     *
     * @return the height
     */
    int height();

    /**
     * Smallest element.
     *
     * @return the smallest element
     */
    E smallestElement();

    /**
     * In order iterable.
     *
     * @return the iterable
     */
    Iterable<E> inOrder();

    /**
     * Pre order iterable.
     *
     * @return the iterable
     */
    Iterable<E> preOrder();

    /**
     * Pos order iterable.
     *
     * @return the iterable
     */
    Iterable<E> posOrder();

    /**
     * Nodes by level map.
     *
     * @return the map
     */
    Map<Integer, List<E>> nodesByLevel();
}

