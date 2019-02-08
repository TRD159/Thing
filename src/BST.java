import java.util.ArrayList;

public class BST<E extends Comparable> implements BST_Interface<E> {
    BSTNode<E> root;

    public BST() {
        root = null;
    }

    public BST(E data) {
        root = new BSTNode<>(data);
    }

    @Override
    public String preOrder() {
        String s = "[";
        s += preHelp(root);
        s += "]";
        return s;
    }

    public String preHelp(BSTNode<E> n) {
        String s = "";
        if (n != null) {
            s += n.getData();
            if (n.getLeft() != null) {
                s += ", " + preHelp(n.getLeft());
            }
            if (n.getRight() != null) {
                s += ", " + preHelp(n.getRight());
            }
        }
        return s;
    }

    @Override
    public String inOrder() {
        String s = "[";
        String t = inHelp(root);
        if (t.length() > 2) {
            s += t.substring(0, t.length() - 2);
        } else {
            s += t;
        }
        s += "]";
        return s;
    }

    public String inHelp(BSTNode<E> n) {
        String s = "";
        if (n != null) {
            if (n.getLeft() != null) {
                s += inHelp(n.getLeft());
            }
            s += n.getData() + ", ";

            if (n.getRight() != null) {
                s += inHelp(n.getRight());
            }
        }
        return s;
    }

    @Override
    public String postOrder() {
        String s = "[";
        s += postHelp(root);
        s += "]";
        return s;
    }

    public String postHelp(BSTNode<E> n) {
        String s = "";
        if (n != null) {
            if (n.getLeft() != null) {
                s += postHelp(n.getLeft()) + ", ";
            }
            if (n.getRight() != null) {
                s += postHelp(n.getRight()) + ", ";
            }
            s += n.getData();
        }
        return s;
    }

    @Override
    public E minValue() {
        BSTNode<E> r = root;
        return minHelp(r);
    }

    public E minHelp(BSTNode<E> r) {
        if (r == null || r.getLeft() == null) {
            return r.getData();
        } else {
            return minHelp(r.getLeft());
        }
    }

    @Override
    public E maxValue() {
        BSTNode<E> r = root;
        return maxHelp(r);
    }

    public E maxHelp(BSTNode<E> r) {
        if (r == null || r.getRight() == null) {
            return r.getData();
        } else {
            return maxHelp(r.getRight());
        }
    }

    @Override
    public int maxDepth() {
        if (root == null) {
            return -1;
        } else {
            int e = 0;
            BSTNode<E> r = root;
            e = depthHelp(r);
            return e;
        }

    }

    public int depthHelp(BSTNode<E> n) {
        int l, r;
        if (n == null) {
            return -1;
        } else {
            l = depthHelp(n.getLeft());
            r = depthHelp(n.getRight());
        }
        if (l > r) {
            return l + 1;
        } else {
            return r + 1;
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public int size() {
        return sizeHelp(root, 0);
    }

    public int sizeHelp(BSTNode<E> n, int s) {
        if (n == null) {
            return s;
        } else {
            if (n.getRight() != null) {
                s++;
                sizeHelp(n, s);
            }
            if (n.getLeft() != null) {
                s++;
                sizeHelp(n, s);
            }
        }
        return s;
    }

    @Override
    public boolean empty() {
        return root == null;
    }

    @Override
    public boolean contains(E data) {
        BSTNode<E> r = root;
        return containsHelp(data, root);
    }

    public boolean containsHelp(E data, BSTNode<E> r) {
        if (r.getData() == data) {
            return true;
        } else {
            if (r.getLeft() != null && data.compareTo(r.getData()) < 0) {
                return containsHelp(data, r.getLeft());
            } else if (r.getRight() != null && data.compareTo(r.getData()) > 0) {
                return containsHelp(data, r.getRight());
            }
        }
        return false;
    }

    @Override
    public boolean insert(E data) {
        return false;
    }

    @Override
    public boolean add(E data) {
        BSTNode<E> r = root;
        if (root == null) {
            root = new BSTNode<>(data);
            return true;
        } else {
            return addHelp(r, data);
        }
    }

    public boolean addHelp(BSTNode<E> r, E data) {
        if (r.getLeft() == null && data.compareTo(r.getData()) < 0) {
            r.setLeft(new BSTNode<>(data));
            return true;
        } else if (r.getRight() == null && data.compareTo(r.getData()) > 0) {
            r.setRight(new BSTNode<>(data));
            return true;
        }

        if (r.getLeft() != null && data.compareTo(r.getData()) < 0) {
            return addHelp(r.getLeft(), data);
        } else if (r.getRight() != null && data.compareTo((r.getData())) > 0) {
            return addHelp(r.getRight(), data);
        }

        return false;
    }

    public void otherAddHelp(BSTNode<E> r, E data, ArrayList<BSTNode<E>> d) {
    }

    @Override
    public boolean remove(E data) {
        if (root == null) {
            return false;
        }
        if (root.getData().equals(data)) {
            BSTNode<E> p = new BSTNode<>(null);
            p.setRight(root);
            if (root.getRight() == null && root.getLeft() == null) {
                p.setRight(null);
            } else if (root.getRight() == null) {
                p.setRight(root.getLeft());
            } else if (root.getLeft() == null) {
                p.setRight(root.getRight());
            } else {
                E value = minHelp(root.getRight());
                removeHelp(value, root);
                root.setData(value);
            }
            return true;
        } else {
            return removeHelp(data, root);
        }
    }

    public boolean removeHelp(E data, BSTNode<E> n) {
        while (true) {
            if (data.compareTo(n.getLeft().getData()) < 0) {
                if (n.getLeft() == null) {
                    return false;
                } else if (n.getData().equals(data)) {
                    if (n.getLeft().getLeft() == null && n.getLeft().getRight() == null) {
                        n.setLeft(null);
                    } else if (n.getLeft().getLeft() != null && n.getLeft().getRight() == null) {
                        n.setLeft(n.getLeft().getLeft());
                    } else if (n.getLeft().getLeft() == null && n.getLeft().getRight() != null) {
                        n.setLeft(n.getLeft().getRight());
                    } else {
                        E value = minHelp(n.getLeft().getRight());
                        removeHelp(value, root);
                        n.getLeft().setData(value);
                    }
                    return true;
                }
               else
                    {
                        n = n.getLeft();
                    }
                } else if (data.compareTo(n.getRight().getData()) > 0) {
                    if (n.getRight() == null) {
                        return false;
                    } else if (n.getData().equals(data)) {
                        if (n.getRight().getLeft() == null && n.getRight().getRight() == null) {
                            n.setRight(null);
                        } else if (n.getRight().getLeft() != null && n.getRight().getRight() == null) {
                            n.setRight(n.getRight().getLeft());
                        } else if (n.getRight().getLeft() == null && n.getRight().getRight() != null) {
                            n.setRight(n.getRight().getRight());
                        } else {
                            E value = minHelp(n.getRight().getRight());
                            removeHelp(value, root);
                            n.getRight().setData(value);
                        }
                        return true;
                    } else {
                        n = n.getRight();
                    }
                }
            }
        }
        public BSTNode<E> otherMinHelp (BSTNode<E> n) {
            if (n.getLeft().getData().equals(minValue())) {
                return n;
            } else {
                return otherMinHelp(n.getLeft());
            }
        }
    }


