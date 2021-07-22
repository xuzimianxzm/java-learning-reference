package com.xuzimian.java.learning.components.beanutils.selma;

import  com.xuzimian.java.learning.components.beanutils.base.BeanModelSource;
import  com.xuzimian.java.learning.components.beanutils.base.BeanModelTarget;
import fr.xebia.extras.selma.Mapper;

@Mapper
public interface SelmaMapper {

    BeanModelTarget asTarget(BeanModelSource in);

    BeanModelTarget copy(BeanModelSource in, BeanModelTarget out);

}