package com.qlsv5.service;

import java.util.List;

public interface CommonService {
    public Object updateObject(Object object);
    public Object addObject(Object object);
    public List<String> deleteLstObject(List<String> lstId, Object object);

    public List<Object> findAllObject(Object object);

    public Object getObjectById(String id, Object object);
}
