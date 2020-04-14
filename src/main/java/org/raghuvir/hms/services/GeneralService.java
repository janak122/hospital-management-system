package org.raghuvir.hms.services;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.springframework.web.multipart.MultipartFile;

public interface GeneralService {

    void insertUpdate(Object entity);

    void delete(Object entity);

    Object getEntity(Class clazz, Serializable key);

    List getList(Class clazz, int start, int size, Criterion...criterions);
    
	 String uploadFile(String dirpath,String userId, MultipartFile file);
}
