package org.raghuvir.hms.daos;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;

public interface GeneralDAO {

    void insertUpdate(Object entity);

    void delete(Object entity);

    Object getEntity(Class clazz, Serializable key);

    List getList(Class clazz, int start, int size, Criterion...criterions);
}
