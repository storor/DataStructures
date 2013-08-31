package ua.redric.training.mylists;

/**
 *
 * @author Serhii_Tolok
 */
public interface MyQueue<T> {

    void offer(T e);

    T peek();

    T poll();
}
