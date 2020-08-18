package com.yanghui.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * JACKSON工具类
 */
public class SerializeUtil {

	private static ObjectMapper om;

	private static Logger logger = LoggerFactory.getLogger(SerializeUtil.class);

    public static ObjectMapper getObjectMapper() {
        if (om == null) {
            synchronized (SerializeUtil.class) {
                if (om == null) {
                    om = new CustomObjectMapper();
                }
            }
        }
        return om;
    }


    /**
     * 对象转json
     */
	public static String encodeJson(Object obj) {
		String result=null;
		try {
			result=getObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			logger.error(String.format("对象序列化失败,对象%s,失败原因 %s"),obj,e.getMessage());
		}
		return result;
	}


    /**
     * json转对象
     */
    public static <T> T decodeJson(String json, Class<T> cls) {
        Object result = null;
        try {
            result = getObjectMapper().readValue(json, cls);
        } catch (Exception e) {
            logger.error(String.format("反序列化失败,序列信息 %s,失败原因",json,e.getMessage()));
        }
        return (T) result;
    }



	/**
	 * 暂不支持复杂嵌套  JSON转list
	 */
	public static <T> List<T> decodeJsonList(String json, final Class<T> cls) {
	    List<Object> result=new ArrayList<>();
		try {
			result= getObjectMapper().readValue(json, new TypeReference<List<T>>() {
				public Type getType() {
					ParameterizedType type = new ParameterizedType() {
						@Override
						public Type getRawType() {
							return List.class;
						}

						@Override
						public Type getOwnerType() {
							return null;
						}

						@Override
						public Type[] getActualTypeArguments() {
							return new Type[] {cls};
						}
					};
					return type;
				}
			});
		} catch (Throwable e) {
        logger.error(String.format("反序列化LIST失败，数据 %s，目标对象 %s,失败原因 %s",json,cls,e.getMessage()));
		}
		return (List<T>) result;
	}

}
