package uy.edu.ort.arqliv.obligatorio.persistencia.bo;

import uy.edu.ort.arqliv.obligatorio.dominio.Container;

public class ContainerBoImpl implements ContainerBo {

	ContainerDao containerDao;
	
	@Override
	public void save(Container c) {
		containerDao.save(c);
	}

	@Override
	public void update(Container c) {
		containerDao.update(c);
	}

	@Override
	public void delete(Container c) {
		containerDao.delete(c);
	}

	@Override
	public Container find(String code) {
		return containerDao.find(code);
	}

}
