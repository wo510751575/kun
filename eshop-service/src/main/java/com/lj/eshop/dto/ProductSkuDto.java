package com.lj.eshop.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductSkuDto implements Serializable {

	/** */
	private String code;

	/** 库存 */
	private Integer cnt;

	/** 商品编码 */
	private String productCode;

	/** 规格组合集合，按规格类型排序，以英文逗号分隔 */
	private String productSpes;

	/** 商品sku组合编码 */
	private String skuNo;

	/** 是否删除 */
	private String delFlag;

	/** 出厂价格 */
	private BigDecimal costPrice;

	/** C端售价 */
	private BigDecimal salePrice;

	/** 小B售价 */
	private BigDecimal orgPrice;

	/** B端和C端的差价 */
	private BigDecimal gapPrice;

	/** 市场价 */
	private BigDecimal price;

	/** 0:默认显示的规格 1：非默认 */
	private String isDefault;

	/** sku描述 */
	private String skuDesc;
	/** 排序 */
	private Integer orderNo;
	/** 商品特权价 */
	private List<ProductRankPriceDto> rankPriceDtos = new ArrayList<ProductRankPriceDto>();
	/** 特权编号 */
	private String rankCode;
	/** 一级佣金 */
	private BigDecimal onePrice;
	/** 二级佣金 */
	private BigDecimal twoPrice;

	public BigDecimal getOnePrice() {
		return onePrice;
	}

	public void setOnePrice(BigDecimal onePrice) {
		this.onePrice = onePrice;
	}

	public BigDecimal getTwoPrice() {
		return twoPrice;
	}

	public void setTwoPrice(BigDecimal twoPrice) {
		this.twoPrice = twoPrice;
	}

	public String getRankCode() {
		return rankCode;
	}

	public void setRankCode(String rankCode) {
		this.rankCode = rankCode;
	}

	public List<ProductRankPriceDto> getRankPriceDtos() {
		return rankPriceDtos;
	}

	public void setRankPriceDtos(List<ProductRankPriceDto> rankPriceDtos) {
		this.rankPriceDtos = rankPriceDtos;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode == null ? null : productCode.trim();
	}

	public String getProductSpes() {
		return productSpes;
	}

	public void setProductSpes(String productSpes) {
		this.productSpes = productSpes == null ? null : productSpes.trim();
	}

	public String getSkuNo() {
		return skuNo;
	}

	public void setSkuNo(String skuNo) {
		this.skuNo = skuNo == null ? null : skuNo.trim();
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag == null ? null : delFlag.trim();
	}

	public BigDecimal getCostPrice() {
		if (costPrice != null) {
			costPrice = costPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public BigDecimal getSalePrice() {
		if (salePrice != null) {
			salePrice = salePrice.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getOrgPrice() {
		if (orgPrice != null) {
			orgPrice = orgPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return orgPrice;
	}

	public void setOrgPrice(BigDecimal orgPrice) {
		this.orgPrice = orgPrice;
	}

	public BigDecimal getGapPrice() {
		if (gapPrice != null) {
			gapPrice = gapPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return gapPrice;
	}

	public void setGapPrice(BigDecimal gapPrice) {
		this.gapPrice = gapPrice;
	}

	public BigDecimal getPrice() {
		if (price != null) {
			price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault == null ? null : isDefault.trim();
	}

	public String getSkuDesc() {
		return skuDesc;
	}

	public void setSkuDesc(String skuDesc) {
		this.skuDesc = skuDesc == null ? null : skuDesc.trim();
	}

	/**
	 * @return the orderNo
	 */
	public Integer getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "ProductSkuDto [code=" + code + ", cnt=" + cnt + ", productCode=" + productCode + ", productSpes="
				+ productSpes + ", skuNo=" + skuNo + ", delFlag=" + delFlag + ", costPrice=" + costPrice
				+ ", salePrice=" + salePrice + ", orgPrice=" + orgPrice + ", gapPrice=" + gapPrice + ", price=" + price
				+ ", isDefault=" + isDefault + ", skuDesc=" + skuDesc + ", orderNo=" + orderNo + ", rankPriceDtos="
				+ rankPriceDtos + ", rankCode=" + rankCode + ", onePrice=" + onePrice + ", twoPrice=" + twoPrice + "]";
	}

}
