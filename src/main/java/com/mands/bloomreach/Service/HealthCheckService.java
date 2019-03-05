package com.mands.bloomreach.Service;

import com.mands.bloomreach.Model.HealthCheckResponse;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {

    public HealthCheckResponse getHealthCheck() {
        HealthCheckResponse response = new HealthCheckResponse();

        response.setStatus("UP");
        response.setWelcomeMessage("Product Search Service is up and running now");

        return response;
    }
}
