package org.insia.mdms.tools;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class for Servlet: Upload
 *
 */
public class Upload extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{

	private static final long serialVersionUID = 1L;

	public Upload()
	{
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List items = null;
		try
		{
			items = upload.parseRequest(request);

		} catch (FileUploadException e)
		{
			e.printStackTrace();
		}

		Iterator itr = items.iterator();

		while(itr.hasNext()) {

			FileItem item = (FileItem) itr.next();

			if(item.isFormField())
			{
				String fieldName = item.getFieldName();

				if(fieldName.equals("name"))
				{
					request.setAttribute("msg", "Thank You: " + item.getString());
				}

			} else
			{
				File fullFile  = new File(item.getName());
				File savedFile = new File(getServletContext().getRealPath("../org.insia.mdmsCorp.eLibrary/org/insia/mdmsCorp/website/ressources/upload") ,  fullFile.getName());
				try
				{
					item.write(savedFile);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}