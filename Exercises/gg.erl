-module( gg ).
-compile ( export_all ).


start () ->
	spawn( fun server /0).

server (N) ->
	recieve
		{From,Ref,start} ->
			From ! {self(), Ref},
			the_rand = rand:uniform(10),
			From ! started,
			server(the_rand);
		{Pid,Ref,Number} ->
			if
				N = Number ->
					io:fwrite("got it"),
					ok;
				true ->
					io:fwrite("try again"),
					From ! again,
					server(N)
			end;
		stop -> true
	end.

client (S) ->
	Ref = make_ref(),
	S ! {self(),Ref,start},
	receive
		{started} ->
			clientGuesser(S);
	end.

clientGuesser(S) ->
	TheGuess = rand:uniform(10),
	S ! {self(),Ref,TheGuess},
	receive
		{again} ->
			clientGuesser(S);
	end.

