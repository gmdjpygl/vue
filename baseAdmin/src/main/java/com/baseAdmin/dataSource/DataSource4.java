package com.baseAdmin.dataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import com.alibaba.druid.pool.DruidDataSource;
import java.sql.SQLException;
import javax.sql.DataSource;

@Configuration//注解到spring容器中
@MapperScan(basePackages = "com.dataManage.mapper.data4",sqlSessionFactoryRef = "data4SqlSessionFactory")
public class DataSource4 {
    @Bean(name="config4")
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration globalConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }
    /**
     * 返回data4数据库的数据源
     * @return
     */
    @Bean(name="data4Source")
    @ConfigurationProperties(prefix = "spring.datasource.druid.data4")
    public DataSource dataSource(){
    	  DruidDataSource druidDataSource = new DruidDataSource();
          try {
			druidDataSource.setFilters("stat,wall,slf4j");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return  druidDataSource;
    }

    /**
     * 返回data4数据库的会话工厂
     * @param ds
     * @return
     * @throws Exception
     */
    @Bean(name = "data4SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("data4Source") DataSource ds,@Qualifier("config4") org.apache.ibatis.session.Configuration config) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(ds);
        bean.setConfiguration(config);
        return bean.getObject();
    }

    /**
     * 返回data4数据库的会话模板
     * @param sessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "data4SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("data4SqlSessionFactory") SqlSessionFactory sessionFactory) throws  Exception{
        return  new SqlSessionTemplate(sessionFactory);
    }

    /**
     * 返回data4数据库的事务
     * @param ds
     * @return
     */
    @Bean(name = "data4TransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("data4Source") DataSource ds){
        return new DataSourceTransactionManager(ds);
    }
}
