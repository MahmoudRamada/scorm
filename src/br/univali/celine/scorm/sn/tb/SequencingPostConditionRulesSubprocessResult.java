/**
 * CELINE SCORM
 *
 * Copyright 2014 Adilson Vahldick.
 * https://celine-scorm.googlecode.com/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.univali.celine.scorm.sn.tb;

import br.univali.celine.scorm.model.imsss.RuleAction;
import br.univali.celine.scorm.sn.model.SequencingRequest;
import br.univali.celine.scorm.sn.model.TerminationRequest;

public class SequencingPostConditionRulesSubprocessResult {

	private TerminationRequest terminationRequest;
	private SequencingRequest sequencingRequest;
	
	public SequencingPostConditionRulesSubprocessResult(RuleAction terminationRuleAction, RuleAction sequenceRuleAction) {
		
		this.terminationRequest = convertToTerminationRequest(terminationRuleAction);
		this.sequencingRequest = convertToSequencingRequest(sequenceRuleAction);
		
	}
	
	private SequencingRequest convertToSequencingRequest(RuleAction sequenceRuleAction) {
		if (sequenceRuleAction == null)
			return null;
			
		switch (sequenceRuleAction) {
			case retry:
				return SequencingRequest.RETRY;
				
			case actionContinue:
				return SequencingRequest.CONTINUE;
				
			case previous:
				return SequencingRequest.PREVIOUS;
	  		default:
				break;
				
		}
		
		return null;
	}

	private TerminationRequest convertToTerminationRequest(RuleAction terminationRuleAction) {
		if (terminationRuleAction == null)
			return null;
		
		switch (terminationRuleAction) {
			case exitParent : return TerminationRequest.EXITPARENT;
			case exitAll    : return TerminationRequest.EXITALL;
			default			: break;
		}
		return null;
	}

	public TerminationRequest getTerminationRequest() {
		return this.terminationRequest;
	}

	public SequencingRequest getSequencingRequest() {
		return this.sequencingRequest;
	}

}
