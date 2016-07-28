package com.adara.pixeldataengineui.model.backend.dto.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class GenericDTOList<T> extends BaseDTO {

    public Collection<T> list = new ArrayList<T>();

    public GenericDTOList() {
    }

    public GenericDTOList(Collection<T> items) {
        this.list.addAll(items);
    }

    public GenericDTOList(T... items) {
        this.list.addAll(Arrays.asList(items));
    }

    public Collection<T> getList() {
        return list;
    }

    public void setList(Collection<T> list) {
        this.list = list;
    }

    public void add(T t) {
        if (list != null) {
            list.add(t);
        }
    }

    public void addAll(Collection<T> list) {
        this.list.addAll(list);
    }

    public int size() {
        if (null != list) {
            return list.size();
        }
        return 0;
    }

    @Override
    public String toString() {
        return "List [list=" + list + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((list == null) ? 0 : list.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        GenericDTOList<?> other = (GenericDTOList<?>) obj;
        if (list == null) {
            if (other.list != null) {
                return false;
            }
        } else if (!list.equals(other.list)) {
            return false;
        }
        return true;
    }
}
