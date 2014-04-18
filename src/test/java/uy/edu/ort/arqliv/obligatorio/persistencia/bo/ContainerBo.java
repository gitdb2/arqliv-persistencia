package uy.edu.ort.arqliv.obligatorio.persistencia.bo;

import uy.edu.ort.arqliv.obligatorio.dominio.Container;

public interface ContainerBo {

	void save (Container c);
	
	void update (Container c);
	
	void delete (Container c);
	
	Container find (String code);
	
}
