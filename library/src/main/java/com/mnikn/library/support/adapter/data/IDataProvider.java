package com.mnikn.library.support.adapter.data;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IDataProvider<Data,Container> {
    Data get(int position);
    Container getDataContainer();
    boolean isEmpty();
    void swap(Container container);
    int size();
}
