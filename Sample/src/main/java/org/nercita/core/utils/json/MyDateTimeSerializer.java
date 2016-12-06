package org.nercita.core.utils.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * java日期对象经过Jackson库转换成JSON日期格式化自定义类<br>
 * 格式为yyyy-MM-dd hh:mm:ss
 */
public class MyDateTimeSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String formattedDate = formatter.format(value);
		jgen.writeString(formattedDate);
	}
}