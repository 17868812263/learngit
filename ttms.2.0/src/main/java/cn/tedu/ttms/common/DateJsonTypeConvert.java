package cn.tedu.ttms.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * springmvc返回的json串中，将Date类型转为long类型，通过此类继承JsonSerializer来自定义格式，
 * 之后在实体类对应的date类型的字段的getter方法上添加注解@JsonSerialize(using=DateJsonTypeConvert.class)
 * @author Admin
 *
 */
public class DateJsonTypeConvert extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		//自定义日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		//将数据以json格式输出
		jgen.writeString(sdf.format(value));
	}

}












