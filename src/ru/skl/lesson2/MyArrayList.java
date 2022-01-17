package ru.skl.lesson2;

import java.util.Arrays;

public class MyArrayList<E> implements MyList<E> {
    private int size;
    private E[] array;
    private final static Object[] EMPTY_ARRAY = {};
    private final static int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        this.array = (E[]) EMPTY_ARRAY;
    }

    @Override
    public void add(E element) {
        if (size == array.length) {
            array = increaseArray();
        }
        array[size] = element;
        size++;
    }

    private E[] increaseArray() {
        if (array.length == 0) {
            return array = Arrays.copyOf(array, DEFAULT_CAPACITY);
        }
        return array = Arrays.copyOf(array, array.length * 2);
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        if (index == size - 1) {
            add(element);
            return;
        }
// Можно так
/*
        if (array.length == size) {
            array = increaseArray();
        }

        for (int i = size; i > index; i--) {
            array[i] = array[i-1];
        }
        array[index] = element;

 */
        // Но оптимальнее так
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    @Override
    public E get(int index) {
        return array[index];
    }

    @Override
    public E remove(int index) {
        E current = array[index];
        if (index == size - 1) {
            array[index] = null;
        } else {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        size--;
        return current;
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
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder().append("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]).append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}
