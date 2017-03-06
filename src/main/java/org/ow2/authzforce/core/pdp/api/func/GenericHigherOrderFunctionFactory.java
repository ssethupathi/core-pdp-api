/**
 * Copyright 2012-2017 Thales Services SAS.
 *
 * This file is part of AuthzForce CE.
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
package org.ow2.authzforce.core.pdp.api.func;

import org.ow2.authzforce.core.pdp.api.PdpExtension;
import org.ow2.authzforce.core.pdp.api.value.AttributeValue;
import org.ow2.authzforce.core.pdp.api.value.DatatypeFactory;

/**
 * Interface for generic higher-order function factories, such as the one used for the standard map function in Authzforce PDP engine. A generic function is a function class with a type parameter
 * depending on the sub-function's return type, e.g. the standard map function, therefore the function is instantiated for a specific sub-function's return type.
 *
 * 
 * @version $Id: $
 */
public abstract class GenericHigherOrderFunctionFactory implements PdpExtension
{

	/**
	 * Returns instance of the Higher-order function
	 *
	 * @param subFunctionReturnTypeFactory
	 *            sub-function's return datatype factory
	 * @return higher-order function instance (non-null, throw exceptions below if cannot return instance)
	 * @throws IllegalArgumentException
	 *             iff {@code subFunctionReturnTypeFactory == null}
	 * @throws UnsupportedOperationException
	 *             iff {@code subFunctionReturnTypeFactory} is not supported by this factory
	 * 
	 */
	public abstract <SUB_RETURN_T extends AttributeValue> HigherOrderBagFunction<?, SUB_RETURN_T> getInstance(DatatypeFactory<SUB_RETURN_T> subFunctionReturnTypeFactory)
			throws IllegalArgumentException, UnsupportedOperationException;

	/** {@inheritDoc} */
	@Override
	public String toString()
	{
		return this.getId();
	}

}
