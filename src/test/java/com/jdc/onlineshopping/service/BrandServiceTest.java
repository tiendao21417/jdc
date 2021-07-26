package com.jdc.onlineshopping.service;

import com.jdc.onlineshopping.mapper.BrandMapper;
import com.jdc.onlineshopping.mapper.BrandMapperImpl;
import com.jdc.onlineshopping.repository.BrandRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tiendao on 25/07/2021
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BrandServiceImpl.class, BrandMapperImpl.class, KafkaServiceImpl.class})
public class BrandServiceTest {

    @Autowired
    private BrandService brandService;

    @MockBean
    private BrandRepository brandRepository;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private KafkaService kafkaService;

    @MockBean
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void create() {

//        BrandDTO brandDTO = new BrandDTO();
//        String requestId = "requestId";
//        brandDTO.setId(0);
//        brandDTO.setName("new Brand 1");
//        brandDTO.setCode("RB");
//
//        Brand brand = new Brand();
//        brand.setId(1);
//        brand.setName(brandDTO.getName());
//        brand.setCode(brandDTO.getCode());
//
//        Mockito.when(brandRepository.save(any(Brand.class))).thenReturn(brand);
//        //Mockito.doNothing().when(kafkaService.send(any(BrandDTO.class), requestId, KafkaTransferKeys.UPDATE_BRAND));
//        // Mockito.doNothing().when(any(BrandDTO.class), requestId, KafkaTransferKeys.UPDATE_BRAND);
//
//        ResponseDTO responseDTO = brandService.create(brandDTO, requestId);
//        Brand entity = (Brand) responseDTO.getData();
//        assertThat(brandDTO.getId()).isNotEqualTo(entity.getId());
    }

    @Test
    public void update() {

    }

    @Test
    public void getList() {

    }

    @Test
    public void detail() {

    }

    @Test
    public void createMultiple() {

    }

}
