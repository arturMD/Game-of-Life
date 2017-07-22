package artur.md.game.of.life;

public class Pattern {  //TODO maybe change name, this can be confused with regex class
    private static final String[] _BLINKER = {"111"};   //TODO move patterns to private class and put here contents of PatternName
    private static final String[] _BEACON = {"1100", "1100","0011","0011"};
    private static final String[] _GLIDER = {"010", "001", "111"};

    private static int sizeX = -1;
    private static int sizeY = -1;

    /***
     * Change size of the grid, where patterns will be copied
     */
    public static void setSize(int x, int y) { //TODO remove this method and add size recognition
        sizeX = x;
        sizeY = y;
    }

    public static void get(PatternName name, CellGrid grid, int startX, int startY) {
        String[] pattern = null;
        switch (name) {
            case BLINKER: pattern = _BLINKER;
                break;
            case BEACON: pattern = _BEACON;
                break;
            case GLIDER: pattern = _GLIDER;
                break;
        }
        if( (sizeX >= pattern.length) && (sizeY >= pattern[0].length()) ) {

            for(int i = 0; i < pattern.length; i++) {
                for (int j = 0; j < pattern[0].length(); j++) {
                    if(pattern[i].charAt(j) == '0') {
                        grid.setCell((startX +i) % sizeX, (startY + j) % sizeY,false);
                    } else {
                        grid.setCell((startX +i) % sizeX, (startY + j) % sizeY,true);
                    }
                }
            }
        }
    }

}

