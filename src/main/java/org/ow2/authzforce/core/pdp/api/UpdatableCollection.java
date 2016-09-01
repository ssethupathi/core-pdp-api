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
package org.ow2.authzforce.core.pdp.api;

import java.util.Collection;

import com.google.common.collect.ImmutableCollection;

/**
 * Updatable collection; "updatable" means elements can only be added to the
 * collection (no removal, no clear)
 * 
 * @param <E>
 *            the type of elements in this collection
 */
public interface UpdatableCollection<E>
{
	/**
	 * Append element to the collection
	 * 
	 * @param e
	 *            new element to append
	 * @return true iff the list changed as a result of the call
	 */
	boolean add(E e);

	/**
	 * Appends all the elements in the specified collection to this collection
	 * 
	 * @param c
	 *            list containing elements to be added to this list
	 * @return true iff the list changed as a result of the call
	 */
	boolean addAll(Collection<? extends E> c);

	/**
	 * Creates an immutable copy with all the elements currently in this
	 * collection
	 * 
	 * @return immutable copy of the collection
	 */
	ImmutableCollection<E> copy();
}
