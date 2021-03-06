import java_cup.runtime.*; // defines the Symbol class
// The generated scanner will return a Symbol for each token that it finds.
// A Symbol contains an Object field named value; that field will be of type
// TokenVal, defined below.
//
// A TokenVal object contains the line number on which the token occurs as
// well as the number of the character on that line that starts the token.
// Some tokens (literals and IDs) also include the value of the token.
class TokenVal {
  // fields
    int linenum;
    int charnum;
  // constructor
    TokenVal(int lnum, int cnum) {
        linenum = lnum;
        charnum = cnum;
    }
}
class IntLitTokenVal extends TokenVal {
  // new field: the value of the integer literal
    int intVal;
  // constructor
    IntLitTokenVal(int lnum, int cnum, int val) {
        super(lnum, cnum);
        intVal = val;
    }
}
class IdTokenVal extends TokenVal {
  // new field: the value of the identifier
    String idVal;
  // constructor
    IdTokenVal(int lnum, int cnum, String val) {
        super(lnum, cnum);
    idVal = val;
    }
}
class StrLitTokenVal extends TokenVal {
  // new field: the value of the string literal
    String strVal;
  // constructor
    StrLitTokenVal(int lnum, int cnum, String val) {
        super(lnum, cnum);
        strVal = val;
    }
}
// The following class is used to keep track of the character number at which
// the current token starts on its line.
class CharNum {
    static int num=1;
}


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NOT_ACCEPT,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NOT_ACCEPT,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"22:9,42,41,22:2,0,22:18,42,36,21,40,22:2,37,22,25,26,34,32,28,33,29,35,20:1" +
"0,22,27,30,39,31,22:32,18,22,19,1,13,8,14,11,19,17,4,19:2,3,19,5,2,15,19,9," +
"12,6,10,7,16,19:3,23,38,24,22:2,43:2")[0];

	private int yy_rmap[] = unpackFromString(1,86,
"0,1,2,3,4:8,5,6,7,8,4,9,10,11,4,12,4,13,4:10,13:4,14,13:7,15,16,17,18,19,20" +
",21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,13,36,37,38,39,40,41,42,43,44" +
",45,46,13,47,48,49,50,51,52")[0];

	private int yy_nxt[][] = unpackFromString(53,44,
"-1,1,79:2,47,79,63,81,79,64,79,65,82,79,83,84,85,79:3,2,3,4,5,6,7,8,9,10,11" +
",12,13,14,15,16,17,18,48,51,19,53,20,21,22,-1,79,66,79:15,67,79,67,-1:43,2," +
"-1:24,3:40,-1,3,-1:75,24,-1:8,25,-1:35,26,-1:7,27,-1:36,28,-1:44,29,-1:45,4" +
"6,-1:47,30,-1:43,33,-1:46,21,-1:2,79:17,67,79,67,-1:24,38:40,-1,38,-1:36,38" +
",-1:9,79:4,50,79:5,23,79:6,67,79,67,-1:60,31,-1:46,38,-1:4,79:5,34,79:11,67" +
",79,67,-1:61,32,-1:6,79:9,35,79:7,67,79,67,-1:63,49,-1:4,79:5,36,79:6,72,79" +
":4,67,79,67,-1:24,79:11,37,79:5,67,79,67,-1:24,79:2,39,79:14,67,79,67,-1:24" +
",79:7,40,79:9,67,79,67,-1:24,79:13,41,79:3,67,79,67,-1:24,79:5,42,79:11,67," +
"79,67,-1:24,79:13,43,79:3,67,79,67,-1:24,79:5,44,79:11,67,79,67,-1:24,79:13" +
",45,79:3,67,79,67,-1:24,79:8,52,79:8,67,79,67,-1:24,79:13,54,79:3,67,79,67," +
"-1:24,79:2,55,79:14,67,79,67,-1:24,79,56,79:15,67,79,67,-1:24,79:3,57,79:13" +
",67,79,67,-1:24,79:8,73,79:8,67,79,67,-1:24,79:11,58,79:5,67,79,67,-1:24,79" +
":3,74,79:13,67,79,67,-1:24,79:13,76,79:3,67,79,67,-1:24,79:9,77,79:7,67,79," +
"67,-1:24,79:4,59,79:12,67,79,67,-1:24,79:2,60,79:14,67,79,67,-1:24,79:3,78," +
"79:13,67,79,67,-1:24,79:12,61,79:4,67,79,67,-1:24,79:6,62,79:10,67,79,67,-1" +
":24,79:3,75,79:13,67,79,67,-1:24,79,68,79:15,67,79,67,-1:24,79:5,69,79:11,6" +
"7,79,67,-1:24,79:2,70,79:14,67,79,67,-1:24,79:8,71,79:8,67,79,67,-1:24,79:1" +
"6,80,67,79,67,-1:23");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

