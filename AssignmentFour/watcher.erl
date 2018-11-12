-module(watcher).
-compile(export_all).
-author("Nick Gattuso").

make_sensors(List, NewId, N) when NewId==N ->
	io:fwrite("Watcher starting: ~w ~n", [List]),
	process_flag(trap_exit, true),
	watch(List);
make_sensors(List, NewId, N) when NewId<N ->
	{Pid, _} = spawn_monitor(sensor, the_sensor, [self(),NewId]),
	TempList =  [{NewId, Pid} | List],
	make_sensors(TempList, NewId+1, N).

watch(List) ->
	receive
		{ID, Measurement} ->
			io:fwrite("Sensor Number: ~w gave Measurement Num: ~w ~n", [ID,Measurement]),
			watch(List);
		{'DOWN', _, process, Pid, Reason} ->
			{ID, _} = lists:keyfind(Pid, 2, List),
			io:fwrite("Sensor Number:: ~w has crashed with ~w ~n", [ID,Reason]),
			NewList = lists:delete({ID, Pid}, List),
			{NewPid, _} = spawn_monitor(sensor, the_sensor, [self(),ID]),
			FinalList = [{ID, NewPid} | NewList],
			io:fwrite("Replacing Sensor ~w with replacement sensor: ~w ~n", [ID,FinalList]),
			watch(FinalList)
	end.
