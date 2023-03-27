//package com.qlsv5.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import java.io.Serializable;
//@Getter
//@Setter
//@MappedSuperclass
//public abstract class BaseEntity implements Serializable {
//
//
//    protected Long id;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//
//        if (!this.getClass().isInstance(o))
//            return false;
//
//        BaseEntity other = (BaseEntity) o;
//
//        return id != null && id.equals(other.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
//}
