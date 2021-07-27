package br.com.builders.client.api.modules.builders;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "br.com.builders.client.api.modules.builders.repository")
@EntityScan(basePackages = "br.com.builders.client.api.modules.builders.entity")
public class BuildersModule {

}
