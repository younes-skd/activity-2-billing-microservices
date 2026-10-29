package ma.emsi.app;

import org.springframework.context.annotation.*;
    import org.springframework.cloud.gateway.route.*;
    import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
    import org.springframework.core.env.Environment;
    @Configuration
    public class Routes {
      @Bean RouteLocator apiRoutes(RouteLocatorBuilder b,Environment env){
        boolean fixed=java.util.Arrays.asList(env.getActiveProfiles()).contains("static");
        return b.routes()
          .route("customers",r->r.path("/api/customers/**").filters(f->f.stripPrefix(1)).uri(fixed?"http://localhost:8081":"lb://customer-service"))
          .route("products",r->r.path("/api/products/**").filters(f->f.stripPrefix(1)).uri(fixed?"http://localhost:8082":"lb://inventory-service"))
          .route("bills",r->r.path("/api/bills/**").filters(f->f.stripPrefix(1)).uri(fixed?"http://localhost:8083":"lb://billing-service"))
          .build();
      }
    }
