package axiom.app.service.impl;

import axiom.app.service.exception.ServiceException;
import axiom.app.service.predicate.HandsetPredicate;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HandsetService {
    Logger logger = LoggerFactory.getLogger(HandsetService.class);

    @Autowired
    private CacheService cacheService;



    /**
     * Used to retrieve mobile handset json string from cache manager "handsets" and filter it by filter values
     * @param filterValues
     * @return List<Object> list of devices
     * @throws ServiceException
     */
    public List<Object> getAllHandset(Map<String , Object> filterValues) throws ServiceException {
        logger.debug("Entering method getAllHandset " + filterValues);
        try {
            String response = cacheService.loadHandsetsData();
            Predicate expensivePredicate = new HandsetPredicate(filterValues);
            List<Object> handsetList = JsonPath.parse(response)
                    .read("$[?]", expensivePredicate);
            logger.debug("Filtered list " + handsetList);
            return handsetList;
        } catch (Exception e){
            throw new ServiceException(e.getMessage(),e);
        } finally {
            logger.debug("Exiting method getAllHandset");
        }


    }

}
