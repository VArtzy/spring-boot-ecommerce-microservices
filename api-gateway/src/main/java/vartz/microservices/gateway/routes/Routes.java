package vartz.microservices.gateway.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions.circuitBreaker;
import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return route("product_service")
            .route(RequestPredicates.path("/api/product"), http("http://localhost:8080"))
            .filter(circuitBreaker("productServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> productServiceSwaggerRoute() {
        return route("product_service_swagger")
            .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"), http("http://localhost:8080"))
            .filter(circuitBreaker("productServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
            .filter(setPath("/api-docs"))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return route("order_service")
            .route(RequestPredicates.path("/api/order"), http("http://localhost:8081"))
            .filter(circuitBreaker("orderServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceSwaggerRoute() {
        return route("order_service_swagger")
            .route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"), http("http://localhost:8081"))
            .filter(circuitBreaker("orderServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
            .filter(setPath("/api-docs"))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute() {
        return route("inventory_service")
            .route(RequestPredicates.path("/api/inventory"), http("http://localhost:8082"))
            .filter(circuitBreaker("inventoryServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceSwaggerRoute() {
        return route("inventory_service_swagger")
            .route(RequestPredicates.path("/aggregate/inventory-service/v3/api-docs"), http("http://localhost:8082"))
            .filter(circuitBreaker("inventoryServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
            .filter(setPath("/api-docs"))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return route("fallbackRoute")
            .GET("/fallbackRoute", req -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service Unavailable, please try again later"))
            .build();
    }
}
