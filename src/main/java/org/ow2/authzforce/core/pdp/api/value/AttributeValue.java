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
package org.ow2.authzforce.core.pdp.api.value;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;

import oasis.names.tc.xacml._3_0.core.schema.wd_17.AttributeValueType;

import org.ow2.authzforce.core.pdp.api.HashCollections;
import org.w3c.dom.Element;

/**
 * The base type for all atomic/non-bag values used in a policy or request/response, this abstract class represents a value for a given attribute type. All the standard primitive datatypes defined in
 * the XACML specification extend this. If you want to provide a new datatype, extend {@link DatatypeFactory} to provide a factory for it. Following JAXB fields (inherited from
 * {@link AttributeValueType}) are made immutable by this class:
 * <ul>
 * <li>content (also accessible via {@link #getContent()} )</li>
 * <li>dataType (also accessible via {@link #getDataType()})</li>
 * <li>otherAttributes (accessible via {@link #getOtherAttributes()})</li>
 * </ul>
 * 
 */
public abstract class AttributeValue extends AttributeValueType implements AtomicValue
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * XML datatype factory for parsing XML-Schema-compliant date/time/duration values into Java types. DatatypeFactory's official javadoc does not say whether it is thread-safe. But bug report
	 * indicates it should be and has been so far: http://bugs.java.com/bugdatabase/view_bug.do?bug_id=6466177 Reusing the same instance matters for performance: https://www.java.net/node/666491 The
	 * alternative would be to use ThreadLocal to limit thread-safety issues in the future.
	 */
	protected static final DatatypeFactory XML_TEMPORAL_DATATYPE_FACTORY;
	static
	{
		try
		{
			XML_TEMPORAL_DATATYPE_FACTORY = DatatypeFactory.newInstance();
		}
		catch (final DatatypeConfigurationException e)
		{
			throw new RuntimeException("Error instantiating XML datatype factory for parsing strings corresponding to XML schema date/time/duration values into Java types", e);
		}
	}

	private static final UnsupportedOperationException UNSUPPORTED_SET_DATATYPE_OPERATION_EXCEPTION = new UnsupportedOperationException("AttributeValue.setDataType() not allowed");

	/*
	 * (non-Javadoc)
	 * 
	 * @see oasis.names.tc.xacml._3_0.core.schema.wd_17.AttributeValueType#setDataType(java.lang.String)
	 */
	@Override
	public final void setDataType(final String value)
	{
		// datatype only set with constructor (immutable)
		throw UNSUPPORTED_SET_DATATYPE_OPERATION_EXCEPTION;
	}

	/**
	 * Default constructor
	 * 
	 * @param datatypeId
	 *            datatype ID (non-null). Note for developers: Do not use the Datatype class here, because if we do, we break the acyclic dependency principle
	 * @param content
	 *            (non-null) list of JAXB content elements of the following types: {@link String}, {@link Element}. Made immutable by this constructor.
	 * @param otherAttributes
	 *            other attributes, made immutable by this constructor.
	 * @throw NullPointerException if {@code datatypeId == null || content == null}
	 */
	protected AttributeValue(final String datatypeId, final List<Serializable> content, final Optional<Map<QName, String>> otherAttributes) throws IllegalArgumentException
	{
		/*
		 * Make fields immutable (datatype made immutable through overriding setDatatype())
		 */
		super(Collections.unmodifiableList(Objects.requireNonNull(content, "Undefined content")), Objects.requireNonNull(datatypeId, "Undefined datatype ID"),
				otherAttributes.isPresent() ? HashCollections.newImmutableMap(otherAttributes.get()) : null);
	}

}
