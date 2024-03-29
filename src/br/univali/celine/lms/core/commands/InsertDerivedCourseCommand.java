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
package br.univali.celine.lms.core.commands;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.univali.celine.lms.config.LMSConfig;
import br.univali.celine.lms.core.Command;
import br.univali.celine.lms.core.HTMLBuilder;
import br.univali.celine.lms.core.LMSControl;
import br.univali.celine.lms.integration.EasyContentPackage;
import br.univali.celine.scorm.model.cam.ContentPackage;


public class InsertDerivedCourseCommand implements Command {

	
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String title = request.getParameter("title");
		String courseId = request.getParameter("courseId");
		
		LMSConfig lms = LMSConfig.getInstance();	
		EasyContentPackage easy = new EasyContentPackage();		
		easy.addContentPackage(lms.openContentPackageByFolderName("tudo"), "tudo");
		
		ContentPackage newCP = easy.build(title, courseId);
		LMSControl.getInstance().insertCourse(newCP);
								
		return HTMLBuilder.buildRedirect("selectaction.jsp");
	}
	
		
	
}
