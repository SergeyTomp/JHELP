package jhelp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableJpaRepositories(basePackages = "jhelp.repos")
@PropertySource("classpath:app.properties")
public class DbConfig {

    @Value("${data.source.url}")
    private String dataSourceUrl;

    @Bean
    public DataSource dataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername("sergey");
//        dataSource.setPassword("Gfhjkm789");
//        dataSource.getConnection().createStatement().execute("CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE (null,'DEFINITION','c:\\Apache\\db-derby-10.14.2.0-bin\\bin\\JHDB\\definition.csv',null,null,null,1)");
//        dataSource.getConnection().createStatement().execute("CALL SYSCS_UTIL.SYSCS_IMPORT_TABLE (null,'TERM','c:\\Apache\\db-derby-10.14.2.0-bin\\bin\\JHDB\\term.csv',null,null,null,1)");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws SQLException {


        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.DERBY);
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.DerbyTenSevenDialect");

        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("jhelp.orm");
        factory.setDataSource(dataSource());
        factory.setJpaDialect(jpaDialect());
        return factory;

    }

    @Bean
    public JpaDialect jpaDialect() {
        return new HibernateJpaDialect();
    }


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;

    }
}
