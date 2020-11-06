package axiom.app.service.predicate;

import axiom.app.service.utils.ServiceUtils;
import com.jayway.jsonpath.Predicate;
import org.springframework.util.StringUtils;

import java.util.*;

public class HandsetPredicate implements Predicate {
    private final Map<String , Object> filterValues;

    private static final List<String> filterPaths =
            Arrays.asList("id", "brand", "phone", "picture", "release.announceDate","release.priceEur","sim"
            ,"resolution","hardware.audioJack","hardware.gps","hardware.battery");



    public HandsetPredicate(Map<String, Object> filterValues) {
        super();
        this.filterValues = filterValues;
    }
    @Override
    public boolean apply(PredicateContext predicateContext) {
        boolean match = true;
        for (Map.Entry<String, Object> entry : filterValues.entrySet()) {
            String path = filterPaths.stream().filter(f -> f.toLowerCase().contains(entry.getKey().toLowerCase()))
                    .findAny().orElse(null);
            if(StringUtils.isEmpty(path))
                throw new RuntimeException("Invalid parameter - "+entry.getKey());
            Map<String, Object> mobileMap = predicateContext.item(Map.class);
            Object valueFromMap = ServiceUtils.readMapValue(path,mobileMap);
            if(valueFromMap != null && !StringUtils.isEmpty(entry.getValue())){
                if(valueFromMap instanceof Integer){
                    match = match && (valueFromMap != null) && ((Integer)valueFromMap).equals(Integer.valueOf(entry.getValue().toString()));
                } else {
                    match = match && (valueFromMap != null) && valueFromMap.toString().toLowerCase().contains(entry.getValue().toString().toLowerCase());
                }
            }

        }
        return match;
    }



}
