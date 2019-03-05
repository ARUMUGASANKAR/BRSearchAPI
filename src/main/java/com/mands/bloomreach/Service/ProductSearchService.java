package com.mands.bloomreach.Service;

import com.mands.bloomreach.Client.ProductSearchRestClient;
import com.mands.bloomreach.Exception.ProductSearchCustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductSearchService {

    @Autowired
    ProductSearchRestClient productSearchRestClient;

    public String getProductSearchDetails(String keyword) throws ProductSearchCustomException

    {
      log.info(("Keyword Passed " +keyword));
        return productSearchRestClient.productSearchDetails(keyword);
    }
}
