package com.nijunyang.mock.feign.starter;

import com.nijunyang.mock.feign.core.MockFeign;
import com.nijunyang.mock.feign.core.Target;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

/**
 * Description: 通过FactoryBean整合spring
 * Created by nijunyang on 2022/3/10 9:50
 */
@Getter
@Setter
public class MockFeignClientFactoryBean implements FactoryBean<Object> {

    private Class<?> type;

    private String name;

    private String url;


    @Override
    public Object getObject() throws Exception {
        String url = this.url;
        if (StringUtils.hasText(this.url) && !this.url.startsWith("http")) {
            url = "http://" + this.url;
        }
        //生成代理对象
        return new MockFeign().target(new Target<>(this.type, this.name, url));
    }

    @Override
    public Class<?> getObjectType() {
        return this.type;
    }

}
