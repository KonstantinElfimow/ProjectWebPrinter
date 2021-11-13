package ru.vsu.cs.elfimov_k_d.util;

import java.util.Comparator;
import java.util.Objects;

public class MyPriorityQueue<T> {
    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;
    private Comparator<T> comparator;

    public MyPriorityQueue (Comparator<T> comparator) {
        head = tail = new ListNode<>();
        size = 0;
        this.comparator = comparator;
    }

    private static class ListNode<T> {
        public T value;
        public ListNode<T> next;

        public ListNode(T value, ListNode<T> next) {
            this.value = value;
            this.next = next;
        }
        public ListNode(T value) {
            this.value = value;
            this.next = null;
        }
        public ListNode() {
            this.value = null;
            this.next = null;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ListNode<T> listNode = (ListNode<T>) o;
            return value.equals(listNode.value);
        }
    }

    public void add (T o) {
        ListNode<T> newNode = new ListNode<>(o);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            if (comparator.compare(newNode.value, tail.value) >= 0) {
                tail.next = newNode;
                tail = newNode;
            } else {
                ListNode<T> node = head;
                if (comparator.compare(node.value, newNode.value) >= 0) {
                    newNode.next = node;
                    head = newNode;
                } else {
                    while (node.next != null) {
                        if (comparator.compare(node.next.value, newNode.value) > 0) {
                            newNode.next = node.next;
                            node.next = newNode;
                            break;
                        }
                        node = node.next;
                    }
                }
            }
        }
        size++;
    }

    public T poll () {
        if (!isEmpty()) {
            T value = head.value;
            if (head != tail) {
                ListNode<T> hat = head;
                head = null;
                head = hat.next;
            } else {
                head = tail = null;
            }
            size--;
            return value;
        }
        throw new NullPointerException();
    }

    public boolean isEmpty () {
        return size == 0;
    }

    private ListNode<T> getNode(int index) throws Exception {
        if (index < 0 || index >= size) {
            throw new Exception("Неверный индекс");
        }
        ListNode<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }
    private void emptyError() throws Exception {
        if (size == 0) {
            throw new Exception("Очередь пуста");
        }
    }

    public T getFirst() throws Exception {
        emptyError();
        return head.value;
    }

    public T getLast() throws Exception {
        emptyError();
        return tail.value;
    }
    public T removeFirst() throws Exception {
        T value = getFirst();
        head = head.next;
        size--;
        if (size == 0) {
            tail = null;
        }
        return value;
    }

    public T removeLast() throws Exception {
        T value = getLast();
        size--;
        if (size == 0) {
            head = tail = null;
        } else {
            tail = getNode(size - 1);
            tail.next = null;
        }
        return value;
    }

    public T remove(int index) throws Exception {
        if (index < 0 || index >= size) {
            throw new Exception("Неверный индекс");
        }

        T value;
        if (index == 0) {
            value = head.value;
            head = head.next;
            if (head == null) {
                tail = null;
            }
        } else {
            ListNode<T> prev = getNode(index - 1);
            value = prev.next.value;
            prev.next = prev.next.next;
            if (prev.next == null) {
                tail = prev;
            }
        }
        size--;
        return value;
    }

    public int size () {
        return size;
    }
}