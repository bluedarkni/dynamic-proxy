package com.nijunyang.mock.feign.core;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Description: 代理方法
 * Created by nijunyang on 2022/3/9 10:41
 */
public class MethodHandler {

    private MethodMetadata metadata;

    public MethodHandler(MethodMetadata metadata) {
        this.metadata = metadata;
    }

    /**
     * 执行目标方法的代理
     * @param arg
     * @return
     * @throws Throwable
     */
    public Object invoke(Object[] arg, Target<?> target) throws Throwable {
        String url = target.getUrl();
        url = url.endsWith("/") ? url : url + "/";
        String path = metadata.getRequestMapping().path()[0];
        path = path.startsWith("/") ? path.substring(1) : path;
        if (RequestMethod.GET == metadata.getRequestMapping().method()[0]) {
            String query = buildParams(arg);
            String s = HttpUtil.get(url + path + query);
            //可以自己任意处理结果
            return s;
        }
        throw new RuntimeException("其他请求方式暂不支持!");
    }

    /**
     * 构建查询参数
     * @return
     */
    private String buildParams(Object[] arg) {
        StringBuilder query = new StringBuilder("");
        Map<Integer, Annotation[]> paramsAnnotationMap = metadata.getParamsAnnotationMap();
        for (int i = 0; i < arg.length; i++) {
            Object param = arg[i];
            if (!CollectionUtils.isEmpty(paramsAnnotationMap)) {
                Annotation[] annotations = paramsAnnotationMap.get(i);
                for (Annotation annotation : annotations) {
                    //处理RequestParam
                    if (annotation instanceof RequestParam) {
                        if (query.length() > 0) {
                            query.append("&");
                        }
                        RequestParam requestParam = (RequestParam) annotation;
                        String name = requestParam.name();
                        String value = requestParam.value();
                        if (StringUtils.hasLength(name)) {
                            query.append(name).append("=").append(param.toString());
                        } else if (StringUtils.hasLength(value)) {
                            query.append(value).append("=").append(param.toString());
                        } else {
                            throw new RuntimeException("@RequestParam 必须指定name或者value");

                        }
                    }
                }
            }
        }

        return "?" + query;
    }
}
