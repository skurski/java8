package know.how.java8.common;

/**
 * Created by skurskip on 10/31/2017.
 */
public enum Office {
    CRACOW,
    WROCLAW,
    GDANSK,
    KATOWICE,
    WARSAW;

    public static Office getOfficeById(int officeId) {
        switch (officeId) {
            case 0: return CRACOW;
            case 1: return WROCLAW;
            case 2: return GDANSK;
            case 3: return KATOWICE;
            case 4: return WARSAW;
            default: return CRACOW;
        }
    }
}
