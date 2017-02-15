package org.cirmmp.elfinder.util;

import org.cirmmp.elfinder.controller.executor.FsItemEx;
import org.cirmmp.elfinder.service.FsItem;
import org.cirmmp.elfinder.service.FsService;

import java.io.IOException;

public abstract class FsServiceUtils
{
	public static FsItemEx findItem(FsService fsService, String hash)
			throws IOException
	{
		FsItem fsi = fsService.fromHash(hash);
		if (fsi == null)
		{
			return null;
		}

		return new FsItemEx(fsi, fsService);
	}
}
