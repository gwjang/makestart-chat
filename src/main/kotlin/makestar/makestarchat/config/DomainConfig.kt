package makestar.makestarchat.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EntityScan("makestar.makestarchat.domain")
@EnableJpaRepositories("makestar.makestarchat.infra")
@EnableTransactionManagement
class DomainConfig 
