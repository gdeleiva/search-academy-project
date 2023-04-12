package co.empathy.academy.search.imdb.config;


import co.empathy.academy.search.imdb.service.ElasticRequest;
import co.empathy.academy.search.imdb.service.ElasticRequestImpl;
import co.empathy.academy.search.imdb.service.ElasticService;
import co.empathy.academy.search.imdb.service.ElasticServiceImpl;
import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.StorageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
@Configuration
@EnableAsync
public class Config {
    /**
     * Method that creates and returns a new ElasticRequestImpl.
     * @return a new ElasticRequestImpl.
     */
    @Bean
    public ElasticRequest elasticRequest() {
        return new ElasticRequestImpl();
    }

    /**
     * Method that creates and returns a new ElasticServiceImpl with an elasticRequest as parameter.
     * @param elasticRequest elastic request needed for the ElasticService.
     * @return new ElasticServiceImpl.
     */
    @Bean
    public ElasticService elasticService(ElasticRequest elasticRequest) {
        return new ElasticServiceImpl(elasticRequest);
    }

    /**
     * Method that we use to initialize jobrunnr.
     * @param jobMapper jsonMapper we put inside the jobrunnr app.
     * @return the memory storage where jobrunnr is allocated.
     */
    @Bean
    public StorageProvider storageProvider(JobMapper jobMapper) {
        InMemoryStorageProvider storageProvider = new InMemoryStorageProvider();
        storageProvider.setJobMapper(jobMapper);
        return storageProvider;
    }

}
