-module( gg ).
-compile ( export_all ).

start () ->
	spawn( fun server/0).

server (N) ->
	receive
		{From,Ref,start} ->
			the_rand = rand:uniform(10),
			From ! {started,RightNum},
			server();
		{Pid,Ref,Number, RightNum} ->
			if
				RightNum = Number ->
					io:fwrite("got it"),
					ok;
				true ->
					io:fwrite("try again"),
					From ! {again,RightNum},
					server()
			end;
		stop -> true
	end.

client (S) ->
	Ref = make_ref(),
	S ! {self(),Ref,start},
	receive
		{started,RightNum} ->
			clientGuesser(S,RightNum);
	end.

clientGuesser(S,RightNum) ->
	TheGuess = rand:uniform(10),
	S ! {self(),Ref,TheGuess,RightNum},
	receive
		{again,RightNum} ->
			clientGuesser(S,RightNum);
	end.

