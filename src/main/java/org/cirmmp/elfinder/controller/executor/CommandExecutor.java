package org.cirmmp.elfinder.controller.executor;

public interface CommandExecutor
{
	void execute(CommandExecutionContext commandExecutionContext)
			throws Exception;
}
