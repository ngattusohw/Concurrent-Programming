-module(sensor).
-compile(export_all).
-author("Nick Gattuso").

the_sensor(Pid, ID) ->
	Sleep_time = rand:uniform(10000),
	timer:sleep(Sleep_time),

	Measurement = rand:uniform(11),
	if Measurement == 11 ->
		exit(anomalous_reading);
		true ->
			Pid ! {ID, Measurement},
			the_sensor(Pid, ID)
	end.
	
