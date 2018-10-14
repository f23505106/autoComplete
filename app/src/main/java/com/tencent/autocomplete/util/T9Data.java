package com.tencent.autocomplete.util;

public class T9Data {
    public String t9;
    public String t9Index;
    public String t9abbr;
    public T9Data(String pinyin){
        StringBuilder t9Builder = new StringBuilder();
        StringBuilder t9IndexBuilder = new StringBuilder();
        StringBuilder t9abbrBuilder = new StringBuilder();
        boolean isFistChar = true;
        int diff = 0;
        for(int i=0;i<pinyin.length();++i){
            char t = pinyin.charAt(i);
            if(t == ','){
                t9IndexBuilder.append(i-diff);
                isFistChar = true;
                diff++;
                continue;
            }
            char t9 = char2t9(t);
            t9Builder.append(t9);
            if(isFistChar){
                t9abbrBuilder.append(t9);
                isFistChar = false;
            }
        }
        t9 = t9Builder.toString();
        t9Index = t9IndexBuilder.toString();
        t9abbr = t9abbrBuilder.toString();
    }
    public static char char2t9(char c){
        if(c<'d')      { return '2';
        }else if(c<'g'){ return '3';
        }else if(c<'j'){ return '4';
        }else if(c<'m'){ return '5';
        }else if(c<'p'){ return '6';
        }else if(c<'t'){ return '7';
        }else if(c<'w'){ return '8';
        }else          { return '9';}
    }
}
