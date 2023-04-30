package com.qlsv5.enumdef;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NienNQ
 * @created 2023 - 03 - 18 10:43 AM
 * @project qlsv5
 */

public enum XepLoaiEnum {
    XUAT_SAC("XUAT_SAC", "Xuất sắc"),
    GIOI("GIOI", "Giỏi"),
    KHA("KHA", "Xuất sắc"),
    TRUNG_BINH("TRUNG_BINH", "Trung bình"),
    YEU("YEU", "Yếu");

    private String value;

    private String name;

    XepLoaiEnum(String value, String name) {
        this.value = value;

        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByValue(String value) {
        for (XepLoaiEnum en : XepLoaiEnum.values()) {
            if (value == en.getValue()) {
                return en.getName();
            }
        }

//        return StringUtils.EMPTY;
        return "";
    }

    public static List<Select2Dto> getSelect2ComboList() {
        List<Select2Dto> list = new ArrayList<>();
        list.add(new Select2Dto("", "", ""));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.XUAT_SAC.getValue()), XepLoaiEnum.XUAT_SAC.getName(), XepLoaiEnum.XUAT_SAC.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.GIOI.getValue()), XepLoaiEnum.GIOI.getName(), XepLoaiEnum.GIOI.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.KHA.getValue()), XepLoaiEnum.KHA.getName(), XepLoaiEnum.KHA.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.TRUNG_BINH.getValue()), XepLoaiEnum.TRUNG_BINH.getName(), XepLoaiEnum.TRUNG_BINH.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.YEU.getValue()), XepLoaiEnum.YEU.getName(), XepLoaiEnum.YEU.getName()));
        return list;
    }

    public static List<Select2Dto> getSelect2ComboList1Layer() {
        List<Select2Dto> list = new ArrayList<>();
        list.add(new Select2Dto("", "", ""));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.XUAT_SAC.getValue()), XepLoaiEnum.XUAT_SAC.getName(), XepLoaiEnum.XUAT_SAC.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.GIOI.getValue()), XepLoaiEnum.GIOI.getName(), XepLoaiEnum.GIOI.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.KHA.getValue()), XepLoaiEnum.KHA.getName(), XepLoaiEnum.KHA.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.TRUNG_BINH.getValue()), XepLoaiEnum.TRUNG_BINH.getName(), XepLoaiEnum.TRUNG_BINH.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.YEU.getValue()), XepLoaiEnum.YEU.getName(), XepLoaiEnum.YEU.getName()));
        return list;
    }
}
