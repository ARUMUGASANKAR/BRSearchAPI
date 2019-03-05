package com.mands.bloomreach.Controller;

import com.mands.bloomreach.Model.HealthCheckResponse;
import com.mands.bloomreach.Service.HealthCheckService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @Autowired
    HealthCheckService service;
    @ApiOperation(notes = "Product Search API health check",
            httpMethod="GET",
            value = "Service for Product Search API health check",
            nickname = "healthCheck",
            produces="application/json",
            response = HealthCheckResponse.class,
            hidden=true)
    @ApiResponses({
            @ApiResponse(
                    code = 200, message = "Success",
                    response = HealthCheckResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Resource not found")})

    @RequestMapping(value = "/health", method = RequestMethod.GET,produces = "application/json" )
    public ResponseEntity<HealthCheckResponse> healthCheck() {
        return new ResponseEntity<HealthCheckResponse>(service.getHealthCheck(), HttpStatus.OK);
    }
}
