package axiom.app.service.impl;


import axiom.app.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class CacheService {
    Logger logger = LoggerFactory.getLogger(CacheService.class);
    @Autowired
    private RestTemplate restTemplate;

    @Value( "${endpoint_url}" )
    private String ENDPOINT_URL;

    /**
     * Used to consume mobile handset database API and cache the result in cache manager "handsets"
     * @return String cashed json string of mobile handset database
     * @throws ServiceException
     */
    @Cacheable("handsets")
    public String loadHandsetsData() throws ServiceException {
        logger.debug("Entering method loadHandsetsData");
        try {
            String response = restTemplate.getForObject(ENDPOINT_URL + "/list", String.class);
            logger.debug("cached handset data " + response);
            return response;
        } catch (HttpClientErrorException.NotFound e){
            throw new ServiceException("Handset endpoint not reachable",e);
        } catch (Exception e){
            throw new ServiceException(e.getMessage(),e);
        } finally {
            logger.debug("Exiting method loadHandsetsData");
        }

    }
}
