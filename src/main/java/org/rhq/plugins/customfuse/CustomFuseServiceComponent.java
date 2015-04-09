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

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.rhq.core.domain.configuration.Configuration;
import org.rhq.core.domain.configuration.ConfigurationUpdateStatus;
import org.rhq.core.domain.configuration.PropertySimple;
import org.rhq.core.domain.configuration.definition.ConfigurationDefinition;
import org.rhq.core.domain.configuration.definition.PropertyDefinitionSimple;
import org.rhq.core.pluginapi.configuration.ConfigurationUpdateReport;
import org.rhq.core.util.exception.ThrowableUtil;
import org.rhq.plugins.jmx.JMXComponent;
import org.rhq.plugins.jmx.MBeanResourceComponent;

@SuppressWarnings({ "deprecation" })
public class CustomFuseServiceComponent extends MBeanResourceComponent<JMXComponent<?>> {

    private static final Log log = LogFactory.getLog(CustomFuseServiceComponent.class);

    @Override
    public void updateResourceConfiguration(ConfigurationUpdateReport report) {
        //Update JMX properties
        super.updateResourceConfiguration(report);

        if (report.getStatus() == ConfigurationUpdateStatus.SUCCESS) {
            //Propagate the JMX configuration updates to HTTPD
            try {
                super.invokeOperation("refresh", new Configuration());
            } catch (Exception e) {
                report.setErrorMessage("Failed to save the resource configuration to file. " + e.getMessage());
            }
        }
    }

    @Override
    protected Object getPropertyValueAsType(PropertySimple propSimple, String typeName) {
        if (typeName.equals(TimeUnit.class.getName())) {
            return TimeUnit.valueOf(propSimple.getStringValue());
        }

        return super.getPropertyValueAsType(propSimple, typeName);
    }
}
