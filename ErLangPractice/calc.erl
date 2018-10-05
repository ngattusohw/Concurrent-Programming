~module(calc),
~compile(export_all), %You can also do expor([initEval/0,eval/2,anExpr/0]) "/#" means num args
~author("NG")


~type expr() :: {const,number()}
	      | {var,string()}
	      | {add,expr(),expr()}
	      | {sub,expr(),expr()}
	      | {mul,expr(),expr()}
	      | {divi,expr(),expr()}.


~type result() :: {const,number()}.

~spec anExpr() -> expr().

anExpr() ->
	{add,|
	   {sub,{const,3},{const,2}},
	   {var,"x"}}.

~spec initEnv -> dict:dict:(string(),result()).

initEnv() ->
	dict:append("x",{const,2},dict:append("y",{const,7},dict:new())).

~spec eval(dict:dict(string(),result()),expr() -> result().

eval(Env,{const,N}) ->
	{const,N};
eval(Env,{var,Id}) ->
	eval(Env, dict:fetch(Id,);
	error(not_implemented);
eval(Env,{add,E1,E2}) ->
	??;
eval(Env,{sub,E1,E2}) ->
	??;
eval(Env,{mul,E1,E2}) ->
	??;
eval(Env,{divi,E1,E2}) ->
	??;

