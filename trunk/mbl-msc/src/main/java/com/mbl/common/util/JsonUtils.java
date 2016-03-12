package com.mbl.common.util;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class JsonUtils
{
    /** Commons Logging instance. */
    private static org.apache.commons.logging.Log log =
        org.apache.commons.logging.LogFactory.getLog(JsonUtils.class);
    /**
     * @param obj 任意对象
     * @return String
     */
    public static String object2json(Object obj) {
        StringBuilder json = new StringBuilder();
        if (obj == null) {
            json.append("\"\"");
        } else if (obj instanceof String || obj instanceof Integer || obj instanceof Float 
                || obj instanceof Boolean || obj instanceof Short || obj instanceof Double 
                || obj instanceof Long || obj instanceof BigDecimal
                || obj instanceof BigInteger || obj instanceof Byte) {
            json.append("\"").append(string2json(obj.toString())).append("\"");
        } else if (obj instanceof Object[]) {
            json.append(array2json((Object[]) obj));
        } else if (obj instanceof List) {
            json.append(list2json((List<?>) obj));
        } else if (obj instanceof Map) {
            json.append(map2json((Map<?, ?>) obj));
        } else if (obj instanceof Set) {
            json.append(set2json((Set<?>) obj));
        } else {
            json.append(bean2json(obj));
        }
        return json.toString();
    }
    /**
     * @param bean bean对象
     * @return String
     */
    public static String bean2json(Object bean) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        PropertyDescriptor[] props = null;
        try {
            props = Introspector.getBeanInfo
                (bean.getClass(), Object.class).getPropertyDescriptors();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        if (props != null) {
            for (int i = 0; i < props.length; i++) {
                try {
                    String name = object2json(props[i].getName());
                    String value = object2json(props[i].getReadMethod().invoke(bean));
                    json.append(name);
                    json.append(":");
                    json.append(value);
                    json.append(",");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }
    /**
     * @param list list对象
     * @return String
     */
    public static String list2json(List<?> list) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (list != null && list.size() > 0) {
            for (Object obj : list) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }
    /**
     * @param array 对象数组
     * @return String
     */
    public static String array2json(Object[] array) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (array != null && array.length > 0) {
            for (Object obj : array) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }
    /**
     * @param map map对象
     * @return String
     */
    public static String map2json(Map<?, ?> map) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        if (map != null && map.size() > 0) {
            for (Object key : map.keySet()) {
                json.append(object2json(key));
                json.append(":");
                json.append(object2json(map.get(key)));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }
    /**
     * @param set 集合对象
     * @return String
     */
    public static String set2json(Set<?> set) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (set != null && set.size() > 0) {
            for (Object obj : set) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }
    /**
     * @param s 参数
     * @return String
     */
    public static String string2json(String s) {
        if (null == s){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
            case '"':
                sb.append("\\\"");
                break;
            case '\\':
                sb.append("\\\\");
                break;
            case '\b':
                sb.append("\\b");
                break;
            case '\f':
                sb.append("\\f");
                break;
            case '\n':
                sb.append("\\n");
                break;
            case '\r':
                sb.append("\\r");
                break;
            case '\t':
                sb.append("\\t");
                break;
            case '/':
                sb.append("\\/");
                break;
            default:
                if (ch >= '\u0000' && ch <= '\u001F') {
                    String ss = Integer.toHexString(ch);
                    sb.append("\\u");
                    for (int k = 0; k < 4 - ss.length(); k++) {
                        sb.append('0');
                    }
                    sb.append(ss.toUpperCase());
                } else {
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }
    
  /*  public static Map JsonToMap(String jsonVal){
    	//JSONArray
    			JSONArray jsonArray = new JSONArray(jsonVal);

    			List<Map<String,Object>> mapListJson = (List)jsonArray;
    			for (int i = 0; i < mapListJson.size(); i++) {
    				Map<String,Object> obj=mapListJson.get(i);
    				
    				for(Entry<String,Object> entry : obj.entrySet()){
    		            String strkey1 = entry.getKey();
    		            Object strval1 = entry.getValue();
    		            System.out.println("KEY:"+strkey1+"  -->  Value:"+strval1+"\n");
    		        }
    			}
    			
    			
    			
    			// JSONObject 
    			String jsonObjectData="{\"data1\":{\"a1\":\"12\",\"b1\":\"112\",\"c1\":\"132\",\"d1\":\"134\"},\"data2\":{\"a2\":\"12\",\"b2\":\"112\",\"c2\":\"132\",\"d2\":\"134\"},\"data3\":{\"a3\":\"12\",\"b3\":\"112\",\"c3\":\"132\",\"d3\":\"134\"}}";
    			JSONObject jsonObject = new JSONObject(jsonObjectData);
    			Map<String, Object> mapJson = new JSONObject.  .toJSONObject(jsonObject);
    			
    	        for(Entry<String,Object> entry : mapJson.entrySet()){
    	            Object strval1 = entry.getValue();
    	            JSONObject jsonObjectStrval1 = JSONObject.toJSONObject(strval1);
    	            Map<String, Object> mapJsonObjectStrval1 = JSONObject.toJSONObject(jsonObjectStrval1);
    	            System.out.println("KEY:"+entry.getKey()+"  -->  Value:"+entry.getValue()+"\n");
    	            for(Entry<String, Object> entry1:mapJsonObjectStrval1.entrySet()){
    	            	System.out.println("KEY:"+entry1.getKey()+"  -->  Value:"+entry1.getValue()+"\n");
    	            }
    	            
    	        }
    	        return mapJson;
    		}
    
    */
    /**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static String parseXml2Json(String strxml) throws JDOMException, IOException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		
		Map m = new HashMap();
		
		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = XMLUtil.getChildrenText(children);
			}
			
			m.put(k, v);
		}
		
		//关闭流
		in.close();
		
		return map2json(m);
	}
} 