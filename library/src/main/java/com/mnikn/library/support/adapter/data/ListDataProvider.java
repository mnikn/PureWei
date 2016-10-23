package com.mnikn.library.support.adapter.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class ListDataProvider<Data> implements IWritableDataProvider<Data,List<Data>> {

    private List<Data> mDataList = new ArrayList<>();

    @Override
    public void add(Data data) {
        mDataList.add(data);
    }

    @Override
    public void add(Data data, int position) {
        mDataList.add(position, data);
    }

    @Override
    public void update(Data data, int position) {
        mDataList.set(position, data);
    }

    @Override
    public void remove(Data data) {
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
    public Data get(int position) {
        return mDataList.get(position);
    }

    @Override
    public List<Data> getDataContainer() {
        return mDataList;
    }

    @Override
    public void swap(List<Data> dataContainer) {
        mDataList = dataContainer;
    }

    @Override
    public int size() {
        return mDataList.size();
    }

    @Override
    public boolean isEmpty() {
        return mDataList.isEmpty();
    }


}
