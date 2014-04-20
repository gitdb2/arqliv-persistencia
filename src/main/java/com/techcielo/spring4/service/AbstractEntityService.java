package com.techcielo.spring4.service;

import java.io.Serializable;

public abstract class AbstractEntityService<T, PK extends Serializable> implements  IEntityService<T, PK>{

}
