package ru.skl.lesson2;


public class MyLinkedList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    public MyLinkedList() {
    }

    public E getFirstElement() {
        return first.getElement();
    }

    public E getLastElement() {
        return last.getElement();
    }

    public Node<E> getFirst() {
        return first;
    }

    public Node<E> getLast() {
        return last;
    }

    public void addFirst(E element) {
        Node<E> temp = first;

        first = new Node<>(element, null, first);
        if (temp != null) {
            temp.setPrev(first);
        } else {
            last = first;
        }

        size++;
    }

    public void addLast(E element) {
        Node<E> temp = last;
        Node<E> newNode = new Node<>(element, temp, null);
        last = newNode;
        // list empty
        if (temp == null) {
            first = newNode;
        } else {
            temp.setNext(newNode);
        }

        size++;
    }

    public void add(E element) {
        addLast(element);
    }

    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        if (index == 0) {
            return getFirstElement();
        }
        if (index == size - 1) {
            return getLastElement();
        }

        int middle = size / 2;
        int counter = 0;
        Node<E> temp = first.getNext();
        counter++;
        if (index <= middle) {
            while (index > counter) {
                temp = temp.getNext();
                counter++;
            }
        }
        return temp.getElement();
    }

    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        if (index == 0) {
            return delete(first);
        }
        if (index == size - 1) {
            return delete(last);
        }

        int middle = size / 2;
        int counter = 0;
        Node<E> temp = first.getNext();
        counter++;
        if (index <= middle) {
            while (index > counter) {
                temp = temp.getNext();
                counter++;
            }
        } else {
            // TODO: поиск с конца списка
        }

        return delete(temp);
    }

    private E delete(Node<E> node) {
        Node<E> tmpNode = new Node<>(node.getElement(), node.getPrev(), node.getNext());
        if (node.getPrev() == null) {
            Node<E> tmpNext = node.getNext();
            tmpNext.setPrev(null);
            first = tmpNext;
        } else {
            node.getPrev().setNext(tmpNode.getNext());
        }

        if (node.getNext() == null) {
            Node<E> tmpPrev = node.getPrev();
            tmpPrev.setNext(null);
            last = tmpPrev;
        } else {
            node.getNext().setPrev(tmpNode.getPrev());
        }

        node.setElement(null);
        node.setNext(null);
        size--;
        return tmpNode.getElement();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        if (first == null) {
            return "[]";
        }
        String delimiter = ", ";
        StringBuilder sb = new StringBuilder().append("[").append(first.getElement()).append(delimiter);
        Node<E> nextElement = first.getNext();
        if (size > 2) {
            sb.append(nextElement.getElement()).append(delimiter);

            int tempSize = size - 3;
            while (tempSize > 0) {
                nextElement = nextElement.getNext();
                sb.append(nextElement.getElement()).append(delimiter);
                tempSize--;
            }
        }
        if (!first.equals(last)) {
            sb.append(last.getElement()).append("]");
        } else {
            sb.setLength(sb.length() - 2);
            sb.append("]");
        }
        return sb.toString();
    }
}
