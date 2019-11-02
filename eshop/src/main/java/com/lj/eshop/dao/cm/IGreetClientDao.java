package com.lj.eshop.dao.cm;

import java.util.List;

import com.lj.eshop.domain.cm.GreetClient;
import com.lj.eshop.dto.cm.FindGreetClientForDayDto;
import com.lj.eshop.dto.cm.FindGreetClientPage;
import com.lj.eshop.dto.cm.FindGreetClientPageReturn;
import com.lj.eshop.dto.cm.FindGreetClientReturnDto;

/**
 * 
 * 
 * 类说明：问候客户表
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 
 * @author 罗书明
 *   
 * CreateDate: 2017年6月21日
 */
public interface IGreetClientDao {
	/**
	 * 
	 *
	 * 方法说明：
	 * 根据主键删除
	 * @param code
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年6月21日
	 *
	 */
    int deleteByPrimaryKey(String code);
    /**
     * 
     *
     * 方法说明：
     * 新增方法
     * @param record
     * @return
     *
     * @author 罗书明 CreateDate: 2017年6月21日
     *
     */
    int insert(GreetClient record);
    /**
     * 
     *
     * 方法说明：
     * 根据所选参数新增
     * @param record
     * @return
     *
     * @author 罗书明 CreateDate: 2017年6月21日
     *
     */
    int insertSelective(GreetClient record);
    /**
     * 
     *
     * 方法说明：
     * 查询方法
     * @param code
     * @return
     *
     * @author 罗书明 CreateDate: 2017年6月21日
     *
     */
    GreetClient selectByPrimaryKey(String code);
    /**
     * 
     *
     * 方法说明：
     * 更新修改方法
     * @param record
     * @return
     *
     * @author 罗书明 CreateDate: 2017年6月21日
     *
     */
    int updateByPrimaryKeySelective(GreetClient record);
   /**
    * 
    *
    * 方法说明：
    * 更新修改方法
    * @param record
    * @return
    *
    * @author 罗书明 CreateDate: 2017年6月21日
    *
    */
    int updateByPrimaryKey(GreetClient record);
     /**
      * 
      *
      * 方法说明：
      * 分页查询
      * @param findGreetClientPage
      * @return
      *
      * @author 罗书明 CreateDate: 2017年6月21日
      *
      */
	List<FindGreetClientPageReturn> findGreetClientPage(
			FindGreetClientPage findGreetClientPage);
     /**
      * 
      *
      * 方法说明：
      * 查询条目数
      * @param findGreetClientPage
      * @return
      *
      * @author 罗书明 CreateDate: 2017年6月21日
      *
      */
	int findGreetClientPageCount(FindGreetClientPage findGreetClientPage);
	
	/**
	 * 
	 *
	 * 方法说明：通过导购编号和时间查询当日的问候(今日工作)
	 *
	 * @param findGreetClientDto
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年7月20日
	 *
	 */
	
	FindGreetClientReturnDto findGreetClientForDay(FindGreetClientForDayDto findGreetClientForDayDto);
}