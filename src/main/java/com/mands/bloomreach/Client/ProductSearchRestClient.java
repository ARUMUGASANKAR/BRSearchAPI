package com.mands.bloomreach.Client;

import com.mands.bloomreach.Exception.ProductSearchCustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@PropertySource("classpath:productsearch.properties")
public class ProductSearchRestClient {

    private RestTemplate restTemplate = new RestTemplate();


    @Value("${mands.br.v1.rest_url}")
    private String bloomReachAPIEndPoint;

    @Value("${mands.br.v1.account_id}")
    private String account_id;

    @Value("${mands.br.v1.auth_key}")
    private String auth_key;

    @Value("${mands.br.v1.domain_key}")
    private String domain_key;

    @Value("${mands.br.v1.request_id}")
    private String request_id;

    @Value("${mands.br.v1.br_uid_2}")
    private String uid_2;

    @Value("${mands.br.v1.ref_url}")
    private String ref_url;

    @Value("${mands.br.v1.url}")
    private String url;

    @Value("${mands.br.v1.request_type}")
    private String request_type;

    @Value("${mands.br.v1.search_type}")
    private String search_type;

    @Value("${mands.br.v1.fl}")
    private String fl;

    @Value("${mands.br.v1.rows}")
    private String rows;

    @Value("${mands.br.v1.start}")
    private String start;

    public String productSearchDetails(String keyword) throws RestClientException, ProductSearchCustomException {
        StringBuilder APIEndPoint = new StringBuilder(bloomReachAPIEndPoint);

        APIEndPoint.append(account_id);
        APIEndPoint.append(auth_key);
        APIEndPoint.append(domain_key);
        APIEndPoint.append(request_id);
        APIEndPoint.append(uid_2);
        APIEndPoint.append(ref_url);
        APIEndPoint.append(url);
        APIEndPoint.append(request_type);
        APIEndPoint.append(search_type);
        APIEndPoint.append(keyword);
        APIEndPoint.append(fl);
        APIEndPoint.append(rows);
        APIEndPoint.append(start);

        log.info("Value of APIEndPoint" + APIEndPoint);
        String FinalAPIEndPoint = APIEndPoint.toString();
        UriComponents bloomReachEndPoint = UriComponentsBuilder.fromUriString(FinalAPIEndPoint).build();
        return restTemplate.getForObject(bloomReachEndPoint.toString(), String.class);

    }
}
