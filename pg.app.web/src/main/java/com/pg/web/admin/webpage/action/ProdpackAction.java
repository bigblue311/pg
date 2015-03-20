package com.pg.web.admin.webpage.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.pg.biz.manager.ProductManager;
import com.pg.dal.model.ProdPackDO;
import com.pg.web.admin.model.form.ProdPacksFO;

public class ProdpackAction {
	
	@Autowired
	private ProductManager productManager;
	
	public void doUpdate(@FormGroup("prodpack") ProdPacksFO prodPacksFO, Navigator nav){
		List<ProdPackDO> list = prodPacksFO.getDO();
		for(ProdPackDO prodPackDO : list){
			productManager.createProdPack(prodPackDO);
		}
		nav.redirectTo("admin").withTarget("prodpack.vm").withParameter("packageId", prodPacksFO.getPackageId().toString());
	}
	
	public void doDelete(@FormGroup("prodpack") ProdPacksFO prodPacksFO, Navigator nav){
		List<ProdPackDO> list = prodPacksFO.getDO();
		for(ProdPackDO prodPackDO : list){
			productManager.deleteProdPack(prodPackDO);
		}
		nav.redirectTo("admin").withTarget("prodpack.vm").withParameter("packageId", prodPacksFO.getPackageId().toString());
	}
}