package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.TypedQuery;

public class UtilsDAO {

	@SuppressWarnings("rawtypes")
	public static void setParameters(TypedQuery query, Map<String, Object> parameters) {
		Iterator<Entry<String, Object>> it = parameters.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> pairs = (Map.Entry<String, Object>) it.next();
			query.setParameter(pairs.getKey(), pairs.getValue());
		}
    }
	
}
