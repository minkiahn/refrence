package reference.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


import reference.properties.DatabaseProperties;
import reference.util.CamelMap;



@Configuration
@MapperScan(basePackages= {"kr.co.sptek.dcim_core_policy.service.mapper"})
@EnableTransactionManagement
public class DatabaseConfig {
	
	@Autowired
	private DatabaseProperties prop;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean 
	public HikariConfig hikariConfig() {
		final HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(prop.getDriverClassName());
		hikariConfig.setJdbcUrl(prop.getUrl());
		hikariConfig.setUsername(prop.getUserName());
		hikariConfig.setPassword(prop.getPassword());
		return hikariConfig;
	}
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() throws Exception {
		final DataSource dataSource = new HikariDataSource(hikariConfig());
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception{
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(datasource);
		sessionFactory.setTypeAliasesPackage("kr.co.sptek.dcim_core_policy.repository");
		sessionFactory.setTypeAliases(CamelMap.class); // hashmap을 camel case로 변환
		sessionFactory.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
		return sessionFactory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory)throws Exception{
		final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
		return sqlSessionTemplate;
	}
	
}