return new Symbol(sym.EOF);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -2:
						break;
					case 2:
						{ //Integer literal
            // NOTE: the following computation of the integer value does NOT
            //       check for overflow.  This must be changed.
            int val = (new Integer(yytext())).intValue();
            Symbol S = new Symbol(sym.INTLITERAL,
                             new IntLitTokenVal(yyline+1, CharNum.num, val));
            CharNum.num += yytext().length();
            return S;
          }
					case -3:
						break;
					case 3:
						{
            //String literals
            int symNum = sym.ID;
            String val = new String(yytext());
            StrLitTokenVal tokenVal = new StrLitTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.STRINGLITERAL, tokenVal);
            CharNum.num += val.length();
            return S;
            //END OF SPECIAL SEQUENCES
          }
					case -4:
						break;
					case 4:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            CharNum.num++;
          }
					case -5:
						break;
					case 5:
						{ 
            //START OF ONE/TWO-CHARACTER SYMBOLS
            int symNum = sym.LCURLY;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -6:
						break;
					case 6:
						{ 
            int symNum = sym.RCURLY;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -7:
						break;
					case 7:
						{ 
            int symNum = sym.LPAREN;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -8:
						break;
					case 8:
						{ 
            int symNum = sym.RPAREN;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -9:
						break;
					case 9:
						{ 
            int symNum = sym.SEMICOLON;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -10:
						break;
					case 10:
						{ 
            int symNum = sym.COMMA;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -11:
						break;
					case 11:
						{ 
            int symNum = sym.DOT;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -12:
						break;
					case 12:
						{ 
            int symNum = sym.LESS;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -13:
						break;
					case 13:
						{ 
            int symNum = sym.GREATER;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -14:
						break;
					case 14:
						{ 
            int symNum = sym.PLUS;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -15:
						break;
					case 15:
						{ 
            int symNum = sym.MINUS;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -16:
						break;
					case 16:
						{ 
            int symNum = sym.TIMES;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -17:
						break;
					case 17:
						{ 
            int symNum = sym.DIVIDE;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -18:
						break;
					case 18:
						{ 
            int symNum = sym.NOT;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -19:
						break;
					case 19:
						{ 
            int symNum = sym.ASSIGN;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
            //END OF ONE/TWO-CHARACTER SYMBOLS
          }
					case -20:
						break;
					case 20:
						{ CharNum.num = 1; }
					case -21:
						break;
					case 21:
						{ CharNum.num += yytext().length(); }
					case -22:
						break;
					case 22:
						
					case -23:
						break;
					case 23:
						{
            int symNum = sym.IF;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -24:
						break;
					case 24:
						{ 
            int symNum = sym.WRITE;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -25:
						break;
					case 25:
						{ 
            int symNum = sym.LESSEQ;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -26:
						break;
					case 26:
						{ 
            int symNum = sym.READ;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -27:
						break;
					case 27:
						{ 
            int symNum = sym.GREATEREQ;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -28:
						break;
					case 28:
						{
            int symNum = sym.PLUSPLUS;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -29:
						break;
					case 29:
						{ 
            int symNum = sym.MINUSMINUS;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -30:
						break;
					case 30:
						{ 
            int symNum = sym.NOTEQUALS;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -31:
						break;
					case 31:
						{ 
            int symNum = sym.AND;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -32:
						break;
					case 32:
						{
            int symNum = sym.OR;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -33:
						break;
					case 33:
						{ 
            int symNum = sym.EQUALS;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -34:
						break;
					case 34:
						{
            int symNum = sym.INT;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -35:
						break;
					case 35:
						{
            int symNum = sym.TRUE;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -36:
						break;
					case 36:
						{ 
            int symNum = sym.RETURN;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
            //END OF THE RESERVED WORDS
          }
					case -37:
						break;
					case 37:
						{
            int symNum = sym.FALSE;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -38:
						break;
					case 38:
						{
              //COMMENTS
              CharNum.num = 1;
            }
					case -39:
						break;
					case 39:
						{
            int symNum = sym.BOOL;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -40:
						break;
					case 40:
						{
            int symNum = sym.VOID;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -41:
						break;
					case 41:
						{
            int symNum = sym.ELSE;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -42:
						break;
					case 42:
						{
            int symNum = sym.PRINT;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -43:
						break;
					case 43:
						{
            int symNum = sym.WHILE;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -44:
						break;
					case 44:
						{
            int symNum = sym.STRUCT;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -45:
						break;
					case 45:
						{
            int symNum = sym.RECEIVE;
            Symbol S = new Symbol(symNum, new TokenVal(yyline+1, CharNum.num));
            CharNum.num += yytext().length();
            return S;
          }
					case -46:
						break;
					case 47:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -47:
						break;
					case 48:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            CharNum.num++;
          }
					case -48:
						break;
					case 50:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -49:
						break;
					case 51:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            CharNum.num++;
          }
					case -50:
						break;
					case 52:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -51:
						break;
					case 53:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "ignoring illegal character: " + yytext());
            CharNum.num++;
          }
					case -52:
						break;
					case 54:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -53:
						break;
					case 55:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -54:
						break;
					case 56:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -55:
						break;
					case 57:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -56:
						break;
					case 58:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -57:
						break;
					case 59:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -58:
						break;
					case 60:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -59:
						break;
					case 61:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -60:
						break;
					case 62:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -61:
						break;
					case 63:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -62:
						break;
					case 64:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -63:
						break;
					case 65:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -64:
						break;
					case 66:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -65:
						break;
					case 67:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -66:
						break;
					case 68:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -67:
						break;
					case 69:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -68:
						break;
					case 70:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -69:
						break;
					case 71:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -70:
						break;
					case 72:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -71:
						break;
					case 73:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -72:
						break;
					case 74:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -73:
						break;
					case 75:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -74:
						break;
					case 76:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -75:
						break;
					case 77:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -76:
						break;
					case 78:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -77:
						break;
					case 79:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -78:
						break;
					case 80:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -79:
						break;
					case 81:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -80:
						break;
					case 82:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -81:
						break;
					case 83:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -82:
						break;
					case 84:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -83:
						break;
					case 85:
						{
            //START OF SPECIAL SEQUENCES
            //Identifier
            int symNum = sym.ID;
            String val = new String(yytext());
            IdTokenVal tokenVal = new IdTokenVal(yyline+1, CharNum.num, val);
            Symbol S = new Symbol(sym.ID, tokenVal);
            CharNum.num += val.length();
            return S;
          }
					case -84:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
