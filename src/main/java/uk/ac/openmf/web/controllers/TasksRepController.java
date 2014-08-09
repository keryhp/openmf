package uk.ac.openmf.web.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.openmf.model.OpenMFUser;
import uk.ac.openmf.utils.OMFUtils;
import uk.ac.openmf.utils.OpenMFPDFGenerator;
import uk.ac.openmf.utils.ServletUtils;
import uk.ac.openmf.web.AppContext;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TasksRepController {
	
	@RequestMapping(value = "/reports/users", method= RequestMethod.GET)
    public String users(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		req.setAttribute("omfusers", OMFUtils.getUsersList());
		return "reports/users";
	}

	@RequestMapping(value = "/reports/taskspdf", method= RequestMethod.GET)
    public void taskspdf(HttpServletRequest req, HttpServletResponse response) throws IOException {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		String omfuId = req.getParameter("omfuId");
		req.setAttribute("omfuId", omfuId);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, baos);
			document.open();
			OpenMFPDFGenerator.addMetaData(document, "User Tasks Report " + omfuId, "User Tasks Report demo");
			OpenMFPDFGenerator.addTitlePage(document, "Demo Report - System genrated");
			OpenMFPDFGenerator.addTaskContent(document, "Demo Report - System genrated", omfuId);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHeader("Content-Disposition", "attachment; filename=\"tasksrep.pdf\"");
		response.setContentType("application/pdf");
		response.setContentLength(baos.size());
		ServletOutputStream outputStream = response.getOutputStream();
		baos.writeTo(outputStream);
		outputStream.flush();
		outputStream.close();
	}

	@RequestMapping(value = "/reports/tasksrep", method= RequestMethod.GET)
    public String tasksrep(HttpServletRequest req) {
		req.setAttribute("currentUser", AppContext.getAppContext().getCurrentUser());
		String omfuId = req.getParameter("omfuId");
		req.setAttribute("omfuId", omfuId);
		OpenMFUser omfuser = null;
		if (omfuId != null) {
			omfuser = AppContext.getAppContext().getUserManager().getUser(ServletUtils.validateEventId(omfuId));
		}
		req.setAttribute("omfuser", omfuser);
		req.setAttribute("tasks", OMFUtils.getTasksByUsername(omfuser.getUsername(), false));
        return "reports/tasksrep";
    }
}
