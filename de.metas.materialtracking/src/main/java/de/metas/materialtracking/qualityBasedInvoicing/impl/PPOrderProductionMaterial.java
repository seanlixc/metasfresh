package de.metas.materialtracking.qualityBasedInvoicing.impl;

/*
 * #%L
 * de.metas.materialtracking
 * %%
 * Copyright (C) 2015 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */


import java.math.BigDecimal;

import org.adempiere.model.IModelWrapper;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.Check;
import org.adempiere.util.Services;
import org.adempiere.util.lang.ObjectUtils;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Product;

import de.metas.materialtracking.IHandlingUnitsInfo;
import de.metas.materialtracking.model.I_PP_Order;
import de.metas.materialtracking.qualityBasedInvoicing.ProductionMaterialType;
import de.metas.materialtracking.spi.IHandlingUnitsInfoFactory;

/* package */class PPOrderProductionMaterial extends AbstractProductionMaterial implements IModelWrapper
{
	// services
	private final IHandlingUnitsInfoFactory handlingUnitsInfoFactory = Services.get(IHandlingUnitsInfoFactory.class);

	private final I_PP_Order ppOrder;

	private IHandlingUnitsInfo _handlingUnitsInfo;
	private boolean _handlingUnitsInfoLoaded = false;

	public PPOrderProductionMaterial(final org.eevolution.model.I_PP_Order ppOrder)
	{
		Check.assumeNotNull(ppOrder, "ppOrder not null");
		this.ppOrder = InterfaceWrapperHelper.create(ppOrder, I_PP_Order.class);

		Check.assumeNotNull(this.getC_UOM(), "getC_UOM() does not return null for {}", this);
		Check.assumeNotNull(this.getM_Product(), "getM_Product() does not return null for {}", this);
	}

	@Override
	public I_M_Product getM_Product()
	{
		return ppOrder.getM_Product();
	}

	@Override
	public BigDecimal getQty()
	{
		final BigDecimal qtyDelivered = ppOrder.getQtyDelivered();
		return qtyDelivered;
	}

	@Override
	public I_C_UOM getC_UOM()
	{
		return ppOrder.getC_UOM();
	}

	@Override
	public ProductionMaterialType getType()
	{
		return ProductionMaterialType.PRODUCED;
	}

	@Override
	public void setQM_QtyDeliveredPercOfRaw(final BigDecimal qtyDeliveredPercOfRaw)
	{
		ppOrder.setQM_QtyDeliveredPercOfRaw(qtyDeliveredPercOfRaw);
	}

	@Override
	public BigDecimal getQM_QtyDeliveredPercOfRaw()
	{
		return ppOrder.getQM_QtyDeliveredPercOfRaw();
	}

	@Override
	public void setQM_QtyDeliveredAvg(final BigDecimal qtyDeliveredAvg)
	{
		ppOrder.setQM_QtyDeliveredAvg(qtyDeliveredAvg);
	}

	@Override
	public BigDecimal getQM_QtyDeliveredAvg()
	{
		return ppOrder.getQM_QtyDeliveredAvg();
	}

	@Override
	public Object getModel()
	{
		return ppOrder;
	}

	@Override
	public String getVariantGroup()
	{
		return null;
	}

	@Override
	public String getComponentType()
	{
		return null;
	}

	@Override
	public IHandlingUnitsInfo getHandlingUnitsInfo()
	{
		if (!_handlingUnitsInfoLoaded)
		{
			_handlingUnitsInfo = handlingUnitsInfoFactory.createFromModel(ppOrder);
			_handlingUnitsInfoLoaded = true;
		}
		return _handlingUnitsInfo;
	}

	@Override
	public I_M_Product getMainComponentProduct()
	{
		// there is no substitute for a produced material
		return null;
	}

	@Override
	public String toString()
	{
		return ObjectUtils.toString(this);
	}
}
