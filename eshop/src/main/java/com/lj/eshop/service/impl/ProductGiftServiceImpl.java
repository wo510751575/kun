package com.lj.eshop.service.impl;

import java.util.Date;
/**
 * Copyright &copy; 2018-2021  All rights reserved.
 *
 * Licensed under the 深圳市小坤 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.eshop.constant.ErrorCode;
import com.lj.eshop.dao.IProductGiftDao;
import com.lj.eshop.domain.ProductGift;
import com.lj.eshop.dto.FindProductGiftPage;
import com.lj.eshop.dto.ProductGiftDto;
import com.lj.eshop.service.IProductGiftService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 
 * 
 * 
 *         CreateDate: 2017.12.14
 */
@Service
public class ProductGiftServiceImpl implements IProductGiftService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ProductGiftServiceImpl.class);

	@Resource
	private IProductGiftDao productGiftDao;

	@Override
	public void addProductGift(ProductGiftDto productGiftDto) throws TsfaServiceException {
		logger.debug("addProductGift(AddProductGift addProductGift={}) - start", productGiftDto);

		AssertUtils.notNull(productGiftDto);
		try {
			ProductGift productGift = new ProductGift();
			// add数据录入
			productGift.setCode(GUID.generateCode());
			productGift.setGiftCode(productGiftDto.getGiftCode());
			productGift.setGiftName(productGiftDto.getGiftName());
			productGift.setName(productGiftDto.getName());
			productGift.setStatus(productGiftDto.getStatus());
			productGift.setCreater(productGiftDto.getCreater());
			productGift.setCreateTime(new Date());
			productGiftDao.insertSelective(productGift);
			logger.debug("addProductGift(ProductGiftDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增赠品礼包信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PRODUCT_GIFT_ADD_ERROR, "新增赠品礼包信息错误！", e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询赠品礼包信息
	 *
	 * @param findProductGiftPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author  CreateDate: 2017年12月14日
	 *
	 */
	public List<ProductGiftDto> findProductGifts(FindProductGiftPage findProductGiftPage) throws TsfaServiceException {
		AssertUtils.notNull(findProductGiftPage);
		List<ProductGiftDto> returnList = null;
		try {
			returnList = productGiftDao.findProductGifts(findProductGiftPage);
		} catch (Exception e) {
			logger.error("查找赠品礼包信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PRODUCT_GIFT_NOT_EXIST_ERROR, "赠品礼包信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateProductGift(ProductGiftDto productGiftDto) throws TsfaServiceException {
		logger.debug("updateProductGift(ProductGiftDto productGiftDto={}) - start", productGiftDto);

		AssertUtils.notNull(productGiftDto);
		AssertUtils.notNullAndEmpty(productGiftDto.getCode(), "Code不能为空");
		try {
			ProductGift productGift = new ProductGift();
			// update数据录入
			productGift.setCode(productGiftDto.getCode());
			productGift.setGiftCode(productGiftDto.getGiftCode());
			productGift.setGiftName(productGiftDto.getGiftName());
			productGift.setName(productGiftDto.getName());
			productGift.setStatus(productGiftDto.getStatus());
			productGift.setCreateTime(new Date());
			AssertUtils.notUpdateMoreThanOne(productGiftDao.updateByPrimaryKeySelective(productGift));
			logger.debug("updateProductGift(ProductGiftDto) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("赠品礼包信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PRODUCT_GIFT_UPDATE_ERROR, "赠品礼包信息更新信息错误！", e);
		}
	}

	@Override
	public ProductGiftDto findProductGift(ProductGiftDto productGiftDto) throws TsfaServiceException {
		logger.debug("findProductGift(FindProductGift findProductGift={}) - start", productGiftDto);

		AssertUtils.notNull(productGiftDto);
		AssertUtils.notAllNull(productGiftDto.getCode(), "Code不能为空");
		try {
			ProductGift productGift = productGiftDao.selectByPrimaryKey(productGiftDto.getCode());
			if (productGift == null) {
				return null;
				// throw new
				// TsfaServiceException(ErrorCode.PRODUCT_GIFT_NOT_EXIST_ERROR,"赠品礼包信息不存在");
			}
			ProductGiftDto findProductGiftReturn = new ProductGiftDto();
			// find数据录入
			findProductGiftReturn.setCode(productGift.getCode());
			findProductGiftReturn.setGiftCode(productGift.getGiftCode());
			findProductGiftReturn.setGiftName(productGift.getGiftName());
			findProductGiftReturn.setName(productGift.getName());
			findProductGiftReturn.setStatus(productGift.getStatus());
			findProductGiftReturn.setCreater(productGift.getCreater());
			findProductGiftReturn.setCreateTime(productGift.getCreateTime());

			logger.debug("findProductGift(ProductGiftDto) - end - return value={}", findProductGiftReturn);
			return findProductGiftReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找赠品礼包信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PRODUCT_GIFT_FIND_ERROR, "查找赠品礼包信息信息错误！", e);
		}

	}

	@Override
	public Page<ProductGiftDto> findProductGiftPage(FindProductGiftPage findProductGiftPage)
			throws TsfaServiceException {
		logger.debug("findProductGiftPage(FindProductGiftPage findProductGiftPage={}) - start", findProductGiftPage);

		AssertUtils.notNull(findProductGiftPage);
		List<ProductGiftDto> returnList = null;
		int count = 0;
		try {
			count = productGiftDao.findProductGiftPageCount(findProductGiftPage);
			if (count > 0) {
				returnList = productGiftDao.findProductGiftPage(findProductGiftPage);
			}
		} catch (Exception e) {
			logger.error("赠品礼包信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.PRODUCT_GIFT_FIND_PAGE_ERROR, "赠品礼包信息不存在错误.！", e);
		}
		Page<ProductGiftDto> returnPage = new Page<ProductGiftDto>(returnList, count, findProductGiftPage);

		logger.debug("findProductGiftPage(FindProductGiftPage) - end - return value={}", returnPage);
		return returnPage;
	}

}
