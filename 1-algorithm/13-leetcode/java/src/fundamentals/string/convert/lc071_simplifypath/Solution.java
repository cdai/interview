package fundamentals.string.convert.lc071_simplifypath;

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

    public String simplifyPath(String path) {
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
