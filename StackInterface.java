/**
 * This interface shows the methods that users can use
 */

public interface StackInterface<T> {

    /**
     * pushes a new entr to the top of the stack 
     * @param newEntry indicate the element what will be pushed to the top of the stack
     */
    public void push (T newEntry);

    /**
     * removes the top element in the stack
     * @return the element that pop
     */
    public T pop ();

    /**
     * Retrieves the stack’s top entry without changing the stack in any way.
     * @return the top element in the stack
     */
    public T peek ();

    /** 
     * checks if the stack is empty
     * @return true if the stack is empty and false if the stack is not empty
     */
    public boolean isEmpty();

    /** 
     * Removes all entries from the stack
     */
    public void clear ();
}