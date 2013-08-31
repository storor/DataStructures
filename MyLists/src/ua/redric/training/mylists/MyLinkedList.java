package ua.redric.training.mylists;

import java.util.Iterator;

/**
 * Based on double-linked list implementation of MyList
 *
 * @author Serhii_Tolok
 */
public class MyLinkedList<T> implements MyList<T>, MyStack<T>, MyQueue<T> {

    private Node<T> first;
    private Node<T> last;
    int size;
    private int currentIndex;
    private Node<T> currentNode;

    private static class Node<T> {

        Node next;
        Node previous;
        T data;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }

        void setNext(Node next) {
            this.next = next;
        }

        Node getNext() {
            return next;
        }

        void setPrevious(Node previous) {
            this.previous = previous;
        }

        Node getPrevious() {
            return previous;
        }

        T getData() {
            return data;
        }

        void setData(T data) {
            this.data = data;
        }
    }

    public MyLinkedList() {
    }

    public MyLinkedList(MyList<T> collection) {
        this();
        if (collection != null) {
            for (Object ob : collection) {
                Node wasLast = last;
                last = new Node((T) ob);
                last.setPrevious(wasLast);
                if (wasLast != null) {
                    wasLast.setNext(last);
                } else {
                    first = last;
                }
                size++;
            }
        }
    }

    public void addFirst(T e) {
        Node wasFirst = first;
        first = new Node((T) e);
        first.setNext(wasFirst);
        if (wasFirst != null) {
            wasFirst.setPrevious(first);
        } else {
            last = first;
        }
        size++;
    }

    public void addLast(T e) {
        Node wasLast = last;
        last = new Node((T) e);
        last.setPrevious(wasLast);
        if (wasLast != null) {
            wasLast.setNext(last);
        } else {
            first = last;
        }
        size++;
    }

    public T getFirst() {
        return first.data;
    }

    public T getLast() {
        return last.data;
    }

    public T removeFirst() {
        if (size > 0) {
            Node<T> result = first;
            first = first.getNext();
            first.setPrevious(null);
            size--;
            return result.data;
        } else {
            throw new MyIndexOutOfBoundsException();
        }
    }

    public T removeLast() {
        if (size > 0) {
            Node<T> result = last;
            last = last.getPrevious();
            last.setNext(null);
            size--;
            return result.data;
        }
        return null;
    }

    public Iterator descendingIterator() {
        currentIndex = size - 1;
        currentNode = last;
        Iterator iterator = new Iterator() {
            @Override
            public boolean hasNext() {
                return currentIndex >= 0;
            }

            @Override
            public T next() {
                T result = currentNode.getData();
                if (hasNext()) {
                    currentNode = currentNode.getPrevious();
                    currentIndex--;
                }
                return result;
            }

            @Override
            public void remove() {
                MyLinkedList.this.remove(currentIndex);
            }
        };
        return iterator;
    }

    @Override
    public void add(T e) {
        addLast(e);
    }

    @Override
    public void add(int index, T e) {
        if (index < size && index >= 0) {
            Node temp = first;
            for (int i = 0; i < index; i++) {
                    temp = temp.getNext();
                }
            temp.setData(e);
        } else {
            throw new MyIndexOutOfBoundsException();
        }

    }

    @Override
    public void addAll(T[] collection) {
        if (collection == null) {
            return;
        }
        for (T ob : collection) {
            addLast(ob);
        }
    }

    @Override
    public void addAll(int index, T[] array) {
        if (array == null) {
            return;
        }
        Node temp;
        Node next = first;
        if (index < size&&index>=0) {
            for (int i = 0; i < index; i++) {
                next = next.getNext();
            }
            temp = next.getPrevious();
            Node newNode;
            for (T ob : array) {
                newNode = new Node((T) ob);
                newNode.setNext(next);//+
                next.setPrevious(newNode);
                newNode.setPrevious(temp);//+
                if (temp != null) {
                    temp.setNext(newNode);
                } else {
                    first = newNode;
                }
                temp = newNode;//+
                size++;

            }
        } else {
            throw new MyIndexOutOfBoundsException();
        }
    }

    @Override
    public T get(int index) {
        Node<T> temp = first;
        if (index < size && index >= 0) {
            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
            return temp.data;
        } else {
            throw new MyIndexOutOfBoundsException();
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            Node<T> temp = first;
            T result;
            if (index == 0) {
                return removeFirst();
            } else if (index == size - 1) {
                return removeLast();
            } else {
                for (int i = 0; i < index; i++) {
                    temp = temp.getNext();
                }
                result = temp.data;
                temp.getNext().setPrevious(temp.getPrevious());
                temp.getPrevious().setNext(temp.getNext());
                size--;
            }
            return result;
        } else {
            throw new MyIndexOutOfBoundsException();
        }
    }

    @Override
    public void clear() {
        first = last = new Node();
        first.setNext(last);
        last.setPrevious(first);
        size = 0;

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void set(int index, T e) {
        Node<T> temp = first;
        if (index < size && index >= 0) {
            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
            temp.setData((T) e);
        } else {
            throw new MyIndexOutOfBoundsException();
        }

    }

    @Override
    public int indexOf(T o) {
        int i = 0;
        Iterator it = this.iterator();
        while (it.hasNext()) {
            if (it.next().equals(o)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;
        Iterator it = this.iterator();
        while (it.hasNext()) {
            arr[i] = it.next();
            i++;
        }
        return arr;
    }

    @Override
    public Iterator iterator() {
        currentNode = first;
        currentIndex = 0;
        Iterator iterator = new Iterator() {
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                T result = currentNode.data;
                if (hasNext()) {
                    currentNode = currentNode.getNext();
                    currentIndex++;
                }
                return result;

            }

            @Override
            public void remove() {
                MyLinkedList.this.remove(currentIndex);
            }
        };
        return iterator;
    }

    //Stack
    @Override
    public void push(T element) {
        addFirst(element);
    }

    @Override
    public T pop() {
        return removeFirst();
    }

    ///Queue
    @Override
    public void offer(T element) {
        addLast(element);
    }

    @Override
    public T peek() {
        return getFirst();
    }

    @Override
    public T poll() {
        return removeFirst();
    }
}
