package com.pdsu.sojacnn.config;

import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 半梦
 * @create 2021-05-08 19:27
 */
@Primary
@Component
@SuppressWarnings("all")
public class SwaggerResourcesConfig implements SwaggerResourcesProvider {

    //RouteLocator可以根据zuul配置的路由列表获取服务
    private final RouteLocator routeLocator;

    public SwaggerResourcesConfig(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    //这个方法用来添加swagger的数据源
    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<Route> routes = routeLocator.getRoutes();
        //在这里遍历的时候，可以排除掉敏感微服务的路由
        routes.forEach(route ->  resources.add(swaggerResource(route.getId(), route.getFullPath()
                .replace("**", "v2/api-docs"),"2.0")));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }


}
