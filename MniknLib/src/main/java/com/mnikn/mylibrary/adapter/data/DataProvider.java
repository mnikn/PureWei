package com.mnikn.mylibrary.adapter.data;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface DataProvider<T,D> {
    T getData(int position);
    D getDataContainer();
    boolean isEmpty();
    void swap(D dataContainer);
}
