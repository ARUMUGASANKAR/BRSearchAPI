package com.mands.bloomreach.Controller;

import com.mands.bloomreach.Exception.ProductSearchCustomException;
import com.mands.bloomreach.Service.ProductSearchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value ="/v1")
public class SearchController {


    @Autowired
    ProductSearchService productSearchService;

    @ApiOperation(value = "Get the Keyword Search API information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the assembled product details", response = String[].class),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "Failed to get the product details") })


    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/productSearch/{keyword}", method = RequestMethod.GET)

    public ResponseEntity<Object> getProductDetails(@PathVariable(value = "keyword") String keyword) throws ProductSearchCustomException, ExecutionException,InterruptedException
    {

            return new ResponseEntity<>(productSearchService.getProductSearchDetails(keyword),HttpStatus.OK);

    }

}
