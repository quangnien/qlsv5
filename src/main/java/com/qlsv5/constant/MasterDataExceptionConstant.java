package com.qlsv5.constant;

/**
 * @author NienNQ
 * @created 2023 - 03 - 05 1:12 PM
 * @project qlsv
 */
public class MasterDataExceptionConstant {

    /* Common */
    public static final String COMMON_EMAIL_WRONG_FORMAT = "You entered the wrong format for email@gmail.com!";
    public static final String COMMON_EMAIL_IS_EXIST = "email is already in use!";
    public static final String E_COMMON_NOT_CONFIRM_PASSWORD = "ConfirmPassword mustn't null!";
    public static final String E_COMMON_NOT_PASSWORD_OLD= "Old password mustn't null!";
    public static final String E_COMMON_NOT_PASSWORD_NEW = "New password mustn't null!";
    public static final String E_COMMON_NOT_MATCH_PASSWORD = "Incorrect password!";
    public static final String E_COMMON_NOT_EQUAL_CONFIRM_PASSWORD = "Password is'nt equal with ConfirmPassword!";

    /* Khoa */
    public static final String E_KHOA_NOT_FOUND_KHOA = "Khoa does not exist!";
    public static final String E_KHOA_DUPLICATE_MA_KHOA = "Ma khoa exists, please input another one!";
    public static final String E_KHOA_IS_USED = "Khoa is used!";


    /* Lop */
    public static final String E_LOP_NOT_FOUND_LOP = "Lop does not exist!";
    public static final String E_LOP_DUPLICATE_MA_LOP = "Ma lop exists, please input another one!";
    public static final String E_LOP_IS_USED = "Lop is used!";

    /* Sinh Vien */
    public static final String E_SINHVIEN_NOT_FOUND_SINHVIEN = "Sinh vien does not exist!";
    public static final String E_SINHVIEN_DUPLICATE_MA_SINHVIEN = "Ma sinh vien exists, please input another one!";
    public static final String E_SINHVIEN_IS_USED = "Sinh Vien is used!";

    /* Giang Vien */
    public static final String E_GIANGVIEN_NOT_FOUND_GIANGVIEN = "Giang vien does not exist!";
    public static final String E_GIANGVIEN_DUPLICATE_MA_GIANGVIEN = "Ma giang vien exists, please input another one!";
    public static final String E_GIANGVIEN_IS_USED = "Giang Vien is used!";

    /* Sinh Vien */
    public static final String E_ADMIN_NOT_FOUND_ADMIN = "Admin does not exist!";

    /* Mon Hoc */
    public static final String E_MONHOC_NOT_FOUND_MONHOC = "Mon hoc does not exist!";
    public static final String E_MONHOC_DUPLICATE_MA_MONHOC = "Ma mon hoc exists, please input another one!";
    public static final String E_MONHOC_IS_USED = "Mon Hoc is used!";

    /* Ds Lop Tc */
    public static final String E_DSLOPTC_NOT_FOUND_DSLOPTC = "Ds Lop Tc does not exist!";
    public static final String E_DSLOPTC_DUPLICATE_MA_DSLOPTC = "Ma lop Tc exists, please input another one!";
    public static final String E_DSLOPTC_FULL_SLOT = "Lop TC is full, please choose another one!";
    public static final String E_DSLOPTC_IS_USED = "Lop TC is used!";

    /* Chi Tiet Lop Tc */
    public static final String E_CHITIETLOPTC_NOT_FOUND_DSLOPTC = "Chi Tiet Lop Tc does not exist!";
    public static final String E_CHITIETLOPTC_DUPLICATE_MA_LOPTC_TIET_THU = "(Ma lop Tc, Tiet, Thu) exists, please input another one!";
    public static final String E_CHITIETLOPTC_IS_USED = "Chi Tiet Lop TC is used!";

    /* Diem */
    public static final String E_DIEM_NOT_FOUND_DSLOPTC = "Diem does not exist!";
    public static final String E_DIEM_DUPLICATE_MASV_MALOPTC = "(Ma sinh vien, Ma lop tin chi) exists, please input another one!";
    public static final String E_DIEM_IS_USED = "Diem is used!";

    /* TKB */
    public static final String E_TKB_NOT_FOUND_DATE_BEGIN = "Date begin is null!";
    public static final String E_TKB_NOT_FOUND_DATE_END = "Date end is null!";

}
