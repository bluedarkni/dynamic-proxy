package com.nijunyang.mock.feign.core;

/**
 * Description: 目标对象
 * Created by nijunyang on 2022/3/8 17:26
 */
public class Target<T> {

    /**
     * 目标对象class
     */
    private Class<T> type;
    /**
     * name
     */
    private String name;
    /**
     * url
     */
    private String url;

    public Target(Class<T> type, String name, String url) {
        this.type = type;
        this.name = name;
        this.url = url;
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
