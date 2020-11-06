package axiom.app.rest.controller;

import axiom.app.rest.exception.ApiException;
import axiom.app.service.exception.ServiceException;
import axiom.app.service.impl.HandsetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("mobile/")
public class HandsetController {
    Logger logger = LoggerFactory.getLogger(HandsetController.class);

    @Autowired
    private HandsetService handsetService;

    /**
     * Search mobile handset database service
     * Allows the caller to retrieve one or more mobile handset record based on the passed search criteria.
     * The criteria can be any field in the handset data along with any value. Examples:
     * • /mobile/search?priceEur=200. Will return 10 devices.
     * • /mobile/search?sim=eSim. Will return 18 devices.
     * • /mobile/search?announceDate=1999&price=200. Will return 2 devices.
     * @param req
     * @return ResponseEntity<List<Object>> list of devices
     */
    @GetMapping("/search")
    public ResponseEntity<List<Object>> search(HttpServletRequest req) {
        logger.debug("Entering method search");
        try {
            Map<String , Object> filterValues = new HashMap<String , Object>();
            Enumeration<String> parameterNames = req.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String parameterName = parameterNames.nextElement();
                String parameterValue = req.getParameter(parameterName);
                filterValues.put(parameterName,parameterValue);
            }
            List<Object> response = handsetService.getAllHandset(filterValues);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            throw new ApiException(e.getMessage(), e);
        } finally {
            logger.debug("Exiting method search");
        }
    }
}
