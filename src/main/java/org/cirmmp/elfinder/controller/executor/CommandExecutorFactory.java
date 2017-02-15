package org.cirmmp.elfinder.controller.executor;

public interface CommandExecutorFactory
{
	CommandExecutor get(String commandName);
}