package advanced.combinatorial.backtracking.lc127_wordladder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find the length of shortest transformation sequence from beginWord to endWord, such that:
 *  1.Only one letter can be changed at a time
 *  2.Each intermediate word must exist in the word list
 * For example,
 * Given:
 *  beginWord = "hit"
 *  endWord = "cog"
 *  wordList = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
 * Note:
 *  Return 0 if there is no such transformation sequence.
 *  All words have the same length.
 *  All words contain only lowercase alphabetic characters.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().ladderLength("hit","cog",
                new HashSet<>(Arrays.asList("hot", "dot", "dog", "lot", "log"))));

        System.out.println(new Solution().ladderLength("cet","ism",
                new HashSet<>(Arrays.asList("kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per",
                        "val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex",
                        "jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark",
                        "has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore",
                        "pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet",
                        "too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin",
                        "feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel",
                        "hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire",
                        "irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don",
                        "tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn",
                        "rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig",
                        "era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can",
                        "pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats",
                        "set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art",
                        "ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap",
                        "den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let",
                        "see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet",
                        "ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass",
                        "dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil",
                        "mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut",
                        "ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi",
                        "yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen",
                        "odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub",
                        "bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop",
                        "tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin",
                        "get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins",
                        "con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del",
                        "imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed",
                        "ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab",
                        "top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil",
                        "rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho",
                        "tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai",
                        "sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg",
                        "zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"))));
    }

    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        wordList.add(endWord);

        for (int i = 1; !queue.isEmpty(); i++) {

            int size = queue.size();
            System.out.println("Queue size: " + size);
            while (size-- > 0) {
                String w = queue.poll();
                if (w.equals(endWord)) {
                    return i;
                }

                // Same result but rely on dict size
                /*for (Iterator<String> it = wordList.iterator(); it.hasNext(); ) {
                    String str = it.next();
                    if (isOnlyOneEdit(w, str)) {
                        queue.offer(str);
                        it.remove();
                    }
                }*/

                // Rely on the word size
                for (int j = 0; j < w.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == w.charAt(j)) {
                            continue;
                        }

                        char[] oldstr = w.toCharArray();
                        oldstr[j] = c;
                        String newstr = new String(oldstr);
                        if (wordList.contains(newstr)) {
                            queue.add(newstr);

                            /*
                             * This is the key! Why? Suppose hit->hot->dot,lot.
                             * Does this mean lot can't go to dog, which is removed by dot?
                             * Suppose X: X + 1 = dot and X + 1 = lot. Then X must be ?ot.
                             * But ?ot must be on the same level as dot,lot.
                             */
                            wordList.remove(newstr);
                        }
                    }
                }
            }
        }
        return 0;
    }

    private boolean isOnlyOneEdit(String w1, String w2) {
        int edit = 0;
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                if (++edit > 1) {
                    return false;
                }
            }
        }
        return (edit == 1); // edit may be 0 or 1 here
    }

}
