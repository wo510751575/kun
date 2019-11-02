package com.lj.eshop.dao.cm;

import java.util.List;

import com.lj.eshop.domain.cm.Bom;
import com.lj.eshop.dto.cm.EditBomDto;
import com.lj.eshop.dto.cm.FindBomPageDto;
/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：产品表接口类
 *   
 * @Company: 深圳扬恩科技有限公司
 * @author 邹磊
 *   
 * CreateDate: 2017年6月30日
 */
public interface IBomDao {
	/**
	 * 
	 *
	 * 方法说明：新增产品
	 *
	 * @param bom
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年6月30日
	 *
	 */
	int addBom(EditBomDto bom);
	/**
	 * 
	 *
	 * 方法说明：编辑产品
	 *
	 * @param bom
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年6月30日
	 *
	 */
	int editBom(EditBomDto bom);
	/**
	 * 
	 *
	 * 方法说明：根据主键查询产品
	 *
	 * @param code
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年6月30日
	 *
	 */
	Bom selectByCode(String code);
	/**
	 * 
	 *
	 * 方法说明：查询所有产品(不分页)
	 *
	 * @param FindExGuiderPageDto
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年6月30日
	 *
	 */
	List<FindBomPageDto> findBoms(FindBomPageDto findBomPageDto);
	/**
	 * 
	 *
	 * 方法说明：查询所有产品(分页)
	 *
	 * @param findBomPageDto
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年6月30日
	 *
	 */
	List<FindBomPageDto> findBomPage(FindBomPageDto findBomPageDto);
	/**
	 * 
	 *
	 * 方法说明：查询产品条数
	 *
	 * @param findBomPageDto
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年6月30日
	 *
	 */
	int findBomPageCount(FindBomPageDto findBomPageDto);
}