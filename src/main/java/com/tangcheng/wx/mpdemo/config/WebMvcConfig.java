package com.tangcheng.wx.mpdemo.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Date;
import java.util.List;

@Profile("FastJsonHttpMessageConverter")
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        // See SPR-7316,for weixin token verify.fastjson will convert string 123 to "123"
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setWriteAcceptCharset(false);
        //增加两个优先处理的转换类型.
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(stringHttpMessageConverter);

        FastJsonHttpMessageConverter httpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.QuoteFieldNames,
                SerializerFeature.WriteEnumUsingToString,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect);
        fastJsonConfig.setSerializeFilters((ValueFilter) (o, s, source) -> {
            if (source == null) {
                return "";
            }
            if (source instanceof Date) {
                return ((Date) source).getTime();
            }
            return source;
        });
        httpMessageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(httpMessageConverter);
    }


}
