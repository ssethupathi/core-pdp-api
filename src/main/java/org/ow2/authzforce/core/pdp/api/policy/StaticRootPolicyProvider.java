/**
 * Copyright 2012-2018 Thales Services SAS.
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
package org.ow2.authzforce.core.pdp.api.policy;

import org.ow2.authzforce.core.pdp.api.EvaluationContext;

/**
 * {@link RootPolicyProvider} that resolves statically the root policy when it is initialized, i.e. it is context-independent. Concretely, this means for any given context:
 * 
 * <pre>
 * this.findPolicy(context) == this.findPolicy(null)
 * </pre>
 */
public interface StaticRootPolicyProvider extends RootPolicyProvider
{
	/**
	 * Get the statically defined root policy (any nested policy reference is statically resolved)
	 * 
	 * @return root policy
	 */
	StaticTopLevelPolicyElementEvaluator getPolicy();

	@Override
	default TopLevelPolicyElementEvaluator getPolicy(final EvaluationContext context)
	{
		return getPolicy();
	}
}