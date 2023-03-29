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


    /* Khoa */
    public static final String E_KHOA_NOT_FOUND_KHOA = "Khoa does not exist!";
    public static final String E_KHOA_DUPLICATE_MA_KHOA = "Ma khoa exists, please input another one!";

    /* Lop */
    public static final String E_LOP_NOT_FOUND_LOP = "Lop does not exist!";
    public static final String E_LOP_DUPLICATE_MA_LOP = "Ma lop exists, please input another one!";

    /* Sinh Vien */
    public static final String E_SINHVIEN_NOT_FOUND_SINHVIEN = "Sinh vien does not exist!";
    public static final String E_SINHVIEN_DUPLICATE_MA_SINHVIEN = "Ma sinh vien exists, please input another one!";

    /* Giang Vien */
    public static final String E_GIANGVIEN_NOT_FOUND_GIANGVIEN = "Giang vien does not exist!";
    public static final String E_GIANGVIEN_DUPLICATE_MA_GIANGVIEN = "Ma giang vien exists, please input another one!";

    /* Mon Hoc */
    public static final String E_MONHOC_NOT_FOUND_MONHOC = "Mon hoc does not exist!";
    public static final String E_MONHOC_DUPLICATE_MA_MONHOC = "Ma mon hoc exists, please input another one!";

    /* Ds Lop Tc */
    public static final String E_DSLOPTC_NOT_FOUND_DSLOPTC = "Ds Lop Tc does not exist!";
    public static final String E_DSLOPTC_DUPLICATE_MA_DSLOPTC = "Ma lop Tc exists, please input another one!";

    /* Chi Tiet Lop Tc */
    public static final String E_CHITIETLOPTC_NOT_FOUND_DSLOPTC = "Chi Tiet Lop Tc does not exist!";
    public static final String E_CHITIETLOPTC_DUPLICATE_MA_LOPTC_TIET_THU = "(Ma lop Tc, Tiet, Thu) exists, please input another one!";

    /* Diem */
    public static final String E_DIEM_NOT_FOUND_DSLOPTC = "Diem does not exist!";
    public static final String E_DIEM_DUPLICATE_MASV_MALOPTC = "(Ma sinh vien, Ma lop tin chi) exists, please input another one!";
}
