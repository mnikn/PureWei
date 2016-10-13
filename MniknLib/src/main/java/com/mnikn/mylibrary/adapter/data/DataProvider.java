package com.mnikn.mylibrary.adapter.data;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface DataProvider<T,D> {
    T get(int position);
    D getDataContainer();
    boolean isEmpty();
    void swap(D dataContainer);
    int size();
}
