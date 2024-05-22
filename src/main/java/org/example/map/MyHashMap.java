package org.example.map;

public class MyHashMap<K, V> {
    static class Node<K, V> {
        private final int hash;
        private final K key;
        private V value;
        private Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public int getHash() {
            return hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    static final int CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Node<K, V>[] table;
    private int size;


    public MyHashMap() {
        this.table = (Node<K, V>[]) new Node[CAPACITY];
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    private int hash(K key) {
        return key == null ? 0 : key.hashCode();
    }

    private int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    public void put(K key, V value) {
        if (key == null) {
            putForNullKey(value);
        } else {
            int hash = hash(key);
            int index = indexFor(hash, table.length);
            if (table[index] == null) {
                table[index] = addNode(hash, key, value);
            } else {
                addNodeToLinkedList(table[index], hash, key, value);
            }
            if (size > table.length * DEFAULT_LOAD_FACTOR) {
                resize();
            }
        }
        size++;
    }

    public void resize() {
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[table.length * 2];
        for (Node<K, V> kvNode : table) {
            Node<K, V> node = kvNode;
            while (node != null) {
                K key = node.getKey();
                V value = node.getValue();

                if (key == null) {
                    if (newTable[0] == null) {
                        newTable[0] = addNode(0, null, value);
                    } else {
                        addNodeToLinkedList(newTable[0], 0, null, value);
                    }
                } else {
                    int hash = hash(key);
                    int index = indexFor(hash, newTable.length);
                    if (newTable[index] == null) {
                        newTable[index] = addNode(hash, key, value);
                    } else {
                        addNodeToLinkedList(newTable[index], hash, key, value);
                    }
                }
                node = node.getNext();
            }
        }
        table = newTable;
    }

    public V get(K key) {
        int index;
        int hash;
        if (key == null) {
            index = 0;
            hash = 0;
        } else {
            hash = hash(key);
            index = indexFor(hash, table.length);
        }
        return getValue(table[index], hash, key);
    }

    private V getValue(Node<K, V> node, int hash, K key) {
        if (node.getHash() == hash
                && (node.getKey() != null && node.getKey().equals(key))
                || (key == null && node.getKey() == null)) {
            return node.getValue();
        } else {
            if (node.getNext() == null) {
                return null;
            } else {
                getValue(node.getNext(), hash, key);
            }
        }
        return null;
    }

    private void putForNullKey(V value) {
        if (table[0] == null) {
            table[0] = addNode(0, null, value);
        } else {
            addNodeToLinkedList(table[0], 0, null, value);
        }
    }

    private void addNodeToLinkedList(Node<K, V> node, int hash, K key, V value) {
        if (node.getHash() == hash
                && (node.getKey() != null && node.getKey().equals(key))
                || (key == null && node.getKey() == null)) {
            node.setValue(value);
        } else {
            if (node.getNext() == null) {
                Node<K, V> nextNode = addNode(hash, key, value);
                node.setNext(nextNode);
            } else {
                addNodeToLinkedList(node.getNext(), hash, key, value);
            }
        }
    }


    private Node<K, V> addNode(int hash, K key, V value) {
        return new Node<>(hash, key, value, null);
    }
}