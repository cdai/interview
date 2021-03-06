package fundamentals.string.manipulate.lc071_simplifypath;

import java.util.Stack;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
 * For example, path = "/home/", => "/home". path = "/a/./b/../../c/", => "/c".
 * Corner Cases:
 *  1.Did you consider the case where path = "/../"? In this case, you should return "/".
 *  2.Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 *      In this case, you should ignore redundant slashes and return "/home/foo".
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().simplifyPath("//abc"));
    }

    // My 3AC. O(N) time without cost of insert(0,X).
    public String simplifyPath(String path) {
        Stack<String> s = new Stack<>();
        for (String p : path.split("/")) {
            if (p.isEmpty() || ".".equals(p)) continue;
            if ("..".equals(p)) {
                if (!s.isEmpty()) s.pop();
            } else s.push(p);
        }
        return "/" + String.join("/", s);
    }

    // My 2AC: split path and process backwards
    public String simplifyPath2(String path) {
        StringBuilder result = new StringBuilder();
        String[] dirs = path.split("/");
        for (int i = dirs.length - 1, skip = 0; i >= 0; i--) {
            if (dirs[i].equals("..")) skip++;
            else if (!dirs[i].isEmpty() && !dirs[i].equals(".")) {
                if (skip > 0) skip--;
                else result.insert(0, "/" + dirs[i]);
            } /*else dirs[i] == "" or "." */
        }
        return result.length() == 0 ? "/" : result.toString(); // error: result could be empty
    }

    // My 1AC: faster due to no split, but a little complex
    public String simplifyPath1(String path) {
        if (!path.startsWith("/")) {
            path = "/" + path;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0, j = path.indexOf('/', 1); i != -1; i = j, j = path.indexOf('/', j + 1)) { // is this comma dangerous?
            String comp = path.substring(i + 1, (j == -1) ? path.length() : j);
            if (comp.isEmpty()) {           /* case-1: "///abc" or "/abc/" => "/abc" */
                continue;
            } else if (".".equals(comp)) {  /* case-2: "/abc/." => "/abc" */
                continue;
            } else if ("..".equals(comp)) { /* case-3: "/abc/../" => "/" */
                if (result.length() > 0) {
                    result.delete(result.lastIndexOf("/"), result.length());
                }
            } else {                        /* case-4: "/abc/def" => "/abc/def" */
                result.append("/").append(comp);
            }
        }
        return (result.length() == 0) ? "/" : result.toString();
    }

}
