package com.mnikn.mylibrary.adapter.data;

import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface WriteDataProvider<T,D> extends DataProvider<T,D>{

    void add(T data);
    void add(T data,int position);
    void addAll(List<T> dataList);
    void update(T data,int position);
    void remove(T data);
    void remove(int position);
    void clear();
}
