package com.pg.web.admin.model.form;

import java.util.List;

import com.google.common.collect.Lists;
import com.pg.dal.model.ProdPackDO;
import com.victor.framework.common.shared.Split;

public class ProdPacksFO {
	private Long packageId;
	private String productIds;
	private String quantitys;
	private String units;
	public Long getPackageId() {
		return packageId;
	}
	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}
	public String getProductIds() {
		return productIds;
	}
	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}
	public String getQuantitys() {
		return quantitys;
	}
	public void setQuantitys(String quantitys) {
		this.quantitys = quantitys;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	
	public List<ProdPackDO> getDO(){
		List<ProdPackDO> list = Lists.newArrayList();
		String[] productIdArray = productIds.split(Split.逗号);
		String[] quantityArray = quantitys.split(Split.逗号);
		String[] unitArray = units.split(Split.逗号);
		int min = productIdArray.length;
		if(min > quantityArray.length){
			min = quantityArray.length;
		}
		if(min > unitArray.length){
			min = unitArray.length;
		}
		for(int i=0;i<min;i++){
			try {
				Long productId = Long.parseLong(productIdArray[i]);
				int quantity = Integer.parseInt(quantityArray[i]);
				String unit = unitArray[i];
				ProdPackDO prodPackDO = new ProdPackDO();
				prodPackDO.setPackageId(packageId);
				prodPackDO.setProductId(productId);
				prodPackDO.setQuantity(quantity);
				prodPackDO.setUnit(unit);
				list.add(prodPackDO);
			} catch (Exception e) {
				// do nothing
			}
		}
		return list;
	}
}
