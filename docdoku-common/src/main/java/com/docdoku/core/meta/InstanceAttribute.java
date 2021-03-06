/*
 * DocDoku, Professional Open Source
 * Copyright 2006 - 2013 DocDoku SARL
 *
 * This file is part of DocDokuPLM.
 *
 * DocDokuPLM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DocDokuPLM is distributed in the hope that it will be useful,  
 * but WITHOUT ANY WARRANTY; without even the implied warranty of  
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the  
 * GNU Affero General Public License for more details.  
 *  
 * You should have received a copy of the GNU Affero General Public License  
 * along with DocDokuPLM.  If not, see <http://www.gnu.org/licenses/>.  
 */

package com.docdoku.core.meta;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Base class for all instance attributes.  
 * 
 * @author Florent Garin
 * @version 1.0, 02/06/08
 * @since   V1.0
 */

@Table(name="INSTANCEATTRIBUTE")
@XmlSeeAlso({InstanceTextAttribute.class, InstanceNumberAttribute.class, InstanceDateAttribute.class, InstanceBooleanAttribute.class, InstanceURLAttribute.class})
@Inheritance()
@Entity
public abstract class InstanceAttribute implements Serializable, Cloneable {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private int id;

    protected String name = "";

    public InstanceAttribute() {
    }

    public InstanceAttribute(String pName) {
        name = pName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object pObj) {
        if (this == pObj) {
            return true;
        }
        if (!(pObj instanceof InstanceAttribute)) {
            return false;
        }
        InstanceAttribute attribute = (InstanceAttribute) pObj;
        return attribute.id == id;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public InstanceAttribute clone() {
        InstanceAttribute clone = null;
        try {
            clone = (InstanceAttribute) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
        return clone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public abstract Object getValue();

    public abstract boolean setValue(Object pValue);

    public boolean isValueEquals(Object pValue) {
        Object value = getValue();
        return value == null ? false : value.equals(pValue);
    }
}
