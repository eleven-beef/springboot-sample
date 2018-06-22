package com.example.demo.config;

import javax.sql.DataSource;

import org.seasar.doma.boot.autoconfigure.DomaConfigBuilder;
import org.seasar.doma.jdbc.ClassHelper;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.Naming;
import org.seasar.doma.jdbc.UnknownColumnHandler;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.PostgresDialect;
import org.seasar.doma.jdbc.entity.EntityType;
import org.seasar.doma.jdbc.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomaConfig implements Config {

    @Autowired
    private DataSource dataSource;

    @Autowired
    Naming naming;

	private ClassHelper classHelper = new MyClassHelper();

	private static class MyClassHelper implements ClassHelper {
		@SuppressWarnings("unchecked")
		@Override
		public <T> Class<T> forName(String className) throws Exception {
			return (Class<T>)this.getClass().getClassLoader().loadClass(className);
		}
	}

	@Bean
	public DomaConfigBuilder domaConfigBuilder() {
		return new DomaConfigBuilder() {
			public ClassHelper classHelper() {
				return classHelper;
			}
		};
	}

    @Override
    public Dialect getDialect() {
        return new PostgresDialect();
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    // select結果とエンティティDtoの項目一致チェックを外す
    @Override
    public UnknownColumnHandler getUnknownColumnHandler() {
        return new UnknownColumnHandler() {
            @Override
            public void handle(Query query, EntityType<?> entityType, String unknownColumnName) {
            }
        };
    }

    @Override
    public Naming getNaming() {
        return naming;
    }
}
