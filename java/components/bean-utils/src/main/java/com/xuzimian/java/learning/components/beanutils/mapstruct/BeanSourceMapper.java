package com.xuzimian.java.learning.components.beanutils.mapstruct;

import  com.xuzimian.java.learning.components.beanutils.base.BeanModelSource;
import  com.xuzimian.java.learning.components.beanutils.base.BeanModelTarget;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BeanSourceMapper {
    BeanSourceMapper INSTANCE = Mappers.getMapper(BeanSourceMapper.class );

    /**
     * 可以使用@Mapping(source = "name", target = "username") 方式来进行不同的属性名映射
     * @param source
     * @return
     */
    BeanModelTarget sourceToTarget(BeanModelSource source);
}
