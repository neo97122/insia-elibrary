package org.insia.eLibrary.services.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseManager {
	
	protected final Log logger;
	
	public BaseManager(){
		logger = LogFactory.getLog(this.getClass());
	}

}
