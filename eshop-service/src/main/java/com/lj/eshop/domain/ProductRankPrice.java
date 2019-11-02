package com.lj.eshop.domain;

import java.math.BigDecimal;

public class ProductRankPrice {

	/**
	 * 商品编号（fk：t_product：code） .
	 */
	private String productCode;

	/**
	 * 特权编号(fk:t_member_rank.code) .
	 */
	private String rankCode;

	/**
	 * sku编号（fk：t_product_sku：code） .
	 */
	private String skuCode;

	/**
	 * 特权价格1 .
	 */
	private BigDecimal rankPrice;

	/**
	 * 商品编号（fk：t_product：code） .
	 *
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * 商品编号（fk：t_product：code） .
	 *
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode == null ? null : productCode.trim();
	}

	/**
	 * 特权编号(fk:t_member_rank.code) .
	 *
	 */
	public String getRankCode() {
		return rankCode;
	}

	/**
	 * 特权编号(fk:t_member_rank.code) .
	 *
	 */
	public void setRankCode(String rankCode) {
		this.rankCode = rankCode == null ? null : rankCode.trim();
	}

	/**
	 * sku编号（fk：t_product_sku：code） .
	 *
	 */
	public String getSkuCode() {
		return skuCode;
	}

	/**
	 * sku编号（fk：t_product_sku：code） .
	 *
	 */
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode == null ? null : skuCode.trim();
	}

	/**
	 * 特权价格1 .
	 *
	 */
	public BigDecimal getRankPrice() {
		return rankPrice;
	}

	/**
	 * 特权价格1 .
	 *
	 */
	public void setRankPrice(BigDecimal rankPrice) {
		this.rankPrice = rankPrice;
	}

	/**
	 * 输出BEAN数据信息
	 * 
	 * @author LeoPeng
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductRankPrice [");
		builder.append(",productCode=").append(productCode);
		builder.append(",rankCode=").append(rankCode);
		builder.append(",skuCode=").append(skuCode);
		builder.append(",rankPrice=").append(rankPrice);
		builder.append("]");
		return builder.toString();
	}
}