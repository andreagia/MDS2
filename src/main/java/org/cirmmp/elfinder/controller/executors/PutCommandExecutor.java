package org.cirmmp.elfinder.controller.executors;

import org.cirmmp.elfinder.controller.executor.AbstractJsonCommandExecutor;
import org.cirmmp.elfinder.controller.executor.CommandExecutor;
import org.cirmmp.elfinder.controller.executor.FsItemEx;
import org.cirmmp.elfinder.service.FsService;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;

public class PutCommandExecutor extends AbstractJsonCommandExecutor implements
		CommandExecutor
{
	@Override
	public void execute(FsService fsService, HttpServletRequest request,
                        ServletContext servletContext, JSONObject json) throws Exception
	{
		String target = request.getParameter("target");

		FsItemEx fsi = super.findItem(fsService, target);
		fsi.writeStream(new ByteArrayInputStream(request
				.getParameter("content").getBytes("utf-8")));
		json.put("changed", new Object[] { super.getFsItemInfo(request, fsi) });
	}
}
