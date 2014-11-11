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
package br.univali.celine.scorm.sn.nb;

import br.univali.celine.scorm.sn.model.ActivityTree;
import br.univali.celine.scorm.sn.model.NavigationRequest;
import br.univali.celine.scorm.sn.model.SequencingRequest;
import br.univali.celine.scorm.sn.model.TerminationRequest;

public class PreviousNavigationRequestCommand implements
		NavigationRequestCommand {

	public NavigationRequestResult run(ActivityTree activityTree, NavigationRequest navigationRequest) {
		NavigationRequestResult res = null;
		
		if (activityTree.getCurrentActivity() == null) {
			res = NavigationRequestResult.buildNotValid(2); // [NB.2.1-2]
		} else {
			if (activityTree.getCurrentActivity() != activityTree.getRootActivity()) {
				
				if (activityTree.getCurrentActivity().getParent().isSequencingControlFlow() == true &&
					activityTree.getCurrentActivity().getParent().isSequencingControlForwardOnly() == false) {
					
					res = NavigationRequestResult.buildValid(SequencingRequest.PREVIOUS);
					if (activityTree.getCurrentActivity().isActive() == true) {
						res.setTerminationRequest(TerminationRequest.EXIT);
					} 
					
				} else {
					res = NavigationRequestResult.buildNotValid(5); // [NB.2.1-5]
				}
				
			} else {
				res = NavigationRequestResult.buildNotValid(6); // [NB.2.1-6]
				
			}
		}
			
		
		return res;
	}

}
