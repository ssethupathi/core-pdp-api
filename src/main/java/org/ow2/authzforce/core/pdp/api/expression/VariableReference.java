/**
 * Copyright (C) 2012-2016 Thales Services SAS.
 *
 * This file is part of AuthZForce CE.
 *
 * AuthZForce CE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AuthZForce CE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AuthZForce CE.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.ow2.authzforce.core.pdp.api.expression;

import org.ow2.authzforce.core.pdp.api.value.Value;

/**
 * Variable reference, i.e. reference to a variable definition. Variables are simply Expressions identified by an ID (VariableId) and replace original XACML VariableReferences for actual evaluation.
 * 
 * @param <V>
 *            reference evaluation's return type
 */
public interface VariableReference<V extends Value> extends Expression<V>
{

	/**
	 * Returns referenced variable ID
	 * 
	 * @return referenced variable ID
	 */
	String getVariableId();

}