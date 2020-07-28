package com.xcxcxcxcx.spring.cloud.gateway.samples;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.RouteToRequestUrlFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;

/**
 * @author XCXCXCXCX
 * @date 2020/7/28 9:17 上午
 */
public class RouteToRequestUrlFilterTest {

    private RouteToRequestUrlFilter routeToRequestUrlFilter;

    private ServerWebExchange exchange;

    private ServerHttpRequest request;

    private GatewayFilterChain chain;

    @Before
    public void init() {
        routeToRequestUrlFilter = new RouteToRequestUrlFilter();
        exchange = Mockito.mock(ServerWebExchange.class);
        request = Mockito.mock(ServerHttpRequest.class);
        Mockito.when(request.getURI()).thenReturn(URI.create("http://0.0.0.0:8080/v1/list?param1==eq_sTAWdYwx0xMFoUNainw===&param2=%E8%83%9C%E5%A4%9A%E8%B4%9F%E5%B0%91%E5%9C%B0%E6%96%B9"));
        Mockito.when(exchange.getRequest()).thenReturn(request);
        Route route = Mockito.mock(Route.class);
        Mockito.when(route.getUri()).thenReturn(URI.create("lb://xxx-xxx"));
        Mockito.when(exchange.getAttribute(org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR))
                .thenReturn(route);

        chain = Mockito.mock(GatewayFilterChain.class);
    }

    @Test
    public void filterTest() {
        routeToRequestUrlFilter.filter(exchange, chain);
    }

}
