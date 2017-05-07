package de.metas.material.event;

import java.time.Instant;
import java.util.Date;

import org.adempiere.util.lang.impl.TableRecordReference;
import org.eevolution.model.I_DD_Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/*
 * #%L
 * metasfresh-material-event
 * %%
 * Copyright (C) 2017 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */
/**
 * Send by the material planner when it came up with a distribution plan that could be turned into an {@link I_DD_Order}.
 * 
 * @author metas-dev <dev@metasfresh.com>
 */
@Data
@Builder
@AllArgsConstructor
public class DistributionPlanEvent implements MaterialEvent
{
	public static final String TYPE = "DistributionPlanEvent";

	@NonNull
	private final Instant when;

	@NonNull
	private final TableRecordReference reference;

	@NonNull
	private final Integer fromWarehouseId;

	@NonNull
	private final Date distributionStart;

	@NonNull
	private final MaterialDescriptor materialDescr;

}