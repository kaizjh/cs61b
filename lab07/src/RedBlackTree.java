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
        node.isBlack = !node.isBlack;
        node.left.isBlack = !node.left.isBlack;
        node.right.isBlack = !node.right.isBlack;
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
        RBTreeNode nodeCopy = new RBTreeNode(node.isBlack, node.item, node.left, node.right);
        RBTreeNode leftCopy = new RBTreeNode(node.left.isBlack, node.left.item, node.left.left, node.left.right);

        // 左边的节点向右旋转成为新的node，新node的颜色是原来node的颜色，原来node向右旋转成为了新node的右子树
        node = node.left;
        node.right = nodeCopy;
        node.isBlack = nodeCopy.isBlack;

        // 右节点（原来的node向右旋转而来）的左子树是原来的左节点的右子树，右节点的颜色是原来左节点的，右节点的右子树保持原来节点的不变
        node.right.left = leftCopy.right;
        node.right.isBlack = leftCopy.isBlack;

        return node;
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
        RBTreeNode nodeCopy = new RBTreeNode(node.isBlack, node.item, node.left, node.right);
        RBTreeNode rightCopy = new RBTreeNode(node.right.isBlack, node.right.item, node.right.left, node.right.right);

        node = node.right;
        node.left = nodeCopy;
        node.isBlack = nodeCopy.isBlack;

        node.left.right = rightCopy.left;
        node.left.isBlack = rightCopy.isBlack;

        return node;
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

        if (node == null) {
            return new RBTreeNode<>(true, item);
        }

        RBTreeNode<T> curr = node;
        RBTreeNode<T> parent = null;
        while (curr.item.compareTo(item) != 0) {
            if (curr.item.compareTo(item) < 0) {
                if (curr.right == null) { // 到达右leaf，为当前节点新增一个右子树
                    curr.right = new RBTreeNode<>(false, item);

                    if (isRed(curr)) { // 如果curr 是红色，则parent 一定不是红色，curr.left 一定不是红色
                        rotateLeft(curr);
                        rotateRight(parent);
                    } else { // 如果curr 不是红色，那么parent 一定不是红色，curr.left 有两种情况
                        if (isRed(curr.left)) { // 如果 curr.left 是红色，对当前节点进行颜色反转
                            flipColors(curr);
                        } else { // 如果curr.left 是红色，向左旋转当前节点
                            rotateLeft(curr);
                        }
                    }
                    break;
                }
                parent = curr;
                curr = curr.right;
            } else {
                if (curr.left == null) { // 到达左leaf
                    curr.left = new RBTreeNode<>(false, item);

                    if (isRed(curr)) { // 如果当前节点为红色，则通过向右旋转父节点来消除两个连续节点同时为红色的异常
                        rotateRight(parent);
                    }
                    break;
                }
                parent = curr;
                curr = curr.left;
            }
        }
        return root;
    }

}
