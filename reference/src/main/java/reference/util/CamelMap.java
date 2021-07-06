package reference.util;

import org.apache.ibatis.type.Alias;
import org.springframework.jdbc.support.JdbcUtils;

import java.util.HashMap;

/**
 * Created by Jeongwon.
 * FileName : CamelMap
 * Date: 2019-10-22
 * Time: 오후 1:59
 */
@Alias("camelmap")
public class CamelMap extends HashMap{

    private static final long serialVersionUID = -770079043928325865L;

    @Override
    public Object put(Object key, Object value) {
        return super.put(JdbcUtils.convertUnderscoreNameToPropertyName((String) key), value);
    }
}
