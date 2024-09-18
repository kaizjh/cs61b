import java.util.Comparator;

public class RedBlackTree<T extends Comparable<T>> {

    /* Root of the tree. */
    RBTreeNode<T> root;

    static class RBTreeNode<T> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /**
         * Creates a RBTreeNode with item ITEM and color depending on ISBLACK
         * value.
         * @param isBlack
         * @param item
         */
        // 这行代码我是第一次见（可能见过类似的），很巧妙，虽然是重载了构造函数，但两者只差了两个参数而已，函数体一样，就可以用这个方法减少重复代码。
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /**
         * Creates a RBTreeNode with item ITEM, color depending on ISBLACK
         * value, left child LEFT, and right child RIGHT.
         * @param isBlack
         * @param item
         * @param left
         * @param right
         */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Creates an empty RedBlackTree.
     */
    public RedBlackTree() {
        root = null;
    }

    /**
     * Flips the color of node and its children. Assume that NODE has both left
     * and right children
     * @param node
     */
    void flipColors(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE
        node.isBlack = false;
        node.left.isBlack = true;
        node.right.isBlack = true;
    }

    private void parentToNewRoot(RBTreeNode<T> oldr, RBTreeNode<T> newr, RBTreeNode<T> parent) {

        if (parent == null || parent.item.compareTo(oldr.item) == 0) {
            root = newr;
        } else if (parent.item.compareTo(oldr.item) < 0) {
            if (parent.right.item.compareTo(oldr.item) == 0) {
                parent.right = newr;
            } else {
                parentToNewRoot(oldr, newr, parent.right);
            }

        } else if (parent.item.compareTo(oldr.item) > 0) {
            if (parent.left.item.compareTo(oldr.item) == 0) {
                parent.left = newr;
            } else {
                parentToNewRoot(oldr, newr, parent.left);
            }
        }
    }

    /**
     * Rotates the given node to the right. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * @param node
     * @return
     */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE

        // 向右旋转，类似于将 node 与 node.left 位置交换，但是新的子节点在右边而不是左边
        RBTreeNode<T> newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;

        // 再交换两个节点的颜色
        boolean tmp = newRoot.isBlack;
        newRoot.isBlack = node.isBlack;
        node.isBlack = tmp;

        parentToNewRoot(node, newRoot, root);

        return newRoot;
    }

    /**
     * Rotates the given node to the left. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * @param node
     * @return
     */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE

        // 向左旋转只是将向右旋转代码中的left全部换成了right，right全部换成了left
        RBTreeNode<T> newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;

        // 再交换两个节点的颜色
        boolean tmp = newRoot.isBlack;
        newRoot.isBlack = node.isBlack;
        node.isBlack = tmp;

        parentToNewRoot(node, newRoot, root);

        return newRoot;
    }

    /**
     * Helper method that returns whether the given node is red. Null nodes (children or leaf
     * nodes) are automatically considered black.
     * @param node
     * @return
     */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

    /**
     * Inserts the item into the Red Black Tree. Colors the root of the tree black.
     * @param item
     */
    public void insert(T item) {
        root = insert(root, item);
        root.isBlack = true;
    }

    /**
     * Inserts the given node into this Red Black Tree. Comments have been provided to help break
     * down the problem. For each case, consider the scenario needed to perform those operations.
     * Make sure to also review the other methods in this class!
     * @param node
     * @param item
     * @return
     */
    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {
        // TODO: Insert (return) new red leaf node.

        // TODO: Handle normal binary search tree insertion.

        // TODO: Rotate left operation

        // TODO: Rotate right operation

        // TODO: Color flip

        // 这是我的写法，感觉写的太烂了，太复杂了，细节问题搞得我头晕眼花！
//        if (node == null) {
//            return new RBTreeNode<>(true, item);
//        }
//
//        RBTreeNode<T> curr = node;
//        RBTreeNode<T> parent = null;
//        while (curr.item.compareTo(item) != 0) {
//            if (curr.item.compareTo(item) < 0) {
//                if (curr.right == null) { // 到达右leaf，为当前节点新增一个右子树
//                    curr.right = new RBTreeNode<>(false, item);
//
//                    if (isRed(curr)) { // 此时，如果curr 是红色，则parent 一定不是红色，且curr 一定“左倾”，curr.left 一定不是红色
//                        rotateLeft(curr);
//                        flipColors(rotateRight(parent));
//                        if (root.right.isBlack == false && root.left.isBlack == false) {
//                            flipColors(root);
//                        }
//                    } else { // 如果curr 不是红色，那么parent 一定不是红色，curr.left 有两种情况
//                        if (isRed(curr.left)) { // 如果 curr.left 是红色，对当前节点进行颜色反转
//                            flipColors(curr);
//                        } else { // 如果curr.left 不是红色，向左旋转当前节点
//                            rotateLeft(curr);
//                        }
//                    }
//                    break;
//                }
//                parent = curr;
//                curr = curr.right;
//            } else {
//                if (curr.left == null) { // 到达左leaf
//                    curr.left = new RBTreeNode<>(false, item);
//
//                    if (isRed(curr)) { // 如果当前节点为红色，则通过向右旋转父节点来消除两个连续节点同时为红色的异常
//                        flipColors(rotateRight(parent));
//                        if (root.right.isBlack == false && root.left.isBlack == false) {
//                            flipColors(root);
//                        }
//                    }
//                    break;
//                }
//                parent = curr;
//                curr = curr.left;
//            }
//        }
//
//        return root;

        // 这是ChatGPT写的代码，写的真好。感觉我总是不能抓住问题的核心，将问题明确分成多个小问题，于是陷入无尽的细节之中，便被耗光了精力
        if (node == null) {
            // Insert new red node.
            return new RBTreeNode<>(false, item);
        }

        // 先把节点加入到左偏红黑树当中去
        int cmp = item.compareTo(node.item);
        if (cmp < 0) {
            node.left = insert(node.left, item);
        } else if (cmp > 0) {
            node.right = insert(node.right, item);
        }

        // 再考虑加入之后的几种情况
        // Fix right-leaning red link.
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        // Fix two consecutive left red links.
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        // Color flip if both children are red.
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

}
