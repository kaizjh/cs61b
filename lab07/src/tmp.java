//public class RedBlackTree<T extends Comparable<T>> {
//
//    /* Root of the tree. */
//    RBTreeNode<T> root;
//
//    static class RBTreeNode<T> {
//
//        final T item;
//        boolean isBlack;
//        RBTreeNode<T> left;
//        RBTreeNode<T> right;
//
//        RBTreeNode(boolean isBlack, T item) {
//            this(isBlack, item, null, null);
//        }
//
//        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left, RBTreeNode<T> right) {
//            this.isBlack = isBlack;
//            this.item = item;
//            this.left = left;
//            this.right = right;
//        }
//    }
//
//    public RedBlackTree() {
//        root = null;
//    }
//
//    void flipColors(RBTreeNode<T> node) {
//        node.isBlack = !node.isBlack;
//        if (node.left != null) {
//            node.left.isBlack = !node.left.isBlack;
//        }
//        if (node.right != null) {
//            node.right.isBlack = !node.right.isBlack;
//        }
//    }
//
//    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
//        RBTreeNode<T> newRoot = node.left;
//        node.left = newRoot.right;
//        newRoot.right = node;
//        newRoot.isBlack = node.isBlack;
//        node.isBlack = false;
//        return newRoot;
//    }
//
//    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
//        RBTreeNode<T> newRoot = node.right;
//        node.right = newRoot.left;
//        newRoot.left = node;
//        newRoot.isBlack = node.isBlack;
//        node.isBlack = false;
//        return newRoot;
//    }
//
//    private boolean isRed(RBTreeNode<T> node) {
//        return node != null && !node.isBlack;
//    }
//
//    public void insert(T item) {
//        root = insert(root, item);
//        root.isBlack = true; // Root must always be black.
//    }
//
//    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {
//        if (node == null) {
//            // Insert new red node.
//            return new RBTreeNode<>(false, item);
//        }
//
//        // 先把节点加入到左偏红黑树当中去
//        int cmp = item.compareTo(node.item);
//        if (cmp < 0) {
//            node.left = insert(node.left, item);
//        } else if (cmp > 0) {
//            node.right = insert(node.right, item);
//        }
//
//        // 再考虑加入之后的几种情况
//        // Fix right-leaning red link.
//        if (isRed(node.right) && !isRed(node.left)) {
//            node = rotateLeft(node);
//        }
//        // Fix two consecutive left red links.
//        if (isRed(node.left) && isRed(node.left.left)) {
//            node = rotateRight(node);
//        }
//        // Color flip if both children are red.
//        if (isRed(node.left) && isRed(node.right)) {
//            flipColors(node);
//        }
//
//        return node;
//    }
//}
