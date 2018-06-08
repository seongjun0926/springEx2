package com.test.lotte.config;
import java.util.List;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * <pre>
 * com.test.lotte.config
 * MybatisConfiguration.java
 * </pre>
 * ----------------------
 * @Auth  : 손성준
 * @Since : 2018. 6. 7.
 * @Desc  : 마이바티스설정
 *
 */
@MapperScan(basePackages = {"com.test.lotte.service"}, annotationClass = Repository.class)
public class MybatisConfiguration extends MybatisAutoConfiguration {


//	private static final Logger logger = LoggerFactory.getLogger(MybatisConfiguration.class);

	@Autowired
    private MybatisProperties properties;

    public MybatisConfiguration(MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider,
			ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider,
			ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
		super(properties, interceptorsProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider);
		// TODO Auto-generated constructor stub
        this.properties = properties;
	}

}