import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * the implementation of the StackInterface using an Array-based data structure
 */
public final class ArrayStack<T> implements StackInterface<T> {
    private T[] stack;
    private int topIndex;
    private boolean integrityOk;
    private static final int DEFAULT_CAPACITY = 100;
    private static final int MAX_CAPACITY = 10000;

    /**
     * default constructor of ArrayStack
     */
    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * a constructor of ArrayStack that makes the array for the elements in the stack
     * @param initialCapacity T[] will now have the size of DEFUALT_CAPACITY
     */
    public ArrayStack(int initialCapacity) {
        integrityOk = false;
        checkCapacity(initialCapacity);
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[]) new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
        integrityOk = true;
    }

    /**
     * adds a new entry to the top of the stack 
     * also checks the integrity and capacity of the array
     * @param the new element that will be added to the top of the stack
     */
    @Override
    public void push(T newEntry) {

        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    }

    /**
     * removes the top element in the stack 
     * also checks the integrity of the stack and throws an 
     * exception if the stack is empty 
     * @return the element that was removed
     */
    @Override
    public T pop() {
        checkIntegrity();
        if(isEmpty()) {
            throw new EmptyStackException();
        } else {
            T top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return top;
        }
    }

    /**
     * Retrieves the stack’s top entry without changing the stack in any way.
     * also checks the integrity of the stack and throws an exception if the 
     * stack is empty 
     * @return the top element in the stack
     */
    @Override
    public T peek() {
        checkIntegrity();
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return stack[topIndex];
        }
    }

    /**
     * checks if the stack is empty or not 
     * @return true if the stack is empty and false if the stack is not empty 
     */
    @Override
    public boolean isEmpty() {
        return topIndex < 0;
    }

    /**
     * removes all the elements in the stack 
     */
    @Override
    public void clear() {
        while(topIndex > -1) {
            stack[topIndex] = null;
            topIndex--;
        }
        topIndex = -1;
    }

    /**
     * checks if the current capacity of the array is smaller then the max capacity 
     * @param capacity takes in the current capacity of the array
     */
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new IllegalStateException("Attempt to create a bag whose "
                    + "capacity exeeds allowed "
                    + "maximum of " + MAX_CAPACITY);
        }
    }

    /**
     * checks the integrity of the program 
     * if the integrity is not ok then it will throw a SecurityException
     */
    private void checkIntegrity() {
        if (!integrityOk) {
            throw new SecurityException("ArrayBag object is corrupt.");
        }
    }

    /**
     * doubles the size of the array if the capacity is not greater than MAX_CAPACITY
     */
    private void ensureCapacity() {
        if(topIndex == stack.length - 1) {
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        }
    }
}