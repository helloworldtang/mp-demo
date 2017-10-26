# mp-demo

 https://github.com/alibaba/fastjson/issues/1545

HttpMessageConverter是对http的request和response进行自动转换      

配置HttpMessageConverter可重载下面两个方法任意一个     
 1. configureMessageConverters:重载会覆盖掉spring mvc默认注册的多个HttpMessageConverter
 2. extendMessageConverters:仅添加一个自定义的HttpMessageConverter,不覆盖默认注册的HttpMessageConverter

通过对上面的讲述,建议重载extendMessageConverters方法


