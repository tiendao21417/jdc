package com.jdc.onlineshopping.mapper;

import com.jdc.onlineshopping.domain.Product;
import com.jdc.onlineshopping.web.rest.dto.ProductDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-23T00:15:28+0700",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( dto.getId() );
        product.setCategory( dto.getCategory() );
        product.setName( dto.getName() );
        product.setPrice( dto.getPrice() );
        product.setBrand( dto.getBrand() );
        product.setColour( dto.getColour() );
        product.setUrl( dto.getUrl() );

        return product;
    }

    @Override
    public ProductDTO toDto(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( entity.getId() );
        productDTO.setCategory( entity.getCategory() );
        productDTO.setName( entity.getName() );
        productDTO.setPrice( entity.getPrice() );
        productDTO.setBrand( entity.getBrand() );
        productDTO.setColour( entity.getColour() );
        productDTO.setUrl( entity.getUrl() );

        return productDTO;
    }

    @Override
    public List<Product> toEntity(List<ProductDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( dtoList.size() );
        for ( ProductDTO productDTO : dtoList ) {
            list.add( toEntity( productDTO ) );
        }

        return list;
    }

    @Override
    public List<ProductDTO> toDto(List<Product> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>( entityList.size() );
        for ( Product product : entityList ) {
            list.add( toDto( product ) );
        }

        return list;
    }
}
