package ru.skl.lesson2;

public class MyLinkedList<E> implements MyList<E> {
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

    @Override
    public void add(E element) {
        addLast(element);
    }

    @Override
    public void add(int index, E element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        if (index == 0) {
            addFirst(element);
            return;
        }
        if (index == size - 1) {
            addLast(element);
            return;
        }

        Node<E> currentNode = getNode(index);
        Node<E> prevNode = currentNode.getPrev();

        Node<E> newNode = new Node<>(element, prevNode, currentNode);
        prevNode.setNext(newNode);
        currentNode.setPrev(newNode);

        size++;
    }

    @Override
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

        return getNode(index).getElement();
    }

    @Override
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

        return delete(getNode(index));
    }

    private Node<E> getNode(int index) {
        int middle = size / 2;
        int counter = 0;
        Node<E> temp;
        if (index <= middle) {
            temp = first.getNext();
            counter++;
            while (index > counter) {
                temp = temp.getNext();
                counter++;
            }
        } else {
            counter = size - 1;
            // 0 1 2 3 4 5 6
            temp = last.getPrev();
            counter--;
            while (index < counter) {
                temp = temp.getPrev();
                counter--;
            }
        }
        return temp;
    }

    private E delete(Node<E> node) {
        Node<E> tmpNode = new Node<>(node.getElement(), node.getPrev(), node.getNext());
        if (size > 1) {
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
        } else {
            first = null;
            last = null;
        }

        node.setElement(null);
        node.setNext(null);
        node.setPrev(null);
        size--;
        return tmpNode.getElement();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        Node<E> currentNode = first;
        while (currentNode.getNext() != null) {
            Node<E> nextNode = currentNode.getNext();
            currentNode.setPrev(null);
            currentNode.setNext(null);
            currentNode.setElement(null);
            currentNode = nextNode;
        }
        size = 0;
        first = last = null;
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
