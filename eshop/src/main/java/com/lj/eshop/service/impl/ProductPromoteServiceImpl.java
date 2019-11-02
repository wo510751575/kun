package com.lj.eshop.service.impl;

/**
 * Copyright &copy; 2018-2021  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;

import com.lj.eshop.dto.ProductPromoteDto;
import com.lj.eshop.dto.FindProductPromotePage;
import com.lj.eshop.constant.ErrorCode;
import com.lj.eshop.dao.IProductPromoteDao;
import com.lj.eshop.domain.ProductPromote;
import com.lj.eshop.service.IProductPromoteService;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 * CreateDate: 2017.12.14
 */
@Service
public class ProductPromoteServiceImpl implements IProductPromoteService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ProductPromoteServiceImpl.class);
	

	@Resource
	private IProductPromoteDao productPromoteDao;
	
	
	@Override
	public void addProductPromote(
			ProductPromoteDto productPromoteDto) throws TsfaServiceException {
		logger.debug("addProductPromote(AddProductPromote addProductPromote={}) - start", productPromoteDto); 

		AssertUtils.notNull(productPromoteDto);
		try {
			ProductPromote productPromote = new ProductPromote();
			//add数据录入
			productPromote.setCode(productPromoteDto.getCode());
			productPromote.setProductCode(productPromoteDto.getProductCode());
			productPromote.setOpenDate(productPromoteDto.getOpenDate());
			productPromote.setCloseDate(productPromoteDto.getCloseDate());
			productPromote.setStatus(productPromoteDto.getStatus());
			productPromote.setCreater(productPromoteDto.getCreater());
			productPromote.setCreateTime(productPromoteDto.getCreateTime());
			productPromote.setUpdater(productPromoteDto.getUpdater());
			productPromote.setUpdateTime(productPromoteDto.getUpdateTime());
			productPromoteDao.insertSelective(productPromote);
			logger.debug("addProductPromote(ProductPromoteDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增商品每日一抢信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PRODUCT_PROMOTE_ADD_ERROR,"新增商品每日一抢信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询商品每日一抢信息
	 *
	 * @param findProductPromotePage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017年12月14日
	 *
	 */
	@Override
	public List<ProductPromoteDto>  findProductPromotes(FindProductPromotePage findProductPromotePage)throws TsfaServiceException{
		AssertUtils.notNull(findProductPromotePage);
		List<ProductPromoteDto> returnList=null;
		try {
			returnList = productPromoteDao.findProductPromotes(findProductPromotePage);
		} catch (Exception e) {
			logger.error("查找商品每日一抢信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.PRODUCT_PROMOTE_NOT_EXIST_ERROR,"商品每日一抢信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateProductPromote(
			ProductPromoteDto productPromoteDto)
			throws TsfaServiceException {
		logger.debug("updateProductPromote(ProductPromoteDto productPromoteDto={}) - start", productPromoteDto); 

		AssertUtils.notNull(productPromoteDto);
		AssertUtils.notNullAndEmpty(productPromoteDto.getCode(),"Code不能为空");
		try {
			ProductPromote productPromote = new ProductPromote();
			//update数据录入
			productPromote.setCode(productPromoteDto.getCode());
			productPromote.setProductCode(productPromoteDto.getProductCode());
			productPromote.setOpenDate(productPromoteDto.getOpenDate());
			productPromote.setCloseDate(productPromoteDto.getCloseDate());
			productPromote.setStatus(productPromoteDto.getStatus());
			productPromote.setCreater(productPromoteDto.getCreater());
			productPromote.setCreateTime(productPromoteDto.getCreateTime());
			productPromote.setUpdater(productPromoteDto.getUpdater());
			productPromote.setUpdateTime(productPromoteDto.getUpdateTime());
			AssertUtils.notUpdateMoreThanOne(productPromoteDao.updateByPrimaryKeySelective(productPromote));
			logger.debug("updateProductPromote(ProductPromoteDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("商品每日一抢信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PRODUCT_PROMOTE_UPDATE_ERROR,"商品每日一抢信息更新信息错误！",e);
		}
	}

	

	@Override
	public ProductPromoteDto findProductPromote(
			ProductPromoteDto productPromoteDto) throws TsfaServiceException {
		logger.debug("findProductPromote(FindProductPromote findProductPromote={}) - start", productPromoteDto); 

		AssertUtils.notNull(productPromoteDto);
		AssertUtils.notAllNull(productPromoteDto.getCode(),"Code不能为空");
		try {
			ProductPromote productPromote = productPromoteDao.selectByPrimaryKey(productPromoteDto.getCode());
			if(productPromote == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.PRODUCT_PROMOTE_NOT_EXIST_ERROR,"商品每日一抢信息不存在");
			}
			ProductPromoteDto findProductPromoteReturn = new ProductPromoteDto(); 
			//find数据录入
			findProductPromoteReturn.setCode(productPromote.getCode());
			findProductPromoteReturn.setProductCode(productPromote.getProductCode());
			findProductPromoteReturn.setOpenDate(productPromote.getOpenDate());
			findProductPromoteReturn.setCloseDate(productPromote.getCloseDate());
			findProductPromoteReturn.setStatus(productPromote.getStatus());
			findProductPromoteReturn.setCreater(productPromote.getCreater());
			findProductPromoteReturn.setCreateTime(productPromote.getCreateTime());
			findProductPromoteReturn.setUpdater(productPromote.getUpdater());
			findProductPromoteReturn.setUpdateTime(productPromote.getUpdateTime());
			logger.debug("findProductPromote(ProductPromoteDto) - end - return value={}", findProductPromoteReturn); 
			return findProductPromoteReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找商品每日一抢信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.PRODUCT_PROMOTE_FIND_ERROR,"查找商品每日一抢信息信息错误！",e);
		}


	}

	
	@Override
	public Page<ProductPromoteDto> findProductPromotePage(
			FindProductPromotePage findProductPromotePage)
			throws TsfaServiceException {
		logger.debug("findProductPromotePage(FindProductPromotePage findProductPromotePage={}) - start", findProductPromotePage); 

		AssertUtils.notNull(findProductPromotePage);
		List<ProductPromoteDto> returnList=null;
		int count = 0;
		try {
			count = productPromoteDao.findProductPromotePageCount(findProductPromotePage);
			if (count > 0) {
				returnList = productPromoteDao.findProductPromotePage(findProductPromotePage);
			}
		}  catch (Exception e) {
			logger.error("商品每日一抢信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.PRODUCT_PROMOTE_FIND_PAGE_ERROR,"商品每日一抢信息不存在错误.！",e);
		}
		Page<ProductPromoteDto> returnPage = new Page<ProductPromoteDto>(returnList, count, findProductPromotePage);

		logger.debug("findProductPromotePage(FindProductPromotePage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
