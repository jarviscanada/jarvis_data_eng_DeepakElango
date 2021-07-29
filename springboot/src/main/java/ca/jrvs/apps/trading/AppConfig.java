package ca.jrvs.apps.trading;

import org.slf4j.*;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class AppConfig {

    private Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public MarketDataConfig marketDataConfig(){
        MarketDataConfig dataConfig = new MarketDataConfig();
        dataConfig.setHost("https://cloud.iexapis.com/v1/");
        dataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));
        return dataConfig;
    }

    @Bean
    public DataSource dataSource() {
        String jdbcUrl;

        jdbcUrl = "jdbc:postgresql://" + System.getenv("PSQL_HOST")
                + ":" + System.getenv("PSQL_PORT") + "/" + System.getenv("PSQL_DB");

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(jdbcUrl);
        basicDataSource.setUsername(System.getenv("PSQL_USER"));
        basicDataSource.setPassword(System.getenv("PSQL_PASSWORD"));
        return basicDataSource;
    }

    @Bean
    public HttpClientConnectionManager httpClientConnectionManager(){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(50);
        connectionManager.setDefaultMaxPerRoute(50);
        return connectionManager;
    }

}
