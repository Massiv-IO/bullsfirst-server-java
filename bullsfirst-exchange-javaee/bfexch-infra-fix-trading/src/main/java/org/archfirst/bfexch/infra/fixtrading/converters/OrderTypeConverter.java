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
package org.archfirst.bfexch.infra.fixtrading.converters;

import org.archfirst.bfexch.domain.trading.order.OrderType;

import quickfix.field.OrdType;

/**
 * OrderTypeConverter
 *
 * @author Naresh Bhatia
 */
public class OrderTypeConverter {

    private static FixConverter<OrderType, OrdType> fixConverter =
        new FixConverter<OrderType, OrdType>();

    static {
        fixConverter.put(OrderType.Market, new OrdType(OrdType.MARKET));
        fixConverter.put(OrderType.Limit, new OrdType(OrdType.LIMIT));
    }

    public static OrdType toFix(OrderType side) {
        return fixConverter.toFix(side);
    }

    public static OrderType toDomain(OrdType side) {
        return fixConverter.toDomain(side);
    }
}