-module(main).
-author("Nick Gattuso").
-compile(export_all).
-import(watcher, [make_sensors/3]).
	
start() ->
    {ok, [ N ]} = io:fread("enter number of sensors> ", "~d") ,
    if N =< 1 ->
          io:fwrite("setup: range must be at least 2~n", []);
		true -> 
      		Num_Sensors = N,
        	setup_loop(N, 0, 10, Num_Sensors) 
	end.

setup_loop(N, Start, End, NumSensors) when N =< 10 ->
	spawn(watcher, make_sensors, [[], Start, NumSensors]);
setup_loop(N, Start, End, NumSensors) when N > 10 ->
	spawn(watcher, make_sensors, [[], Start, End]),
	setup_loop(N-10, Start+10, End+10, NumSensors).