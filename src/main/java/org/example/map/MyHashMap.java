package org.example.map;

import java.util.Arrays;

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

    private static final int CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
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

    private void resize() {
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[table.length * 2];
        for (Node<K, V> mainNode : table) {
            Node<K, V> node = mainNode;
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

    /**
     * Returns {@code true} if this map contains no key-value mappings.
     *
     * @return {@code true} if this map contains no key-value mappings
     */
    public boolean isEmpty() {
        return size == 0;
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
        return table[index] == null ? null : getValue(table[index], hash, key);
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
                return getValue(node.getNext(), hash, key);
            }
        }
    }

    public void put(K key, V value) {
        if (key == null) {
            putForNullKey(value);
        } else {
            int hash = hash(key);
            int index = indexFor(hash, table.length);
            if (table[index] == null) {
                table[index] = addNode(hash, key, value);
                size++;
            } else {
                addNodeToLinkedList(table[index], hash, key, value);
            }
            if (size > table.length * DEFAULT_LOAD_FACTOR) {
                int saveSize = size;
                resize();
                size = saveSize;
            }
        }
    }

    /**
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     */
    public void clear() {
        if (table != null && size > 0) {
            size = 0;
            Arrays.fill(table, null);
        }
    }

    /**
     * Returns {@code true} if this map contains a mapping for the
     * specified key.
     *
     * @param key The key whose presence in this map is to be tested
     * @return {@code true} if this map contains a mapping for the specified
     * key.
     */
    public boolean containsKey(K key) {
        int index;
        int hash;
        if (key == null) {
            index = 0;
            hash = 0;
        } else {
            hash = hash(key);
            index = indexFor(hash, table.length);
        }
        return table[index] != null && isKey(table[index], hash, key);
    }

    private boolean isKey(Node<K, V> node, int hash, K key) {
        if (node.getHash() == hash
                && (node.getKey() != null && node.getKey().equals(key))
                || (key == null && node.getKey() == null)) {
            return true;
        } else {
            if (node.getNext() == null) {
                return false;
            } else {
                return isKey(node.getNext(), hash, key);
            }
        }
    }

    public boolean containsValue(V value) {
        boolean flag = false;
        if (size != 0) {
            for (Node<K, V> mainNode : table) {
                Node<K, V> node = mainNode;
                while (node != null) {
                    if (value.equals(node.getValue())) {
                        return true;
                    } else {
                        node = node.getNext();
                    }
                }
            }
        }
        return flag;
    }

    private void putForNullKey(V value) {
        if (table[0] == null) {
            table[0] = addNode(0, null, value);
            size++;
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
                size++;
            } else {
                addNodeToLinkedList(node.getNext(), hash, key, value);
            }
        }
    }

    private Node<K, V> addNode(int hash, K key, V value) {
        return new Node<>(hash, key, value, null);
    }

    public V remove(K key) {
        int index;
        int hash;
        if (key == null) {
            index = 0;
            hash = 0;
        } else {
            hash = hash(key);
            index = indexFor(hash, table.length);
        }
        return table[index] == null ? null : removeNode(table[index], hash, key, null, index);
    }

    private V removeNode(Node<K, V> node, int hash, K key, Node<K, V> beforeNode, int index) {
        if (node.getHash() == hash
                && (node.getKey() != null && node.getKey().equals(key))
                || (key == null && node.getKey() == null)) {
            V value = node.getValue();
            if (beforeNode != null) {
                if (node.getNext() == null) {
                    beforeNode.setNext(null);
                } else {
                    beforeNode.setNext(node.getNext());
                }
            } else {
                if (node.getNext() == null) {
                    table[index] = null;
                } else {
                    table[index] = node.getNext();
                }
            }
            size--;
            return value;
        } else {
            if (node.getNext() == null) {
                return null;
            } else {
                return removeNode(node.getNext(), hash, key, node, index);
            }
        }
    }
}