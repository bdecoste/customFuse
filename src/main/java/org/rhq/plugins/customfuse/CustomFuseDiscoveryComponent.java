/*
 * RHQ Management Platform
 * Copyright (C) 2005-2008 Red Hat, Inc.
 * All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package org.rhq.plugins.customfuse;

import java.util.Set;

import org.rhq.core.pluginapi.inventory.ResourceComponent;
import org.rhq.plugins.jmx.MBeanResourceDiscoveryComponent;
import org.rhq.core.pluginapi.inventory.DiscoveredResourceDetails;
import org.rhq.core.pluginapi.inventory.ResourceComponent;
import org.rhq.core.pluginapi.inventory.ResourceDiscoveryContext;

/**
 * @author Partha Aji
 */
public class CustomFuseDiscoveryComponent extends MBeanResourceDiscoveryComponent {
	

    @Override
    public Set<DiscoveredResourceDetails> discoverResources(ResourceDiscoveryContext context) {
 
        Set<DiscoveredResourceDetails> tempSet = super.discoverResources(context);

        for (DiscoveredResourceDetails detail : tempSet) {
            //detail.getPluginConfiguration().put(new PropertySimple(MOD_CLUSTER_CONFIG_FILE, configurationFile));
        }

        return tempSet;
    }

}
