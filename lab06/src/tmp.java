//public class tmp {
//    public class BSTSet<K extends Comparable<K>> implements Set61B<K> {
//        private class BSTNode {
//            K item;
//            // ...
//        }
//
//        private BSTNode root;
//
//        /* Returns whether this BSTSet's root is greater than, equal to, or less
//         * than the other BSTSet's root, following the usual `compareTo`
//         * convention. */
//        public int compareRoots(BSTSet other) {
//            /* We are able to safely invoke `compareTo` on `n1.item` because we
//             * know that `K` extends `Comparable<K>`, so `K` is a `Comparable`, and
//             *`Comparable`s must implement `compareTo`. */
//            return this.root.item.compareTo(other.root.item);
//        }
//
//        // ...
//    }
//
//}
