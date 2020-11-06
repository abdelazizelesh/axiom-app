package axiom.app.service.utils;

import org.springframework.util.StringUtils;

import java.util.Map;

public class ServiceUtils {
    public static Object readMapValue(String path, Map<String, Object> map){
        if(StringUtils.isEmpty(path) || map == null)
            return null;
        String[] keys = path.split("\\.");
        Object value = map.get(keys[0]);
        for (int i = 1; i < keys.length; i++) {
            if (i == keys.length - 1) {
                return ((Map<String, Object>) value).get(keys[i]);
            } else {
                value = ((Map<String, Object>) value).get(keys[i]);
            }
        }
        return value;
    }
}
