package net.th1232.configuration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Name("configuration")
@Scope(ScopeType.APPLICATION)
public class Configuration {
	final private static Logger log = Logger.getLogger(Configuration.class);
	private Map<String, Object> properties = new HashMap<String, Object>();
	@Create
	public void init() {

	}

	public void initXmlFile() {
		File xmlFile = new File("config-properties.xml");
		if (xmlFile.exists() || !xmlFile.isFile())
			return;
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		try {
			Document doc = builderFactory.newDocumentBuilder().parse(xmlFile);
			doc.normalize();
			NodeList propertiesNodes = doc.getElementsByTagName("properties");
			for (int i = 0; i < propertiesNodes.getLength(); i++){
				Node propertiesNode = propertiesNodes.item(i);
				NodeList properties = propertiesNode.getChildNodes();
				for (int j = 0; j < properties.getLength(); j++) {
					Node property = properties.item(j);
					if (property.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) property;
						Object obj = parseObject(element);
						String name = element.getAttribute("name");
						if (obj != null)
							this.properties.put(name, obj);
					}
				}
			}
		} catch (SAXException e) {
			log.error("There was an error when parsing file config-properties.xml:" + e.getMessage(),
					e.fillInStackTrace());
		} catch (IOException e) {
			log.warn("could not find the config-properties.xml file in the classpaths.");
			return;
		} catch (ParserConfigurationException e) {
			log.error("There was an error when parsing file config-properties.xml:" + e.getMessage(),
					e.fillInStackTrace());
		}
	}

	private Object parseObject(Element element) {
		NamedNodeMap attrs = element.getAttributes();
		ConfigurationDataType type = ConfigurationDataType.valueOf(element.getAttribute("type").toUpperCase());
		String eName = element.getAttribute("name");
		if (type == null) {
			String t = element.getAttribute("type");
			try {
				Class c = Class.forName(t);
				Object o = c.newInstance();
				for (int i = 0; i < attrs.getLength(); i++) {
					Node attr = attrs.item(i);
					String name = attr.getNodeName();
					if (name.equals("name") || name.equals("type") || name.equals("init"))
						continue;
					if (!setField(o, name, attr.getNodeValue())) {
						log.error("could not parse the field {" + name + "} of property {"
								+ element.getAttribute("name") + "}");
						return null;
					}
				}
				for (int i = 0; i < element.getChildNodes().getLength(); i++) {
					Node node = element.getChildNodes().item(i);
					String name = 
				}
				
				return o;
			} catch (Exception e) {
				log.error("could not parse the property {" + eName + "} of property {" + element.getAttribute("name")
						+ "}");
				return null;
			}
		} else switch (type) {
		case STRING:
			
			break;

		default:
			break;
		}
		return null;
	}

	private boolean setField(Object obj, String name, String value) {
		if (obj == null)
			return false;
		try {
			Field field = obj.getClass().getDeclaredField(name);
			if (!field.isAccessible())
				field.setAccessible(true);
			if (field.getClass().equals(Boolean.TYPE) || field.getClass().equals(Boolean.class))
				return setFieldBoolean(obj, field, value);
			else if (field.getClass().equals(Integer.TYPE) || field.getClass().equals(Integer.class))
				return setFieldBoolean(obj, field, value);
			else if (field.getClass().equals(Byte.TYPE) || field.getClass().equals(Byte.class))
				return setFieldBoolean(obj, field, value);
			else if (field.getClass().equals(Short.TYPE) || field.getClass().equals(Short.class))
				return setFieldBoolean(obj, field, value);
			else if (field.getClass().equals(Long.TYPE) || field.getClass().equals(Long.class))
				return setFieldLong(obj, field, value);
			else if (field.getClass().equals(Float.TYPE) || field.getClass().equals(Float.class))
				return setFieldBoolean(obj, field, value);
			else if (field.getClass().equals(Double.TYPE) || field.getClass().equals(Double.class))
				return setFieldDouble(obj, field, value);
			else if (field.getClass().equals(Character.TYPE) || field.getClass().equals(Character.class))
				return setFieldCharacter(obj, field, value);
			else if (field.getClass().equals(String.class))
				return setFieldString(obj, field, value);
			else return setFieldObject(obj, field, value);
		} catch (Exception e) {
			return false;
		}
	}

	private boolean setFieldObject(Object obj, Field field, String value) {
		if (field == null) return false;
		if (!properties.containsKey(value)) {
			log.error("could not set field {" + field.getName() + "} type OBJECT of object {" + obj.getClass().getName()
					+ "} with value {" + value + "}. There is no property named {" + value + "} in the context");
			return false;
		}
		try {
			field.set(obj, properties.get(value));
		} catch (Exception e) {
			log.error("could not set field {" + field.getName() + "} type OBJECT of object {" + obj.getClass().getName()
					+ "} with value {" + value + "}");
			return false;
		}
		return true;
	}
	private boolean setFieldString(Object obj, Field field, String value) {
		if (field == null) return false;
		try {
			field.set(obj, value);
		} catch (Exception e) {
			log.error("could not set field {" + field.getName() + "} type STRING of object {" + obj.getClass().getName()
					+ "} with value {" + value + "}");
			return false;
		}
		return true;
	}
	private boolean setFieldDouble(Object obj, Field field, String value) {
		if (field == null) return false;
		try {
			Double i = Double.parseDouble(value);
			field.setFloat(obj, i.floatValue());
		} catch (Exception e) {
			log.error("could not set field {" + field.getName() + "} type Double of object {" + obj.getClass().getName()
					+ "} with value {" + value + "}");
			return false;
		}
		return true;
	}

	private boolean setFieldCharacter(Object obj, Field field, String value) {
		if (field == null) return false;
		try {
			field.setFloat(obj, value.charAt(0));
		} catch (Exception e) {
			log.error("could not set field {" + field.getName() + "} type CHAR of object {" + obj.getClass().getName()
					+ "} with value {" + value + "}");
			return false;
		}
		return true;
	}
	private boolean setFieldFloat(Object obj, Field field, String value) {
		if (field == null) return false;
		try {
			Float i = Float.parseFloat(value);
			field.setFloat(obj, i.floatValue());
		} catch (Exception e) {
			log.error("could not set field {" + field.getName() + "} type FLOAT of object {"
					+ obj.getClass().getName() + "} with value {" + value + "}");
			return false;
		}
		return true;
	}

	private boolean setFieldLong(Object obj, Field field, String value) {
		if (field == null) return false;
		try {
			Long i = Long.parseLong(value);
			field.setLong(obj, i.longValue());
		} catch (Exception e) {
			log.error("could not set field {" + field.getName() + "} type Long of object {"
					+ obj.getClass().getName() + "} with value {" + value + "}");
			return false;
		}
		return true;
	}
	private boolean setFieldInteger(Object obj, Field field, String value) {
		if (field == null) return false;
		try {
			Integer i = Integer.parseInt(value);
			field.setInt(obj, i.intValue());
		} catch (Exception e) {
			log.error("could not set field {" + field.getName() + "} type INTEGER (or BYTE or SHORT) of object {"
					+ obj.getClass().getName() + "} with value {" + value + "}");
			return false;
		}
		return true;
	}
	private boolean setFieldBoolean(Object obj, Field field, String value) {
		if (field == null)
			return false;
		boolean b = true;
		if (value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("y") || value.equalsIgnoreCase("true")
				|| value.equalsIgnoreCase("t"))
			b = true;
		else if (value.equalsIgnoreCase("no") || value.equalsIgnoreCase("n") || value.equalsIgnoreCase("false")
				|| value.equalsIgnoreCase("f"))
			b = false;
		else
			return false;
		try {
			field.setBoolean(obj, true);
		} catch (Exception e) {
			log.error("could not set field {" + field.getName() + "} type BOOL of object {"
					+ obj.getClass().getName() + "} with value {" + value + "}");
			return false;
		}
		return true;
	}

	public static enum ConfigurationDataType {
		STRING, DATE, INTEGER, BYTE, SHORT, LONG, DOUBLE, FLOAT, BOOL, CHAR;
	}
}
