package com.baseAdmin.dataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import com.alibaba.druid.pool.DruidDataSource;
import java.sql.SQLException;
import javax.sql.DataSource;

@Configuration//注解到spring容器中
@MapperScan(basePackages = "com.dataManage.mapper.data3",sqlSessionFactoryRef = "data3SqlSessionFactory")
public class DataSource3 {
    @Bean(name="config3")
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration globalConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }
    /**
     * 返回data3数据库的数据源
     * @return
     */
    @Bean(name="data3Source")
    @ConfigurationProperties(prefix = "spring.datasource.druid.data3")
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
     * 返回data3数据库的会话工厂
     * @param ds
     * @return
     * @throws Exception
     */
    @Bean(name = "data3SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("data3Source") DataSource ds,@Qualifier("config3") org.apache.ibatis.session.Configuration config) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(ds);
        bean.setConfiguration(config);
        return bean.getObject();
    }

    /**
     * 返回data3数据库的会话模板
     * @param sessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "data3SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("data3SqlSessionFactory") SqlSessionFactory sessionFactory) throws  Exception{
        return  new SqlSessionTemplate(sessionFactory);
    }

    /**
     * 返回data3数据库的事务
     * @param ds
     * @return
     */
    @Bean(name = "data3TransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("data3Source") DataSource ds){
        return new DataSourceTransactionManager(ds);
    }
}
