package artur.md.game.of.life;

public class Cell {
    private boolean enabled;

    Cell() {
        enabled = false;
    }

    Cell(Cell c) {
        enabled = c.enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    public int value() {
        return enabled ? 1 : 0;
    }

    public void update(int sum) {
        if ( (enabled && ((sum < 2) || (sum > 3)) ) ||
                (!enabled && (sum == 3)) ) {
            enabled = !enabled;
        }
    }

    public void set(boolean val) {
        enabled = val;
    }

    public String toString() {
        return enabled ? "*" : ".";
    }

    public static void main(String[] args) {
        Cell c = new Cell();
        c.disable();
        c.update(4);
        System.out.print(c.toString());
    }
}
