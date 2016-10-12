package com.mnikn.mylibrary.adapter.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class ListDataProvider<T> implements WriteDataProvider<T, List<T>> {

    private List<T> mDataList = new ArrayList<>();

    @Override
    public void add(T data) {
        mDataList.add(data);
    }

    @Override
    public void add(T data, int position) {
        mDataList.add(position, data);
    }

    @Override
    public void addAll(List<T> dataList) {
        mDataList.addAll(dataList);
    }

    @Override
    public void update(T data, int position) {
        mDataList.set(position, data);
    }

    @Override
    public void remove(T data) {
        mDataList.remove(data);
    }

    @Override
    public void remove(int position) {
        mDataList.remove(position);
    }

    @Override
    public void clear() {
        mDataList.clear();
    }

    @Override
    public T getData(int position) {
        return mDataList.get(position);
    }

    @Override
    public List<T> getDataContainer() {
        return mDataList;
    }

    @Override
    public void swap(List<T> dataContainer) {
        mDataList = dataContainer;
    }

    @Override
    public boolean isEmpty() {
        return mDataList.isEmpty();
    }


}
