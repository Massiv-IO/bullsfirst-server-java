/**
 * Copyright 2011 Archfirst
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.archfirst.bfexch.domain.trading.order;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * OrderStatus
 *
 * @author Naresh Bhatia
 */
@XmlType(name = "OrderStatus")
@XmlEnum
public enum OrderStatus {
    @XmlEnumValue("PendingNew")
    PendingNew("PENDNEW", "Pending New"),
    @XmlEnumValue("New")
    New("NEW", "New"),
    @XmlEnumValue("PartiallyFilled")
    PartiallyFilled("PARTFILD", "Partially Filled"),
    @XmlEnumValue("Filled")
    Filled("FILLED", "Filled"),
    @XmlEnumValue("PendingCancel")
    PendingCancel("PENDCNCL", "Pending Cancel"),
    @XmlEnumValue("Canceled")
    Canceled("CNCLD", "Canceled"),
    @XmlEnumValue("DoneForDay")
    DoneForDay("DFD", "Done For Day");

    private final String identifier;
    private final String displayString;
    
    private static Map<String, OrderStatus> identifiers =
        new HashMap<String, OrderStatus>();
    
    static {
        for (OrderStatus type : EnumSet.allOf(OrderStatus.class)) {
            identifiers.put(type.toIdentifier(), type);
        }
    }
    
    private OrderStatus(String identifier, String displayString) {
        this.identifier = identifier;
        this.displayString = displayString;
    }
    
    public String toIdentifier() {
        return this.identifier;
    }
    
    public static final OrderStatus fromIdentifier(String identifier) {
        return identifiers.get(identifier);
    }

    public String getDisplayString() {
        return displayString;
    }
}