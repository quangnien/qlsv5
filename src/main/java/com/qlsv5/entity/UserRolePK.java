//package com.qlsv5.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.Objects;
//
///**
// * @author NienNQ
// * @created 2023 - 03 - 26 9:10 AM
// * @project qlsv5
// */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class UserRolePK {
//
//    private String userId;
//
//    private String roleId;
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see java.lang.Object#hashCode()
//     */
//    @Override
//    public int hashCode() {
//        return Objects.hash(roleId, userId);
//    }
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see java.lang.Object#equals(java.lang.Object)
//     */
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        UserRolePK other = (UserRolePK) obj;
//        return Objects.equals(roleId, other.roleId) && Objects.equals(userId, other.userId);
//    }
//}
