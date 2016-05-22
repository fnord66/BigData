import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// Source of string similarity algorithm:
//   http://www.catalystsoft.com/articles/strikeamatch.html
public class StringSimilarity
{
  /**
   * Calculates the similarity between two words.
   * <p>
   * The similarity rating produced by this method is a value between 0.0
   * and 1.0, where identical words are given a rating of 1.0 and completely
   * dissimilar words are given a rating of 0.0.
   *
   * @param  a A word to compare.
   * @param  b Another word to compare with.
   * @return A similarity rating between 0.0 and 1.0.
   */
  public static float calculate(String a, String b)
  {
    a = a.toLowerCase();
    b = b.toLowerCase();
    List<String> a_pairs = new ArrayList<String>(Arrays.asList(letterPairs(a)));
    List<String> b_pairs = new ArrayList<String>(Arrays.asList(letterPairs(b)));
    int n_pairs_total = a_pairs.size() + b_pairs.size();
    int n_pairs_intersection = 0;
    for(String bigram : a_pairs)
    {
      if(b_pairs.remove(bigram))
      {
        ++n_pairs_intersection;
      }
    }
    return (2f * n_pairs_intersection) / n_pairs_total;
  }

  // This helper function breaks a string up into an array of adjacent letter
  // pairs.
  private static String[] letterPairs(String str)
  {
    int n_pairs = str.length() - 1;
    String[] pairs = new String[n_pairs];
    for(int i = 0; i < n_pairs; ++i)
      pairs[i] = str.substring(i, i + 2);
    return pairs;
  }
}