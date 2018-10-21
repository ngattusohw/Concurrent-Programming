-module(interp).
-export([scanAndParse/1,runFile/1,runStr/1,scanAndParseString/1,valueOf/2]).
-include("types.hrl").

loop(InFile,Acc) ->
    case io:request(InFile,{get_until,prompt,lexer,token,[1]}) of
        {ok,Token,_EndLine} ->
            loop(InFile,Acc ++ [Token]);
        {error,token} ->
            exit(scanning_error);
        {eof,_} ->
            Acc
    end.

scanAndParse(FileName) ->
    {ok, InFile} = file:open(FileName, [read]),
    Acc = loop(InFile,[]),
    file:close(InFile),
    {Result, AST} = parser:parse(Acc),
    case Result of 
	ok -> AST;
	_ -> io:format("Parse error~n")
    end.


-spec runFile(string()) -> valType().
runFile(FileName) ->
    valueOf(scanAndParse(FileName),env:new()).

scanAndParseString(String) ->
    {_ResultL, TKs, _L} = lexer:string(String),
    parser:parse(TKs).

-spec runStr(string()) -> valType().
runStr(String) ->
    {Result, AST} = scanAndParseString(String),
    case Result  of 
    	ok -> valueOf(AST,env:new());
    	_ -> io:format("Parse error~n")
    end.


-spec numVal2Num(numValType()) -> integer().
numVal2Num({num, N}) ->
    N.

-spec boolVal2Bool(boolValType()) -> boolean().
boolVal2Bool({bool, B}) ->
    B.

-spec valueOf(expType(),envType()) -> valType().
valueOf(Exp,Env) ->
    case Exp of
        {letExp, {id, _, Id}, Val, Body} ->
            valueOf(Body,env:add(Env,Id,valueOf(Val,Env)));
        {idExp, {id, _, Id}} -> 
            env:lookup(Env,Id);
        {numExp, {num, _, Val}} ->
            {num, Val};
        {plusExp, A, B} ->
            {num, numVal2Num(valueOf(A,Env)) + numVal2Num(valueOf(B,Env))};
        {diffExp, A, B} ->
            {num, numVal2Num(valueOf(A,Env)) - numVal2Num(valueOf(B,Env))};
        {isZeroExp, E} ->
            case numVal2Num(valueOf(E,Env)) of
                0 -> {bool, true};
                _ -> {bool, false}
            end;
        {ifThenElseExp, Cond, T, F} ->
            case boolVal2Bool(valueOf(Cond, Env)) of
                true -> valueOf(T, Env);
                false -> valueOf(F, Env)
            end;
        {procExp, {id, _, IdVal}, Body} ->
            {proc, IdVal, Body, Env};
        {appExp, Rator, Rand} ->
            Operator = valueOf(Rator, Env),
            Operand = valueOf(Rand, Env),
            case Operator of 
                {proc, ProcArg, Body, ProcEnv} ->
                    valueOf(Body,env:add(ProcEnv, ProcArg, Operand));
                _ -> error
            end;

        _Else -> Exp
    end.
    %% complete
