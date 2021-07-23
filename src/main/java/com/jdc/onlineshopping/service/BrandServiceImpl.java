package com.jdc.onlineshopping.service;

import com.jdc.onlineshopping.constant.CErrors;
import com.jdc.onlineshopping.domain.Brand;
import com.jdc.onlineshopping.kafka.KafkaTransferKeys;
import com.jdc.onlineshopping.mapper.BrandMapper;
import com.jdc.onlineshopping.app.ops.service.OpsKafkaService;
import com.jdc.onlineshopping.repository.BrandRepository;
import com.jdc.onlineshopping.utils.ResponseUtils;
import com.jdc.onlineshopping.utils.Throws;
import com.jdc.onlineshopping.web.rest.dto.BrandDTO;
import com.jdc.onlineshopping.web.rest.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author tiendao on 18/07/2021
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private OpsKafkaService kafkaService;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ResponseDTO create(BrandDTO brandDTO, String requestId) {

        Brand brand = brandMapper.toEntity(brandDTO);
        brand = brandRepository.save(brand);
        BrandDTO result = brandMapper.toDto(brand);
        kafkaService.send(result, requestId, KafkaTransferKeys.UPDATE_BRAND);
        return ResponseUtils.responseOK(result);
    }

    @Override
    public ResponseDTO update(BrandDTO dto, String requestId) {

        Optional<Brand> brandOptional = brandRepository.findById(dto.getId());

        Brand brand;
        if (brandOptional.isEmpty()) {
            Throws.of(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER, messageSource.getMessage(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER,
                    new Object[]{"body"}, null));
        }
        brand = brandOptional.get();
        brand.setCode(dto.getCode());
        brand.setName(dto.getName());
        brand = brandRepository.save(brand);
        BrandDTO result = brandMapper.toDto(brand);
        kafkaService.send(result, requestId, KafkaTransferKeys.UPDATE_BRAND);
        return ResponseUtils.responseOK(result);
    }

    @Override
    public ResponseDTO getList(String requestId, int page, int limit) {

        Pageable paging = PageRequest.of(page, limit, Sort.by("id").descending());
        Page<Brand> result = brandRepository.findAll(paging);
        List<Brand> brands = new ArrayList<>(result.getContent());
        return ResponseUtils.buildResponse(ResponseUtils.CODE_OK,
                ResponseUtils.MESSAGE_OK, page, limit, result.getTotalElements(), brands);
    }

    @Override
    public ResponseDTO detail(Long id, String requestId) {

        Optional<Brand> brandOptional = brandRepository.findById(id);

        if (brandOptional.isEmpty()) {
            Throws.of(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER, messageSource.getMessage(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER,
                    new Object[]{"body"}, null));
        }
        BrandDTO result = brandMapper.toDto(brandOptional.get());
        return ResponseUtils.responseOK(result);
    }
}
