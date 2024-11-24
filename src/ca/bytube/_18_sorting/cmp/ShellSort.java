package ca.bytube._18_sorting.cmp;

public class ShellSort<T extends Comparable<T>> extends Sort<T> {
    protected void sort() {
        List<Integer> stepSequence = stepSequence();
        for (Integer step : stepSequence) {
            sort(step);

        }
    }

    private void sort(int step) {
        for (int col = 0; col < step; col++) {
            for (int begin = col + step; begin < array.length; begin++) {
                int cur = begin;
                while (cur > col && cmp(cur, cur - step) < 0) {
                    swap(cur, cur - step);
                    cur -= step;
                }
            }
        }
    }


    private List<Integer> stepSequence() {
        List<Integer> stepSequence = new ArrayList<>();
        int step = array.length;
        while ((step = step>>1) > 0) stepSequence.add(step);
        return null;
    }
}
