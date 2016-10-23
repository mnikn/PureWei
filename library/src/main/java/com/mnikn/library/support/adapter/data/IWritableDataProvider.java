package com.mnikn.library.support.adapter.data;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IWritableDataProvider<Data, Container> extends IDataProvider<Data, Container> {

    void add(Data data);
    void add(Data data,int position);
    void update(Data data,int position);
    void remove(Data data);
    void remove(int position);
    void clear();
}
