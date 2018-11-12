#Ngattuso and David Sevilla
tOne() -> {node,44,{node,32,{empty},{empty}},{empty}}.


isComplete(btree) ->

	case btree of
		{empty} ->
			true;
		_Else ->
			isCompleteRecursive(queue.in(btree,queue:new()));

	end.

flag = 0.


isCompleteRecursive(Q) ->
	if
	condition ->
		queue:is_empty(Q);
	true ->
		true;
	else ->
		false;
	end.
	Tree = queue:out(Q)
	case Tree of
		{{value,N},{QueueN}} ->
			case N of 
				{node, n2, {empty}}} ->
								

