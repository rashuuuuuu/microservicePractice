//package com.rashmita.product_service.interfacee;
//
//import com.rashmita.base_domains.model.ServerResponse;
//import jakarta.validation.Valid;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@FeignClient("PRODUCT-SERVICE")
//public interface ProductInterface {
//    @PostMapping("/create")
//    public ServerResponse<?> createProduct(@Valid @RequestBody ProductRequest productRequest);
//    @PostMapping("/update")
//    public ServerResponse<?> updateProduct(@Valid @RequestBody ProductUpdateRequest productUpdateRequest) ;
//    @PostMapping("getByCode")
//    public  ServerResponse<ProductResponse> getProductByCode(@RequestBody ProductRequestCode productRequestCode) ;
//    @PostMapping("delete")
//    public ServerResponse<?> deleteProduct(@RequestBody ProductRequestCode productRequestCode) ;
//    @GetMapping("getAll")
//    public ServerResponse<?> getAllProducts(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "establishedDate") String sortBy,
//            @RequestParam(defaultValue = "desc") String sortDir
//    );
//
//}
