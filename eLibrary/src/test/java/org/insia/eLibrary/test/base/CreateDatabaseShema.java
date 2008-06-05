package org.insia.eLibrary.test.base;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CreateDatabaseShema {

	public static void main(String[] args) {
		//recree le schema de la base de donnee en rechargeant le
		//contexte de Spring avec celui d'hibernate
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
        		"ApplicationContext.xml",
        		"ApplicationContextDao.xml"
        });
	}

}
