package com.lj.eshop.service.impl;

import java.util.Date;
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
import com.lj.eshop.dao.IProTextureDao;
import com.lj.eshop.domain.ProductTexture;
import com.lj.eshop.dto.FindProTexturePage;
import com.lj.eshop.dto.ProductTextureDto;
import com.lj.eshop.service.IProductTextureService;
@Service
public class ProductTextureServiceImpl implements IProductTextureService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ProductTextureServiceImpl.class);
	
	@Resource
	private IProTextureDao sysSpeDao;
	@Override
	public void addProductTexture(ProductTextureDto proTextureDto) throws TsfaServiceException {
		logger.debug("addSysSpe(AddProductTexture addProductTexture={}) - start", proTextureDto); 

		AssertUtils.notNull(proTextureDto);
		try {
			ProductTexture sysSpe = new ProductTexture();
			//add数据录入
			sysSpe.setCode(GUID.generateCode());
			sysSpe.setTextureName(proTextureDto.getTextureName());
			sysSpe.setCreater(proTextureDto.getCreater());
			sysSpe.setCreateTime(new Date());
			sysSpe.setStatus(proTextureDto.getStatus());
			sysSpeDao.insertSelective(sysSpe);
			logger.debug("addSysSpe(SysSpeDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增商品材质信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SYS_SPE_ADD_ERROR,"新增商品材质信息错误！",e);
		}

	}
	

	@Override
	public ProductTextureDto findProTexture(ProductTextureDto proTextureDto) throws TsfaServiceException {
		logger.debug("findSysSpe(FindSysSpe findSysSpe={}) - start", proTextureDto); //$NON-NLS-1$

		AssertUtils.notNull(proTextureDto);
		AssertUtils.notAllNull(proTextureDto.getCode(),"Code不能为空");
		try {
			ProductTexture pt = sysSpeDao.selectByPrimaryKey(proTextureDto.getCode()); 
			if(pt == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.SYS_SPE_NOT_EXIST_ERROR,"商品规格信息不存在");
			}
			ProductTextureDto findProReturn = new ProductTextureDto();
			//find数据录入
			findProReturn.setCode(pt.getCode());
			findProReturn.setTextureName(pt.getTextureName());
			findProReturn.setCreater(pt.getCreater());
			findProReturn.setCreateTime(pt.getCreateTime());
			findProReturn.setStatus(pt.getStatus());
			
			logger.debug("findSysSpe(SysSpeDto) - end - return value={}", findProReturn); //$NON-NLS-1$
			return findProReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找商品材质信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SYS_SPE_FIND_ERROR,"查找商品材质信息信息错误！",e);
		}
	}

	/**
	 * 
	 *
	 * 方法说明：不分页查询商品材质信息
	 *
	 * @param findProTextures
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2017年07月10日
	 *
	 */	
	@Override
	public List<ProductTextureDto> findProTextures(FindProTexturePage findProTexturePage) throws TsfaServiceException {
		AssertUtils.notNull(findProTexturePage);
		List<ProductTextureDto> returnList=null;
		try {
			returnList = sysSpeDao.findProTextures(findProTexturePage);
		} catch (Exception e) {
			logger.error("查找商品材质信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.SYS_SPE_NOT_EXIST_ERROR,"商品材质信息不存在");
		}
		return returnList;
	}

	@Override
	public void updateProTexture(ProductTextureDto sysSpeDto) throws TsfaServiceException {
		logger.debug("updateSysSpe(SysSpeDto sysSpeDto={}) - start", sysSpeDto); //$NON-NLS-1$

		AssertUtils.notNull(sysSpeDto);
		AssertUtils.notNullAndEmpty(sysSpeDto.getCode(),"Code不能为空");
		try {
			ProductTexture sysSpe = new ProductTexture();
			//update数据录入
			sysSpe.setCode(sysSpeDto.getCode());
			sysSpe.setTextureName(sysSpeDto.getTextureName());
			sysSpe.setCreater(sysSpeDto.getCreater());
			sysSpe.setCreateTime(sysSpeDto.getCreateTime());
			sysSpe.setStatus(sysSpeDto.getStatus());
			AssertUtils.notUpdateMoreThanOne(sysSpeDao.updateByPrimaryKeySelective(sysSpe));
			logger.debug("updateSysSpe(SysSpeDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("商品材质信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SYS_SPE_UPDATE_ERROR,"商品材质信息更新信息错误！",e);
		}
	}

	@Override
	public Page<ProductTextureDto> findProTexturePage(FindProTexturePage findProTexturePage)
			throws TsfaServiceException {
		logger.debug("findSysSpePage(FindSysSpePage findSysSpePage={}) - start", findProTexturePage); //$NON-NLS-1$

		AssertUtils.notNull(findProTexturePage);
		List<ProductTextureDto> returnList=null;
		int count = 0;
		try {
			returnList = sysSpeDao.findProTexturePage(findProTexturePage);
			count = sysSpeDao.findProTexturePageCount(findProTexturePage);
		}  catch (Exception e) {
			logger.error("商品规格信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.SYS_SPE_FIND_PAGE_ERROR,"商品规格信息不存在错误.！",e);
		}
		Page<ProductTextureDto> returnPage = new Page<ProductTextureDto>(returnList, count, findProTexturePage);

		logger.debug("findSysSpePage(FindSysSpePage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


}
