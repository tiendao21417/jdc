package com.jdc.onlineshopping.service;

import com.jdc.onlineshopping.constant.CErrors;
import com.jdc.onlineshopping.domain.Brand;
import com.jdc.onlineshopping.domain.Category;
import com.jdc.onlineshopping.kafka.KafkaTransferKeys;
import com.jdc.onlineshopping.mapper.CategoryMapper;
import com.jdc.onlineshopping.repository.CategoryRepository;
import com.jdc.onlineshopping.utils.ResponseUtils;
import com.jdc.onlineshopping.utils.Throws;
import com.jdc.onlineshopping.web.rest.dto.BrandDTO;
import com.jdc.onlineshopping.web.rest.dto.CategoryDTO;
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
 * @author tiendao on 22/07/2021
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ResponseDTO create(CategoryDTO dto, String requestId) {

        Category category = categoryMapper.toEntity(dto);
        category = categoryRepository.save(category);
        CategoryDTO categoryDTO = categoryMapper.toDto(category);
        kafkaService.send(categoryDTO, requestId, KafkaTransferKeys.UPDATE_CATEGORY);
        return ResponseUtils.responseOK(categoryDTO);
    }

    @Override
    public ResponseDTO createMultiple(List<CategoryDTO> categoryDTOS, String requestId) {

        List<Category> entities = categoryMapper.toEntity(categoryDTOS);
        entities = categoryRepository.saveAll(entities);
        List<CategoryDTO> result = categoryMapper.toDto(entities);
        kafkaService.send(result, requestId, KafkaTransferKeys.UPDATE_CATEGORIES);
        return ResponseUtils.responseOK(result);
    }

    @Override
    public ResponseDTO update(CategoryDTO dto, String requestId) {

        Optional<Category> categoryOptional = categoryRepository.findById(dto.getId());

        Category category;
        if (categoryOptional.isEmpty()) {
            Throws.of(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER, messageSource.getMessage(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER,
                    new Object[]{"body"}, null));
        }
        category = categoryOptional.get();
        category.setCode(dto.getCode());
        category.setName(dto.getName());
        category = categoryRepository.save(category);
        CategoryDTO result = categoryMapper.toDto(category);
        kafkaService.send(result, requestId, KafkaTransferKeys.UPDATE_CATEGORY);
        return ResponseUtils.responseOK(result);
    }

    @Override
    public ResponseDTO getList(String requestId, int page, int limit) {

        Pageable paging = PageRequest.of(page, limit, Sort.by("id").descending());
        Page<Category> result = categoryRepository.findAll(paging);
        List<Category> categories = new ArrayList<>(result.getContent());
        return ResponseUtils.buildResponse(ResponseUtils.CODE_OK,
                ResponseUtils.MESSAGE_OK, page, limit, result.getTotalElements(), categories);
    }

    @Override
    public ResponseDTO detail(Long id, String requestId) {

        Optional<Category> optional = categoryRepository.findById(id);

        if (optional.isEmpty()) {
            Throws.of(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER, messageSource.getMessage(CErrors.BAD_REQUEST_BY_WRONG_PARAMETER,
                    new Object[]{"body"}, null));
        }
        CategoryDTO result = categoryMapper.toDto(optional.get());
        return ResponseUtils.responseOK(result);
    }
}
