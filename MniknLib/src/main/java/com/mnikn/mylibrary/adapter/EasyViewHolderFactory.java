package com.mnikn.mylibrary.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 *         A factory uses to create ViewHolder
 */
public class EasyViewHolderFactory {

    private Context mContext;
    private List<Class<? extends EasyViewHolder>> holderClassValues = new ArrayList<>();

    public EasyViewHolderFactory(Context context) {
        mContext = context;
    }

    @SuppressWarnings("unchecked")
    public <T extends EasyViewHolder> T create(int viewType, View itemView) {
        Class valueClass = holderClassValues.get(viewType);
        try {
            Constructor<T> constructor = valueClass.getDeclaredConstructor(View.class);
            return constructor.newInstance(itemView);
        } catch (Throwable e) {
            throw new RuntimeException(
                    "Unable to create ViewHolder for" + valueClass + ". " + e.getCause().getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends EasyViewHolder> T create(int viewType, Context context,
                                               ViewGroup parent, int layoutId) {
        Class valueClass = holderClassValues.get(viewType);
        try {
            Constructor<T> constructor = valueClass.getDeclaredConstructor(Context.class,
                    ViewGroup.class, Integer.class);
            return constructor.newInstance(context, parent, layoutId);
        } catch (Throwable e) {
            throw new RuntimeException(
                    "Unable to create ViewHolder for" + valueClass + ". " + e.getCause().getMessage(), e);
        }
    }

    public void bind(Class<? extends EasyViewHolder> holderClassValue) {
        holderClassValues.add(holderClassValue);
    }

    public void bind(int itemViewType, Class<? extends EasyViewHolder> holderClassValue) {
        holderClassValues.add(itemViewType, holderClassValue);
    }

    public <T extends EasyViewHolder> int itemViewType(T holder) {
        return holderClassValues.indexOf(holder.getClass());
    }
}
