package cn.liaozhi.core.util;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.type.TypeReference;

/**
 * @Author liaozhi
 * @Date: 2020/4/25 23:02
 * @Version 1.0
 */
public final class JsonUtil {
    private static ObjectMapper mapper = null;

    static {
        mapper = new ObjectMapper();
        //json转对象时，json里有对象不存在的属性时，会把对象里有的属性复制过去，没有的忽略，不报错
        mapper.getDeserializationConfig().set(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 私有构造函数
     *
     */
    private JsonUtil(){}

    /**
     * 将一个POJO对象转换成JSON字符串表示。
     *
     * @author cKF49709
     * @since 2011-11-4
     * @param name		节点的名称
     * @param object	要写入的对象
     * @return	对象的JSON字符串表示
     * @throws IOException
     */
    public static String getJsonString(String name, Object object) throws IOException {
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.putPOJO(name, object);
        return mapper.writeValueAsString(rootNode);
    }

    /**
     * 将一个POJO对象转换成JSON字符串表示。
     *
     * @param object	要写入的对象
     * @return	对象的JSON字符串表示
     * @throws IOException
     */
    public static String getJsonString(Object object) throws IOException {
        return mapper.writeValueAsString(object);
    }

    /**
     * 将JSON写入流中
     * @author l54883
     * @since 2012-11-20
     * @param object
     * @return
     * @throws IOException
     */
    public static void writeJsonToStream(Object object, OutputStream out) throws IOException {
        mapper.writeValue(out, object);
    }

    /**
     * 将一个对象以JSON格式输出到指定的流。
     * @author cKF49709
     * @since Jan 11, 2012
     * @param fieldName
     * @param object
     * @param stream
     * @throws IOException
     */
    public static void writeTo(String fieldName, Object object, OutputStream stream) throws IOException {
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.putPOJO(fieldName, object);
        mapper.writeValue(stream, rootNode);
    }



    /**
     * 其他格式数据转json，支持vo，map，list等
     * @param inValue
     * @return
     */
    public static String objectToJson(Object inputValue) throws IOException {

        return mapper.writeValueAsString(inputValue);
    }
    /**
     * 将Json格式的字符串转换成指定类型.
     * @param inputValue
     * @param cls待转换为的类型Class.
     * @return
     */
    public static <T> T  stringToObject(String inputValue,Class<T> cls) throws IOException {
        return mapper.readValue(inputValue, cls);
    }
    /**
     * 对集合进行反序列化
     * example：
     * TypeReference<List<UserVO>> type = new TypeReference<List<UserVO>>(){};
     * List<UserVO> userList = JsonUtil.stringToObject(string,type);
     * @author h00214522
     * @since 2013-7-12
     * @param inputValue
     * @param cls
     * @return
     * @throws IOException
     */
    public static <T> T  stringToObject(String inputValue,TypeReference<T> cls) throws IOException {
        return mapper.readValue(inputValue, cls);
    }
    /**
     * 流的方式来反序列化pojo对象
     * @author h00214522
     * @since 2013-7-12
     * @param inputValue
     * @param cls
     * @return
     * @throws IOException
     */
    public static <T> T  inputStreamToObject(InputStream inputValue,Class<T> cls) throws IOException {
        return mapper.readValue(inputValue, cls);
    }
    /**
     *  流的方式来反序列化集合对象
     * @author h00214522
     * @since 2013-7-12
     * @param inputValue
     * @param cls
     * @return
     * @throws IOException
     */
    public static <T> T  inputStreamToObject(InputStream inputValue,TypeReference<T> cls) throws IOException {
        return mapper.readValue(inputValue, cls);
    }
}
