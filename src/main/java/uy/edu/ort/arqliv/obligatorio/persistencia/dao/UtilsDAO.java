package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.TypedQuery;

public class UtilsDAO {

	@SuppressWarnings("rawtypes")
	public static void setParameters(TypedQuery query, Map<String, String> parameters) {
		Iterator<Entry<String, String>> it = parameters.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
			query.setParameter(pairs.getKey(), pairs.getValue());
		}
    }
	
}
